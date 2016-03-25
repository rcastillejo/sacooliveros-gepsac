/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.evaluacion;

import com.sacooliveros.gepsac.model.experto.Alumno;

/**
 *
 * @author Ricardo
 */
public class SolicitudAlumno {

    private String codigoSolicitud;
    private Alumno alumno;
    private boolean dirigido;

    public String getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(String codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public boolean isDirigido() {
        return dirigido;
    }

    public void setDirigido(boolean dirigido) {
        this.dirigido = dirigido;
    }

    @Override
    public String toString() {
        return "SolicitudAlumno{" + "codigoSolicitud=" + codigoSolicitud + ", alumno=" + alumno + ", dirigido=" + dirigido + '}';
    }

}
