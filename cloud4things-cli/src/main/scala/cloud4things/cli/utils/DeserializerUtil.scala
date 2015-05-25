package cloud4things.cli.utils

import java.io.{FileInputStream, InputStream}
import javax.xml.bind.helpers.DefaultValidationEventHandler
import javax.xml.bind.{JAXBContext, Unmarshaller, ValidationEventHandler}
import javax.xml.transform.stream.StreamSource

import org.eclipse.persistence.jaxb.UnmarshallerProperties
import org.fosstrak.ale.wsdl.alelr.epcglobal.{AddReaders, SetReaders, RemoveReaders, SetProperties}
import org.fosstrak.ale.xsd.ale.epcglobal._

object DeserializerUtil {

  def deserializeECSpec(filePath: String) : ECSpec = {
    unmarshall("org.fosstrak.ale.xsd.ale.epcglobal", filePath, null, classOf[ECSpec]).asInstanceOf[ECSpec]
  }

  def deserializeECSpec(is: InputStream) : ECSpec = {
    unmarshall("org.fosstrak.ale.xsd.ale.epcglobal", is, null, classOf[ECSpec]).asInstanceOf[ECSpec]
  }

  def deserializeLRSpec(filePath: String) : LRSpec = {
    unmarshall("org.fosstrak.ale.wsdl.alelr.epcglobal", filePath, null, classOf[LRSpec]).asInstanceOf[LRSpec]
  }

  def deserializeLRSpec(is: InputStream) : LRSpec = {
    unmarshall("org.fosstrak.ale.wsdl.alelr.epcglobal", is, null, classOf[LRSpec]).asInstanceOf[LRSpec]
  }

  def deserializeLRProperty(is: InputStream) : LRProperty = {
    unmarshall("org.fosstrak.ale.wsdl.alelr.epcglobal", is, null, classOf[LRProperty]).asInstanceOf[LRProperty]
  }

  def deserializeSetProperties(filePath: String) : SetProperties = {
    unmarshall("org.fosstrak.ale.wsdl.alelr.epcglobal", filePath, null, classOf[SetProperties]).asInstanceOf[SetProperties]
  }

  def deserializeRemoveReaders(filePath: String) : RemoveReaders = {
    unmarshall("org.fosstrak.ale.wsdl.alelr.epcglobal", filePath, null, classOf[RemoveReaders]).asInstanceOf[RemoveReaders]
  }

  def deserializeSetReaders(filePath: String) : SetReaders = {
    unmarshall("org.fosstrak.ale.wsdl.alelr.epcglobal", filePath, null, classOf[SetReaders]).asInstanceOf[SetReaders]
  }

  def deserializeAddReaders(filePath: String) : AddReaders = {
    unmarshall("org.fosstrak.ale.wsdl.alelr.epcglobal", filePath, null, classOf[AddReaders]).asInstanceOf[AddReaders]
  }

  def deserializeECReports(is: InputStream) : ECReports = {
    unmarshall("org.fosstrak.ale.xsd.ale.epcglobal", is, null, classOf[ECReports]).asInstanceOf[ECReports]
  }

  private def getUnmarshaller(contextPath: String, handler: ValidationEventHandler): Unmarshaller = {

    val context: JAXBContext = JAXBContext.newInstance(contextPath)

    val unmarshaller: Unmarshaller = context.createUnmarshaller()

    unmarshaller.setEventHandler(handler)

    unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json")

    unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true)

    unmarshaller
  }

  private def unmarshall(context: String, filePath: String, validationEventHandler: ValidationEventHandler, clazz: Any) : Any = {

    val handler: ValidationEventHandler =
      if(validationEventHandler == null) new DefaultValidationEventHandler else validationEventHandler

    val unmarshaller: Unmarshaller = getUnmarshaller(context, handler)

    val in: InputStream = new FileInputStream(filePath)

    val json:StreamSource = new StreamSource(in)

    unmarshaller.unmarshal(json, clazz.getClass).getValue()
  }

  private def unmarshall(context: String, stream: InputStream, validationEventHandler: ValidationEventHandler, clazz: Any) : Any = {

    val handler: ValidationEventHandler =
      if(validationEventHandler == null) new DefaultValidationEventHandler else validationEventHandler

    val unmarshaller: Unmarshaller = getUnmarshaller(context, handler)

    val json:StreamSource = new StreamSource(stream)

    unmarshaller.unmarshal(json, clazz.getClass).getValue()

  }
}
