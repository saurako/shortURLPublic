package helpers

import akka.actor.Actor
import concurrent._
import scala.util.Try
import play.api.libs.concurrent.Execution.Implicits._
import models.LastWrittenID
import akka.actor.ActorRef

case class GetId()
case class InitializeList()

class IdSetGenerator extends Actor {
   private var idList: List[String] = List()
   
   def receive = {  
     case GetId() => {
       if(idList.isEmpty) {
         initialize
         idList = Base62Counter.generateIdSet(100000)         
       }
       
       var client = sender
       
       var id = idList(0)
       idList = idList.drop(1)
       
       var lastId = LastWrittenID.getLastWrittenID.get.lastId
       LastWrittenID.updateLastWrittenID(lastId, new LastWrittenID(id))
       
       client ! id     
     }
     
     case InitializeList() => {
       initialize
       idList = Base62Counter.generateIdSet(100000)
     }
       
   }
   
   def initialize = {
    if (Base62Counter.currentCount.toString == "") {

      val lastID = LastWrittenID.getLastWrittenID
      if(!lastID.isEmpty) {
        val lastIdString = lastID.get.lastId
        Base62Counter.setInitialValues(lastIdString)
      } 
      else {
    	  Base62Counter.setInitialValues("0")
    	  LastWrittenID.save(new LastWrittenID("0"))
      }
      
    }
  }

}