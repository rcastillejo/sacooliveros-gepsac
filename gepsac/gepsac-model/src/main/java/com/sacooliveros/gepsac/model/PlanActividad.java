/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model;

import java.util.Date;

/**
 *
 * @author Ricardo
 */
public class PlanActividad extends EstrategiaActividad {

    private Date fechaProgramada;
    private Date fechaEjecutada;
    private int meta;
    private boolean programado;

    public void setProgramado(boolean programado) {
        this.programado = programado;
    }

    public Date getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(Date fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public Date getFechaEjecutada() {
        return fechaEjecutada;
    }

    public void setFechaEjecutada(Date fechaEjecutada) {
        this.fechaEjecutada = fechaEjecutada;
    }

    public int getMeta() {
        return meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
    }

    public boolean isProgramado() {
        return programado;
    }

    @Override
    public String toString() {
        return "PlanActividad{" + "fechaProgramada=" + fechaProgramada + ", fechaEjecutada=" + fechaEjecutada + ", meta=" + meta + ", programado=" + programado + '}';
    }

}
