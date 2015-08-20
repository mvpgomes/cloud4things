package controllers

import main.scala.cloud4things.SmartPlaceManager
import main.scala.cloud4things.util.ConfigurationSerializer
import play.api.mvc._

object ViewController extends Controller {

  def view(id: String) = Action {
    println(id)
    Ok(ConfigurationSerializer.serializeConfiguration(SmartPlaceManager.getConfiguration(id)))
  }
}
