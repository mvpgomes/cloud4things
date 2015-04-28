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

  // Filtering & Collection endpoints
  var ALEServiceURL : String;
  var ALELRServiceURL : String;

  val listOptions = List("1 - Set ALELRService endpoint", "2 - Set ALEService endpoint",
                        "3 - Define Reader", "4 - Define Event-Cycle", "5 - Help")

  def printOptions() {
    listOptions.foreach(println)
    print("Cloud4ThingsCLI:> ")
  }

  def setALELRServiceURL(url: String) { this.ALELRServiceURL = url }

  def setALEServiceURL(url: String) { this.ALEServiceURL = url  }

  def defineReader(name: String) : Boolean = {
    true
  }

  def defineEventCycle(name: String) : Boolean = {
    true
  }

  def subscribeEventCycle(name: String, url: String) : Boolean = {
    true
  }

  def main(args: Array[String]): Unit = {
    printOptions()
    for (line <- stdin.getLines)
      printOptions()
  }
}
