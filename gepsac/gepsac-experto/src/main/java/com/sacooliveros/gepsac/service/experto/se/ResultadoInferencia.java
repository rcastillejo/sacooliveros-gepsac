/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.se;

/**
 *
 * @author Ricardo
 */
public class ResultadoInferencia {
    private String aliasPregunta;
    private String tipo;
    private String conclusion;

    public String getNombre() {
        return aliasPregunta;
    }

    public void setNombre(String nombre) {
        this.aliasPregunta = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public boolean esConclusion(){
        return tipo != null  && tipo.equals("respuesta");
    }

    @Override
    public String toString() {
        return "ResultadoInferencia{" + "aliasPregunta=" + aliasPregunta + ", tipo=" + tipo + ", conclusion=" + conclusion + '}';
    }
    
}
