/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.evaluacion;

import com.sacooliveros.gepsac.model.comun.Model;
import com.sacooliveros.gepsac.model.experto.Alumno;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class SolicitudPsicologica extends Model {

    private Date fechaSolicitud;
    private Date fechaAtencion;
    private String solicitante;
    private String motivo; 
    private String descripcion; 
    private Alumno alumno; 
    private List<PreguntaEvaluacion> preguntas;
    
    /*public void setPerfil(String codigoPerfil) {
        if(codigoPerfil != null && !codigoPerfil.isEmpty()){
            perfil = new Perfil();
            perfil.setCodigo(codigoPerfil);
        }else{
            perfil = null;
        }
    }*/

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @Override
    public String toString() {
        return "SolicitudPsicologica{" + "fechaSolicitud=" + fechaSolicitud + ", alumno=" + alumno + ", solicitante=" + solicitante + '}';
    }

}
