/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.experto;

import com.sacooliveros.gepsac.model.comun.Perfil;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class ExplicacionResultado {

    private Alumno alumno;
    private Perfil perfil;
    private List<String> premisas;
    private List<Regla> reglas;

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public List<String> getPremisas() {
        return premisas;
    }

    public void setPremisas(List<String> premisas) {
        this.premisas = premisas;
    }

    public List<Regla> getReglas() {
        return reglas;
    }

    public void setReglas(List<Regla> reglas) {
        this.reglas = reglas;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "ExplicacionResultado{" + "alumno=" + alumno + ", perfil=" + perfil + ", premisas=" + premisas + ", reglas=" + reglas + '}';
    }

}
