/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao;

import com.sacooliveros.gepsac.model.comun.Perfil;
import com.sacooliveros.gepsac.model.evaluacion.Pregunta;
import com.sacooliveros.gepsac.model.experto.PreguntaRegla;
import com.sacooliveros.gepsac.model.experto.Regla;
import java.util.List;

/**
 *
 * @author rcastillejo
 */
public interface ReglaDAO extends BaseDao<Regla> {

    List<Perfil> listarPerfil();

    List<Pregunta> listarPregunta();
    
    List<PreguntaRegla> listarPreguntaRegla(String codigoRegla);
    
    List<Regla> listarReglaActiva();
    /*
    void actualizarPreguntas(Regla regla);

    void registrarPreguntas(Regla regla);*/
}
