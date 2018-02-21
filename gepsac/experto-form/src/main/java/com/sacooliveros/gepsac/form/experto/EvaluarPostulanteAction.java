/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.form.experto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sacooliveros.gepsac.proxyws.util.ProxyUtil;
import com.sacooliveros.gepsac.service.BOService;
import com.sacooliveros.gepsac.service.EvaluacionPostulante;
import edu.pe.sacoliveros.app.WebServiceAlumno;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rcastillejo
 */
public class EvaluarPostulanteAction extends DispatchAction {

    private static final Logger logger = LoggerFactory.getLogger(EvaluarPostulanteAction.class);
    private final Gson jsonBuilder;

    interface Mensaje {

        String EVALUAR = "La evaluación fue realizada con éxito [{0}]";
    }

    public EvaluarPostulanteAction() {
        jsonBuilder = new GsonBuilder().create();
    }

    public void initEvaluarAlumno(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            EvaluacionPostulante evaluacion = new EvaluacionPostulante();
            Resultado resultado = createSuccessResult(evaluacion);
            generalAction(resultado, response);
        } catch (SOAPFaultException e) {
            LoggerUtil.error(logger, "initEvaluarAlumno", "experto", request, e);
            generalAction(createErrorResult(e), response);
        } catch (WebServiceException e) {
            LoggerUtil.error(logger, "initEvaluarAlumno", "experto", request, e);
            generalAction(createErrorResult("Ocurrio un error al evaluar al alumno"), response);
        } catch (Exception e) {
            LoggerUtil.error(logger, "initEvaluarAlumno", "experto", request, e);
            generalAction(createErrorResult(e), response);
        }
    }

    public void initBuscarAlumnoNuevo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            WebServiceAlumno service = ProxyUtil.getAlumoServicePort(Config.TIMEOUT);
            logger.debug("Buscando Alumnos Nuevos al Servicio Saco Oliveros");

            List<edu.pe.sacoliveros.app.Alumno> listaAlumno = service.listarAlumnoPostulante();
            if(listaAlumno == null || listaAlumno.isEmpty()){
                throw new Exception("No existe información de los alumnos postulantes");
            }
            
            Resultado resultado = createSuccessResult(listaAlumno);

            generalAction(resultado, response);
        } catch (SOAPFaultException e) {
            LoggerUtil.error(logger, "initBuscarAlumnoNuevo", "experto", request, e);
            generalAction(createErrorResult(e), response);
        } catch (WebServiceException e) {
            LoggerUtil.error(logger, "initBuscarAlumnoNuevo", "experto", request, e);
            generalAction(createErrorResult("Ocurrio un error al consultar los alumnos nuevos"), response);
        } catch (Exception e) {
            LoggerUtil.error(logger, "initBuscarAlumnoNuevo", "experto", request, e);
            generalAction(createErrorResult(e), response);
        }
    }

    public void initBuscarAlumnoEvaluado(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            WebServiceAlumno service = ProxyUtil.getAlumoServicePort(Config.TIMEOUT);
            logger.debug("Buscando Alumnos Nuevos al Servicio Saco Oliveros");

            List<edu.pe.sacoliveros.app.Alumno> listaAlumno = service.listarAlumnoEvaluado(); 
            if(listaAlumno == null || listaAlumno.isEmpty()){
                throw new Exception("No existe información de los alumnos postulantes");
            }
            
            Resultado resultado = createSuccessResult(listaAlumno);

            generalAction(resultado, response);
        } catch (SOAPFaultException e) {
            LoggerUtil.error(logger, "initBuscarAlumnoEvaluado", "experto", request, e);
            generalAction(createErrorResult(e), response);
        } catch (WebServiceException e) {
            LoggerUtil.error(logger, "initBuscarAlumnoEvaluado", "experto", request, e);
            generalAction(createErrorResult("Ocurrio un error al consultar los alumnos evaluados"), response);  
        } catch (Exception e) {
            LoggerUtil.error(logger, "initBuscarAlumnoEvaluado", "experto", request, e);
            generalAction(createErrorResult(e), response);
        }
    }
    
    public void buscarAlumnoNuevo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            WebServiceAlumno service = ProxyUtil.getAlumoServicePort(Config.TIMEOUT);
            logger.debug("Buscando Alumnos Nuevos al Servicio Saco Oliveros");
            String codigo = request.getParameter("codigo");
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            
            List<edu.pe.sacoliveros.app.Alumno> listaAlumno= service.buscarAlumnoPostulante(codigo, nombres, apellidos);
            if(listaAlumno.isEmpty()){
                throw new Exception("No existe información que coincida con lo ingresado");
            }
            
            Resultado resultado = createSuccessResult(listaAlumno);
            generalAction(resultado, response);
        } catch (SOAPFaultException e) {            
            LoggerUtil.error(logger, "buscarAlumnoNuevo", "experto", request, e);
            generalAction(createErrorResult(e), response);  
        } catch (WebServiceException e) {
            LoggerUtil.error(logger, "buscarAlumnoNuevo", "experto", request, e);
            generalAction(createErrorResult("Ocurrio un error al consultar los alumnos nuevos"), response);  
        } catch (Exception e) {
            LoggerUtil.error(logger, "buscarAlumnoNuevo", "experto", request, e);
            generalAction(createErrorResult(e), response);
        }
    }
    
    public void buscarAlumnoEvaluado(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            WebServiceAlumno service = ProxyUtil.getAlumoServicePort(Config.TIMEOUT);
            logger.debug("Buscando Alumnos Nuevos al Servicio Saco Oliveros");
            String codigo = request.getParameter("codigo");
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            
            List<edu.pe.sacoliveros.app.Alumno> listaAlumno= service.buscarAlumnoEvaluado(codigo, nombres, apellidos);
            if(listaAlumno.isEmpty()){
                throw new Exception("No existe información que coincida con lo ingresado");
            }
            
            Resultado resultado = createSuccessResult(listaAlumno);
            generalAction(resultado, response);
        } catch (SOAPFaultException e) {
            LoggerUtil.error(logger, "initBuscarAlumnoNuevo", "experto", request, e);
            generalAction(createErrorResult(e), response);   
        } catch (WebServiceException e) {
            LoggerUtil.error(logger, "initBuscarAlumnoNuevo", "experto", request, e);
            generalAction(createErrorResult("Ocurrio un error al consultar los alumnos evaluados"), response);     
        } catch (Exception e) {
            LoggerUtil.error(logger, "initBuscarAlumnoNuevo", "experto", request, e);
            generalAction(createErrorResult(e), response);
        }
    }

    public void evaluarAlumno(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("evaluando alumno ... ");
        try {
            BOService service = ProxyUtil.getBOServicePort(Config.TIMEOUT);
            String evaluacionSerealizable = request.getParameter("evaluacion");
            logger.debug("evaluacionSerealizable [{}]", evaluacionSerealizable);
            String evaluacionDecode = new String(evaluacionSerealizable.getBytes("iso-8859-1"), "UTF-8");
            logger.debug("evaluacionDecode [{}]", evaluacionDecode);
            com.sacooliveros.gepsac.service.EvaluacionPostulante evaluacion = jsonBuilder.fromJson(evaluacionDecode, com.sacooliveros.gepsac.service.EvaluacionPostulante.class);

            logger.debug("alumno a evaluar [contextura={}]", evaluacion.getAlumno().getContextura().getNombre());
            logger.debug("alumno a evaluar [distrito={}]", evaluacion.getAlumno().getDistrito().getNombre());
            logger.debug("alumno a evaluar [religion={}]", evaluacion.getAlumno().getReligion().getNombre());

            EvaluacionPostulante evaluacionPostulante = service.evaluarAlumno(evaluacion);

            String msg = MessageFormat.format(Mensaje.EVALUAR, new Object[]{evaluacionPostulante.getCodigo()});

            logger.info("Evaluacion resultado [{}]", evaluacionPostulante.getCodigo());
            generalAction(createSuccessResult(new Object[]{evaluacionPostulante, msg}), response);
        } catch (SOAPFaultException e) {
            LoggerUtil.error(logger, "evaluarAlumno", "experto", request, e);
            generalAction(createErrorResult(e), response);       
        } catch (WebServiceException e) {
            LoggerUtil.error(logger, "evaluarAlumno", "experto", request, e);
            generalAction(createErrorResult("Ocurrio un error al evaluar al alumno"), response);            
        } catch (Exception e) {
            LoggerUtil.error(logger, "evaluarAlumno", "experto", request, e);
            generalAction(createErrorResult(e), response);
        }
    }

    public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("evaluarPostulante");
    }

    private Resultado createSuccessResult(Object obj) {
        String json = jsonBuilder.toJson(obj, obj.getClass());
        return new Resultado(json, HttpServletResponse.SC_OK);
    }

    private Resultado createErrorResult(Exception e) {
        return createErrorResult(e.getMessage());
    }

    private Resultado createErrorResult(String message) {
        return new Resultado(null, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
    }

    private void generalAction(Resultado resultado, HttpServletResponse response) {
        String json;
        String mensaje;
        json = resultado.getJson();
        mensaje = resultado.getMensaje();

        response.setStatus(resultado.getCodigo());
        if (StringUtils.isNotBlank(json)) {
            try {
                response.setContentType("text/json;charset=utf-8");
                response.setHeader("cache-control",
                        "no-cache");
                PrintWriter out = response.getWriter();
                out.print(json);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (StringUtils.isNotBlank(mensaje)) {
            try {
                response.getWriter().write(mensaje);
                response.flushBuffer();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        
    }

}
