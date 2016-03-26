/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.experto;

import com.sacooliveros.gepsac.model.evaluacion.*;

/**
 *
 * @author Ricardo
 */
public class PreguntaEvaluacionRegla {

    private String codigoRegla;
    private PreguntaEvaluacionAlternativa preguntaAlternativa;

    public String getCodigoRegla() {
        return codigoRegla;
    }

    public void setCodigoRegla(String codigoRegla) {
        this.codigoRegla = codigoRegla;
    }

    public PreguntaEvaluacionAlternativa getPreguntaAlternativa() {
        return preguntaAlternativa;
    }

    public void setPreguntaAlternativa(PreguntaEvaluacionAlternativa preguntaAlternativa) {
        this.preguntaAlternativa = preguntaAlternativa;
    }

    @Override
    public String toString() {
        return "PreguntaEvaluacionRegla{" + "codigoRegla=" + codigoRegla + ", preguntaAlternativa=" + preguntaAlternativa + '}';
    }

}
