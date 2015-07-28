/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.controller.module;

import com.sacooliveros.gepsac.controller.module.exception.ConrollerModuleException;
import com.sacooliveros.gepsac.dao.DAOFactory;
import com.sacooliveros.gepsac.dao.PlanEstrategicoDAO;
import com.sacooliveros.gepsac.model.PlanEstrategico;
import com.sacooliveros.gepsac.model.util.Estado;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class PlanificacionController {

    interface Mensaje {

        String CONFIGURAR = "La configuración fue satisfactoria [{0}]";
        String APERTURAR = "La configuración fue satisfactoria [{0}]";

    }

    interface Error {

        interface Codigo {

            String GENERAL = "EPE099";
        }

        interface Mensaje {

            String GENERAL = "No se pudo realizar la  operación [{0}]";
            String LISTAR = "No se encuentra planes estrategicos";
            String CONFIGURAR = "Error al configurar el plan";
            String APERTURAR = "Error al aperturar el plan";
            String GENERAR = "Error al generar el plan";
        }
    }

    public List<PlanEstrategico> listar() {
        List<PlanEstrategico> listado;
        try {
            PlanEstrategicoDAO planDao = DAOFactory.getDAOFactory().getPlanEstrategicoDAO();
            listado = planDao.listar();
        } catch (Exception e) {
            throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.GENERAL, e);
        }

        if (listado == null || listado.isEmpty()) {
            throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.LISTAR);
        }
        return listado;
    }

    public String configurar(PlanEstrategico plan) {
        try {
            PlanEstrategicoDAO planDao = DAOFactory.getDAOFactory().getPlanEstrategicoDAO();

            plan.setEstado(Estado.PlanEstrategico.CONFIGURADO);
            plan.setFecRegistro(new Date());

            planDao.ingresar(plan);
            return MessageFormat.format(Mensaje.CONFIGURAR, plan.getCodigo());
        } catch (Exception e) {
            throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.CONFIGURAR, e);
        }
    }

    public String aperturar(PlanEstrategico plan) {
        try {
            PlanEstrategicoDAO planDao = DAOFactory.getDAOFactory().getPlanEstrategicoDAO();

            plan.setEstado(Estado.PlanEstrategico.APERTURADO);
            plan.setFecApertura(new Date());

            planDao.actualizar(plan);
            return MessageFormat.format(Mensaje.APERTURAR, plan.getCodigo());
        } catch (Exception e) {
            throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.APERTURAR, e);
        }
    }

    public String programar(PlanEstrategico plan) {
        try {
            PlanEstrategicoDAO planDao = DAOFactory.getDAOFactory().getPlanEstrategicoDAO();

            plan.setEstado(Estado.PlanEstrategico.APERTURADO);
            plan.setFecApertura(new Date());

            planDao.actualizar(plan);
            return MessageFormat.format(Mensaje.APERTURAR, plan.getCodigo());
        } catch (Exception e) {
            throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.APERTURAR, e);
        }
    }

}
