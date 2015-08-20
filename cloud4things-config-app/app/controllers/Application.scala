package controllers

import java.io.File

import play.Play
import play.api.mvc._

object Application extends Controller {

  def index(any: String) = Action {
    Ok(views.html.index()).withNewSession
  }

  /** resolve "any" into the corresponding HTML page URI */
  def getURI(any: String): String = any match {
    case "login" => "/public/html/login.html"
    case "home" => "/public/html/home.html"
    case "create" => "/public/html/create.html"
    case "view" => "/public/html/view.html"
    case _ => "error"
  }
  /** load an HTML page from public/html */
  def loadPublicHTML(any: String) = Action {
    val projectRoot = Play.application().path()
    val file = new File(projectRoot + getURI(any))
    if (file.exists())
      Ok(scala.io.Source.fromFile(file.getCanonicalPath()).mkString).as("text/html");
    else
      NotFound
  }

}


