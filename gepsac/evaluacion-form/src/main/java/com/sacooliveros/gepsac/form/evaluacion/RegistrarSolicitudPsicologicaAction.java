/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.form.evaluacion;

import com.sacooliveros.gepsac.model.comun.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author rcastillejo
 */
public class RegistrarSolicitudPsicologicaAction extends DispatchAction {

    public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("listarSolicitudPsicologica");
    }

    public ActionForward initRegistrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
        Usuario user = (Usuario) httpSession.getAttribute("session");
        if (user == null) {
            user = new Usuario("gepsac", "Jose", "Perez");
            httpSession.setAttribute("session", user);
        }

        return mapping.findForward("registrarSolicitudPsicologica");
    }
    
    public ActionForward initConsultar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
        Usuario user = (Usuario) httpSession.getAttribute("session");
        if (user == null) {
            user = new Usuario("gepsac", "Jose", "Perez");
            httpSession.setAttribute("session", user);
        }
        return mapping.findForward("consultarSolicitudPsicologica");
    }
    
    public ActionForward initEditar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);
        Usuario user = (Usuario) httpSession.getAttribute("session");
        if (user == null) {
            user = new Usuario("gepsac", "Jose", "Perez");
            httpSession.setAttribute("session", user);
        }
        return mapping.findForward("editarSolicitudPsicologica");
    }
    
    

}
