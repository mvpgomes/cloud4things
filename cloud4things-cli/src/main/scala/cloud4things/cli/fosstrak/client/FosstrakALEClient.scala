package cloud4things.cli.fosstrak.client

import org.fosstrak.ale.wsdl.ale.epcglobal.{ALEServicePortType, EmptyParms}

/*
 * ALEClient : A ALE client implementation that
 * contains the methods stub's for the ALE service
 * of the Fosstrak Filtering & Collecting Server.
 */
class FosstrakALEClient() extends FosstrakClient(classOf[ALEServicePortType],
                                                 classOf[ALEServicePortType].getMethod("getStandardVersion", classOf[EmptyParms]),
                                                  new EmptyParms) {

  def defineEventCycle() = {}

  def getSubscribers() = {}
  
  def subscribe() = {}
  
  def unsubscribe() = {}

  def getECSpecNames() = getAleServiceProxy.getECSpecNames(new EmptyParms)

  def getStandardVersion() = getAleServiceProxy.getStandardVersion(new EmptyParms)

  def getVendorVersion() = getAleServiceProxy.getVendorVersion(new EmptyParms)

  def getAleServiceProxy() : ALEServicePortType = getAleProxy()

  def setAleServiceEndpointAddress(address: String) = setEndpointService(address)
}
