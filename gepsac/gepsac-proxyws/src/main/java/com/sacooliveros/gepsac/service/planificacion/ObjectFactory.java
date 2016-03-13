
package com.sacooliveros.gepsac.service.planificacion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sacooliveros.gepsac.service.planificacion package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Registrar_QNAME = new QName("http://planificacion.service.gepsac.sacooliveros.com/", "registrar");
    private final static QName _ListarResponse_QNAME = new QName("http://planificacion.service.gepsac.sacooliveros.com/", "listarResponse");
    private final static QName _ConfigurarResponse_QNAME = new QName("http://planificacion.service.gepsac.sacooliveros.com/", "configurarResponse");
    private final static QName _GenerarProgramacionResponse_QNAME = new QName("http://planificacion.service.gepsac.sacooliveros.com/", "generarProgramacionResponse");
    private final static QName _ObtenerVigenteResponse_QNAME = new QName("http://planificacion.service.gepsac.sacooliveros.com/", "obtenerVigenteResponse");
    private final static QName _GenerarProgramacion_QNAME = new QName("http://planificacion.service.gepsac.sacooliveros.com/", "generarProgramacion");
    private final static QName _ObtenerProgramarPlan_QNAME = new QName("http://planificacion.service.gepsac.sacooliveros.com/", "obtenerProgramarPlan");
    private final static QName _ObtenerConfigurarPlanResponse_QNAME = new QName("http://planificacion.service.gepsac.sacooliveros.com/", "obtenerConfigurarPlanResponse");
    private final static QName _ObtenerConfigurarPlan_QNAME = new QName("http://planificacion.service.gepsac.sacooliveros.com/", "obtenerConfigurarPlan");
    private final static QName _ObtenerProgramarPlanResponse_QNAME = new QName("http://planificacion.service.gepsac.sacooliveros.com/", "obtenerProgramarPlanResponse");
    private final static QName _Programar_QNAME = new QName("http://planificacion.service.gepsac.sacooliveros.com/", "programar");
    private final static QName _Configurar_QNAME = new QName("http://planificacion.service.gepsac.sacooliveros.com/", "configurar");
    private final static QName _Listar_QNAME = new QName("http://planificacion.service.gepsac.sacooliveros.com/", "listar");
    private final static QName _ProgramarResponse_QNAME = new QName("http://planificacion.service.gepsac.sacooliveros.com/", "programarResponse");
    private final static QName _RegistrarResponse_QNAME = new QName("http://planificacion.service.gepsac.sacooliveros.com/", "registrarResponse");
    private final static QName _ServiceException_QNAME = new QName("http://planificacion.service.gepsac.sacooliveros.com/", "ServiceException");
    private final static QName _ObtenerVigente_QNAME = new QName("http://planificacion.service.gepsac.sacooliveros.com/", "obtenerVigente");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sacooliveros.gepsac.service.planificacion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Configurar }
     * 
     */
    public Configurar createConfigurar() {
        return new Configurar();
    }

    /**
     * Create an instance of {@link RegistrarResponse }
     * 
     */
    public RegistrarResponse createRegistrarResponse() {
        return new RegistrarResponse();
    }

    /**
     * Create an instance of {@link Actividad }
     * 
     */
    public Actividad createActividad() {
        return new Actividad();
    }

    /**
     * Create an instance of {@link Listar }
     * 
     */
    public Listar createListar() {
        return new Listar();
    }

    /**
     * Create an instance of {@link Indicador }
     * 
     */
    public Indicador createIndicador() {
        return new Indicador();
    }

    /**
     * Create an instance of {@link Plan }
     * 
     */
    public Plan createPlan() {
        return new Plan();
    }

    /**
     * Create an instance of {@link GenerarProgramacion }
     * 
     */
    public GenerarProgramacion createGenerarProgramacion() {
        return new GenerarProgramacion();
    }

    /**
     * Create an instance of {@link ObtenerProgramarPlan }
     * 
     */
    public ObtenerProgramarPlan createObtenerProgramarPlan() {
        return new ObtenerProgramarPlan();
    }

    /**
     * Create an instance of {@link Registrar }
     * 
     */
    public Registrar createRegistrar() {
        return new Registrar();
    }

    /**
     * Create an instance of {@link GenerarProgramacionResponse }
     * 
     */
    public GenerarProgramacionResponse createGenerarProgramacionResponse() {
        return new GenerarProgramacionResponse();
    }

    /**
     * Create an instance of {@link RestriccionFecha }
     * 
     */
    public RestriccionFecha createRestriccionFecha() {
        return new RestriccionFecha();
    }

    /**
     * Create an instance of {@link PlanIndicador }
     * 
     */
    public PlanIndicador createPlanIndicador() {
        return new PlanIndicador();
    }

    /**
     * Create an instance of {@link Estado }
     * 
     */
    public Estado createEstado() {
        return new Estado();
    }

    /**
     * Create an instance of {@link ConfigurarResponse }
     * 
     */
    public ConfigurarResponse createConfigurarResponse() {
        return new ConfigurarResponse();
    }

    /**
     * Create an instance of {@link ListarResponse }
     * 
     */
    public ListarResponse createListarResponse() {
        return new ListarResponse();
    }

    /**
     * Create an instance of {@link ObtenerConfigurarPlan }
     * 
     */
    public ObtenerConfigurarPlan createObtenerConfigurarPlan() {
        return new ObtenerConfigurarPlan();
    }

    /**
     * Create an instance of {@link ObtenerConfigurarPlanResponse }
     * 
     */
    public ObtenerConfigurarPlanResponse createObtenerConfigurarPlanResponse() {
        return new ObtenerConfigurarPlanResponse();
    }

    /**
     * Create an instance of {@link Estrategia }
     * 
     */
    public Estrategia createEstrategia() {
        return new Estrategia();
    }

    /**
     * Create an instance of {@link Programar }
     * 
     */
    public Programar createProgramar() {
        return new Programar();
    }

    /**
     * Create an instance of {@link PlanActividad }
     * 
     */
    public PlanActividad createPlanActividad() {
        return new PlanActividad();
    }

    /**
     * Create an instance of {@link EstrategiaActividad }
     * 
     */
    public EstrategiaActividad createEstrategiaActividad() {
        return new EstrategiaActividad();
    }

    /**
     * Create an instance of {@link ObtenerVigente }
     * 
     */
    public ObtenerVigente createObtenerVigente() {
        return new ObtenerVigente();
    }

    /**
     * Create an instance of {@link ProgramarResponse }
     * 
     */
    public ProgramarResponse createProgramarResponse() {
        return new ProgramarResponse();
    }

    /**
     * Create an instance of {@link ObtenerVigenteResponse }
     * 
     */
    public ObtenerVigenteResponse createObtenerVigenteResponse() {
        return new ObtenerVigenteResponse();
    }

    /**
     * Create an instance of {@link Model }
     * 
     */
    public Model createModel() {
        return new Model();
    }

    /**
     * Create an instance of {@link ServiceException }
     * 
     */
    public ServiceException createServiceException() {
        return new ServiceException();
    }

    /**
     * Create an instance of {@link ObtenerProgramarPlanResponse }
     * 
     */
    public ObtenerProgramarPlanResponse createObtenerProgramarPlanResponse() {
        return new ObtenerProgramarPlanResponse();
    }

    /**
     * Create an instance of {@link PlanEstrategia }
     * 
     */
    public PlanEstrategia createPlanEstrategia() {
        return new PlanEstrategia();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Registrar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://planificacion.service.gepsac.sacooliveros.com/", name = "registrar")
    public JAXBElement<Registrar> createRegistrar(Registrar value) {
        return new JAXBElement<Registrar>(_Registrar_QNAME, Registrar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://planificacion.service.gepsac.sacooliveros.com/", name = "listarResponse")
    public JAXBElement<ListarResponse> createListarResponse(ListarResponse value) {
        return new JAXBElement<ListarResponse>(_ListarResponse_QNAME, ListarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigurarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://planificacion.service.gepsac.sacooliveros.com/", name = "configurarResponse")
    public JAXBElement<ConfigurarResponse> createConfigurarResponse(ConfigurarResponse value) {
        return new JAXBElement<ConfigurarResponse>(_ConfigurarResponse_QNAME, ConfigurarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerarProgramacionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://planificacion.service.gepsac.sacooliveros.com/", name = "generarProgramacionResponse")
    public JAXBElement<GenerarProgramacionResponse> createGenerarProgramacionResponse(GenerarProgramacionResponse value) {
        return new JAXBElement<GenerarProgramacionResponse>(_GenerarProgramacionResponse_QNAME, GenerarProgramacionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerVigenteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://planificacion.service.gepsac.sacooliveros.com/", name = "obtenerVigenteResponse")
    public JAXBElement<ObtenerVigenteResponse> createObtenerVigenteResponse(ObtenerVigenteResponse value) {
        return new JAXBElement<ObtenerVigenteResponse>(_ObtenerVigenteResponse_QNAME, ObtenerVigenteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerarProgramacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://planificacion.service.gepsac.sacooliveros.com/", name = "generarProgramacion")
    public JAXBElement<GenerarProgramacion> createGenerarProgramacion(GenerarProgramacion value) {
        return new JAXBElement<GenerarProgramacion>(_GenerarProgramacion_QNAME, GenerarProgramacion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerProgramarPlan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://planificacion.service.gepsac.sacooliveros.com/", name = "obtenerProgramarPlan")
    public JAXBElement<ObtenerProgramarPlan> createObtenerProgramarPlan(ObtenerProgramarPlan value) {
        return new JAXBElement<ObtenerProgramarPlan>(_ObtenerProgramarPlan_QNAME, ObtenerProgramarPlan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerConfigurarPlanResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://planificacion.service.gepsac.sacooliveros.com/", name = "obtenerConfigurarPlanResponse")
    public JAXBElement<ObtenerConfigurarPlanResponse> createObtenerConfigurarPlanResponse(ObtenerConfigurarPlanResponse value) {
        return new JAXBElement<ObtenerConfigurarPlanResponse>(_ObtenerConfigurarPlanResponse_QNAME, ObtenerConfigurarPlanResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerConfigurarPlan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://planificacion.service.gepsac.sacooliveros.com/", name = "obtenerConfigurarPlan")
    public JAXBElement<ObtenerConfigurarPlan> createObtenerConfigurarPlan(ObtenerConfigurarPlan value) {
        return new JAXBElement<ObtenerConfigurarPlan>(_ObtenerConfigurarPlan_QNAME, ObtenerConfigurarPlan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerProgramarPlanResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://planificacion.service.gepsac.sacooliveros.com/", name = "obtenerProgramarPlanResponse")
    public JAXBElement<ObtenerProgramarPlanResponse> createObtenerProgramarPlanResponse(ObtenerProgramarPlanResponse value) {
        return new JAXBElement<ObtenerProgramarPlanResponse>(_ObtenerProgramarPlanResponse_QNAME, ObtenerProgramarPlanResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Programar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://planificacion.service.gepsac.sacooliveros.com/", name = "programar")
    public JAXBElement<Programar> createProgramar(Programar value) {
        return new JAXBElement<Programar>(_Programar_QNAME, Programar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Configurar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://planificacion.service.gepsac.sacooliveros.com/", name = "configurar")
    public JAXBElement<Configurar> createConfigurar(Configurar value) {
        return new JAXBElement<Configurar>(_Configurar_QNAME, Configurar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Listar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://planificacion.service.gepsac.sacooliveros.com/", name = "listar")
    public JAXBElement<Listar> createListar(Listar value) {
        return new JAXBElement<Listar>(_Listar_QNAME, Listar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProgramarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://planificacion.service.gepsac.sacooliveros.com/", name = "programarResponse")
    public JAXBElement<ProgramarResponse> createProgramarResponse(ProgramarResponse value) {
        return new JAXBElement<ProgramarResponse>(_ProgramarResponse_QNAME, ProgramarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://planificacion.service.gepsac.sacooliveros.com/", name = "registrarResponse")
    public JAXBElement<RegistrarResponse> createRegistrarResponse(RegistrarResponse value) {
        return new JAXBElement<RegistrarResponse>(_RegistrarResponse_QNAME, RegistrarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://planificacion.service.gepsac.sacooliveros.com/", name = "ServiceException")
    public JAXBElement<ServiceException> createServiceException(ServiceException value) {
        return new JAXBElement<ServiceException>(_ServiceException_QNAME, ServiceException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerVigente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://planificacion.service.gepsac.sacooliveros.com/", name = "obtenerVigente")
    public JAXBElement<ObtenerVigente> createObtenerVigente(ObtenerVigente value) {
        return new JAXBElement<ObtenerVigente>(_ObtenerVigente_QNAME, ObtenerVigente.class, null, value);
    }

}
