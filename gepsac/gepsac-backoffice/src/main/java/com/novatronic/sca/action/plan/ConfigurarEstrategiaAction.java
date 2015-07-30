/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.action.plan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.novatronic.sca.form.plan.ConfigurarEstrategiaForm;
import com.novatronic.sca.util.ActionUtil;
import com.novatronic.sca.util.Config;
import com.novatronic.sca.util.Resultado;
import com.sacooliveros.gepsac.proxyws.PlanificacionService;
import com.sacooliveros.gepsac.proxyws.util.ProxyUtil;
import java.io.IOException;
import java.util.Map;
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
public class ConfigurarEstrategiaAction extends DispatchAction {

    private static final Logger log = LoggerFactory.getLogger(ConfigurarEstrategiaAction.class);
    private final Gson jsonBuilder;
    private Map parametros;

    public ConfigurarEstrategiaAction() {
        jsonBuilder = new GsonBuilder().create();
    }

    public void obtenerPlanRegistrado(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            PlanificacionService service = ProxyUtil.getPlanificacionServicePort(Config.TIMEOUT);

            Resultado resultado = createSuccessResult(service.obtenerVigente());

            generalAction(resultado, response);
        } catch (Exception e) {
            log.error("Error al obtener plan", e);
            generalAction(createErrorResult(e), response);
        }
    }

    public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("configurarEstrategia");
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
            ActionUtil.enviarJson(json, response);
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
