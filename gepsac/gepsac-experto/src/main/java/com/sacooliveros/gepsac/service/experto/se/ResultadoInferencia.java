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

    private String nombre;
    private String pregunta;
    private String tipo;
    private String conclusion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public boolean esConclusion() {
        return tipo != null && tipo.equals("respuesta");
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public String toString() {
        return "ResultadoInferencia{" + "nombre=" + nombre + ", pregunta=" + pregunta + ", tipo=" + tipo + ", conclusion=" + conclusion + '}';
    }

}
