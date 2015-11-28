
package com.sacooliveros.gepsac.proxyws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sacooliveros.gepsac.proxyws package. 
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

    private final static QName _EvaluarAlumno_QNAME = new QName("http://experto.service.gepsac.sacooliveros.com/", "evaluarAlumno");
    private final static QName _EvaluarAlumnoResponse_QNAME = new QName("http://experto.service.gepsac.sacooliveros.com/", "evaluarAlumnoResponse");
    private final static QName _ServiceException_QNAME = new QName("http://experto.service.gepsac.sacooliveros.com/", "ServiceException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sacooliveros.gepsac.proxyws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Estado }
     * 
     */
    public Estado createEstado() {
        return new Estado();
    }

    /**
     * Create an instance of {@link PerfilEvaluado }
     * 
     */
    public PerfilEvaluado createPerfilEvaluado() {
        return new PerfilEvaluado();
    }

    /**
     * Create an instance of {@link EvaluarAlumnoResponse }
     * 
     */
    public EvaluarAlumnoResponse createEvaluarAlumnoResponse() {
        return new EvaluarAlumnoResponse();
    }

    /**
     * Create an instance of {@link Perfil }
     * 
     */
    public Perfil createPerfil() {
        return new Perfil();
    }

    /**
     * Create an instance of {@link Alumno }
     * 
     */
    public Alumno createAlumno() {
        return new Alumno();
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
     * Create an instance of {@link EvaluacionPostulante }
     * 
     */
    public EvaluacionPostulante createEvaluacionPostulante() {
        return new EvaluacionPostulante();
    }

    /**
     * Create an instance of {@link EvaluarAlumno }
     * 
     */
    public EvaluarAlumno createEvaluarAlumno() {
        return new EvaluarAlumno();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvaluarAlumno }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://experto.service.gepsac.sacooliveros.com/", name = "evaluarAlumno")
    public JAXBElement<EvaluarAlumno> createEvaluarAlumno(EvaluarAlumno value) {
        return new JAXBElement<EvaluarAlumno>(_EvaluarAlumno_QNAME, EvaluarAlumno.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvaluarAlumnoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://experto.service.gepsac.sacooliveros.com/", name = "evaluarAlumnoResponse")
    public JAXBElement<EvaluarAlumnoResponse> createEvaluarAlumnoResponse(EvaluarAlumnoResponse value) {
        return new JAXBElement<EvaluarAlumnoResponse>(_EvaluarAlumnoResponse_QNAME, EvaluarAlumnoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://experto.service.gepsac.sacooliveros.com/", name = "ServiceException")
    public JAXBElement<ServiceException> createServiceException(ServiceException value) {
        return new JAXBElement<ServiceException>(_ServiceException_QNAME, ServiceException.class, null, value);
    }

}
