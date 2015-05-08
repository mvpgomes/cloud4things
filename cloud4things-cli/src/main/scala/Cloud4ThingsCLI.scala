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

import cloud4things.cli.eventcycle.EventCycle
import cloud4things.cli.fosstrak.client.{FosstrakALELRClient, FosstrakALEClient}
import cloud4things.cli.reader.Reader

import scala.collection.mutable.ListBuffer
import scala.io.{Source, StdIn}

object Cloud4ThingsCLI {

  // List that contains the options available for the user
  val listOptions = List("1 - Set ALELRService endpoint",
    "2 - Set ALEService endpoint",
    "3 - Define Reader",
    "4 - Define Event-Cycle",
    "5 - Help",
    "6 - Show Readers",
    "7 - Show EventCycles",
    "8 - Generate Readers Config",
    "9 - Generate EventCycles Config")

  // List of Readers
  val readers = new ListBuffer[Reader]()

  // List of EventCycles
  val eventCycles = new ListBuffer[EventCycle]()

  // Fosstrak ALEClient instance
  val ALEClient = new FosstrakALEClient()

  // Fosstrak ALELRClient instance
  val ALELRClient = new FosstrakALELRClient()

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
    case "3" => defineReader()
    case "4" => defineEventCycle()
    case "5" => println("Need help!\n")
    case "6" => showReaders()
    case "7" => showEventCycles()
    case _ => println("ERROR: Invalid option, choose another one.\n")
  }

  //Set the URL of the ALELRService at the Filtering & Collecting server
  def setALELRServiceURL() {
    print("Enter the ALELRService URL: ")
    ALELRClient.setAleLrServiceEndpointAddress(StdIn.readLine())
  }

  // Set the URL of the ALEService at the Filtering & Collecting server
  def setALEServiceURL() {
    print("Enter the ALEService URL: ")
    ALEClient.setAleServiceEndpointAddress(StdIn.readLine())
  }

  // Create a new Reader
  def defineReader() {
    print("Enter the reader name: ")
    readers += new Reader(StdIn.readLine())
  }

  // Delete a Reader with name {name}
  private def deleteReader(name: String) {
    readers.filter(_.readerName == name).foreach(readers -= _)
  }

  // List all Readers
  def showReaders() {
    println("Printing readers ....")
    readers.foreach(_.readerName)
  }

  // Create a new EventCycle
  def defineEventCycle() {
    print("Enter the event cycle name: ")
    eventCycles += new EventCycle(StdIn.readLine())
  }

  // Delete a EventCycle with name {name}
  private def deleteEventCycle(name: String) {
    eventCycles.filter(_.eventName == name).foreach(eventCycles -= _)
  }

  // List all EventCycles
  def showEventCycles() {
    println("Printing event cycles ....")
    eventCycles.foreach(_.eventName)
  }

  // Subscribe the EventCycles to the URL of the capturing application
  def subscribeEventCycle(name: String, url: String) {}

  /*
    - main : the main functions runs an interactive command-line
      prompt allowing the user to define the scenario of the EPC application.
  */
  def main(args: Array[String]): Unit = {
    printOptions()
    for (line <- Source.stdin.getLines) {
      selectOption(line)
      printOptions()
    }
  }
}