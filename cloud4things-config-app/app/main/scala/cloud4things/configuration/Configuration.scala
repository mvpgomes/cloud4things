package main.scala.cloud4things.configuration

import main.scala.cloud4things.events.EventCycle
import main.scala.cloud4things.readers.Reader

class Configuration(var id: String, var endpoint: String) {

  // List of Readers
  private val readers = scala.collection.mutable.Map[String, Reader]()

  // List of EventCycles
  private val eventCycles = scala.collection.mutable.Map[String, EventCycle]()

  // Add a new reader to a configuration
  def addReader(reader: Reader) = {
    readers += (reader.readerName -> reader)
  }

  // Delete a Reader with name {name}
  def deleteReader(name: String) = {
    readers -= name
  }

  // List all Readers of a given smart-place
  def showReaders = {
    println("Printing readers ....")
    readers.foreach( r => println (r._1))
  }

  // Get all readers
  def getAllReaders = {
    readers.values
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

  // Get all EventCyles
  def getAllEvents = {
    eventCycles.values
  }

}
