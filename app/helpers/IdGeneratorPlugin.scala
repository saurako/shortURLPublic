package helpers

import play.api.{Application, Logger, Plugin}
import play.api.libs.concurrent.Akka
import play.api.Play.current
import akka.actor.Props
import akka.actor.ActorSystem

class IdGeneratorPlugin (val app: Application) extends Plugin {
	override def onStart() {
	  Logger.info("Initializing IdGeneratorPlugin")
	  val system = ActorSystem("mySystem")
	  Shortener.idGenerator = system.actorOf(Props[IdSetGenerator], "idGeneratorActor")
	  Shortener.idGenerator ! InitializeList()
	}
	
	override def onStop() {
	  Logger.info("Stopping applcation")
	}
	
	override def enabled = true

}