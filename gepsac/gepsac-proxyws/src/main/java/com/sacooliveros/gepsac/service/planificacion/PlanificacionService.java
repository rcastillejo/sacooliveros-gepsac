
package com.sacooliveros.gepsac.service.planificacion;

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
@WebService(name = "PlanificacionService", targetNamespace = "http://planificacion.service.gepsac.sacooliveros.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PlanificacionService {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     * @throws ServiceException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "configurar", targetNamespace = "http://planificacion.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.planificacion.Configurar")
    @ResponseWrapper(localName = "configurarResponse", targetNamespace = "http://planificacion.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.planificacion.ConfigurarResponse")
    public String configurar(
        @WebParam(name = "arg0", targetNamespace = "")
        Plan arg0)
        throws ServiceException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "programar", targetNamespace = "http://planificacion.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.planificacion.Programar")
    @ResponseWrapper(localName = "programarResponse", targetNamespace = "http://planificacion.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.planificacion.ProgramarResponse")
    public String programar(
        @WebParam(name = "arg0", targetNamespace = "")
        Plan arg0);

    /**
     * 
     * @return
     *     returns com.sacooliveros.gepsac.service.planificacion.Plan
     * @throws ServiceException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "obtenerProgramarPlan", targetNamespace = "http://planificacion.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.planificacion.ObtenerProgramarPlan")
    @ResponseWrapper(localName = "obtenerProgramarPlanResponse", targetNamespace = "http://planificacion.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.planificacion.ObtenerProgramarPlanResponse")
    public Plan obtenerProgramarPlan()
        throws ServiceException_Exception
    ;

    /**
     * 
     * @return
     *     returns java.util.List<com.sacooliveros.gepsac.service.planificacion.Plan>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "listar", targetNamespace = "http://planificacion.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.planificacion.Listar")
    @ResponseWrapper(localName = "listarResponse", targetNamespace = "http://planificacion.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.planificacion.ListarResponse")
    public List<Plan> listar();

    /**
     * 
     * @return
     *     returns com.sacooliveros.gepsac.service.planificacion.Plan
     * @throws ServiceException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "obtenerConfigurarPlan", targetNamespace = "http://planificacion.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.planificacion.ObtenerConfigurarPlan")
    @ResponseWrapper(localName = "obtenerConfigurarPlanResponse", targetNamespace = "http://planificacion.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.planificacion.ObtenerConfigurarPlanResponse")
    public Plan obtenerConfigurarPlan()
        throws ServiceException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns com.sacooliveros.gepsac.service.planificacion.Plan
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "generarProgramacion", targetNamespace = "http://planificacion.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.planificacion.GenerarProgramacion")
    @ResponseWrapper(localName = "generarProgramacionResponse", targetNamespace = "http://planificacion.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.planificacion.GenerarProgramacionResponse")
    public Plan generarProgramacion(
        @WebParam(name = "arg0", targetNamespace = "")
        Plan arg0);

    /**
     * 
     * @return
     *     returns com.sacooliveros.gepsac.service.planificacion.Plan
     * @throws ServiceException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "obtenerVigente", targetNamespace = "http://planificacion.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.planificacion.ObtenerVigente")
    @ResponseWrapper(localName = "obtenerVigenteResponse", targetNamespace = "http://planificacion.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.planificacion.ObtenerVigenteResponse")
    public Plan obtenerVigente()
        throws ServiceException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "registrar", targetNamespace = "http://planificacion.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.planificacion.Registrar")
    @ResponseWrapper(localName = "registrarResponse", targetNamespace = "http://planificacion.service.gepsac.sacooliveros.com/", className = "com.sacooliveros.gepsac.service.planificacion.RegistrarResponse")
    public String registrar(
        @WebParam(name = "arg0", targetNamespace = "")
        Plan arg0);

}