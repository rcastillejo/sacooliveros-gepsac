/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.controller.planificacion;

import com.sacooliveros.gepsac.controller.exception.ConrollerModuleException;
import com.sacooliveros.gepsac.dao.PlanDAO;
import com.sacooliveros.gepsac.dao.SingletonDAOFactory;
import com.sacooliveros.gepsac.model.Plan;
import com.sacooliveros.gepsac.model.PlanActividad;
import com.sacooliveros.gepsac.model.PlanEstrategia;
import com.sacooliveros.gepsac.model.PlanIndicador;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sacooliveros.gepsac.model.util.State;

/**
 *
 * @author Ricardo
 */
public class PlanificacionController {

    private static final Logger log = LoggerFactory.getLogger(PlanificacionController.class);

    interface Mensaje {

        String REGISTRAR = "El registro fue satisfactorio [{0}]";
        String CONFIGURAR = "Los cambios se grabaron con éxito [{0}]";
        String PROGRAMAR = "La programación fue satisfactoria [{0}]";

    }

    interface Error {

        interface Codigo {

            String GENERAL = "ERR099";
        }

        interface Mensaje {

            String GENERAL = "No se pudo realizar la  operación [{0}]";
            String LISTAR = "No se encuentra planes";
            String REGISTRAR = "Error al registrar el plan";
            String CONFIGURAR = "Error al configurar el plan";
            String PROGRAMAR = "Error al programar el plan";
            String CONSULTAR_VIGENTE = "No se encontro un plan vigente";
            String CONSULTAR_PARA_CONFIGURAR = "Plan no disponible para configurar";
            String CONSULTAR_CONFIGURADO = "Error al consultar plan configurado";
            String CONSULTAR_PARA_PROGRAMAR = "Plan no disponible para realizar la programacion";
            String CONSULTAR_PROGRAMADO = "Error al consultar plan programado";
        }
    }

    public List<Plan> listar() {
        List<Plan> listado;
        try {
            PlanDAO planDao = SingletonDAOFactory.getDAOFactory().getPlanEstrategicoDAO();
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
            PlanDAO planDao = SingletonDAOFactory.getDAOFactory().getPlanEstrategicoDAO();
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
            PlanDAO planDao = SingletonDAOFactory.getDAOFactory().getPlanEstrategicoDAO();

            plan.setCodigoEstado(State.PlanEstrategico.REGISTRADO);
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
            PlanDAO planDao = SingletonDAOFactory.getDAOFactory().getPlanEstrategicoDAO();

            planAConfigurar = planDao.obtener(plan.getCodigo());

            planAConfigurar.setEstrategiasSeleccionadas(plan.getEstrategiasSeleccionadas());
            planAConfigurar.setCodigoEstado(State.PlanEstrategico.CONFIGURADO);
            planAConfigurar.setFecConfiguracion(new Date());

            planDao.actualizar(planAConfigurar);

            try {
                planDao.deleteIndicador(plan.getCodigo());
            } catch (Exception e) {
                log.warn("Error al eliminar indicador", e);
            }
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

                    for (PlanIndicador indicador : actividad.getIndicadoresSeleccionados()) {
                        indicador.setCodigoPlan(actividad.getCodigoPlan());
                        indicador.setCodigoEstrategia(actividad.getCodigoEstrategia());
                        indicador.setCodigoActividad(actividad.getActividad().getCodigo());
                        indicador.setEstado(planAConfigurar.getEstado());

                        planDao.insertIndicador(indicador);
                    }
                }

            }

