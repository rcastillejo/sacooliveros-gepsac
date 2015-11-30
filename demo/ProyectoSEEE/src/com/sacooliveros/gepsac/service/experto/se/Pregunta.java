/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.se;

/**
 *
 * @author Ricardo
 */
public class Pregunta {

    private String alias;
    private String enunciado;
    private String tipo;
    private String respuesta;

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

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "Pregunta{" + "alias=" + alias + ", enunciado=" + enunciado + ", tipo=" + tipo + ", respuesta=" + respuesta + '}';
    }

}
