package main.scala.cloud4things.events


/* EventCycle: class that represents a EventCycle that occurs in the smart place.
   Params:
    - @eventName : String = the EventCycle name.
    - @repeatPeriod : Int = the period of a EventCycle in ms.
    - @duration : Int = the duration of an EventCycle in ms.
    - @stableSetInterval : Int = the stableSetInterval of the EventCycle.
*/
class EventCycle(name: String, reader: String) {
  val eventName : String = name
  val associatedReader : String = reader
  val repeatPeriod : Int = 2000
  val duration : Int = 2000
  val stableSetInterval : Int = 0
}
