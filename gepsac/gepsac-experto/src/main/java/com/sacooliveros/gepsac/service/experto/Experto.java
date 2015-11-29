/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto;

import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;

/**
 *
 * @author Ricardo
 */
public interface Experto {

    interface Mensaje {

        String REGISTRAR = "El registro fue satisfactorio [{0}]";
        String CONFIGURAR = "Los cambios se grabaron con éxito [{0}]";
        String PROGRAMAR = "La programación fue satisfactoria [{0}]";

    }

    interface Error {

        interface Codigo {

            String GENERAL = "ERR099";
        }

        interface Mensaje {

            String GENERAL = "No se pudo realizar la  evaluación [{0}]";
            String LISTAR = "No se encuentra planes";
            String REGISTRAR = "Error al evaluar alumno postulante [{0}]";
        }
    }
    
    interface Estado{
        String REGISTRADO = "EVP0001";
    }

    EvaluacionPostulante evaluarAlumno(EvaluacionPostulante evaluacionAlumno) throws ExpertoServiceException;
}
