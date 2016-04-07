
package edu.pe.sacoliveros.app;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.pe.sacoliveros.app package. 
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

    private final static QName _BuscarAlumnoPostulante_QNAME = new QName("http://app.sacoliveros.pe.edu/", "buscarAlumnoPostulante");
    private final static QName _ListarAlumnoEvaluadoResponse_QNAME = new QName("http://app.sacoliveros.pe.edu/", "listarAlumnoEvaluadoResponse");
    private final static QName _ObtenerAlumnoPostulanteResponse_QNAME = new QName("http://app.sacoliveros.pe.edu/", "obtenerAlumnoPostulanteResponse");
    private final static QName _ListarAlumnoEvaluado_QNAME = new QName("http://app.sacoliveros.pe.edu/", "listarAlumnoEvaluado");
    private final static QName _BuscarAlumnoEvaluado_QNAME = new QName("http://app.sacoliveros.pe.edu/", "buscarAlumnoEvaluado");
    private final static QName _ListarAlumnoPostulanteResponse_QNAME = new QName("http://app.sacoliveros.pe.edu/", "listarAlumnoPostulanteResponse");
    private final static QName _BuscarAlumnoPostulanteResponse_QNAME = new QName("http://app.sacoliveros.pe.edu/", "buscarAlumnoPostulanteResponse");
    private final static QName _ObtenerAlumnoEvaluacion_QNAME = new QName("http://app.sacoliveros.pe.edu/", "obtenerAlumnoEvaluacion");
    private final static QName _ObtenerAlumnoPostulante_QNAME = new QName("http://app.sacoliveros.pe.edu/", "obtenerAlumnoPostulante");
    private final static QName _ListarAlumnoPostulante_QNAME = new QName("http://app.sacoliveros.pe.edu/", "listarAlumnoPostulante");
    private final static QName _ObtenerAlumnoEvaluacionResponse_QNAME = new QName("http://app.sacoliveros.pe.edu/", "obtenerAlumnoEvaluacionResponse");
    private final static QName _BuscarAlumnoEvaluadoResponse_QNAME = new QName("http://app.sacoliveros.pe.edu/", "buscarAlumnoEvaluadoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.pe.sacoliveros.app
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BuscarAlumnoPostulante }
     * 
     */
    public BuscarAlumnoPostulante createBuscarAlumnoPostulante() {
        return new BuscarAlumnoPostulante();
    }

    /**
     * Create an instance of {@link ObtenerAlumnoPostulanteResponse }
     * 
     */
    public ObtenerAlumnoPostulanteResponse createObtenerAlumnoPostulanteResponse() {
        return new ObtenerAlumnoPostulanteResponse();
    }

    /**
     * Create an instance of {@link ListarAlumnoEvaluadoResponse }
     * 
     */
    public ListarAlumnoEvaluadoResponse createListarAlumnoEvaluadoResponse() {
        return new ListarAlumnoEvaluadoResponse();
    }

    /**
     * Create an instance of {@link ListarAlumnoPostulante }
     * 
     */
    public ListarAlumnoPostulante createListarAlumnoPostulante() {
        return new ListarAlumnoPostulante();
    }

    /**
     * Create an instance of {@link ObtenerAlumnoEvaluacion }
     * 
     */
    public ObtenerAlumnoEvaluacion createObtenerAlumnoEvaluacion() {
        return new ObtenerAlumnoEvaluacion();
    }

    /**
     * Create an instance of {@link BuscarAlumnoEvaluado }
     * 
     */
    public BuscarAlumnoEvaluado createBuscarAlumnoEvaluado() {
        return new BuscarAlumnoEvaluado();
    }

    /**
     * Create an instance of {@link ObtenerAlumnoEvaluacionResponse }
     * 
     */
    public ObtenerAlumnoEvaluacionResponse createObtenerAlumnoEvaluacionResponse() {
        return new ObtenerAlumnoEvaluacionResponse();
    }

    /**
     * Create an instance of {@link Alumno }
     * 
     */
    public Alumno createAlumno() {
        return new Alumno();
    }

    /**
     * Create an instance of {@link ObtenerAlumnoPostulante }
     * 
     */
    public ObtenerAlumnoPostulante createObtenerAlumnoPostulante() {
        return new ObtenerAlumnoPostulante();
    }

    /**
     * Create an instance of {@link BuscarAlumnoEvaluadoResponse }
     * 
     */
    public BuscarAlumnoEvaluadoResponse createBuscarAlumnoEvaluadoResponse() {
        return new BuscarAlumnoEvaluadoResponse();
    }

    /**
     * Create an instance of {@link ListarAlumnoEvaluado }
     * 
     */
    public ListarAlumnoEvaluado createListarAlumnoEvaluado() {
        return new ListarAlumnoEvaluado();
    }

    /**
     * Create an instance of {@link ListarAlumnoPostulanteResponse }
     * 
     */
    public ListarAlumnoPostulanteResponse createListarAlumnoPostulanteResponse() {
        return new ListarAlumnoPostulanteResponse();
    }

    /**
     * Create an instance of {@link BuscarAlumnoPostulanteResponse }
     * 
     */
    public BuscarAlumnoPostulanteResponse createBuscarAlumnoPostulanteResponse() {
        return new BuscarAlumnoPostulanteResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarAlumnoPostulante }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://app.sacoliveros.pe.edu/", name = "buscarAlumnoPostulante")
    public JAXBElement<BuscarAlumnoPostulante> createBuscarAlumnoPostulante(BuscarAlumnoPostulante value) {
        return new JAXBElement<BuscarAlumnoPostulante>(_BuscarAlumnoPostulante_QNAME, BuscarAlumnoPostulante.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarAlumnoEvaluadoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://app.sacoliveros.pe.edu/", name = "listarAlumnoEvaluadoResponse")
    public JAXBElement<ListarAlumnoEvaluadoResponse> createListarAlumnoEvaluadoResponse(ListarAlumnoEvaluadoResponse value) {
        return new JAXBElement<ListarAlumnoEvaluadoResponse>(_ListarAlumnoEvaluadoResponse_QNAME, ListarAlumnoEvaluadoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerAlumnoPostulanteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://app.sacoliveros.pe.edu/", name = "obtenerAlumnoPostulanteResponse")
    public JAXBElement<ObtenerAlumnoPostulanteResponse> createObtenerAlumnoPostulanteResponse(ObtenerAlumnoPostulanteResponse value) {
        return new JAXBElement<ObtenerAlumnoPostulanteResponse>(_ObtenerAlumnoPostulanteResponse_QNAME, ObtenerAlumnoPostulanteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarAlumnoEvaluado }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://app.sacoliveros.pe.edu/", name = "listarAlumnoEvaluado")
    public JAXBElement<ListarAlumnoEvaluado> createListarAlumnoEvaluado(ListarAlumnoEvaluado value) {
        return new JAXBElement<ListarAlumnoEvaluado>(_ListarAlumnoEvaluado_QNAME, ListarAlumnoEvaluado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarAlumnoEvaluado }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://app.sacoliveros.pe.edu/", name = "buscarAlumnoEvaluado")
    public JAXBElement<BuscarAlumnoEvaluado> createBuscarAlumnoEvaluado(BuscarAlumnoEvaluado value) {
        return new JAXBElement<BuscarAlumnoEvaluado>(_BuscarAlumnoEvaluado_QNAME, BuscarAlumnoEvaluado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarAlumnoPostulanteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://app.sacoliveros.pe.edu/", name = "listarAlumnoPostulanteResponse")
    public JAXBElement<ListarAlumnoPostulanteResponse> createListarAlumnoPostulanteResponse(ListarAlumnoPostulanteResponse value) {
        return new JAXBElement<ListarAlumnoPostulanteResponse>(_ListarAlumnoPostulanteResponse_QNAME, ListarAlumnoPostulanteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarAlumnoPostulanteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://app.sacoliveros.pe.edu/", name = "buscarAlumnoPostulanteResponse")
    public JAXBElement<BuscarAlumnoPostulanteResponse> createBuscarAlumnoPostulanteResponse(BuscarAlumnoPostulanteResponse value) {
        return new JAXBElement<BuscarAlumnoPostulanteResponse>(_BuscarAlumnoPostulanteResponse_QNAME, BuscarAlumnoPostulanteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerAlumnoEvaluacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://app.sacoliveros.pe.edu/", name = "obtenerAlumnoEvaluacion")
    public JAXBElement<ObtenerAlumnoEvaluacion> createObtenerAlumnoEvaluacion(ObtenerAlumnoEvaluacion value) {
        return new JAXBElement<ObtenerAlumnoEvaluacion>(_ObtenerAlumnoEvaluacion_QNAME, ObtenerAlumnoEvaluacion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerAlumnoPostulante }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://app.sacoliveros.pe.edu/", name = "obtenerAlumnoPostulante")
    public JAXBElement<ObtenerAlumnoPostulante> createObtenerAlumnoPostulante(ObtenerAlumnoPostulante value) {
        return new JAXBElement<ObtenerAlumnoPostulante>(_ObtenerAlumnoPostulante_QNAME, ObtenerAlumnoPostulante.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarAlumnoPostulante }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://app.sacoliveros.pe.edu/", name = "listarAlumnoPostulante")
    public JAXBElement<ListarAlumnoPostulante> createListarAlumnoPostulante(ListarAlumnoPostulante value) {
        return new JAXBElement<ListarAlumnoPostulante>(_ListarAlumnoPostulante_QNAME, ListarAlumnoPostulante.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerAlumnoEvaluacionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://app.sacoliveros.pe.edu/", name = "obtenerAlumnoEvaluacionResponse")
    public JAXBElement<ObtenerAlumnoEvaluacionResponse> createObtenerAlumnoEvaluacionResponse(ObtenerAlumnoEvaluacionResponse value) {
        return new JAXBElement<ObtenerAlumnoEvaluacionResponse>(_ObtenerAlumnoEvaluacionResponse_QNAME, ObtenerAlumnoEvaluacionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarAlumnoEvaluadoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://app.sacoliveros.pe.edu/", name = "buscarAlumnoEvaluadoResponse")
    public JAXBElement<BuscarAlumnoEvaluadoResponse> createBuscarAlumnoEvaluadoResponse(BuscarAlumnoEvaluadoResponse value) {
        return new JAXBElement<BuscarAlumnoEvaluadoResponse>(_BuscarAlumnoEvaluadoResponse_QNAME, BuscarAlumnoEvaluadoResponse.class, null, value);
    }

}
