package com.novatronic.sca.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.novatronic.pscabas.core.implementacion.SeguridadCore;
//import com.novatronic.pscabas.core.exception.UtilSCA;
//import com.novatronic.pscabas.core.model.DataAdapter;
import static com.novatronic.sca.action.OlvideClaveAction.logger;
import com.novatronic.sca.security.form.OlvideClaveForm;
import com.novatronic.sca.util.Constantes;

public class OlvideClaveAction extends DispatchAction {

    public static Logger logger = Logger.getLogger(CambioClaveAction.class);
    private SeguridadCore seguridadCore;

    /**
     *
     * @param mapping Instancia utilizada para seleccionar una determinada
     * acción que viaja a travez del Action
     * @param form Objeto que representa el formulario (archivo
     * HorarioBuscar.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     * @return ActionForward indica el formulario donde se mostrarán los
     * resultados de la peticion del cliente
     */
    public ActionForward olvideClave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        logger.info("#### redirecionando a olvide Clave");
        OlvideClaveForm claveForm = (OlvideClaveForm) form;

            //System.out.println(
        //List<DataAdapter> bean = ActionUtil.getBean(BeanEnum.TIPODOC);
        // claveForm.setTipoDocumentos(bean);
        claveForm.setUsuario("");
        claveForm.setTipoDocumento("-1");
        claveForm.setNumeroDocumento("");
        claveForm.setCorreo("");

        return mapping.findForward(Constantes.OPCION_LOGIN_OLVIDE_CLAVE);
    }

    /**
     *
     * @param mapping Instancia utilizada para seleccionar una determinada
     * acción que viaja a travez del Action
     * @param form Objeto que representa el formulario (archivo OlvideClave.jsp)
     * @param request Objeto que encapsula los datos de petición del cliente
     * @param response Objeto que encapsula los datos de respuesta hacia cliente
     * @return ActionForward indica el formulario donde se mostrarán los
     * resultados de la peticion del cliente
     * @throws CoreSPException
     */
   

}
