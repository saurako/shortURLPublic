package models

import play.api.Play
import play.api.db.DB
import play.api.Play.current
import play.api.libs.json.Json
import play.api.libs.json._
import com.novus.salat._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import se.radley.plugin.salat._
import se.radley.plugin.salat.Binders._
import mongoContext._
import com.mongodb.BasicDBObject

case class LastWrittenID(lastId: String)

object LastWrittenID extends LastWrittenIdDAO with LastWrittenIdJson

trait LastWrittenIdDAO extends ModelCompanion[LastWrittenID, ObjectId] {
  val collection = mongoCollection("LastWrittenID")
  val dao = new SalatDAO[LastWrittenID, ObjectId](collection) {}

  def getLastWrittenID(): Option[LastWrittenID] = dao.findOne(MongoDBObject())
  def updateLastWrittenID(oldId: String, newID: LastWrittenID) = {
    val query = new BasicDBObject("lastId", oldId)
    dao.update(query, newID, true, false, WriteConcern.Safe)

  }
  override def save(lastId: LastWrittenID) = dao.save(lastId, WriteConcern.Safe)
}

trait LastWrittenIdJson {
  implicit val lastWrittenJsonWrite = new Writes[LastWrittenID] {
    def writes(lastWrittenId: LastWrittenID): JsValue = {
      Json.obj(
        "lastId" -> lastWrittenId.lastId)
    }
  }
}