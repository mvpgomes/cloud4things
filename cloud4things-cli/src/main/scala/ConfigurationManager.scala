import cloud4things.cli.configuration.Configuration
import cloud4things.cli.eventcycle.EventCycle
import cloud4things.cli.reader.Reader

object ConfigurationManager {

  // Smart-places configuration
  private val smartConfiguration = new scala.collection.mutable.Map[String, Configuration] {
    override def get(key: String): Option[Configuration] = ???

    override def iterator: Iterator[(String, Configuration)] = ???

    override def +=(kv: (String, Configuration)): this.type = ???

    override def -=(key: String): this.type = ???
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

}
