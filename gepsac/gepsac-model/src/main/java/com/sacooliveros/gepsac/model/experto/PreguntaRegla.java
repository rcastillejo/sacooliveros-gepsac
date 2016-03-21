/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.experto;

import com.sacooliveros.gepsac.model.evaluacion.*;

/**
 *
 * @author Ricardo
 */
public class PreguntaRegla {

    private String codigoRegla;
    private Pregunta pregunta;

    public String getCodigoRegla() {
        return codigoRegla;
    }

    public void setCodigoRegla(String codigoRegla) {
        this.codigoRegla = codigoRegla;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }
    
    public String getCodigoPregunta(){
        return this.pregunta == null ? null : this.pregunta.getCodigo();
    }

    @Override
    public String toString() {
        return "PreguntaRegla{" + "codigoRegla=" + codigoRegla + ", pregunta=" + pregunta + '}';
    }

    
}
