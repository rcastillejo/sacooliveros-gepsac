/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service;

import com.sacooliveros.gepsac.controller.module.PlanificacionController;
import com.sacooliveros.gepsac.controller.module.exception.ConrollerModuleException;
import com.sacooliveros.gepsac.model.PlanEstrategico;
import com.sacooliveros.gepsac.service.exception.ServiceException;
import java.util.List;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
@WebService(serviceName = "PlanificacionService")
public class PlanificacionService implements IPlanificacion {

    private static final Logger log = LoggerFactory.getLogger(PlanificacionService.class);

    private final PlanificacionController controller;

    public PlanificacionService() {
        controller = new PlanificacionController();
    }

    @Override
    public List<PlanEstrategico> listar() {
        try {
            return controller.listar();
        } catch (ConrollerModuleException e) {
            log.error(e.getMessage(), e);
            throw new ServiceException(e.getCodigo(), e.getMessage());
        }
    }
    
    @Override
    public String configurar(PlanEstrategico plan) {
        try {
            return controller.configurar(plan);
        } catch (ConrollerModuleException e) {
            log.error(e.getMessage(), e);
            throw new ServiceException(e.getCodigo(), e.getMessage());
        }
    }

    @Override
    public String aperturar(PlanEstrategico plan) {
        try {
            return controller.aperturar(plan);
        } catch (ConrollerModuleException e) {
            log.error(e.getMessage(), e);
            throw new ServiceException(e.getCodigo(), e.getMessage());
        }
    }

    @Override
    public String generar(PlanEstrategico plan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
