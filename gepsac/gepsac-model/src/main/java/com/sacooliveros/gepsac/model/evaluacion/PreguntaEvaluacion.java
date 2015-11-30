/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.evaluacion;

/**
 *
 * @author Ricardo
 */
public class PreguntaEvaluacion {

    private String codigoEvaluacion;
    private Pregunta pregunta;
    private String respuesta;
    private boolean seleccionado;

    public void setPerfil(String codigoPregunta) {
        if (codigoPregunta != null && !codigoPregunta.isEmpty()) {
            pregunta = new Pregunta();
            pregunta.setCodigo(codigoPregunta);
        }
    }

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

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
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
        return "PreguntaEvaluacion{" + "codigoEvaluacion=" + codigoEvaluacion + ", pregunta=" + pregunta + ", respuesta=" + respuesta + ", seleccionado=" + seleccionado + '}';
    }

}
