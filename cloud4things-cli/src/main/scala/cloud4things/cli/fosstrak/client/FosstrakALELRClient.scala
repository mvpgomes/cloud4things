package cloud4things.cli.fosstrak.client

import org.fosstrak.ale.wsdl.alelr.epcglobal._
import org.fosstrak.ale.xsd.ale.epcglobal.LRSpec

/*
 * ALELRClient : A ALELR client implementation that
 * contains the methods stub's for the ALELR service
 * of the Fosstrak Filtering & Collecting Server.
 */
class FosstrakALELRClient() extends FosstrakClient(classOf[ALELRServicePortType],
                                                   classOf[ALELRServicePortType].getMethod("getStandardVersion", classOf[EmptyParms]),
                                                   new EmptyParms) {


  def addReaders() = {
    val readers: AddReaders = null
    getAleLrServiceProxy.addReaders(readers)
  }

  def defineReaders() = {
    val readers: Define = null
    getAleLrServiceProxy.define(readers)
  }

  def getReaderSpec(readerName: String) = {
    val reader: GetLRSpec = null
    getAleLrServiceProxy.getLRSpec(reader)
  }


  def removeReaders() = {
    val readers: RemoveReaders = null
    getAleLrServiceProxy.removeReaders(readers)
  }

  def setReaders() = {
    val readers: SetReaders = null
    getAleLrServiceProxy.setReaders(readers)
  } 

  def setReaderProperties() = {
    val properties: SetProperties = null
    getAleLrServiceProxy.setProperties(properties)
  }

  def undefineReader(readerName: String) = {
    val reader: Undefine = null
    getAleLrServiceProxy.undefine(reader)
  }

  def updateReader(readerName: String, spec: LRSpec) = {
    val reader: Update = null
    getAleLrServiceProxy.update(reader)
  }

  def getLogicalReaderNames(): ArrayOfString = getAleLrServiceProxy.getLogicalReaderNames(new EmptyParms)


  def getStandardVersion(): String = getAleLrServiceProxy.getStandardVersion(new EmptyParms)

  def getVendorVersion(): String = getAleLrServiceProxy.getVendorVersion(new EmptyParms)

  def getAleLrServiceProxy: ALELRServicePortType = getAleLrProxy()

  def setAleLrServiceEndpointAddress(address: String) = setEndpointService(address)

}
