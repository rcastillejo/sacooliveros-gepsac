/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sacooliveros.gepsac.dao;

import com.sacooliveros.gepsac.model.Plan;
import com.sacooliveros.gepsac.model.PlanActividad;
import com.sacooliveros.gepsac.model.PlanEstrategia;

/**
 *
 * @author rcastillejo
 */
public interface PlanDAO extends BaseDao<Plan>{ 
    Plan obtenerVigente(int anio);
    void deleteEstrategia(String codigoPlan);
    void deleteActividad(String codigoPlan);
    void insertEstrategia(PlanEstrategia estrategia);
    void insertActividad(PlanActividad actividad);
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
