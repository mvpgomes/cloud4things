package main.scala.cloud4things.readers

/* Reader: class that represents a RFID reader.
   Params:
    - @readerName : String = the reader name.
    - @readerDescription : String = the reader description
*/
class Reader(name: String, description: String) {
  val readerName : String = name
  val readerDescription : String = description
}