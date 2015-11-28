/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.experto;

import com.sacooliveros.gepsac.model.comun.Model;

/**
 *
 * @author Ricardo
 */
public class Alumno extends Model {

    private String genero;
    private int edad;
    private String contextura;
    private String altura;
    private String tipoFamilia;
    private int ordenNacimiento;
    private int numHnos;
    private String nivelEscolar;
    private int gradoEscolar;
    private double promedioEscolar;
    private int nroCambioColegio;
    private String religion;
    private String nacionalidad;
    private String distrito;
    private String provincia;
    private String departamento;

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getContextura() {
        return contextura;
    }

    public void setContextura(String contextura) {
        this.contextura = contextura;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getTipoFamilia() {
        return tipoFamilia;
    }

    public void setTipoFamilia(String tipoFamilia) {
        this.tipoFamilia = tipoFamilia;
    }

    public int getOrdenNacimiento() {
        return ordenNacimiento;
    }

    public void setOrdenNacimiento(int ordenNacimiento) {
        this.ordenNacimiento = ordenNacimiento;
    }

    public int getNumHnos() {
        return numHnos;
    }

    public void setNumHnos(int numHnos) {
        this.numHnos = numHnos;
    }

    public String getNivelEscolar() {
        return nivelEscolar;
    }

    public void setNivelEscolar(String nivelEscolar) {
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

    public int getNroCambioColegio() {
        return nroCambioColegio;
    }

    public void setNroCambioColegio(int nroCambioColegio) {
        this.nroCambioColegio = nroCambioColegio;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Alumno{" + "genero=" + genero + ", edad=" + edad + ", contextura=" + contextura + ", altura=" + altura + ", tipoFamilia=" + tipoFamilia + ", ordenNacimiento=" + ordenNacimiento + ", numHnos=" + numHnos + ", nivelEscolar=" + nivelEscolar + ", gradoEscolar=" + gradoEscolar + ", promedioEscolar=" + promedioEscolar + ", nroCambioColegio=" + nroCambioColegio + ", religion=" + religion + ", nacionalidad=" + nacionalidad + ", distrito=" + distrito + ", provincia=" + provincia + ", departamento=" + departamento + '}';
    }

}
