/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class PlanEstrategico extends Model {

    private Date fecRegistro;
    private Date fecApertura;
    private Date fecPlan;
    private Date fecInicio;
    private Date fecFin;

    private String titulo;
    private int anio;
    private String hitos;

    private List<RestriccionFecha> restriccionFechas;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Date getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(Date fecRegistro) {
        this.fecRegistro = fecRegistro;
    }

    public Date getFecApertura() {
        return fecApertura;
    }

    public void setFecApertura(Date fecApertura) {
        this.fecApertura = fecApertura;
    }

    public Date getFecPlan() {
        return fecPlan;
    }

    public void setFecPlan(Date fecPlan) {
        this.fecPlan = fecPlan;
    }

    public Date getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(Date fecInicio) {
        this.fecInicio = fecInicio;
    }

    public Date getFecFin() {
        return fecFin;
    }

    public void setFecFin(Date fecFin) {
        this.fecFin = fecFin;
    }

    public String getHitos() {
        return hitos;
    }

    public void setHitos(String hitos) {
        this.hitos = hitos;
    }

    public List<RestriccionFecha> getRestriccionFechas() {
        return restriccionFechas;
    }

    public void setRestriccionFechas(List<RestriccionFecha> restriccionFechas) {
        this.restriccionFechas = restriccionFechas;
    }


}
