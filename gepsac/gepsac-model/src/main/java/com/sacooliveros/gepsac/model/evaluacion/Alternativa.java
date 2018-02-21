/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.evaluacion;

/**
 *
 * @author Ricardo
 */
public class Alternativa {

    private int secuencia;
    private String alternativa;

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public String getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(String alternativa) {
        this.alternativa = alternativa;
    }

    @Override
    public String toString() {
        return "Alternativa{" + "secuencia=" + secuencia + ", alternativa=" + alternativa + '}';
    }

}
