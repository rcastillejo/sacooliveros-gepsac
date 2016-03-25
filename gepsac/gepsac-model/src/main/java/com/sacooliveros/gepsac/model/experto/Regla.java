/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.experto;

import com.sacooliveros.gepsac.model.comun.Model;
import com.sacooliveros.gepsac.model.comun.Perfil;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author Ricardo
 */
public class Regla extends Model {

    private boolean deshabilitado;
    private Perfil perfil;
    private List<PreguntaRegla> preguntas;

    public boolean isDeshabilitado() {
        return deshabilitado;
    }

    public void setDeshabilitado(boolean deshabilitado) {
        this.deshabilitado = deshabilitado;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<PreguntaRegla> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaRegla> preguntas) {
        this.preguntas = preguntas;
    }

    public TreeSet<String> getSetCodigoPreguntas() {
        TreeSet<String> codigoPreguntas = null;
        if (preguntas != null) {
            codigoPreguntas = new TreeSet<>();
            for (PreguntaRegla pregunta : preguntas) {
                codigoPreguntas.add(pregunta.getCodigoPregunta());
            }
        }
        return codigoPreguntas;
    }

}
