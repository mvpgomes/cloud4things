package cloud4things.cli.fosstrak.client

import java.lang.reflect.Method

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean
import org.fosstrak.ale.wsdl.ale.epcglobal.ALEServicePortType
import org.fosstrak.ale.wsdl.alelr.epcglobal.ALELRServicePortType

abstract class FosstrakClient(methodClass: Object, method: Method, methodParameter: Object) {

  // ALE proxy object
  protected var aleServiceProxy: ALEServicePortType = null

  // ALELR proxy object
  protected var aleLrServiceProxy : ALELRServicePortType = null

  // Test method to call for the connection establishment test
  protected val testMethod: Method = method

  // Parameter object for the test method call
  protected val testMethodParameter: Object = methodParameter

  // Proxy stub class
  protected val testMethodClass: Object = methodClass

  // Key to the endpoint in the properties.
  protected var methodEndpointAddress : String = null

  // Set the service endpoint
  protected def setEndpointService(endpointAddress: String) = {
    methodEndpointAddress = endpointAddress
  }

  // Creates a proxy object
  protected def createAleProxyObject(address: String, proxy: Object) : ALEServicePortType = {
    val factory : JaxWsProxyFactoryBean = new JaxWsProxyFactoryBean
    factory.setServiceClass(proxy.getClass)
    factory.setAddress(address)
    factory.create().asInstanceOf[ALEServicePortType]
  }

  // Creates a proxy object
  protected def createAleLrProxyObject(address: String, proxy: Object) : ALELRServicePortType = {
    val factory : JaxWsProxyFactoryBean = new JaxWsProxyFactoryBean
    factory.setServiceClass(proxy.getClass)
    factory.setAddress(address)
    factory.create().asInstanceOf[ALELRServicePortType]
  }

  // Test the proxy method call by performing a method call
  protected def callTestMethod(proxy: Object, method: Method, methodParameter : Object) = {
    method.invoke(proxy, methodParameter)
  }

  // Returns the ALELR proxy object
  protected def getAleLrProxy(): ALELRServicePortType = {
    aleLrServiceProxy = try {
      val proxy: ALELRServicePortType = createAleLrProxyObject(methodEndpointAddress, testMethodClass)
      callTestMethod(proxy, testMethod, testMethodParameter)
      proxy
    } catch {
      case e: Exception => null
    }
    aleLrServiceProxy
  }

  // Returns the ALE proxy object
  protected def getAleProxy(): ALEServicePortType = {
    aleServiceProxy = try {
      val proxy: ALEServicePortType = createAleProxyObject(methodEndpointAddress, testMethodClass)
      callTestMethod(proxy, testMethod, testMethodParameter)
      proxy
    } catch {
      case e: Exception => null
    }
    aleServiceProxy
  }
}
