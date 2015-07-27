/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.model;

import java.io.Serializable;

/**
 *
 * @author Marco
 * Clase que contienes los atributos de búsqueda de la interfaz UsuarioBuscar.jsp
 */
public class UsuarioBusqueda extends AbstractBusqueda implements Serializable{
    
    private String nombre;
    private String apellido;
    private String estado;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
