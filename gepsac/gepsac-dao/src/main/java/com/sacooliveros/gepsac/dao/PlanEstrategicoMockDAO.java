/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.dao;

import com.sacooliveros.gepsac.dao.exception.DAOException;
import com.sacooliveros.gepsac.model.PlanEstrategico;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rcastillejo
 */
public class PlanEstrategicoMockDAO implements PlanEstrategicoDAO {
    
    private final List<PlanEstrategico> planes;
    
    public PlanEstrategicoMockDAO() {
        planes = new ArrayList();
    }
  

    @Override
    public List listar() {
        return planes;
    }
     
    @Override
    public PlanEstrategico obtener(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public PlanEstrategico obtener(String id) {
        PlanEstrategico planEncontrado = null;
        for (PlanEstrategico plan : planes) {
            if(plan.getCodigo().equals(id)){
                planEncontrado = plan;
                break;
            }
        }
        return planEncontrado;
    }

    @Override
    public PlanEstrategico obtenerAnio(int anio) {
        PlanEstrategico planEncontrado = null;
        for (PlanEstrategico plan : planes) {
            if(plan.getAnio() == anio){
                planEncontrado = plan;
                break;
            }
        }
        return planEncontrado;
    }


    @Override
    public void ingresar(PlanEstrategico plan) {
        plan.setFecCre(new Date());
        boolean agregado = planes.add(plan);
        if(!agregado){
            throw new DAOException("No se pudo ingresar");
        }
    }

    @Override
    public void actualizar(PlanEstrategico plan) {
        plan.setFecMod(new Date());
        PlanEstrategico planEncontrado = obtener(plan.getCodigo());
        int index = planes.indexOf(planEncontrado);
        planes.set(index, plan);
    }

    @Override
    public void eliminar(PlanEstrategico plan) {
        PlanEstrategico planEncontrado = obtener(plan.getCodigo());
        boolean eliminado = planes.remove(planEncontrado);
        if(!eliminado){
            throw new DAOException("No se pudo eliminar");
        }
    }
 

}
