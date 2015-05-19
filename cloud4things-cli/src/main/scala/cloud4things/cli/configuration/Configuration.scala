package cloud4things.cli.configuration

import cloud4things.cli.eventcycle.EventCycle
import cloud4things.cli.reader.Reader

class Configuration(name: String) {

  // List of Readers
  private val readers = new scala.collection.mutable.Map[String, Reader]() {
    override def +=(kv: (String, Reader)): this.type = ???

    override def -=(key: String): this.type = ???

    override def get(key: String): Option[Reader] = ???

    override def iterator: Iterator[(String, Reader)] = ???
  }

  // List of EventCycles
  private val eventCycles = new scala.collection.mutable.Map[String, EventCycle]() {
    override def +=(kv: (String, EventCycle)): this.type = ???

    override def -=(key: String): this.type = ???

    override def get(key: String): Option[EventCycle] = ???

    override def iterator: Iterator[(String, EventCycle)] = ???
  }

  // Add a new reader to a configuration
  def addReader(reader: Reader) = {
    readers += (reader.readerName -> reader)
  }

  // Delete a Reader with name {name}
  def deleteReader(name: String) = {
    readers -= name
  }

  // List all Readers of a given smart-place
  def showReaders() = {
    println("Printing readers ....")
    readers.foreach( r => println (r._1))
  }

  // Add a new EventCycle to a configuration
  def addEventCycle(eventCycle: EventCycle) {
    eventCycles += (eventCycle.eventName -> eventCycle)
  }

  // Delete a EventCycle with name {name}
  def deleteEventCycle(name: String) = {
    eventCycles -= name
  }

  // List all EventCycles
  def showEventCycles() = {
    println("Printing event cycles ....")
    eventCycles.foreach( e => println (e._1))
  }


}
