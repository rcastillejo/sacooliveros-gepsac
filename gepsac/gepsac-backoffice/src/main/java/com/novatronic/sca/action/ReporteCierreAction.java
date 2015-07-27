/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.novatronic.pscabas.annotation.LogPerformance;
import com.novatronic.pscabas.core.model.EstadoUsuario;
//import com.novatronic.pscabas.core.model.ReporteCierre;
import com.novatronic.pscabas.core.model.Usuario;
//import com.novatronic.pscabas.core.model.DataAdapter;
//import com.novatronic.pscabas.core.exception.UtilSCA;
import com.novatronic.sca.json.UsuarioJsonStrategy;
import com.novatronic.sca.security.form.ReporteCierreForm;
import com.novatronic.sca.util.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author mamartinez
 */
public class ReporteCierreAction extends GenericAction<ReporteCierreForm> {

    public static Logger logger = org.apache.log4j.Logger.getLogger(ReporteCierreAction.class);
    private Gson jsonBuilder;
    private Gson jsonBuilderMin;
    //private UtilSCA utilSCA;

    public ReporteCierreAction() {
        jsonBuilder = new GsonBuilder().setExclusionStrategies(new UsuarioJsonStrategy()).create();
        jsonBuilderMin = new GsonBuilder().create();
        //utilSCA = new UtilSCA();
    }

//    public ReporteCierreAction(UsuarioSP usuarioSP) {
//        jsonBuilder = new GsonBuilder().setExclusionStrategies(new UsuarioJsonStrategy()).create();
//        jsonBuilderMin = new GsonBuilder().create();
//        // utilSCA = new UtilSCA();
//    }
    /**
     *
     * @param mapping Instancia utilizada para seleccionar una determinada
     * acción que viaja a travez del Action
     * @param form Objeto que representa el formulario (archivo
     * UsuarioBuscar.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     * @return ActionForward indica el formulario donde se mostrarán los
     * resultados de la peticion del cliente
     * @throws CoreSPException
     */
    @Override
    public ActionForward initImpl(ActionMapping mapping, ReporteCierreForm form, HttpServletRequest request, HttpServletResponse response) {
        logger.info("UsuarioAction - Metodo: initImpl");

        ActionUtil.setAtributoSuperUsuario(request);
        return mapping.findForward(Constantes.OPCION_USUARIO);
    }

    /**
     *
     * @param mapping Instancia utilizada para seleccionar una determinada
     * acción que viaja a travez del Action
     * @param form Objeto que representa el formulario (archivo
     * UsuarioBuscar.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     * @return Resultado Objeto que contiene los mensajes de exito o error luego
     * de ejecutada alguna petición por el lado del cliente
     * @throws CoreSPException
     */
    @Override
    public Resultado inicializarBusquedaImpl(ActionMapping mapping, ReporteCierreForm form, HttpServletRequest request, HttpServletResponse response) {
        logger.info("UsuarioAction - Metodo: inicializarBusquedaImpl");
        int code = Constantes.CODE_FAILED;
        String json = null;

        return new Resultado(json, code);
    }

    /**
     *
     * @param mapping Instancia utilizada para seleccionar una determinada
     * acción que viaja a travez del Action
     * @param form Objeto que representa el formulario (archivo
     * UsuarioBuscar.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     * @return Resultado Objeto que contiene los mensajes de exito o error luego
     * de ejecutada alguna petición por el lado del cliente
     * @throws CoreSPException
     */
    @Override
    public Resultado busquedaImpl(ActionMapping mapping, ReporteCierreForm form, HttpServletRequest request, HttpServletResponse response) {
        logger.info("UsuarioAction - Metodo: busquedaImpl");
        int code = Constantes.CODE_FAILED;
        String json = null;

        code = Constantes.CODE_OK;
        return new Resultado(json, code);
    }

    /**
     *
     * @param mapping Instancia utilizada para seleccionar una determinada
     * acción que viaja a travez del Action
     * @param form Objeto que representa el formulario (archivo
     * UsuarioBuscar.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     * @return Resultado Objeto que contiene los mensajes de exito o error luego
     * de ejecutada alguna petición por el lado del cliente
     * @throws CoreSPException
     */
    @Override
    public Resultado inicializarAgregarImpl(ActionMapping mapping, ReporteCierreForm form, HttpServletRequest request, HttpServletResponse response) {
        logger.info("UsuarioAction - Metodo: inicializarAgregarImpl");
        Usuario tmp = new Usuario();
        tmp.setEstado(String.valueOf(EstadoUsuario.CREADO.ordinal()));
        return new Resultado(null, Constantes.CODE_OK);
    }

