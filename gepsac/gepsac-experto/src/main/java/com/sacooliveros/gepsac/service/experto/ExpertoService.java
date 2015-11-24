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
public class ExpertoService implements Experto {

    @Override
    public EvaluacionPostulante evaluarAlumno(Alumno alumno) {
        /*AlumnoDAO alumnoDao = DAOFactory.getDAOFactory().getAlumnoDAO();
        
        try {

            //Grabar Alumno a evaluar
            alumnoDao.ingresar(alumno);
            //Obtener Prediccion
            
        } catch (Exception e) {
            throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.GENERAL, e);
        }

        return planVigente;*/
        return null;
    }

}
