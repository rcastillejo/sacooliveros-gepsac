/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto;

import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;

/**
 *
 * @author Ricardo
 */
public interface Experto {

    EvaluacionPostulante evaluarAlumno(Alumno alumno);
}
