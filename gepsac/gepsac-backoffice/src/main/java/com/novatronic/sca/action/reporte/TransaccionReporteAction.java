/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.action.reporte;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.novatronic.sca.action.GenericAction;
import com.novatronic.sca.controller.ReporteController;
import com.novatronic.sca.form.reporte.TransaccionReporteForm;
import com.novatronic.sca.model.Customer;
import com.novatronic.sca.model.Transaccion;
import com.novatronic.sca.service.exception.ServiceException;
import com.novatronic.sca.util.Pages;
import com.novatronic.sca.util.Resultado;
import com.pe.nova.components.bd.exception.BDException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rcastillejo
 */
public class TransaccionReporteAction extends GenericAction<TransaccionReporteForm> {

    private static final Logger log = LoggerFactory.getLogger(TransaccionReporteAction.class);
    private final ReporteController reporteController;
    private final Gson jsonBuilder;
    private Map parametros;

    public TransaccionReporteAction() throws IOException, BDException {
        reporteController = new ReporteController();
        jsonBuilder = new GsonBuilder().create();
    }

    public ActionForward getBuscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        TransaccionReporteForm actionForm = (TransaccionReporteForm) form;
        String mensajeError = null;
        Map dynformValues = actionForm.getFiltro();

        log.info("Mapeado [{}]", dynformValues.get("campo1"));
        log.info("Mapeado [{}]", dynformValues.get("campo2"));

        Map result = null;

        try {
            result = reporteController.listarTransaccion();
        } catch (Exception e) {
            mensajeError = e.getMessage();
        }

