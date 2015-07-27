/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.security.form;

import com.novatronic.sca.model.UsuarioDesbloqueoBusqueda;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 *
 * @author Marco
 * Clase que contiene los componentes y atributos de la interfaz UsuarioDesbloqueo.jsp
 */
public class UsuarioDesbloqueoForm extends ValidatorForm implements Serializable{
    
    private UsuarioDesbloqueoBusqueda usuarioDesbloqueoBusqueda;
//    private List<DataAdapter> empresas;
    private String[] bean;
    
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return super.validate(mapping, request);
    }

    public UsuarioDesbloqueoForm() {
        usuarioDesbloqueoBusqueda = new UsuarioDesbloqueoBusqueda();
    }

    

    public UsuarioDesbloqueoBusqueda getUsuarioDesbloqueoBusqueda() {
        return usuarioDesbloqueoBusqueda;
    }

    public void setUsuarioDesbloqueoBusqueda(UsuarioDesbloqueoBusqueda usuarioDesbloqueoBusqueda) {
        this.usuarioDesbloqueoBusqueda = usuarioDesbloqueoBusqueda;
    }

    public String[] getBean() {
        return bean;
    }

    public void setBean(String[] bean) {
        String[] set = bean;
        this.bean = set;
    }
}
