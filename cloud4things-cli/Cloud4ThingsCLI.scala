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
import scala.io.StdIn
import scala.io.Source
import scala.collection.mutable.ListBuffer

object Cloud4ThingsCLI {

  // Filtering & Collection endpoints variables
  var ALEServiceURL : String = _
  var ALELRServiceURL : String = _

  // List that contains the options available for the user
  val listOptions = List("1 - Set ALELRService endpoint",
                         "2 - Set ALEService endpoint",
                         "3 - Define Reader",
                         "4 - Define Event-Cycle",
                         "5 - Help",
                         "6 - Show Readers",
                         "7 - Show EventCycles")

  // List of Readers
  val readers = new ListBuffer[Reader]()

  // List of EventCycles
  val eventCycles = new ListBuffer[EventCycle]()

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

  def setALELRServiceURL() {
    print("Enter the ALELRService URL: ")
    ALELRServiceURL = StdIn.readLine()
  }

  def setALEServiceURL() {
    print("Enter the ALEService URL: ")
    ALEServiceURL = StdIn.readLine()
  }

  def defineReader() {
    print("Enter the reader name: ")
    readers += new Reader(StdIn.readLine())
  }

  private def deleteReader(name: String) {

  }

  def defineEventCycle() {
    print("Enter the event cycle name: ")
    eventCycles += new EventCycle(StdIn.readLine())
  }

  private def deleteEventCycle(name: String) {

  }

  def showReaders() {
    println("Printing readers ....")
    readers.toList.foreach(println)
  }

  def showEventCycles() {
    println("Printing event cycles ....")
    eventCycles.toList.foreach(println)
  }

  def subscribeEventCycle(name: String, url: String) {}

  def main(args: Array[String]): Unit = {
    printOptions()
    for (line <- Source.stdin.getLines) {
      selectOption(line)
      printOptions()
    }
  }
}
