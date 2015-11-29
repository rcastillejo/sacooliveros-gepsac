/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.experto;

import com.sacooliveros.gepsac.model.comun.Perfil;

/**
 *
 * @author Ricardo
 */
public class PerfilEvaluado {
    private String codigoEvaluacion;
    private int indice;
    private Perfil perfil;
    private double probabilidad;
    private boolean seleccionado;

    public PerfilEvaluado(Perfil perfil, double probabilidad) {
        this.perfil = perfil;
        this.probabilidad = probabilidad;
    }

    public PerfilEvaluado() {
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getCodigoEvaluacion() {
        return codigoEvaluacion;
    }

    public void setCodigoEvaluacion(String codigoEvaluacion) {
        this.codigoEvaluacion = codigoEvaluacion;
    }

    public void setPerfil(String codigoPerfil) {
        if(codigoPerfil != null && !codigoPerfil.isEmpty()){
            perfil = new Perfil();
            perfil.setCodigo(codigoPerfil);
        }
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public double getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(double probabilidad) {
        this.probabilidad = probabilidad;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public void seleccionar() {
        seleccionado = Boolean.TRUE;
    }

    @Override
    public String toString() {
        return "PerfilEvaluado{" + "perfil=" + perfil + ", probabilidad=" + probabilidad + ", seleccionado=" + seleccionado + '}';
    }    

}
