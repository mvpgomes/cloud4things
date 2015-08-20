package main.scala.cloud4things.configuration

import main.scala.cloud4things.events.EventCycle
import main.scala.cloud4things.readers.Reader

object ConfigurationManager {

  // Smart-places configuration
  private val smartPlacesConfiguration = scala.collection.mutable.Map[String, Configuration]()

  // Create a new smart-place configuration
  def createSmartPlaceConfiguration(name: String, endpoint: String) = {
    smartPlacesConfiguration += (name -> new Configuration(name, endpoint))
  }

  // Get a configuration
  def getConfiguration(configName: String) : Option[Configuration] = {
    smartPlacesConfiguration.get(configName)
  }

  // Verify if a configuration is defined
  def configurationIsDefined(configName: String) = {
    smartPlacesConfiguration.keySet.exists(_ == configName)
  }

  // Return all defined Configuration names
  def getAllConfigurations : Iterable[String] = {
    smartPlacesConfiguration.keys
  }

  // Set the configuration endpoint
  def setEndpoint(configName: String, endpoint: String) = {
    smartPlacesConfiguration(configName).endpoint = endpoint
  }

  // Add a reader to a configuration
  def addReader(configName: String, readerName: String, readerDescription: String) = {
    smartPlacesConfiguration(configName).addReader(new Reader(readerName, readerDescription))
  }

  //  Delete a reader from a configuration
  def deleteReader(configName: String, readerName: String) = {
    smartPlacesConfiguration(configName).deleteReader(readerName)
  }

  // Add event cycle to a configuration
  def addEvent(configName: String, eventName: String, associatedReader: String) = {
    smartPlacesConfiguration(configName).addEventCycle(new EventCycle(eventName, associatedReader))
  }

  //  Delete a eventCycle from a configuration
  def deleteEvent(configName: String, eventName: String) = {
    smartPlacesConfiguration(configName).deleteEventCycle(eventName)
  }

}
