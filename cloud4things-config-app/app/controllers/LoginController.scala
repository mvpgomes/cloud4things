package controllers

import play.api.libs.json.Json._
import play.api.libs.json.{JsSuccess, Json, Reads}
import play.api.mvc.{Action, Controller}

object LoginController extends Controller {

  def login = Action(parse.json) { request =>
    // Creates a reader for the JSON - turns into a LoginRequest
    implicit val loginRequest: Reads[LoginRequest] = Json.reads[LoginRequest]

    /*
    * Call validate and if ok we return valid=true and put the username in session
     */
    request.body.validate[LoginRequest] match {
      case s: JsSuccess[LoginRequest] if (s.get.authenticate) => {
        Ok(toJson(Map("valid" -> true))).withSession("user" -> s.get.username)
      }
      // Not valid
      case _ => Ok(toJson(Map("valid" -> false)))
    }
  }
}

case class LoginRequest(username: String, password: String) {
  // Simple username-password map instead of a database
  val validUsers = Map("root@root.com" -> "root")

  def authenticate = validUsers.exists(_ == (username, password))
}
