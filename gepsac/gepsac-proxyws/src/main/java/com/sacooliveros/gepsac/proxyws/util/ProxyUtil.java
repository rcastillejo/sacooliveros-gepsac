/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.proxyws.util;

import com.sacooliveros.gepsac.proxyws.handler.LogHandler;
import com.sacooliveros.gepsac.service.BOService;
import com.sacooliveros.gepsac.service.BOService_Service;
import com.sacooliveros.gepsac.service.comun.ComunService;
import com.sacooliveros.gepsac.service.comun.ComunService_Service;
import com.sacooliveros.gepsac.service.planificacion.PlanificacionService;
import com.sacooliveros.gepsac.service.planificacion.PlanificacionService_Service;
import edu.pe.sacoliveros.app.WebServiceAlumno;
import edu.pe.sacoliveros.app.WebServiceAlumno_Service;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

/**
 *
 * @author Ricardo
 */
public class ProxyUtil {

    private static final String SERVICE_IP = "localhost";
    private static final String PLAN_ENDPOINT = "http://" + SERVICE_IP + ":8180/gepsac-service/PlanificacionService";
    private static final String COMMON_ENDPOINT = "http://" + SERVICE_IP + ":8180/gepsac-service/ComunService";
    private static final String BO_ENDPOINT = "http://" + SERVICE_IP + ":8180/gepsac-service/BOService";
    private static final String WSALUMNO_ENDPOINT = "http://" + SERVICE_IP + ":8180/WebServiceIntranet/WebServiceAlumno";

    public static PlanificacionService getPlanificacionServicePort(long timeout) {
        PlanificacionService_Service service = new PlanificacionService_Service();
        PlanificacionService port = service.getPlanificacionServicePort();
        BindingProvider bp = (BindingProvider) port;
        setTimeout(bp, PLAN_ENDPOINT, timeout);
        return port;
    }

    public static ComunService getCommonServicePort(long timeout) {
        ComunService_Service service = new ComunService_Service();
        ComunService port = service.getComunServicePort();
        BindingProvider bp = (BindingProvider) port;
        setTimeout(bp, COMMON_ENDPOINT, timeout);
        return port;
    }

    public static BOService getBOServicePort(long timeout) {
        BOService_Service service = new BOService_Service();
        BOService port = service.getBOServicePort();
        BindingProvider bp = (BindingProvider) port;
        setTimeout(bp, BO_ENDPOINT, timeout);
        setHandlers(bp);
        return port;
    }

    public static WebServiceAlumno getAlumoServicePort(long timeout) {
        WebServiceAlumno_Service service = new WebServiceAlumno_Service();
        WebServiceAlumno port = service.getWebServiceAlumnoPort();
        BindingProvider bp = (BindingProvider) port;
        setTimeout(bp, WSALUMNO_ENDPOINT, timeout);
        return port;
    }

    private static void setTimeout(BindingProvider bp, String endpoint, long timeout) {
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        bp.getRequestContext().put("com.sun.xml.internal.ws.connect.timeout", timeout);
        bp.getRequestContext().put("com.sun.xml.internal.ws.request.timeout", timeout);
    }

    private static void setHandlers(BindingProvider bp) {
        Binding binding = bp.getBinding();
        List<Handler> handlerChain = new ArrayList<Handler>();
        //Add a handler to the handler chain
        handlerChain.add(new LogHandler());
        binding.setHandlerChain(handlerChain);
    }

}
