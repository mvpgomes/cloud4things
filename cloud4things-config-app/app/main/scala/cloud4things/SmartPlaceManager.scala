package main.scala.cloud4things

import main.scala.cloud4things.configuration.{Configuration, ConfigurationManager}
import main.scala.cloud4things.fosstrak.client.{FosstrakALELRClient, FosstrakALEClient}

object SmartPlaceManager {

  val aleClient = new FosstrakALEClient

  val aleLrClient = new FosstrakALELRClient

  def configurationExists(configName: String) = {
    ConfigurationManager.configurationIsDefined(configName)
  }

  def createConfiguration(name: String, endpoint: String) = {
    ConfigurationManager.createSmartPlaceConfiguration(name, endpoint)
  }

  def addReaderToConfiguration(configName: String, readerName: String, readerDescription: String) = {
    ConfigurationManager.addReader(configName, readerName, readerDescription)
  }

  def addEventToConfiguration(configName: String, eventName: String, associatedReader: String) = {
    ConfigurationManager.addEvent(configName, eventName, associatedReader)
  }

  def getConfiguration(configName: String): Option[Configuration] = {
    ConfigurationManager.getConfiguration(configName)
  }

  def getDefinedConfigurations: Iterable[String] = {
    ConfigurationManager.getAllConfigurations
  }

  def deployConfiguration() = {
  }

}
