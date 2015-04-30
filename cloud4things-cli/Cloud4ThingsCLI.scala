/* --------------------------------------------------------
        Cloud4Things command-line Application Tool

    * The C4T command-line application tool allows to desc
    cribe and configure an scenario for a EPC application
    based on Fosstrak. The command-line allow to perform
    the following operations:

    - Configure the Filter & Collecting server endpoints.
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
                         "7 - Show EventCycles",
                         "8 - Generate Readers Config",
                         "9 - Generate EventCycles Config")

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
    case "8" => generateReadersConfig()
    case "9" => generateEventCyclesConfig()
    case _ => deleteReader("TestReader")//println("ERROR: Invalid option, choose another one.\n")
  }

  // Set the URL of the ALELRService at the Filtering & Collecting server
  def setALELRServiceURL() {
    print("Enter the ALELRService URL: ")
    ALELRServiceURL = StdIn.readLine()
  }

  // Set the URL of the ALEService at the Filtering & Collecting server
  def setALEServiceURL() {
    print("Enter the ALEService URL: ")
    ALEServiceURL = StdIn.readLine()
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
    for (reader <- readers)
      println(reader.readerName)
  }

  // Generate the Reader configuration file in XML format
  def generateReaderSpecXML(reader : Reader) {
    val readerConfigXML =
      <ns3:LRSpec xmlns:ns2="urn:epcglobal:ale:wsdl:1" xmlns:ns3="urn:epcglobal:ale:xsd:1">
        <isComposite>{reader.isComposite}</isComposite>
        <readers/>
          <properties>
            <property>
              <name>ReaderType</name>
              <value>{reader.readerType}</value>
            </property>
            <property>
              <name>Description</name>
              <value>{reader.readerDescription}</value>
            </property>
            <property>
              <name>PhysicalReaderName</name>
              <value>{reader.readerName}</value>
            </property>
            <property>
              <name>ReadTimeInterval</name>
              <value>{reader.readTimeInterval}</value>
            </property>
            <property>
              <name>PropertiesFile</name>
              <value>{reader.propertiesFilePath}</value>
            </property>
          </properties>
      </ns3:LRSpec>
  }

  // Generate the configuration files for all readers
  def generateReadersConfig() = {
    readers.foreach(generateReaderSpecXML)
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
    for (eventCycle <- eventCycles)
      println(eventCycle.eventName)
  }

  // Generate the EventCycle configuration file in XML format
  def generateEventCycleSpecXML(eventCycle : EventCycle) {
    val eventCycleConfigXML =
      <ns2:ECSpec xmlns:ns2="urn:epcglobal:ale:xsd:1">
        <logicalReaders>
          <logicalReader>{eventCycle.associatedReader}</logicalReader>
        </logicalReaders>
        <boundarySpec>
          <repeatPeriod unit="MS">{eventCycle.repeatPeriod}</repeatPeriod>
          <duration unit="MS">{eventCycle.duration}</duration>
          <stableSetInterval unit="MS">{eventCycle.stableSetInterval}</stableSetInterval>
        </boundarySpec>
        <reportSpecs>
          <reportSpec>
            <reportSet set="CURRENT"/>
              <output includeRawHex="true" includeRawDecimal="true" includeEPC="true"/>
          </reportSpec>
        </reportSpecs>
      </ns2:ECSpec>
  }

  // Generate the configuration files for all EventCycles
  def generateEventCyclesConfig() = {
    eventCycles.foreach(generateEventCycleSpecXML)
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
