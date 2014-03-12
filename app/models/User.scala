package models

import play.api.Play
import play.api.db.DB
import play.api.Play.current
import play.api.libs.json.Json
import play.api.libs.json._

//mongodb imports
import com.novus.salat._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import play.api.libs.json._
import play.api.libs.functional.syntax._

import se.radley.plugin.salat._
import se.radley.plugin.salat.Binders._
import mongoContext._


case class User (firstName: String, lastName: String, email: String, password: String)

object User extends UserDAO with UserJson

trait UserDAO extends ModelCompanion[User, ObjectId] {
  val database = Play.current.configuration.getString("application.userscollection").get.toString()
  def collection = mongoCollection(database)
  val dao = new SalatDAO[User, ObjectId](collection){}
  
  
  //indexes
  collection.ensureIndex(DBObject("email" -> 1), "email", unique = true)
  
  def findByEmailAndPassword(email: String, password: String): Option[User] = dao.findOne(MongoDBObject("email" -> email, "password" -> password))
  
}

trait UserJson {
  implicit val userJsonWrite = new Writes[User] {
    def writes(user: User): JsValue = {
      Json.obj(
    		  "email" -> user.email,
    		  "password" -> user.password,
    		  "firstName" -> user.firstName,
    		  "lastName" -> user.lastName
      )
    }
  }
  
  implicit val userJsonRead = (
		  (__ \ 'email).read[String] ~
		  (__ \ 'password).read[String] ~
		  (__ \ 'firstName).read[String] ~
		  (__ \ 'lastName).read[String]
  )(User.apply _)
  
}