            return MessageFormat.format(Mensaje.CONFIGURAR, plan.getCodigo());
        } catch (Exception e) {
            log.error("Error al grabar la configuración", e);
            throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.CONFIGURAR, e);
        }
    }

    public Plan generarProgramacion(Plan plan) {
        Programacion programacion = new Programacion();
        try {
            PlanDAO planDao = SingletonDAOFactory.getDAOFactory().getPlanEstrategicoDAO();
            Plan planAProgramar = planDao.obtener(plan.getCodigo());
            planAProgramar.setEstrategiasSeleccionadas(plan.getEstrategiasSeleccionadas());

            programacion.configure(planAProgramar);
            programacion.calcularFechasDisponibles();
            programacion.elaborarCronograma();

            return plan;

        } catch (Exception e) {
            log.error("Error al generar la programación", e);
            throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.PROGRAMAR, e);
        }
    }
    /*
     public String programar(Plan plan) {
     Plan planAProgramar;
     try {
     PlanDAO planDao = SingletonDAOFactory.getDAOFactory().getPlanEstrategicoDAO();

     planAProgramar = planDao.obtener(plan.getCodigo());

     planAProgramar.setEstrategiasSeleccionadas(plan.getEstrategiasSeleccionadas());
     planAProgramar.setCodigoEstado(State.PlanEstrategico.PROGRAMADO);
     planAProgramar.setFecConfiguracion(new Date());

     planDao.actualizar(planAProgramar);

     for (PlanEstrategia estrategia : planAProgramar.getEstrategiasSeleccionadas()) {

     estrategia.setCodigoPlan(planAProgramar.getCodigo());

     planDao.updateEstrategia(estrategia);

     for (PlanActividad actividad : estrategia.getActividadesSeleccionadas()) {
     actividad.setCodigoPlan(estrategia.getCodigoPlan());
     actividad.setCodigoEstrategia(estrategia.getCodigo());
     actividad.setCodigoEstado(planAProgramar.getEstado());

     planDao.updateActividad(actividad);

     for (PlanIndicador indicador : actividad.getIndicadoresSeleccionados()) {
     indicador.setCodigoPlan(actividad.getCodigoPlan());
     indicador.setCodigoEstrategia(actividad.getCodigoEstrategia());
     indicador.setCodigoActividad(actividad.getActividad().getCodigo());
     indicador.setCodigoEstado(planAProgramar.getEstado());

     planDao.updateIndicador(indicador);
     }
     }

     }

     return MessageFormat.format(Mensaje.PROGRAMAR, plan.getCodigo());
     } catch (Exception e) {
     throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.PROGRAMAR, e);
     }
        
     }
     */

    public Plan obtenerConfigurarPlan() {
        Plan planVigente;

        planVigente = obtenerPlanVigente();

        if (planVigente.getEstado().getCodigo().equals(State.PlanEstrategico.CONFIGURADO)) {

            try {

                PlanDAO planDao = SingletonDAOFactory.getDAOFactory().getPlanEstrategicoDAO();

                List<PlanEstrategia> estrategiasSeleccionadas = planDao.listarPlanEstrategia(planVigente.getCodigo());

                for (PlanEstrategia estrategia : estrategiasSeleccionadas) {
                    List<PlanActividad> actividadesSeleccionadas = planDao.listarPlanActividad(planVigente.getCodigo(), estrategia.getCodigo());
                    estrategia.setActividadesSeleccionadas(actividadesSeleccionadas);

                    for (PlanActividad actividad : actividadesSeleccionadas) {
                        List<PlanIndicador> indicadoresSeleccionados = planDao.listarPlanIndicador(
                                planVigente.getCodigo(), estrategia.getCodigo(), actividad.getActividad().getCodigo());
                        actividad.setIndicadoresSeleccionados(indicadoresSeleccionados);
                    }

                }

                planVigente.setEstrategiasSeleccionadas(estrategiasSeleccionadas);
                return planVigente;
            } catch (Exception e) {
                throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.CONSULTAR_CONFIGURADO, e);
            }
        } else if (planVigente.getEstado().getCodigo().equals(State.PlanEstrategico.REGISTRADO)) {
            return planVigente;
        } else {
            throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.CONSULTAR_PARA_CONFIGURAR);
        }

    }

    public Plan obtenerProgramarPlan() {
        Plan planVigente;

        planVigente = obtenerPlanVigente();

        /*if (planVigente.getEstado().getCodigo().equals(State.PlanEstrategico.PROGRAMADO)) {

         try {

         PlanDAO planDao = SingletonDAOFactory.getDAOFactory().getPlanEstrategicoDAO();

         List<PlanEstrategia> estrategiasSeleccionadas = planDao.listarPlanEstrategia(planVigente.getCodigo());

         for (PlanEstrategia estrategia : estrategiasSeleccionadas) {
         List<PlanActividad> actividadesSeleccionadas = planDao.listarPlanActividad(planVigente.getCodigo(), estrategia.getCodigo());
         estrategia.setActividadesSeleccionadas(actividadesSeleccionadas);
                    
         for (PlanActividad actividad : actividadesSeleccionadas) {
         List<PlanIndicador> indicadoresSeleccionados = planDao.listarPlanIndicador(
         planVigente.getCodigo(), estrategia.getCodigo(), actividad.getActividad().getCodigo());
         actividad.setIndicadoresSeleccionados(indicadoresSeleccionados);
         }
                    
         }

         planVigente.setEstrategiasSeleccionadas(estrategiasSeleccionadas);
         return planVigente;
         } catch (Exception e) {
         throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.CONSULTAR_CONFIGURADO, e);
         }/*if (planVigente.getEstado().getCodigo().equals(Estado.PlanEstrategico.PROGRAMADO)) {

         try {

         PlanDAO planDao = SingletonDAOFactory.getDAOFactory().getPlanEstrategicoDAO();

         List<PlanEstrategia> estrategiasSeleccionadas = planDao.listarPlanEstrategia(planVigente.getCodigo());

         for (PlanEstrategia estrategia : estrategiasSeleccionadas) {
         List<PlanActividad> actividadesSeleccionadas = planDao.listarPlanActividad(planVigente.getCodigo(), estrategia.getCodigo());
         estrategia.setActividadesSeleccionadas(actividadesSeleccionadas);
                    
         for (PlanActividad actividad : actividadesSeleccionadas) {
         List<PlanIndicador> indicadoresSeleccionados = planDao.listarPlanIndicador(
         planVigente.getCodigo(), estrategia.getCodigo(), actividad.getActividad().getCodigo());
         actividad.setIndicadoresSeleccionados(indicadoresSeleccionados);
         }

         }

         planVigente.setEstrategiasSeleccionadas(estrategiasSeleccionadas);
         return planVigente;
         } catch (Exception e) {
         throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.CONSULTAR_CONFIGURADO, e);
         }
         } else*/ if (planVigente.getEstado().getCodigo().equals(State.PlanEstrategico.CONFIGURADO)) {

            try {

                PlanDAO planDao = SingletonDAOFactory.getDAOFactory().getPlanEstrategicoDAO();

                List<PlanEstrategia> estrategiasSeleccionadas = planDao.listarPlanEstrategia(planVigente.getCodigo());

                for (PlanEstrategia estrategia : estrategiasSeleccionadas) {
                    List<PlanActividad> actividadesSeleccionadas = planDao.listarPlanActividad(planVigente.getCodigo(), estrategia.getCodigo());
                    estrategia.setActividadesSeleccionadas(actividadesSeleccionadas);

                    for (PlanActividad actividad : actividadesSeleccionadas) {
                        List<PlanIndicador> indicadoresSeleccionados = planDao.listarPlanIndicador(
                                planVigente.getCodigo(), estrategia.getCodigo(), actividad.getActividad().getCodigo());
                        actividad.setIndicadoresSeleccionados(indicadoresSeleccionados);
                    }

                }

                planVigente.setEstrategiasSeleccionadas(estrategiasSeleccionadas);
                return planVigente;
            } catch (Exception e) {
                throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.CONSULTAR_PROGRAMADO, e);
            }
        } else {
            throw new ConrollerModuleException(Error.Codigo.GENERAL, Error.Mensaje.CONSULTAR_PARA_PROGRAMAR);
        }

    }

}
