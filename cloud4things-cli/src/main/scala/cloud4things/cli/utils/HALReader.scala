package cloud4things.cli.utils

/* HALReader: class that represents a HAL RFID reader.
   Params:
    - @readerName : String = the reader name.
    - @readerDescription : String = the reader description
    - @readerType : String = the reader type
    - @propertiesFilePath : String = the reader properties file path
    - @readTimeInterval : Int = the reader interval time in ms
    - @isComposite : Boolean  = identify if the reader is composite
*/
class HALReader(name : String) extends Reader(name){
  val readerType : String = "org.fosstrak.ale.server.readers.hal.HALAdaptor"
  val propertiesFilePath : String = "/path/to/properties/file"
  val readTimeInterval : Int = 500
  val isComposite : Boolean = false
}
