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
import com.sacooliveros.gepsac.model.PlanActividad;
import com.sacooliveros.gepsac.model.PlanEstrategia;
import com.sacooliveros.gepsac.model.util.Estado;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
public class PlanificacionController {

    private static final Logger log = LoggerFactory.getLogger(PlanificacionController.class);

    interface Mensaje {

        String REGISTRAR = "El registro fue satisfactorio [{0}]";
        String CONFIGURAR = "La configuración fue satisfactoria [{0}]";
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
            String CONFIGURAR = "Error al configurar el plan";
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

    public String configurar(Plan plan) {
        Plan planAConfigurar;
        try {
            PlanDAO planDao = DAOFactory.getDAOFactory().getPlanEstrategicoDAO();

            planAConfigurar = planDao.obtener(plan.getCodigo());

            planAConfigurar.setEstrategiasSeleccionadas(plan.getEstrategiasSeleccionadas());
            planAConfigurar.setEstado(Estado.PlanEstrategico.CONFIGURADO);
            planAConfigurar.setFecConfiguracion(new Date());

            planDao.actualizar(planAConfigurar);

            try {
                planDao.deleteActividad(plan.getCodigo());
            } catch (Exception e) {
                log.warn("Error al eliminar actividad", e);
            }
            try {
                planDao.deleteEstrategia(plan.getCodigo());
            } catch (Exception e) {
                log.warn("Error al eliminar estrategia", e);
            }

            for (PlanEstrategia estrategia : planAConfigurar.getEstrategiasSeleccionadas()) {

                estrategia.setCodigoPlan(planAConfigurar.getCodigo());

                planDao.insertEstrategia(estrategia);

                for (PlanActividad actividad : estrategia.getActividadesSeleccionadas()) {
                    actividad.setCodigoPlan(estrategia.getCodigoPlan());
                    actividad.setCodigoEstrategia(estrategia.getCodigo());
                    actividad.setEstado(planAConfigurar.getEstado());

                    planDao.insertActividad(actividad);
                }

            }

            return MessageFormat.format(Mensaje.CONFIGURAR, plan.getCodigo());
        } catch (Exception e) {
            throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.CONFIGURAR, e);
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
