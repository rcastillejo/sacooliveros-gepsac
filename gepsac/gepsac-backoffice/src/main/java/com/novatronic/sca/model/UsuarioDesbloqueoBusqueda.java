/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.model;

import java.io.Serializable;

/**
 *
 * @author Marco
 * Clase que contienes los atributos de búsqueda de la interfaz UsuarioDesbloqueo.jsp
 */
public class UsuarioDesbloqueoBusqueda extends AbstractBusqueda implements Serializable{
    
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
