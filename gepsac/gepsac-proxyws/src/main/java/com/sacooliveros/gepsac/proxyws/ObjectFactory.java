
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

    private final static QName _ConfigurarResponse_QNAME = new QName("http://service.gepsac.sacooliveros.com/", "configurarResponse");
    private final static QName _Configurar_QNAME = new QName("http://service.gepsac.sacooliveros.com/", "configurar");
    private final static QName _Aperturar_QNAME = new QName("http://service.gepsac.sacooliveros.com/", "aperturar");
    private final static QName _AperturarResponse_QNAME = new QName("http://service.gepsac.sacooliveros.com/", "aperturarResponse");
    private final static QName _Generar_QNAME = new QName("http://service.gepsac.sacooliveros.com/", "generar");
    private final static QName _GenerarResponse_QNAME = new QName("http://service.gepsac.sacooliveros.com/", "generarResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sacooliveros.gepsac.proxyws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Documento }
     * 
     */
    public Documento createDocumento() {
        return new Documento();
    }

    /**
     * Create an instance of {@link PlanEstrategico }
     * 
     */
    public PlanEstrategico createPlanEstrategico() {
        return new PlanEstrategico();
    }

    /**
     * Create an instance of {@link AperturarResponse }
     * 
     */
    public AperturarResponse createAperturarResponse() {
        return new AperturarResponse();
    }

    /**
     * Create an instance of {@link GenerarResponse }
     * 
     */
    public GenerarResponse createGenerarResponse() {
        return new GenerarResponse();
    }

    /**
     * Create an instance of {@link Generar }
     * 
     */
    public Generar createGenerar() {
        return new Generar();
    }

    /**
     * Create an instance of {@link Configurar }
     * 
     */
    public Configurar createConfigurar() {
        return new Configurar();
    }

    /**
     * Create an instance of {@link ConfigurarResponse }
     * 
     */
    public ConfigurarResponse createConfigurarResponse() {
        return new ConfigurarResponse();
    }

    /**
     * Create an instance of {@link Aperturar }
     * 
     */
    public Aperturar createAperturar() {
        return new Aperturar();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigurarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.gepsac.sacooliveros.com/", name = "configurarResponse")
    public JAXBElement<ConfigurarResponse> createConfigurarResponse(ConfigurarResponse value) {
        return new JAXBElement<ConfigurarResponse>(_ConfigurarResponse_QNAME, ConfigurarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Configurar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.gepsac.sacooliveros.com/", name = "configurar")
    public JAXBElement<Configurar> createConfigurar(Configurar value) {
        return new JAXBElement<Configurar>(_Configurar_QNAME, Configurar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Aperturar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.gepsac.sacooliveros.com/", name = "aperturar")
    public JAXBElement<Aperturar> createAperturar(Aperturar value) {
        return new JAXBElement<Aperturar>(_Aperturar_QNAME, Aperturar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AperturarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.gepsac.sacooliveros.com/", name = "aperturarResponse")
    public JAXBElement<AperturarResponse> createAperturarResponse(AperturarResponse value) {
        return new JAXBElement<AperturarResponse>(_AperturarResponse_QNAME, AperturarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Generar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.gepsac.sacooliveros.com/", name = "generar")
    public JAXBElement<Generar> createGenerar(Generar value) {
        return new JAXBElement<Generar>(_Generar_QNAME, Generar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.gepsac.sacooliveros.com/", name = "generarResponse")
    public JAXBElement<GenerarResponse> createGenerarResponse(GenerarResponse value) {
        return new JAXBElement<GenerarResponse>(_GenerarResponse_QNAME, GenerarResponse.class, null, value);
    }

}
