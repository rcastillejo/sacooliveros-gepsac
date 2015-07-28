/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sacooliveros.gepsac.dao;

import com.sacooliveros.gepsac.model.PlanEstrategico;

/**
 *
 * @author rcastillejo
 */
public interface PlanEstrategicoDAO extends BaseDao<PlanEstrategico>{ 
    PlanEstrategico obtener(String id);
    PlanEstrategico obtenerAnio(int anio);
    /*
    Map buscarComoReporteInicio();
    Map listar(Map fecha);
    Map buscarInicio(Map params);
    Map buscarComboInicio(Map params);
    Map buscar(Map params);
    Map obtener(Map id);
    Map ingresar(Map model);
    Map actualizar(Map model);
    Map eliminar(Map model);*/
    
    
}
