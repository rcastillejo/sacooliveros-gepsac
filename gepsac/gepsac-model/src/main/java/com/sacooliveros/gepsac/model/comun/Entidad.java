/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.comun;

/**
 *
 * @author Ricardo
 */
public class Entidad {

    private int codigo;
    private String nombre;

    public Entidad() {
    }

    public Entidad(String nombre) {
        this.nombre = nombre;
    }

    public Entidad(int codigo) {
        this.codigo = codigo;
    }

    public Entidad(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Entidad{" + "codigo=" + codigo + ", nombre=" + nombre + '}';
    }

}
