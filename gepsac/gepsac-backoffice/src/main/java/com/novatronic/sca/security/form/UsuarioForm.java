/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.security.form;

import com.novatronic.pscabas.core.model.Usuario;
//import com.novatronic.pscabas.core.model.DataAdapter;
import com.novatronic.sca.model.UsuarioBusqueda;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
/**
 *
 * @author mamartinez
 * Clase que contiene los componentes y atributos de la interfaz UsuarioBuscar.jsp
 */
public class UsuarioForm extends ValidatorForm{

    private UsuarioBusqueda     usuBusqueda;
    private Usuario             usuario;
  /*  private List<DataAdapter>   empresas;
    private List<DataAdapter>   estados;
    private List<DataAdapter>   tipoDocumentos;
    private List<DataAdapter>   aplicaciones;*/
    private List<Usuario>       listaUsuario;
    private String              appRol;
    private String[]            roles;
    
    private String bean;
    
    public UsuarioForm() {
        usuario     = new Usuario();
        usuBusqueda = new UsuarioBusqueda();
    }
    
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return super.validate(mapping, request);
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

/*    public List<DataAdapter> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<DataAdapter> empresas) {
        this.empresas = empresas;
    }

    public List<DataAdapter> getEstados() {
        return estados;
    }

    public void setEstados(List<DataAdapter> estados) {
        this.estados = estados;
    }

    public List<DataAdapter> getTipoDocumentos() {
        return tipoDocumentos;
    }

    public void setTipoDocumentos(List<DataAdapter> tipoDocumentos) {
        this.tipoDocumentos = tipoDocumentos;
    }

    public List<DataAdapter> getAplicaciones() {
        return aplicaciones;
    }

    public void setAplicaciones(List<DataAdapter> aplicaciones) {
        this.aplicaciones = aplicaciones;
    }
*/
    public UsuarioBusqueda getUsuBusqueda() {
        return usuBusqueda;
    }

    public void setUsuBusqueda(UsuarioBusqueda usuBusqueda) {
        this.usuBusqueda = usuBusqueda;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String getAppRol() {
        return appRol;
    }

    public void setAppRol(String appRol) {
        this.appRol = appRol;
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        String[] set = roles;
        this.roles = set;
    }
}
