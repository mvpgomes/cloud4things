package controllers

import main.scala.cloud4things.SmartPlaceManager
import main.scala.cloud4things.util.ConfigurationSerializer
import play.api.mvc._

object HomeController extends Controller {

  def home = Action {
    Ok(ConfigurationSerializer.serializeAllConfigurations(SmartPlaceManager.getDefinedConfigurations))
  }

}
