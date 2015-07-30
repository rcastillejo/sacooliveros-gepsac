/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.controller.module;

import com.sacooliveros.gepsac.controller.module.exception.ConrollerModuleException;
import com.sacooliveros.gepsac.dao.DAOFactory;
import com.sacooliveros.gepsac.dao.PlanDAO;
import com.sacooliveros.gepsac.model.Plan;
import com.sacooliveros.gepsac.model.util.Estado;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class PlanificacionController {

    interface Mensaje {

        String REGISTRAR = "La configuración fue satisfactoria [{0}]";
        String PROGRAMAR = "La programación fue satisfactoria [{0}]";

    }

    interface Error {

        interface Codigo {

            String GENERAL = "EPE099";
        }

        interface Mensaje {

            String GENERAL = "No se pudo realizar la  operación [{0}]";
            String LISTAR = "No se encuentra planes";
            String REGISTRAR = "Error al registrar el plan";
            String PROGRAMAR = "Error al programar el plan";
            String CONSULTAR_VIGENTE = "No se encontro un plan vigente";
        }
    }

    public List<Plan> listar() {
        List<Plan> listado;
        try {
            PlanDAO planDao = DAOFactory.getDAOFactory().getPlanEstrategicoDAO();
            listado = planDao.listar();
        } catch (Exception e) {
            throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.GENERAL, e);
        }

        if (listado == null || listado.isEmpty()) {
            throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.LISTAR);
        }
        return listado;
    }

    public Plan obtenerPlanVigente() {
        Plan planVigente;
        try {
            PlanDAO planDao = DAOFactory.getDAOFactory().getPlanEstrategicoDAO();
            planVigente = planDao.obtenerVigente(Calendar.getInstance().get(Calendar.YEAR));
        } catch (Exception e) {
            throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.GENERAL, e);
        }

        if (planVigente == null) {
            throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.CONSULTAR_VIGENTE);
        }

        return planVigente;
    }

    public String registrar(Plan plan) {
        try {
            PlanDAO planDao = DAOFactory.getDAOFactory().getPlanEstrategicoDAO();

            plan.setEstado(Estado.PlanEstrategico.REGISTRADO);
            plan.setFecRegistro(new Date());

            planDao.ingresar(plan);
            return MessageFormat.format(Mensaje.REGISTRAR, plan.getCodigo());
        } catch (Exception e) {
            throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.REGISTRAR, e);
        }
    }

    public String programar(Plan plan) {
        try {
            Programacion programacion = new Programacion();
            PlanDAO planDao = DAOFactory.getDAOFactory().getPlanEstrategicoDAO();

            plan.setEstado(Estado.PlanEstrategico.PROGRAMADO);
            plan.setFecProgramacion(new Date());

            programacion.configure(plan);
            programacion.calcularFechasDisponibles();
            programacion.elaborarCronograma();

            planDao.actualizar(plan);
            return MessageFormat.format(Mensaje.PROGRAMAR, plan.getCodigo());
        } catch (Exception e) {
            throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.PROGRAMAR, e);
        }
    }

}
