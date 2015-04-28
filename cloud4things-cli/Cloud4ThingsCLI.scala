/* --------------------------------------------------------
        Cloud4Things command-line Application Tool

    * The C4T command-line application tool allows to desc
    cribe and configure an scenario for a EPC application
    based on Fosstrak. The command-line allow to perform
    the following operations:

    - Configure the Filter & Collection server endpoints.
    - Configure the reader that are in the smart-place.
    - Configure the event-cycles that are associated with
      each reader.
   ------------------------------------------------------- */
import scala.io.Source

object Cloud4ThingsCLI {

  // Filtering & Collection endpoints variables
  var ALEServiceURL : String = _
  var ALELRServiceURL : String = _

  val listOptions = List("1 - Set ALELRService endpoint", "2 - Set ALEService endpoint",
                        "3 - Define Reader", "4 - Define Event-Cycle", "5 - Help")

  def printOptions() {
    listOptions.foreach(println)
    print("Cloud4ThingsCLI:> ")
  }

  def selectOption(option: String) = option match {
    case "1" => setALELRServiceURL()
    case "2" => setALEServiceURL()
    case "3" => defineReader()
    case "4" => defineEventCycle()
    case "5" => println("Need help!\n")
    case _ => println("ERROR: Invalid option, choose another one.\n")
  }

  def setALELRServiceURL() { ALELRServiceURL = url }

  def setALEServiceURL() { ALEServiceURL = url }

  def defineReader() : Boolean = {
    true
  }

  def defineEventCycle() : Boolean = {
    true
  }

  def subscribeEventCycle(name: String, url: String) : Boolean = {
    true
  }

  def main(args: Array[String]): Unit = {
    printOptions()
    for (line <- Source.stdin.getLines) {
      selectOption(line)
      printOptions()
    }
  }
}
