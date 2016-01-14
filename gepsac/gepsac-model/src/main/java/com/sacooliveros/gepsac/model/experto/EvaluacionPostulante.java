/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.experto;

import com.sacooliveros.gepsac.model.comun.Model;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class EvaluacionPostulante extends Model {
    private Date fechaEvaluacion;
    private Alumno alumno;
    private List<PerfilEvaluado> perfiles;

    public EvaluacionPostulante() {
    }

    public Date getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(Date fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
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

    @Override
    public String toString() {
        return "Model{" + super.toString() + "}, EvaluacionPostulante{" + "alumno=" + alumno + ", perfiles=" + perfiles + '}';
    }

}
