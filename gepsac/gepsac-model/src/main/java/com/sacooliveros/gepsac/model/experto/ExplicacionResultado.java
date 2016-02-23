/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.experto;

import com.sacooliveros.gepsac.model.comun.Perfil;
import com.sacooliveros.gepsac.model.evaluacion.PreguntaEvaluacion;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class ExplicacionResultado {

    private Alumno alumno;
    private Perfil perfil;
    //@TODO: Listado de Reglas de Perfiles (Regla = Perfil y Pregunta)
    private List<PreguntaEvaluacion> preguntas;

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public List<PreguntaEvaluacion> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaEvaluacion> preguntas) {
        this.preguntas = preguntas;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "ExplicacionResultado{" + "alumno=" + alumno + ", perfil=" + perfil + ", preguntas=" + preguntas + '}';
    }

}
