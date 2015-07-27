package com.novatronic.sca.security.action;

import com.novatronic.components.aas.exception.AASException;
import com.novatronic.pscabas.core.exception.ValidacionException;
import com.novatronic.pscabas.core.model.Usuario;
import com.novatronic.sca.security.form.CambioClaveForm;
import com.novatronic.sca.security.form.LoginForm;
import com.novatronic.sca.util.ActionUtil;
import com.novatronic.sca.util.Constantes;
import com.novatronic.sca.util.TiposUtil;
import com.novatronic.sca.util.UtilitarioSCA;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginAction extends DispatchAction {

    private Logger logger = LoggerFactory.getLogger(LoginAction.class);
    private boolean isMock;
    private String token;

    public LoginAction() {
        isMock = Boolean.FALSE;
    }

    private List<String> getRolesMock() {
        List permisos = new ArrayList();
        permisos.add("menu.Workflow");
        permisos.add("menu.Formateador");
        permisos.add("menu.ConfiguracionConector");
        permisos.add("menu.CambiarContrasena");
        return permisos;
    }

    private List<String> getPermisosMock() {
        List permisos = new ArrayList();
        permisos.add("menu.Workflow");
        permisos.add("menu.Formateador");
        permisos.add("menu.ConfiguracionConector");
        permisos.add("menu.CambiarContrasena");
        return permisos;
    }

    public ActionForward initLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        return mapping.findForward(Constantes.OPCION_VOLVER_A_LOGIN);
    }

    /**
     *
     * @param mapping Instancia utilizada para seleccionar una determinada
     * acción que viaja a travez del Action
     * @param form Objeto que representa el formulario (archivo Login.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     * @return ActionForward indica el formulario donde se mostrarán los
     * resultados de la peticion del cliente
     * @throws CoreSPException
     */
    public ActionForward postLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ValidacionException {
        //logger.info("in method postLogin");
        String redirect = Constantes.OPCION_INDEX;
        logger.debug("Login [mock={}, form={}] ...", new Object[]{isMock, form});
        if (isMock) {
            ActionUtil.setUsuarioEnSession(request, new Usuario());
           // ActionUtil.setRolEnSession(request, getRolesMock());
            //ActionUtil.setPermisoEnSession(request, getPermisosMock());
            ActionUtil.setPermisoTotalEnSession(request);
            redirect = Constantes.OPCION_INDEX;
            return mapping.findForward(redirect);
        }

        LoginForm loginForm = (LoginForm) form;
        //loginForm.

        Usuario usuario = new Usuario();

        try {
            UtilitarioSCA.cargaPropertiesSCA();
            UtilitarioSCA.crearFactory();
            UtilitarioSCA.iniciaServicio();

            //token = UtilitarioSCA.generarToken("seguridad", loginForm.getInput().getUsuario(), loginForm.getInput().getClave());
            token = UtilitarioSCA.generarToken("seguridad", loginForm.getInput().getUsuario(), loginForm.getInput().getClave());
            //logger.debug("6: " + token);
            Map<String, Object> userAttributes = UtilitarioSCA.service.getUserAttributes(token);
            logger.info("Attr[{}]" + userAttributes);

            if (token != null) {
                logger.info("Token: " + token);

                List<String> permisos = UtilitarioSCA.service.listResources(token);
                List<String> roles = UtilitarioSCA.service.listProfiles(token);
                //UtilitarioSCA.service.
                Map<String, Object> dataUser = UtilitarioSCA.service.getUserAttributes(token);
                usuario.setUsuario(loginForm.getInput().getUsuario());
                ActionUtil.setUsuarioEnSession(request, usuario);
                ActionUtil.setRolEnSession(request, roles);
                ActionUtil.setPermisoEnSession(request, permisos);
                ActionUtil.setPermisoTotalEnSession(request);
                //ActionUtil.

                redirect = Constantes.OPCION_INDEX;

            } else {

                redirect = Constantes.OPCION_VOLVER_A_LOGIN;

            }
        } catch (AASException ex) {
            logger.error(ex.getMessage());
            redirect = Constantes.OPCION_VOLVER_A_LOGIN;

            String msj = ex.getLocalizedMessage();
            request.setAttribute("mensaje", msj);

        }

        return mapping.findForward(redirect);
    }

    /**
     *
     * @param mapping Instancia utilizada para seleccionar una determinada
     * acción que viaja a travez del Action
     * @param form Objeto que representa el formulario (archivo Login.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     */
    private boolean validateLoginForm(LoginForm loginForm, ActionMapping mapping, HttpServletRequest request) {
        if (!TiposUtil.isValidString(loginForm.getInput().getUsuario()) && !TiposUtil.isValidString(loginForm.getInput().getClave())) {
            request.setAttribute("mensaje", Constantes.CODIGO_MENSAJE_2012);
            return false;
        } else if (!TiposUtil.isValidString(loginForm.getInput().getUsuario())) {
            request.setAttribute("mensaje", Constantes.CODIGO_MENSAJE_2002);
            return false;
        } else if (!TiposUtil.isValidString(loginForm.getInput().getClave())) {
            request.setAttribute("mensaje", Constantes.CODIGO_MENSAJE_2003);
            return false;
        }
        if (!TiposUtil.isValidString(loginForm.getInput().getClave()) || !TiposUtil.isValidString(loginForm.getInput().getUsuario())) {
            request.setAttribute("mensaje", Constantes.CODIGO_MENSAJE_2001);
            return false;
        }
        return true;
    }

    /**
     *
     * @param mapping Instancia utilizada para seleccionar una determinada
     * acción que viaja a travez del Action
     * @param form Objeto que representa el formulario (archivo Login.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     * @return ActionForward indica el formulario donde se mostrarán los
     * resultados de la peticion del cliente
     * @throws CoreSPException
     */
    public ActionForward cerrarCesion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        logger.info("in method cerrarCesion");
        String redirect = Constantes.OPCION_VOLVER_A_LOGIN;
        try {

            if (ActionUtil.eliminarUsuarioEnSession(request)) {
                return mapping.findForward(redirect);
            } else {
                redirect = Constantes.OPCION_INDEX;
            }
        } catch (Exception e) {
            logger.info("Error al Intenar Cerrar Sesion: " + e.getMessage());
        }
        return mapping.findForward(redirect);
    }

    /**
     *
     * @param mapping Instancia utilizada para seleccionar una determinada
     * acción que viaja a travez del Action
     * @param form Objeto que representa el formulario (archivo Login.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     * @return ActionForward indica el formulario donde se mostrarán los
     * resultados de la peticion del cliente
     * @throws CoreSPException
     */
    public ActionForward cambiarClave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        logger.info("#### vamos a cambiarClave");
        Usuario usuario = TiposUtil.getUsuarioEnSesion(request);
        CambioClaveForm claveForm = new CambioClaveForm();
        logger.info("EL USUARIO: " + usuario.getUsuario());
        return mapping.findForward(Constantes.OPCION_LOGIN_CAMBIO_CLAVE);

    }
}
