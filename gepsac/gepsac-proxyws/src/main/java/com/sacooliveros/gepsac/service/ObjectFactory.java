
package com.sacooliveros.gepsac.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sacooliveros.gepsac.service package. 
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

    private final static QName _EvaluarAlumno_QNAME = new QName("http://service.gepsac.sacooliveros.com/", "evaluarAlumno");
    private final static QName _EvaluarAlumnoResponse_QNAME = new QName("http://service.gepsac.sacooliveros.com/", "evaluarAlumnoResponse");
    private final static QName _Explicacion_QNAME = new QName("http://service.gepsac.sacooliveros.com/", "explicacion");
    private final static QName _ExplicacionResponse_QNAME = new QName("http://service.gepsac.sacooliveros.com/", "explicacionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sacooliveros.gepsac.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PreguntaRegla }
     * 
     */
    public PreguntaRegla createPreguntaRegla() {
        return new PreguntaRegla();
    }

    /**
     * Create an instance of {@link ExplicacionResultado }
     * 
     */
    public ExplicacionResultado createExplicacionResultado() {
        return new ExplicacionResultado();
    }

    /**
     * Create an instance of {@link Participante }
     * 
     */
    public Participante createParticipante() {
        return new Participante();
    }

    /**
     * Create an instance of {@link EvaluarAlumno }
     * 
     */
    public EvaluarAlumno createEvaluarAlumno() {
        return new EvaluarAlumno();
    }

    /**
     * Create an instance of {@link Estado }
     * 
     */
    public Estado createEstado() {
        return new Estado();
    }

    /**
     * Create an instance of {@link Explicacion }
     * 
     */
    public Explicacion createExplicacion() {
        return new Explicacion();
    }

    /**
     * Create an instance of {@link Pregunta }
     * 
     */
    public Pregunta createPregunta() {
        return new Pregunta();
    }

    /**
     * Create an instance of {@link Alumno }
     * 
     */
    public Alumno createAlumno() {
        return new Alumno();
    }

    /**
     * Create an instance of {@link PerfilEvaluado }
     * 
     */
    public PerfilEvaluado createPerfilEvaluado() {
        return new PerfilEvaluado();
    }

    /**
     * Create an instance of {@link Model }
     * 
     */
    public Model createModel() {
        return new Model();
    }

    /**
     * Create an instance of {@link Entidad }
     * 
     */
    public Entidad createEntidad() {
        return new Entidad();
    }

    /**
     * Create an instance of {@link Regla }
     * 
     */
    public Regla createRegla() {
        return new Regla();
    }

    /**
     * Create an instance of {@link Perfil }
     * 
     */
    public Perfil createPerfil() {
        return new Perfil();
    }

    /**
     * Create an instance of {@link EvaluarAlumnoResponse }
     * 
     */
    public EvaluarAlumnoResponse createEvaluarAlumnoResponse() {
        return new EvaluarAlumnoResponse();
    }

    /**
     * Create an instance of {@link ExplicacionResponse }
     * 
     */
    public ExplicacionResponse createExplicacionResponse() {
        return new ExplicacionResponse();
    }

    /**
     * Create an instance of {@link EvaluacionPostulante }
     * 
     */
    public EvaluacionPostulante createEvaluacionPostulante() {
        return new EvaluacionPostulante();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvaluarAlumno }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.gepsac.sacooliveros.com/", name = "evaluarAlumno")
    public JAXBElement<EvaluarAlumno> createEvaluarAlumno(EvaluarAlumno value) {
        return new JAXBElement<EvaluarAlumno>(_EvaluarAlumno_QNAME, EvaluarAlumno.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvaluarAlumnoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.gepsac.sacooliveros.com/", name = "evaluarAlumnoResponse")
    public JAXBElement<EvaluarAlumnoResponse> createEvaluarAlumnoResponse(EvaluarAlumnoResponse value) {
        return new JAXBElement<EvaluarAlumnoResponse>(_EvaluarAlumnoResponse_QNAME, EvaluarAlumnoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Explicacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.gepsac.sacooliveros.com/", name = "explicacion")
    public JAXBElement<Explicacion> createExplicacion(Explicacion value) {
        return new JAXBElement<Explicacion>(_Explicacion_QNAME, Explicacion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExplicacionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.gepsac.sacooliveros.com/", name = "explicacionResponse")
    public JAXBElement<ExplicacionResponse> createExplicacionResponse(ExplicacionResponse value) {
        return new JAXBElement<ExplicacionResponse>(_ExplicacionResponse_QNAME, ExplicacionResponse.class, null, value);
    }

}