        actionForm.setResultado(result);
        actionForm.setMensajeError(mensajeError);
        log.info("Inicilizado Busqueda [{}]", actionForm);
        return mapping.findForward(Pages.REPORTE_TRANSACCION);

    }

    public ActionForward postBuscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        TransaccionReporteForm actionForm = (TransaccionReporteForm) form;
        String mensajeError = null;
        Map result = null;

        try {
            result = reporteController.buscarTransaccion(actionForm.getFiltro());
        } catch (Exception e) {
            mensajeError = e.getMessage();
        }

        actionForm.setResultado(result);
        actionForm.setMensajeError(mensajeError);
        log.info("Busqueda realizada [{}]", actionForm);
        return mapping.findForward(Pages.REPORTE_TRANSACCION);
    }

    @Override
    public Resultado eliminarImpl(ActionMapping mapping, TransaccionReporteForm form, HttpServletRequest request, HttpServletResponse response) throws NumberFormatException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Resultado editarImpl(ActionMapping mapping, TransaccionReporteForm form, HttpServletRequest request, HttpServletResponse response) throws NumberFormatException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Resultado agregarImpl(ActionMapping mapping, TransaccionReporteForm form, HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Resultado inicializarEditarImpl(ActionMapping mapping, TransaccionReporteForm form, HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Resultado inicializarAgregarImpl(ActionMapping mapping, TransaccionReporteForm form, HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Resultado busquedaImpl(ActionMapping mapping, TransaccionReporteForm form, HttpServletRequest request, HttpServletResponse response) {
        Resultado resultadoJson = null;

        try {
            log.info("BusquedaImpl");
            TransaccionReporteForm actionForm = (TransaccionReporteForm) form;
            String mensajeError;
            Map result;
            org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();

            Enumeration keys = request.getParameterNames();
            String JSON = keys.nextElement().toString();
            Map mapObject;
            mapObject = new TreeMap();
            mapObject = mapper.readValue(JSON,
                    new TypeReference<Map>() {

                    });
            Iterator it = mapObject.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                //System.out.println((String) e.getKey() + "--------" + e.getValue());

            }
            //Map dynformValues = new TreeMap();
            //dynformValues = actionForm.getFiltro();
            //Iterator it = dynformValues.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                //System.out.println((String) e.getKey() + "----BUSQUEDAIMPL----" + e.getValue());

            }

            try {
                result = reporteController.buscarTransaccion(mapObject);
                actionForm.setResultado(result);
                resultadoJson = createSuccessResult(actionForm);
            } catch (ServiceException e) {
                log.warn("Servicio", e);
                mensajeError = e.getMessage();
                actionForm.setMensajeError(mensajeError);
                resultadoJson = createSuccessResult(actionForm);
            } catch (Exception e) {
                log.error("Error", e);
                resultadoJson = createErrorResult(e.getMessage());
            }

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TransaccionReporteAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoJson;

    }

    @Override
    public Resultado inicializarBusquedaImpl(ActionMapping mapping, TransaccionReporteForm form, HttpServletRequest request, HttpServletResponse response) {
        Resultado resultadoJson = null;

        try {

            TransaccionReporteForm actionForm = (TransaccionReporteForm) form;
            org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();

            Enumeration keys = request.getParameterNames();
            String JSON = keys.nextElement().toString();
            Map mapObject;
            mapObject = new TreeMap();
            mapObject = mapper.readValue(JSON,
                    new TypeReference<Map>() {

                    });

            Iterator it = mapObject.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                System.out.println((String) e.getKey() + "--------" + e.getValue());

            }
//            List<LabelValueBean> newList = new ArrayList<LabelValueBean>();
//            LabelValueBean lb1 = new LabelValueBean("One", "One");
//            LabelValueBean lb2 = new LabelValueBean("Two", "Two");
//            LabelValueBean lb3 = new LabelValueBean("Three", "Three");
//            newList.add(lb1);
//            newList.add(lb2);
//            newList.add(lb3);
//            actionForm.setItemList(newList);

            //LabelValueBean v;
            String mensajeError = null;
            Map result = null;

            try {
                //result = reporteController.buscarTransaccion(mapObject);
                result = reporteController.buscarTransaccionInicio(mapObject);
                actionForm.setResultado(result);
                actionForm.setMensajeError(mensajeError);
                resultadoJson = createSuccessResult(actionForm);
            } catch (ServiceException e) {
                log.warn("Servicio", e);
                mensajeError = e.getMessage();
                actionForm.setResultado(result);
                actionForm.setMensajeError(mensajeError);
                resultadoJson = createSuccessResult(actionForm);
            } catch (Exception e) {
                log.error("Error", e);
                resultadoJson = createErrorResult(e.getMessage());
            }

            log.info("Busqueda realizada [{}]", actionForm);

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TransaccionReporteAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoJson;

    }

    @Override
    public Resultado inicializarComboImpl(ActionMapping mapping, TransaccionReporteForm form, HttpServletRequest request, HttpServletResponse response) {
        Resultado resultadoJson = null;

        try {

            TransaccionReporteForm actionForm = (TransaccionReporteForm) form;
            org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();

            Enumeration keys = request.getParameterNames();
            System.out.println("-" + keys);
            String JSON = keys.nextElement().toString();
            System.out.println("JSON" + JSON);

            Map mapObject;
            mapObject = new TreeMap();
            mapObject = mapper.readValue(JSON,
                    new TypeReference<Map>() {

                    });

            Iterator it = mapObject.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                System.out.println((String) e.getKey() + "--------" + e.getValue());

            }

            String mensajeError = null;
            Map result = null;

            try {
                result = reporteController.buscarComboInicio(mapObject);
                actionForm.setResultado(result);
                actionForm.setMensajeError(mensajeError);
                resultadoJson = createSuccessResult(actionForm);
            } catch (ServiceException e) {
                log.warn("Servicio", e);
                mensajeError = e.getMessage();
                actionForm.setResultado(result);
                actionForm.setMensajeError(mensajeError);
                resultadoJson = createSuccessResult(actionForm);
            } catch (Exception e) {
                log.error("Error", e);
                resultadoJson = createErrorResult(e.getMessage());
            }

            log.info("Busqueda realizada [{}]", actionForm);

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TransaccionReporteAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoJson;

    }

    @Override
    public ActionForward initImpl(ActionMapping mapping, TransaccionReporteForm form, HttpServletRequest request, HttpServletResponse response) {
        //form = new TransaccionReporteForm();

        return mapping.findForward(Pages.REPORTE_TRANSACCION);
    }

    private Resultado createSuccessResult(TransaccionReporteForm actionForm) {
        String json = jsonBuilder.toJson(actionForm, TransaccionReporteForm.class);
        return new Resultado(json, HttpServletResponse.SC_OK);
    }

    private Resultado createErrorResult(String message) {
        return new Resultado(null, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
    }

    @Override
    public Resultado inicializarComboReporteImpl(ActionMapping mapping, TransaccionReporteForm form, HttpServletRequest request, HttpServletResponse response) {
        Resultado resultadoJson = null;
        //System.out.println("inicializarComboReporteImplinicializarComboReporteImplinicializarComboReporteImplinicializarComboReporteImpl");
      

            TransaccionReporteForm actionForm = (TransaccionReporteForm) form;
           // org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();

           // Enumeration keys = request.getParameterNames();
            //System.out.println("----------" + keys);
           // String JSON = keys.nextElement().toString();
           // System.out.println("JSONNNNNNNNNNNNNNNNNN" + JSON);

           /* Map mapObject;
            mapObject = new TreeMap();
            mapObject = mapper.readValue(JSON,
                    new TypeReference<Map>() {

                    });

            Iterator it = mapObject.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                System.out.println((String) e.getKey() + "--------" + e.getValue());

            }*/

            String mensajeError = null;
            Map result = null;

            try {
                result = reporteController.buscarComboReporte();
                actionForm.setResultado(result);
                actionForm.setMensajeError(mensajeError);
                resultadoJson = createSuccessResult(actionForm);
            } catch (ServiceException e) {
                log.warn("Servicio", e);
                mensajeError = e.getMessage();
                actionForm.setResultado(result);
                actionForm.setMensajeError(mensajeError);
                resultadoJson = createSuccessResult(actionForm);
            } catch (Exception e) {
                log.error("Error", e);
                resultadoJson = createErrorResult(e.getMessage());
            }

            log.info("Busqueda realizada [{}]", actionForm);

       
        return resultadoJson;
    }

}
