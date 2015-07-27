/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.security.form;

//import com.novatronic.pscabas.core.model.DataAdapter;
import com.novatronic.sca.model.UsuarioBloqueoBusqueda;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 *
 * @author Marco
 * Clase que contiene los componentes y atributos de la interfaz UsuarioBloqueo.jsp
 */
public class UsuarioBloqueoForm extends ValidatorForm implements Serializable{
    
    private UsuarioBloqueoBusqueda usuarioBloqueoBusqueda;
    private String[] bean;

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return super.validate(mapping, request);
    }

    public UsuarioBloqueoForm() {
        usuarioBloqueoBusqueda = new UsuarioBloqueoBusqueda();
    }

    

    

    public UsuarioBloqueoBusqueda getUsuarioBloqueoBusqueda() {
        return usuarioBloqueoBusqueda;
    }

    public void setUsuarioBloqueoBusqueda(UsuarioBloqueoBusqueda usuarioBloqueoBusqueda) {
        this.usuarioBloqueoBusqueda = usuarioBloqueoBusqueda;
    }

    public String[] getBean() {
        return bean;
    }

    public void setBean(String[] bean) {
        String[] set =  bean;
        this.bean = set;
    }
    
}
