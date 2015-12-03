/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.form.experto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sacooliveros.gepsac.proxyws.BOService;
import com.sacooliveros.gepsac.proxyws.EvaluacionPostulante;
import com.sacooliveros.gepsac.proxyws.WebServiceAlumno;
import com.sacooliveros.gepsac.proxyws.util.ProxyUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private String getCodigoDocumento(String codigo) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
        return codigo + sdf.format(new Date());
    }

    public void initEvaluarAlumno(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            EvaluacionPostulante evaluacion = new EvaluacionPostulante();
            evaluacion.setCodigo(getCodigoDocumento(Config.CODIGO_DOCUMENTO));
            Resultado resultado = createSuccessResult(evaluacion);

            generalAction(resultado, response);
        } catch (Exception e) {
            LoggerUtil.error(logger, "initEvaluarAlumno", "experto", request, e);
            generalAction(createErrorResult(e), response);
        }
    }

    public void initBuscarAlumno(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            WebServiceAlumno service = ProxyUtil.getAlumoServicePort(Config.TIMEOUT);
            //String codigoEstrategia = request.getParameter("codigoEstrategia");
            logger.debug("Consultando Alumnos al Servicio Saco Oliveros");

            Resultado resultado = createSuccessResult(service.listarAlumnoPostulante());

            generalAction(resultado, response);
        } catch (Exception e) {
            LoggerUtil.error(logger, "initBuscarAlumno", "experto", request, e);
            generalAction(createErrorResult(e), response);
        }
    }

    public void evaluarAlumno(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("evaluando alumno ... ");
        try {
            BOService service = ProxyUtil.getBOServicePort(Config.TIMEOUT);
            String json = request.getParameter("evaluacion");
            logger.debug("json [{}]", json);
            com.sacooliveros.gepsac.proxyws.EvaluacionPostulante evaluacion = jsonBuilder.fromJson(json, com.sacooliveros.gepsac.proxyws.EvaluacionPostulante.class);

            logger.debug("alumno a evaluar [{}]", evaluacion);

            EvaluacionPostulante evaluacionPostulante = service.evaluarAlumno(evaluacion);

            String msg = MessageFormat.format(Mensaje.EVALUAR, new Object[]{evaluacionPostulante.getCodigo()});

            logger.info("Evaluacion resultado [{}]", evaluacionPostulante.getCodigo());
            generalAction(createSuccessResult(msg), response);
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
        response.setStatus(resultado.getCodigo());
    }

}
