package com.novatronic.sca.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.novatronic.pscabas.core.exception.ValidacionException;
import com.novatronic.pscabas.core.implementacion.SeguridadCore;
import com.novatronic.pscabas.core.model.Usuario;
import com.novatronic.pscabas.core.util.SCAConstants;
//import com.novatronic.pscabas.core.exception.UtilSCA;
import com.novatronic.pscabas.core.ws.CambioClaveInput;
//import com.novatronic.pscabas.core.ws.CambioClaveInput;
import com.novatronic.pscabas.core.ws.CambioClaveOutput;
import static com.novatronic.sca.action.CambioClaveAction.CLAVE_INCORRECTA;
import static com.novatronic.sca.action.CambioClaveAction.DATOS_INCORRECTOS;
import static com.novatronic.sca.action.CambioClaveAction.logger;
import com.novatronic.sca.security.form.CambioClaveForm;
import com.novatronic.sca.util.ActionUtil;
import com.novatronic.sca.util.Constantes;
import com.novatronic.sca.util.TiposUtil;

public class CambioClaveAction extends DispatchAction {

    public static String DATOS_INCORRECTOS = "1003";
    public static String CLAVE_INCORRECTA = "2023";
    public static Logger logger = Logger.getLogger(CambioClaveAction.class);
    private SeguridadCore seguridadCore;

    /**
     *
     * @param mapping Instancia utilizada para seleccionar una determinada acción que viaja a travez del Action
     * @param form Objeto que representa el formulario (archivo AplicacionBuscar.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     * @return ActionForward indica el formulario donde se mostrarán los resultados de la peticion del cliente
     * @throws CoreSPException
     */
 
    /**
     *
     * @param claveForm Objeto que representa el formulario (archivo CambioClave.jsp)
     */
    private boolean validateCambioClave(CambioClaveForm claveForm) {

        if (claveForm.getNuevaClave().equals(claveForm.getConfirmarClave())) {
            return true;
        } else {
            return false;
        }
    }
}
