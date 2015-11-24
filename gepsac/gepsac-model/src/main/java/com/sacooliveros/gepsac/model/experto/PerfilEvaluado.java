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

    private Perfil perfil;
    private double probabilidad;
    private boolean seleccionado;

    public PerfilEvaluado(Perfil perfil, double probabilidad) {
        this.perfil = perfil;
        this.probabilidad = probabilidad;
    }

    public PerfilEvaluado() {
    }

    public void setPerfil(String codigoPerfil) {
        perfil = new Perfil();
        perfil.setCodigo(codigoPerfil);
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

    public void seleccionar() {
        seleccionado = Boolean.TRUE;
    }

}
