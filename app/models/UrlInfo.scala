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

case class UrlInfo(id: String, url: String, hash: String, clicks: Int, customcode: String)

object UrlInfo extends UrlInfoDAO with UrlInfoJson 

trait UrlInfoDAO extends ModelCompanion[UrlInfo, ObjectId]{
  
  val database = Play.current.configuration.getString("mongodb.default.db").get.toString()
  def collection = mongoCollection(database)
  val dao = new SalatDAO[UrlInfo, ObjectId](collection){}
  
  //indexes
  collection.ensureIndex(DBObject("url" -> 1), "id", unique = true)
  
  //queries
  def findByID(id: String): Option[UrlInfo] = dao.findOne(MongoDBObject("_id" -> id))
  def findByHash(hash: String): Option[UrlInfo] = dao.findOne(MongoDBObject("hash" -> hash))
  def findLastByInsert(): List[UrlInfo] = dao.find(MongoDBObject.empty).toList
  def findByCustomCode(customcode: String): Option[UrlInfo] = dao.findOne(MongoDBObject("customcode" -> customcode))
  
}

trait UrlInfoJson {
  implicit val urlInfoJsonWrite = new Writes[UrlInfo] {
    def writes(url: UrlInfo): JsValue = {
      Json.obj(
    		  "id" -> url.id,
    		  "url" -> url.url,
    		  "hash" -> url.hash,
    		  "clicks" -> url.clicks,
    		  "customcode" -> url.customcode
      )
    }
  }
  
  implicit val urlInfoJsonRead = (
		  (__ \ 'id).read[String] ~
		  (__ \ 'url).read[String] ~
		  (__ \ 'hash).read[String] ~
		  (__ \ 'clicks).read[Int] ~
		  (__ \ 'customcode).read[String]
  )(UrlInfo.apply _)
}