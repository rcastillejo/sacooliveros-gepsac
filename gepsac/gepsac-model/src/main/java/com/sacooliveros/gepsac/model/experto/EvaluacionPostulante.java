/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.experto;

import java.util.List;

/**
 *
 * @author Ricardo
 */
public class EvaluacionPostulante {

    private Alumno alumno;
    private List<PerfilEvaluado> perfiles;

    public EvaluacionPostulante() {
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public List<PerfilEvaluado> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<PerfilEvaluado> perfiles) {
        this.perfiles = perfiles;
    }

}
