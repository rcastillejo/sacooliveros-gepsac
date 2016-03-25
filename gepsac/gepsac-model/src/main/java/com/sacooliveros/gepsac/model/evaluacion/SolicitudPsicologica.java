/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.evaluacion;

import com.sacooliveros.gepsac.model.comun.Model;
import com.sacooliveros.gepsac.model.comun.Usuario;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class SolicitudPsicologica extends Model {

    private Date fechaSolicitud;
    private Date fechaAtencion;
    private Usuario solicitante;
    private int motivo;
    private String descripcion;
    private boolean existeAlumnoDirigido;
    private boolean existeAlumnosInvolucrados;

    private List<SolicitudAlumno> alumnoInvolucrado;

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

    public Usuario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    public int getMotivo() {
        return motivo;
    }

    public void setMotivo(int motivo) {
        this.motivo = motivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<SolicitudAlumno> getAlumnoInvolucrado() {
        return alumnoInvolucrado;
    }

    public void setAlumnoInvolucrado(List<SolicitudAlumno> alumnoInvolucrado) {
        this.alumnoInvolucrado = alumnoInvolucrado;
    }

    public void determinarAlumnos() {
        for (SolicitudAlumno solicitudAlumno : alumnoInvolucrado) {
            if (solicitudAlumno.isDirigido()) {
                existeAlumnoDirigido = Boolean.TRUE;
            } else {
                existeAlumnosInvolucrados = Boolean.TRUE;
            }
        }
    }

    public boolean existeAlumnoDirigido() {
        return existeAlumnoDirigido;
    }

    public boolean existeAlumnosInvolucrados() {
        return existeAlumnosInvolucrados;
    }

    @Override
    public String toString() {
        return "SolicitudPsicologica{" + "fechaSolicitud=" + fechaSolicitud + ", fechaAtencion=" + fechaAtencion + ", solicitante=" + solicitante + ", motivo=" + motivo + ", descripcion=" + descripcion + ", alumnoInvolucrado=" + alumnoInvolucrado + '}';
    }

}
