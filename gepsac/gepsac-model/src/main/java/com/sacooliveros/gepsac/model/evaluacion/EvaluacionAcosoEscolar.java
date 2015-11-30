/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.evaluacion;

import com.sacooliveros.gepsac.model.comun.Model;
import com.sacooliveros.gepsac.model.comun.Perfil;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class EvaluacionAcosoEscolar extends Model {

    private Date fechaEvaluacion;
    private List<PreguntaEvaluacion> preguntas;
    private Perfil perfil;

    public Date getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(Date fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
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

    public List<PreguntaEvaluacion> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaEvaluacion> preguntas) {
        this.preguntas = preguntas;
    }

    @Override
    public String toString() {
        return "EvaluacionAcosoEscolar{" + "fechaEvaluacion=" + fechaEvaluacion + ", preguntas=" + preguntas + ", perfil=" + perfil + '}';
    }

}
