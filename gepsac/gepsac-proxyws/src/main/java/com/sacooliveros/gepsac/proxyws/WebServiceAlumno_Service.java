
package com.sacooliveros.gepsac.proxyws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.7-b01-
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "WebServiceAlumno", targetNamespace = "http://app.sacoliveros.pe.edu/", wsdlLocation = "http://localhost:8180/WebServiceIntranet/WebServiceAlumno?wsdl")
public class WebServiceAlumno_Service
    extends Service
{

    private final static URL WEBSERVICEALUMNO_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.sacooliveros.gepsac.proxyws.WebServiceAlumno_Service.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.sacooliveros.gepsac.proxyws.WebServiceAlumno_Service.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:8180/WebServiceIntranet/WebServiceAlumno?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:8180/WebServiceIntranet/WebServiceAlumno?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        WEBSERVICEALUMNO_WSDL_LOCATION = url;
    }

    public WebServiceAlumno_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WebServiceAlumno_Service() {
        super(WEBSERVICEALUMNO_WSDL_LOCATION, new QName("http://app.sacoliveros.pe.edu/", "WebServiceAlumno"));
    }

    /**
     * 
     * @return
     *     returns WebServiceAlumno
     */
    @WebEndpoint(name = "WebServiceAlumnoPort")
    public WebServiceAlumno getWebServiceAlumnoPort() {
        return super.getPort(new QName("http://app.sacoliveros.pe.edu/", "WebServiceAlumnoPort"), WebServiceAlumno.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WebServiceAlumno
     */
    @WebEndpoint(name = "WebServiceAlumnoPort")
    public WebServiceAlumno getWebServiceAlumnoPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://app.sacoliveros.pe.edu/", "WebServiceAlumnoPort"), WebServiceAlumno.class, features);
    }

}
