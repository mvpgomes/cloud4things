package cloud4things.utils

/* Reader: class that represents a RFID reader.
   Params:
    - @readerName : String = the reader name.
    - @readTimeInterval : Int = the time interval between two consecutive
    reads in ms.
*/
class Reader(name: String) {
  val readerName : String = name
  val readerDescription : String = "Description"
  val readerType : String = "org.fosstrak.ale.server.readers.hal.HALAdaptor"
  val propertiesFilePath : String = "/path/to/properties/file"
  val readTimeInterval : Int = 500
  val isComposite : Boolean = false
}