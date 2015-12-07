/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.model.util;

/**
 *
 * @author Ricardo
 */
public interface State {

    interface PlanEstrategico {

        String REGISTRADO = "PLA0001";
        String CONFIGURADO = "PLA0002";
        String PROGRAMADO = "PLA0003";
    }

    interface EvaluacionPostulante {

        String REGISTRADO = "EVP0001";
    }

    interface EvaluacionAcosoEscolar {

        String REGISTRADO = "EVA0001";
        String EVALUADO = "EVA0002";
    }

    interface Alumno {

        String REGISTRADO = "ALU0001";
        String EVALUADO = "ALU0002";
    }
}
