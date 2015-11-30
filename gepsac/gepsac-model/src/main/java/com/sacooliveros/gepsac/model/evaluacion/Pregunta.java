/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.evaluacion;

import com.sacooliveros.gepsac.model.comun.Model;

/**
 *
 * @author Ricardo
 */
public class Pregunta extends Model{
    
    private String alias;
    private String enunciado;
    private String tipo;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Pregunta{" + "alias=" + alias + ", enunciado=" + enunciado + ", tipo=" + tipo + '}';
    }
 
}
