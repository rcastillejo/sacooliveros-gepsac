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
public interface Estado {
    
    interface PlanEstrategico{
        int CONFIGURADO = 11;
        int APERTURADO = 12;
        int PROGRAMADO = 13;
    }
}