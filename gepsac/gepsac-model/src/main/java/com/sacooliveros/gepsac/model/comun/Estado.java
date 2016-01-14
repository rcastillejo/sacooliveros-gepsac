/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.comun;

import com.sacooliveros.gepsac.model.util.State;
import java.io.Serializable;

/**
 *
 * @author Ricardo
 */
public class Estado implements State, Serializable {

    private String codigo;
    private String nombre;
    private String descripcion;

    public Estado() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Estado{" + "codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }

}
