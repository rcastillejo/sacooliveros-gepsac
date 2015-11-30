/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto;

import com.sacooliveros.gepsac.model.evaluacion.EvaluacionAcosoEscolar;
import com.sacooliveros.gepsac.model.evaluacion.Pregunta;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.experto.se.Engine;
import com.sacooliveros.gepsac.service.experto.se.ResultadoInferencia;
import java.util.List;

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
            String LISTAR_EV_ACOSO_ESCOLAR = "No existen evaluaciones de acoso escolar [{0}]";
            String EVALUAR_RESPUESTA_ACOSO_ESCOLAR = "Error al evaluar las respuesta de acoso escolar [{0}]";
        }
    }

    interface Estado {

        String REGISTRADO = "EVP0001";
    }

    EvaluacionPostulante evaluarAlumno(EvaluacionPostulante evaluacionAlumno) throws ExpertoServiceException;

    List<EvaluacionAcosoEscolar> listarEvaluacionAcosoEscolar(com.sacooliveros.gepsac.model.comun.Estado estado) throws ExpertoServiceException;
    
    EvaluacionAcosoEscolar evaluarRespuestaAcosoEscolar(EvaluacionAcosoEscolar evaluacionAcosoEscolar, Engine<Pregunta, ResultadoInferencia> engine) throws ExpertoServiceException;
}
