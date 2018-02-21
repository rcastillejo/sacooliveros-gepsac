/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.evaluacion;

import java.util.List;

/**
 *
 * @author Ricardo
 */
public class PreguntaEvaluacion {

    private String codigoEvaluacion;
    private Pregunta pregunta;
    private List<PreguntaEvaluacionAlternativa> alternativas;

    public String getCodigoEvaluacion() {
        return codigoEvaluacion;
    }

    public void setCodigoEvaluacion(String codigoEvaluacion) {
        this.codigoEvaluacion = codigoEvaluacion;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public List<PreguntaEvaluacionAlternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<PreguntaEvaluacionAlternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public PreguntaEvaluacionAlternativa getAternativaSeleccionada() {
        if (alternativas != null) {
            for (PreguntaEvaluacionAlternativa alternativa : alternativas) {
                if (alternativa.isSeleccionado()) {
                    return alternativa;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "PreguntaEvaluacion{" + "codigoEvaluacion=" + codigoEvaluacion + ", pregunta=" + pregunta + ", alternativas=" + alternativas + '}';
    }

}
