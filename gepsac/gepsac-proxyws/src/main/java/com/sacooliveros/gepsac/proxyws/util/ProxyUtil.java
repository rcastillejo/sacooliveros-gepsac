/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.proxyws.util;

import com.sacooliveros.gepsac.proxyws.CommonService;
import com.sacooliveros.gepsac.proxyws.CommonService_Service;
import com.sacooliveros.gepsac.proxyws.PlanificacionService;
import com.sacooliveros.gepsac.proxyws.PlanificacionService_Service;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author Ricardo
 */
public class ProxyUtil {

    public static PlanificacionService getPlanificacionServicePort(long timeout) {
        PlanificacionService_Service service = new PlanificacionService_Service();
        PlanificacionService port = service.getPlanificacionServicePort();
        BindingProvider bp = (BindingProvider) port;
        //bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        bp.getRequestContext().put("com.sun.xml.internal.ws.connect.timeout", timeout);
        bp.getRequestContext().put("com.sun.xml.internal.ws.request.timeout", timeout);
        return port;
    }

    public static CommonService getCommonServicePort(long timeout) {
        CommonService_Service service = new CommonService_Service();
        CommonService port = service.getCommonServicePort();
        BindingProvider bp = (BindingProvider) port;
        //bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        bp.getRequestContext().put("com.sun.xml.internal.ws.connect.timeout", timeout);
        bp.getRequestContext().put("com.sun.xml.internal.ws.request.timeout", timeout);
        return port;
    }

}