/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto.rna.instance;

import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;

/**
 *
 * @author Ricardo
 */
public class InstanciaFactory {
    
    public static Instancia create() {
        Instancia instancia;
        try {
            instancia = new AlumnoInstancia();
            return instancia;
        } catch (Exception e) {
            throw new ExpertoServiceException("Error al crear la instancia", e);
        }
    }
}
