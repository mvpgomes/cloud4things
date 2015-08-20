package main.scala.cloud4things.fosstrak.client

import java.util

import org.fosstrak.ale.wsdl.ale.epcglobal._
import org.fosstrak.ale.xsd.ale.epcglobal.ECSpec

/*
 * ALEClient : A ALE client implementation that
 * contains the methods stub's for the ALE service
 * of the Fosstrak Filtering & Collecting Server.
 */
class FosstrakALEClient() extends FosstrakClient(classOf[ALEServicePortType],
  classOf[ALEServicePortType].getMethod("getStandardVersion", classOf[EmptyParms]),
  new EmptyParms) {

  def defineEventCycle(eventName: String, spec: ECSpec) = {
    val eventCycle: Define = null
    getAleServiceProxy().define(eventCycle)
  }

  def getEventCycleSpec(eventName: String) = {
    val eventCycle: GetECSpec = null
    getAleServiceProxy().getECSpec(eventCycle)
  }

  def getECSubscribers(eventName: String) = {
    val eventCycle: GetSubscribers = null
    getAleServiceProxy().getSubscribers(eventCycle)
  }

  def immediateEventCycle(eventName: String) = {
    val eventCycle: Immediate = null
    getAleServiceProxy().immediate(eventCycle)
  }

  def poolEventCycle(eventName: String) = {
    val eventCycle: Poll = null
    getAleServiceProxy().poll(eventCycle)
  }

  def subscribeEventCycle(eventName: String) = {
    val eventCycle: Subscribe = null
    getAleServiceProxy().subscribe(eventCycle)
  }

  def undefineEventCycle(eventName: String) = {
    val eventCycle: Undefine = null
    getAleServiceProxy().undefine(eventCycle)
  }

  def unsubscribe(eventName: String) = {
    val eventCycle: Unsubscribe = null
    getAleServiceProxy().unsubscribe(eventCycle)
  }

  // Return a list containing the EventCycle names
  def getECSpecNames(): util.List[String] = getAleServiceProxy.getECSpecNames(new EmptyParms).getString

  // Return the standard version
  def getStandardVersion() = getAleServiceProxy.getStandardVersion(new EmptyParms)

  // Return the ALER vendor version
  def getVendorVersion() = getAleServiceProxy.getVendorVersion(new EmptyParms)

  // Return the ALE Service proxy
  def getAleServiceProxy() : ALEServicePortType = getAleProxy()

  // Set the ALE Service endpoint address to @address
  def setAleServiceEndpointAddress(address: String) = setEndpointService(address)
}
