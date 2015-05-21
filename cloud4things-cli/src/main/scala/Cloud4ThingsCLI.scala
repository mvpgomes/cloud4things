/* --------------------------------------------------------
     Cloud4Things command-line Application Tool
    The C4T command-line application tool allows to describe
    and configure an scenario for a EPC application
    based on Fosstrak. The command-line allow to perform
    the following operations:
    - Configure the Filter & Collecting server endpoints.
    - Configure the reader that are in the smart-place.
    - Configure the event-cycles that are associated with
      each reader.
   ------------------------------------------------------- */

import cloud4things.cli.configuration.Configuration
import cloud4things.cli.eventcycle.EventCycle
import cloud4things.cli.fosstrak.client.{FosstrakALEClient, FosstrakALELRClient}
import cloud4things.cli.reader.Reader

import scala.io.{Source, StdIn}

object Cloud4ThingsCLI {

  // List that contains the options available for the user
  private val listOptions = List(
    "1 - Set ALELRService endpoint",
    "2 - Set ALEService endpoint")

  // Fosstrak ALEClient instance
  private val ALEClient = new FosstrakALEClient()

  // Fosstrak ALELRClient instance
  private val ALELRClient = new FosstrakALELRClient()

  // Smart-places configuration
  private val smartConfiguration = new scala.collection.mutable.Map[String, Configuration] {
    override def get(key: String): Option[Configuration] = ???

    override def iterator: Iterator[(String, Configuration)] = ???

    override def +=(kv: (String, Configuration)): this.type = ???

    override def -=(key: String): this.type = ???
  }

  // Print the options for the user choose
  def printOptions() {
    listOptions.foreach(println)
    print("Cloud4ThingsCLI:> ")
  }

  /* A dictionary-based function that invokes a function based
         on the input entered by the user.*/
  def selectOption(option: String) = option match {
    case "1" => setALELRServiceURL()
    case "2" => setALEServiceURL()
    case _ => println("ERROR: Invalid option, choose another one.\n")
  }

  // Set the URL of the ALEService at the Filtering & Collecting server
  def setALEServiceURL() = {
    print("Enter the ALEService URL: ")
    ALEClient.setAleServiceEndpointAddress(StdIn.readLine)
  }

  //Set the URL of the ALELRService at the Filtering & Collecting server
  def setALELRServiceURL() = {
    print("Enter the ALELRService URL: ")
    ALELRClient.setAleLrServiceEndpointAddress(StdIn.readLine)
  }

  // Create a new smart-place configuration
  def createSmartPlaceConfiguration(name: String) = {
    smartConfiguration += (name -> new Configuration(name))
  }

  // Add a reader to a configuration
  def addReaderToConfiguration(configName: String, reader: Reader) = {
    smartConfiguration(configName).addReader(reader)
  }

  //  Delete a reader from a configuration
  def deleteReaderFromConfiguration(configName: String, readerName: String) = {
    smartConfiguration(configName).deleteReader(readerName)
  }

  // Add event cycle to a configuration
  def addEventCycleToConfiguration(configName: String, eventCycle: EventCycle) = {
    smartConfiguration(configName).addEventCycle(eventCycle)
  }

  //  Delete a eventCycle from a configuration
  def deleteEventFromConfiguration(configName: String, eventName: String) = {
    smartConfiguration(configName).deleteEventCycle(eventName)
  }

  // Subscribe the EventCycles to the URL of the capturing application
  def subscribeEventCycle(name: String, url: String) = {}

  /*
    - main : the main functions runs an interactive command-line
      prompt allowing the user to define the scenario of the EPC application.
  */
  def main(args: Array[String]) = {
    printOptions()
    for (line <- Source.stdin.getLines) {
      selectOption(line)
      printOptions()
    }
  }
}