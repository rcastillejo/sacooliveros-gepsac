/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.evaluador.message;

import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.service.experto.se.Engine;

/**
 *
 * @author Ricardo
 */
public class Mensaje {

    private String id;
    private Engine engine;
    private EvaluacionAcosoEscolar evaluacion;
    private String response;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EvaluacionAcosoEscolar getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(EvaluacionAcosoEscolar evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "EvaluadorModel{" + "id=" + id + ", evaluaciones=" + evaluacion + ", response=" + response + '}';
    }

}
