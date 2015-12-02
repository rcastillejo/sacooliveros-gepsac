/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.experto;

import com.sacooliveros.gepsac.model.comun.Entidad;
import com.sacooliveros.gepsac.model.planificacion.Participante;
import java.util.Date;

/**
 *
 * @author Ricardo
 */
public class Alumno extends Participante {

    //Datos generales
    private String direccion;
    private Date fechaNacimiento;
    private String lugarNacimiento;

    //Atributos a clasificar
    private Entidad nivelEscolar;
    private int gradoEscolar;
    private double promedioEscolar;
    private Entidad sexo;
    //private int edad;
    private Entidad contextura;
    private Entidad estatura;
    private Entidad tipoFamilia;
    private int ordenNacimiento;
    private int cantHnos;
    private int cantCambioColegio;
    private Entidad religion;
    private Entidad nacionalidad;
    private Entidad distrito;
    private Entidad provincia;
    private Entidad departamento;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public Entidad getNivelEscolar() {
        return nivelEscolar;
    }

    public void setNivelEscolar(Entidad nivelEscolar) {
        this.nivelEscolar = nivelEscolar;
    }

    public int getGradoEscolar() {
        return gradoEscolar;
    }

    public void setGradoEscolar(int gradoEscolar) {
        this.gradoEscolar = gradoEscolar;
    }

    public double getPromedioEscolar() {
        return promedioEscolar;
    }

    public void setPromedioEscolar(double promedioEscolar) {
        this.promedioEscolar = promedioEscolar;
    }

    public Entidad getSexo() {
        return sexo;
    }

    public void setSexo(Entidad sexo) {
        this.sexo = sexo;
    }

    public Entidad getContextura() {
        return contextura;
    }

    public void setContextura(Entidad contextura) {
        this.contextura = contextura;
    }

    public Entidad getEstatura() {
        return estatura;
    }

    public void setEstatura(Entidad estatura) {
        this.estatura = estatura;
    }

    public Entidad getTipoFamilia() {
        return tipoFamilia;
    }

    public void setTipoFamilia(Entidad tipoFamilia) {
        this.tipoFamilia = tipoFamilia;
    }

    public int getOrdenNacimiento() {
        return ordenNacimiento;
    }

    public void setOrdenNacimiento(int ordenNacimiento) {
        this.ordenNacimiento = ordenNacimiento;
    }

    public int getCantHnos() {
        return cantHnos;
    }

    public void setCantHnos(int cantHnos) {
        this.cantHnos = cantHnos;
    }

    public int getCantCambioColegio() {
        return cantCambioColegio;
    }

    public void setCantCambioColegio(int cantCambioColegio) {
        this.cantCambioColegio = cantCambioColegio;
    }

    public Entidad getReligion() {
        return religion;
    }

    public void setReligion(Entidad religion) {
        this.religion = religion;
    }

    public Entidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Entidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Entidad getDistrito() {
        return distrito;
    }

    public void setDistrito(Entidad distrito) {
        this.distrito = distrito;
    }

    public Entidad getProvincia() {
        return provincia;
    }

    public void setProvincia(Entidad provincia) {
        this.provincia = provincia;
    }

    public Entidad getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Entidad departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Alumno{" + "direccion=" + direccion + ", fechaNacimiento=" + fechaNacimiento + ", lugarNacimiento=" + lugarNacimiento + ", nivelEscolar=" + nivelEscolar + ", gradoEscolar=" + gradoEscolar + ", promedioEscolar=" + promedioEscolar + ", sexo=" + sexo + ", contextura=" + contextura + ", estatura=" + estatura + ", tipoFamilia=" + tipoFamilia + ", ordenNacimiento=" + ordenNacimiento + ", cantHnos=" + cantHnos + ", cantCambioColegio=" + cantCambioColegio + ", religion=" + religion + ", nacionalidad=" + nacionalidad + ", distrito=" + distrito + ", provincia=" + provincia + ", departamento=" + departamento + '}';
    }

}
