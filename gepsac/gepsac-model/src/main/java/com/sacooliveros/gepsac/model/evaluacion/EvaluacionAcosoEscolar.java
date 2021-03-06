/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.evaluacion;

import com.sacooliveros.gepsac.model.comun.Model;
import com.sacooliveros.gepsac.model.comun.Perfil;
import com.sacooliveros.gepsac.model.experto.Alumno;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class EvaluacionAcosoEscolar extends Model {

    private String codigoPlantilla;
    private String codigoSolicitud;
    private Date fechaRegistro;
    private Date fechaResuelto;
    private Date fechaEvaluacion;
    private Alumno alumno;
    private List<PreguntaEvaluacion> preguntas;
    private Perfil perfil;

    public String getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(String codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }
    

    public String getCodigoPlantilla() {
        return codigoPlantilla;
    }

    public void setCodigoPlantilla(String codigoPlantilla) {
        this.codigoPlantilla = codigoPlantilla;
    }

    
    
    public Date getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(Date fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }
    
    public void setCodigoPerfil(String codigoPerfil) {
        if(codigoPerfil != null && !codigoPerfil.isEmpty()){
            perfil = new Perfil();
            perfil.setCodigo(codigoPerfil);
        }else{
            perfil = null;
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

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaResuelto() {
        return fechaResuelto;
    }

    public void setFechaResuelto(Date fechaResuelto) {
        this.fechaResuelto = fechaResuelto;
    }
    


    @Override
    public String toString() {
        return "EvaluacionAcosoEscolar{" + "fechaEvaluacion=" + fechaEvaluacion + ", preguntas=" + preguntas + ", perfil=" + perfil + '}';
    }

}
