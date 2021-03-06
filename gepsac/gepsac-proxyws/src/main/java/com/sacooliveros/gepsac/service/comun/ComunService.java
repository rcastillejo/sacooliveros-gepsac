
package com.sacooliveros.gepsac.service.comun;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.7-b01-
 * Generated source version: 2.1
 * 
 */
@WebService(name = "ComunService", targetNamespace = "http://comun.service.gepsac.sacooliveros.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ComunService {


    /**
     * 
     * @return
     *     returns java.util.List<com.sacooliveros.gepsac.service.comun.Estrategia>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "listarEstrategia", targetNamespace = "http://comun.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.comun.ListarEstrategia")
    @ResponseWrapper(localName = "listarEstrategiaResponse", targetNamespace = "http://comun.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.comun.ListarEstrategiaResponse")
    public List<Estrategia> listarEstrategia();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<com.sacooliveros.gepsac.service.comun.EstrategiaActividad>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "listarEstrategiaActividad", targetNamespace = "http://comun.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.comun.ListarEstrategiaActividad")
    @ResponseWrapper(localName = "listarEstrategiaActividadResponse", targetNamespace = "http://comun.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.comun.ListarEstrategiaActividadResponse")
    public List<EstrategiaActividad> listarEstrategiaActividad(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
