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

import cloud4things.cli.fosstrak.client.{FosstrakALEClient, FosstrakALELRClient}

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