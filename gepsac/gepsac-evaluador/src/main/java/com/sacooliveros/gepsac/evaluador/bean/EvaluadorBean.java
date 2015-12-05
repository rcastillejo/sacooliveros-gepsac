/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.evaluador.bean;

import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class EvaluadorBean {

    private String id;
    private List<EvaluacionAcosoEscolar> evaluaciones;
    private String response;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<EvaluacionAcosoEscolar> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<EvaluacionAcosoEscolar> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "EvaluadorModel{" + "id=" + id + ", evaluaciones=" + evaluaciones + ", response=" + response + '}';
    }

}
