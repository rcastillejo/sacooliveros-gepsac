/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Marco
 */
public interface AbstractAction {
    
    ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);
    void inicializarBusqueda(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);
    void busqueda(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);
    void inicializarAgregar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);
    void inicializarEditar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);
    void agregar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);
    void editar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);
    void eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);
    
}