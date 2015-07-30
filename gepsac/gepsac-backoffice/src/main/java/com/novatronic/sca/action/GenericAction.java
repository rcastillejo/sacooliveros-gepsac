/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.novatronic.sca.util.ActionUtil;
import com.novatronic.sca.util.Constantes;
import com.novatronic.sca.util.Resultado;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author Marco
 * @param <E>
 */
public abstract class GenericAction<E> extends DispatchAction {

    public static Logger logger = Logger.getLogger(GenericAction.class);
    private final Gson jsonBuilder;

    public GenericAction() {
    	
        jsonBuilder = new GsonBuilder().create();
    }

    public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        ActionForward actionForward = null;
    	/*if(!ActionUtil.estaLogeado(request)){
    		ActionUtil.redirectionarALogin(request,response);
    	}*/
    	E action = (E) form;
        try {
            actionForward = initImpl(mapping, action, request, response);        		
        } catch (NumberFormatException e) {
            logger.error("EX* NumberFormatException : " + e.getMessage());
        } catch (Exception e) {
            logger.error("EX* Exception : " + e.getMessage());
        }
        
        return actionForward;
    }

    public void inicializarBusqueda(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            E action = (E) form;
            Resultado resultado = inicializarBusquedaImpl(mapping, action, request, response);
            generalAction(resultado, response);
        } catch (NumberFormatException e) {
            logger.error("EX* NumberFormatException : " + e.getMessage());
        } catch (Exception e) {
            logger.error("EX* Exception : " + e.getMessage());
        }
    }
    public void inicializarCombo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            E action = (E) form;
            Resultado resultado = inicializarComboImpl(mapping, action, request, response);
            generalAction(resultado, response);
        } catch (NumberFormatException e) {
            logger.error("EX* NumberFormatException : " + e.getMessage());
        } catch (Exception e) {
            logger.error("EX* Exception : " + e.getMessage());
        }
    }
    public void inicializarComboReporte(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            E action = (E) form;
            Resultado resultado = inicializarComboReporteImpl(mapping, action, request, response);
            generalAction(resultado, response);
        } catch (NumberFormatException e) {
            logger.error("EX* NumberFormatException : " + e.getMessage());
        } catch (Exception e) {
            logger.error("EX* Exception : " + e.getMessage());
        }
    }

    public void busqueda(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            E action = (E) form;
            Resultado resultado = busquedaImpl(mapping, action, request, response);
            generalAction(resultado, response);
        } catch (NumberFormatException e) {
            logger.error("EX* NumberFormatException : " + e.getMessage());
        } catch (Exception e) {
            logger.error("EX* Exception : ", e);            
        }
    }

    public void inicializarAgregar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            E action = (E) form;
            Resultado resultado = inicializarAgregarImpl(mapping, action, request, response);
            generalAction(resultado, response);
        } catch (NumberFormatException e) {
            logger.error("EX* NumberFormatException : " + e.getMessage());
        } catch (Exception e) {
            logger.error("EX* Exception : " + e.getMessage());
        }
    }

    public void inicializarEditar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            E action = (E) form;
            Resultado resultado = inicializarEditarImpl(mapping, action, request, response);
            generalAction(resultado, response);
        } catch (NumberFormatException e) {
            logger.error("EX* NumberFormatException : " + e.getMessage());
        } catch (Exception e) {
            logger.error("EX* Exception : " + e.getMessage());
        }
    }

    public void agregar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            E action = (E) form;
            Resultado resultado = null;
            ActionMessages mensajes = form.validate(mapping, request);
            HashMap<String, String> errores = getErrors(mensajes);
            if (errores != null && !errores.isEmpty()) {
                String json = jsonBuilder.toJson(errores, new TypeToken<HashMap<String, String>>() {
                }.getType());
                resultado = new Resultado(json, Constantes.CODE_FAILED);
            } else {
                resultado = agregarImpl(mapping, action, request, response);
            }
                generalAction(resultado, response);
        } catch (NumberFormatException e) {
            logger.error("EX* Num0berFormatException : " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("EX* Exception : " + e.getMessage(), e);
        }
    }

    public void editar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Resultado resultado = new Resultado(Constantes.EMPTY, Constantes.CODE_OK);
        try {
            E action = (E) form;
            ActionMessages mensajes = form.validate(mapping, request);
            HashMap<String, String> errores = getErrors(mensajes);
            if (errores != null && !errores.isEmpty()) {
                String json = jsonBuilder.toJson(errores, new TypeToken<HashMap<String, String>>() {
                }.getType());
                resultado = new Resultado(json, Constantes.CODE_FAILED);
            } else {
                resultado = editarImpl(mapping, action, request, response);
            }
        } catch (NumberFormatException e) {
            logger.error("EX* NumberFormatException : " + e.getMessage());
        } catch (Exception e) {
            logger.error("EX* Exception : " + e.getMessage());
        }
            generalAction(resultado, response);
    }

    public void eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            E action = (E) form;
            Resultado resultado = eliminarImpl(mapping, action, request, response);
            generalAction(resultado, response);
        } catch (NumberFormatException e) {
            logger.error("EX* NumberFormatException : " + e.getMessage());
        } catch (Exception e) {
            logger.error("EX* Exception : " + e.getMessage());
        }
    }

    protected void generalAction(Resultado resultado, HttpServletResponse response) {
        int code = Constantes.CODE_FAILED;
        String json = null;
        String mensaje = null;
        json = resultado.getJson();
        code = resultado.getCodigo();
        mensaje = resultado.getMensaje();
        
        if (StringUtils.isNotBlank(json)) {
            ActionUtil.enviarJson(json, response);
        }
        
        if(StringUtils.isNotBlank(mensaje)) {
            try {
        	response.getWriter().write(mensaje);
        	response.flushBuffer();                
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        response.setStatus(code);
    }

    protected HashMap<String, String> getErrors(ActionMessages mensajes) {
        HashMap<String, String> errores = new HashMap<String, String>();
        Iterator propiedades = mensajes.properties();

        while (propiedades.hasNext()) {
            String key = (String) propiedades.next();
            Iterator items = mensajes.get(key);
            String resultado = null;
            if (items.hasNext()) {
                ActionMessage item = (ActionMessage) items.next();
                resultado = item.getKey();
            }
            errores.put(key, resultado);
        }
        return errores;
    }

    public abstract Resultado eliminarImpl(ActionMapping mapping, E form, HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException;

    public abstract Resultado editarImpl(ActionMapping mapping, E form, HttpServletRequest request,
            HttpServletResponse response) throws NumberFormatException;

    public abstract Resultado agregarImpl(ActionMapping mapping, E form, HttpServletRequest request,
            HttpServletResponse response) ;

    public abstract Resultado inicializarEditarImpl(ActionMapping mapping, E form, HttpServletRequest request,
            HttpServletResponse response) ;

    public abstract Resultado inicializarAgregarImpl(ActionMapping mapping, E form, HttpServletRequest request,
            HttpServletResponse response) ;

    public abstract Resultado busquedaImpl(ActionMapping mapping, E form, HttpServletRequest request,
            HttpServletResponse response) ;

    public abstract Resultado inicializarBusquedaImpl(ActionMapping mapping, E form, HttpServletRequest request,
            HttpServletResponse response);
    
    public abstract Resultado inicializarComboImpl(ActionMapping mapping, E form, HttpServletRequest request,
            HttpServletResponse response);
    public abstract Resultado inicializarComboReporteImpl(ActionMapping mapping, E form, HttpServletRequest request,
            HttpServletResponse response);

    public abstract ActionForward initImpl(ActionMapping mapping, E form, HttpServletRequest request,
            HttpServletResponse response) ;
    
    protected Gson getJsonBuilder(){
        return jsonBuilder;
    }
}
