/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model;

/**
 *
 * @author Ricardo
 */
public class Actividad extends Documento {

    private String nombre;
    private String descripcion;
    private int duracion;
    private String tipoFrecuencia;
    private int sesiones;
    private double frecuencia;

    public Actividad() {
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

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getTipoFrecuencia() {
        return tipoFrecuencia;
    }

    public void setTipoFrecuencia(String tipoFrecuencia) {
        this.tipoFrecuencia = tipoFrecuencia;
    }

    public int getSesiones() {
        return sesiones;
    }

    public void setSesiones(int sesiones) {
        this.sesiones = sesiones;
    }

    public double getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(double frecuencia) {
        this.frecuencia = frecuencia;
    }

}