    /**
     *
     * @param mapping Instancia utilizada para seleccionar una determinada
     * acción que viaja a travez del Action
     * @param form Objeto que representa el formulario (archivo
     * UsuarioBuscar.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     * @return Resultado Objeto que contiene los mensajes de exito o error luego
     * de ejecutada alguna petición por el lado del cliente
     * @throws CoreSPException
     */
    @Override
    public Resultado inicializarEditarImpl(ActionMapping mapping, ReporteCierreForm form, HttpServletRequest request, HttpServletResponse response) {
        logger.info("UsuarioAction - Metodo: inicializarEditarImpl");
        int code = Constantes.CODE_FAILED;
        String json = null;
        return new Resultado(json, code);
    }

    private void enviarCorreoNuevoUsuario(Usuario usu, HttpServletRequest request) {
        logger.info("UsuarioAction - Metodo: enviarCorreoNuevoUsuario");
        try {
            Map<String, Object> mapTemplate = new HashMap<String, Object>();
            mapTemplate.put("nombre", usu.getNombre());
            mapTemplate.put("apellidos", usu.getApellidoPaterno());
            mapTemplate.put("usuario", usu.getUsuario());
            mapTemplate.put("contrasenia", usu.getNoCriptoContra());
            mapTemplate.put(Constantes.NAME_PARAMETER_TEMPLATE_MAP, Constantes.TEMPLATE_NUEVO_USUARIO);
            String mensaje = com.novatronic.pscabas.core.util.EmailUtil.getMessage(mapTemplate);
            com.novatronic.pscabas.core.util.EmailUtil.enviarCorreoUsuario(usu.getCorreo(), mensaje, Constantes.ASUNTO_NUEVO_USUARIO);
        } catch (Exception e) {
            logger.error("Ex* enviarCorreoNuevoUsuario : " + e.getMessage());
        }
    }

    /**
     *
     * @param mapping Instancia utilizada para seleccionar una determinada
     * acción que viaja a travez del Action
     * @param form Objeto que representa el formulario (archivo
     * UsuarioBuscar.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     * @return Resultado Objeto que contiene los mensajes de exito o error luego
     * de ejecutada alguna petición por el lado del cliente
     * @throws CoreSPException
     */
    @Override
    public Resultado agregarImpl(ActionMapping mapping, ReporteCierreForm form, HttpServletRequest request, HttpServletResponse response) {
        logger.info("UsuarioAction - Metodo: agregarImpl");
        int code = Constantes.CODE_FAILED;
        String json = null;

        //utilSCA.IDEMPRESA = -1;
        return new Resultado(json, code);
    }

    /**
     *
     * @param mapping Instancia utilizada para seleccionar una determinada
     * acción que viaja a travez del Action
     * @param form Objeto que representa el formulario (archivo
     * UsuarioBuscar.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     * @return Resultado Objeto que contiene los mensajes de exito o error luego
     * de ejecutada alguna petición por el lado del cliente
     * @throws CoreSPException
     */
    @Override
    public Resultado editarImpl(ActionMapping mapping, ReporteCierreForm form, HttpServletRequest request, HttpServletResponse response) {
        logger.info("UsuarioAction - Metodo: editarImpl");
        int code = Constantes.CODE_FAILED;
        String json = null;

        return new Resultado(json, code);
    }

    /**
     *
     * @param mapping Instancia utilizada para seleccionar una determinada
     * acción que viaja a travez del Action
     * @param form Objeto que representa el formulario (archivo
     * UsuarioBuscar.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     * @return Resultado Objeto que contiene los mensajes de exito o error luego
     * de ejecutada alguna petición por el lado del cliente
     * @throws CoreSPException
     */
    @Override
    public Resultado eliminarImpl(ActionMapping mapping, ReporteCierreForm form, HttpServletRequest request, HttpServletResponse response) {
        logger.info("UsuarioAction - Metodo: eliminarImpl");
        int code = Constantes.CODE_FAILED;
        String mensaje = Constantes.EMPTY;

        return new Resultado(null, code, mensaje);
    }

