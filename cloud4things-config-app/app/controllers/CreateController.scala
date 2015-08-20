package controllers

import main.scala.cloud4things.SmartPlaceManager
import play.api.libs.json.Json.toJson
import play.api.libs.json.{JsSuccess, JsValue, Json, Reads}
import play.api.mvc._

object CreateController extends Controller {

  def create = Action(parse.json) { request =>

    implicit val createSmartPlaceRequest: Reads[CreateSmartPlaceRequest] = Json.reads[CreateSmartPlaceRequest]

    request.body.validate[CreateSmartPlaceRequest] match {
      case s: JsSuccess[CreateSmartPlaceRequest] if(!s.get.configurationAlreadyDefined) => {
        s.get.createConfiguration
        Ok(toJson(Map("result" -> "success")))
      }
      case _ => Ok(toJson(Map("result" -> "failed")))

    }
  }
}

case class CreateSmartPlaceRequest(name: String, endpoint: String,
                                   readers: List[JsValue], events: List[JsValue]) {

  def configurationAlreadyDefined = {
    SmartPlaceManager.configurationExists(name)
  }

  def addReaders(readers: List[JsValue]) = {
    readers.map { value =>
      val reader_name = (value \ "reader_name").as[String]
      val reader_description = (value \ "reader_description").as[String]
      SmartPlaceManager.addReaderToConfiguration(name, reader_name, reader_description)
    }
  }

  def addEvents(events: List[JsValue]) = {
    events.map { value =>
      val event_name = (value \ "event_name").as[String]
      val associated_reader = (value \ "associated_reader").as[String]
      SmartPlaceManager.addEventToConfiguration(name, event_name, associated_reader)
    }
  }

  def createConfiguration = {
    SmartPlaceManager.createConfiguration(name, endpoint)
    addReaders(readers)
    addEvents(events)
  }

}