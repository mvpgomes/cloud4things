package cloud4things.cli.fosstrak.client

import org.fosstrak.ale.wsdl.alelr.epcglobal.{ALELRServicePortType, EmptyParms}

/*
 * ALELRClient : A ALELR client implementation that
 * contains the methods stub's for the ALELR service
 * of the Fosstrak Filtering & Collecting Server.
 */
class FosstrakALELRClient() extends FosstrakClient(classOf[ALELRServicePortType],
                                                   classOf[ALELRServicePortType].getMethod("getStandardVersion", classOf[EmptyParms]),
                                                   new EmptyParms) {

  def addReader() = {}

  def defineReader() = {}

  def removeReader() = {}

  def setReader() = {}

  def setReaderProperties() = {}

  def updateReader() = {}

  def getLogicalReaderNames() = getAleLrServiceProxy.getLogicalReaderNames(new EmptyParms)

  def getStandardVersion() = getAleLrServiceProxy.getStandardVersion(new EmptyParms)

  def getVendorVersion() = getAleLrServiceProxy.getVendorVersion(new EmptyParms)

  def getAleLrServiceProxy: ALELRServicePortType = getAleLrProxy()

  def setAleLrServiceEndpointAddress(address: String) = setEndpointService(address)

}