    /**
     *
     * @param mapping Instancia utilizada para seleccionar una determinada
     * acción que viaja a travez del Action
     * @param form Objeto que representa el formulario (archivo
     * UsuarioBuscar.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     *
     */
    @LogPerformance
    public void restablecerPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
//        logger.info("UsuarioAction - Metodo: restablecerPassword");
//        int codigo = Constantes.CODE_FAILED;
//        String json = Constantes.EMPTY;
//        try {
//            UsuarioForm usuarioForm = (UsuarioForm) form;
//            String bean = usuarioForm.getBean();
//            if (bean != null) {
//                Long idUsuario = Long.parseLong(bean);
//               // List<Usuario> lista = usuarioSP.consultarUsuario(idUsuario, null);
//                if (lista != null && !lista.isEmpty()) {
//                    Usuario usuario = lista.get(0);
//                    //String pass = utilSCA.clave();
//                    //  usuario.setNoCriptoContra(pass);
//                    //usuario.setContrasena(utilSCA.cifrarContrasena(pass, usuario.getUsuario()));
//                    usuario.setEstado(EstadoUsuario.RESETEADO.toString());
//                    //usuario.setMac(utilSCA.obtenerMac(utilSCA.armarDatosUsuarioMAC(usuario), usuario.getUsuario()));
//                    usuario.setAudUsuModif(ActionUtil.obtenerUsuarioAuditor(request));
//                //    String email = usuarioSP.cambiarPassword(usuario);
//                    if (email != null) {
//                        codigo = Constantes.CODE_OK;
//                        usuario.setEstado(EstadoUsuario.RESETEADO.toString());
//                        enviarCorreoRestrablecer(usuario, request);
//                    }
//                    json = jsonBuilderMin.toJson(usuario, Usuario.class);
//                }
//            }
//            generalAction(new Resultado(json, codigo), response);
//        } catch (NumberFormatException ex) {
//            logger.error("EX* NumberFormatException : " + ex.getMessage());
//        } catch (Exception ex) {
//            logger.error("ex error:" + ex.getMessage());
//        }
    }

    @LogPerformance
    private void enviarCorreoRestrablecer(Usuario usu, HttpServletRequest request) {
        logger.info("UsuarioAction - Metodo: enviarCorreoRestrablecer");
        try {
            Map<String, Object> mapTemplate = new HashMap<String, Object>();
            mapTemplate.put("usuario", usu.getUsuario());
            mapTemplate.put("contrasenia", usu.getNoCriptoContra());
            mapTemplate.put(Constantes.NAME_PARAMETER_TEMPLATE_MAP, Constantes.TEMPLATE_RESETEO_USUARIO);
            String mensaje = com.novatronic.pscabas.core.util.EmailUtil.getMessage(mapTemplate);
            com.novatronic.pscabas.core.util.EmailUtil.enviarCorreoUsuario(usu.getCorreo(), mensaje, Constantes.ASUNTO_RESETEO_USUARIO);
        } catch (Exception e) {
            logger.error("Ex* enviarCorreoNuevoUsuario : " + e.getMessage());
        }
    }

    /**
     *
     * @param mapping Instancia utilizada para seleccionar una determinada
     * acción que viaja a travez del Action
     * @param form Objeto que representa el formulario (archivo
     * UsuarioBuscar.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     *
     */
    public void guardarRol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        logger.info("UsuarioAction - Metodo: guardarRol");
//        int codigo = Constantes.CODE_FAILED;
//        try {
//            UsuarioForm usuarioForm = (UsuarioForm) form;
//            String appRol = usuarioForm.getAppRol();
//            String bean = usuarioForm.getBean();
//            if (StringUtils.isNotBlank(bean) && StringUtils.isNotBlank(appRol)) {
//                String rolAppUser = StringUtils.join(usuarioForm.getRoles(), Constantes.COMA);
//                Long idUsuario = Long.parseLong(bean);
//                Long idAplicacion = Long.parseLong(appRol);
//                RolSP rolSP = new RolSP();
//                if (rolSP.registrarRolesUsuarioApp(rolAppUser, idAplicacion, idUsuario)) {
//                    codigo = Constantes.CODE_OK;
//                    usuarioForm.setRoles(null);
//                }
//            }
//            generalAction(new Resultado(Constantes.EMPTY, codigo), response);
//        } catch (NumberFormatException ex) {
//            logger.error("EX* NumberFormatException : " + ex.getMessage());
//        } catch (Exception ex) {
//            logger.error("ex error : " + ex.getMessage());
//        }
    }

    @Override
    public Resultado inicializarComboImpl(ActionMapping mapping, ReporteCierreForm form, HttpServletRequest request, HttpServletResponse response) {
        logger.info("UsuarioAction - Metodo: inicializarComboImpl");
        int code = Constantes.CODE_FAILED;
        String json = null;

        return new Resultado(json, code);
    }

    @Override
    public Resultado inicializarComboReporteImpl(ActionMapping mapping, ReporteCierreForm form, HttpServletRequest request, HttpServletResponse response) {
        logger.info("UsuarioAction - Metodo: inicializarComboReporteImpl");
        int code = Constantes.CODE_FAILED;
        String json = null;

        return new Resultado(json, code);    }
}

/**
 *
 * @param mapping Instancia utilizada para seleccionar una determinada acción
 * que viaja a travez del Action
 * @param form Objeto que representa el formulario (archivo UsuarioBuscar.jsp)
 * @param request Objeto que encapsula los datos de petición del cliente
 * @param response Objeto que encapsula los datos de respuesta hacia cliente
 *
 */
/**
 *
 * @param mapping Instancia utilizada para seleccionar una determinada acción
 * que viaja a travez del Action
 * @param form Objeto que representa el formulario (archivo UsuarioBuscar.jsp)
 * @param request Objeto que encapsula los datos de petición del cliente
 * @param response Objeto que encapsula los datos de respuesta hacia cliente
 *
 */
