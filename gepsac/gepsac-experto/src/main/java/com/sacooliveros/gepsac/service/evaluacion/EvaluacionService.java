/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.evaluacion;

import com.sacooliveros.gepsac.dao.SingletonDAOFactory;
import com.sacooliveros.gepsac.dao.SolicitudPsicologicaDAO;
import com.sacooliveros.gepsac.dao.exception.DAOException;
import com.sacooliveros.gepsac.model.evaluacion.SolicitudPsicologica;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
public class EvaluacionService implements Evaluacion {

    private static final Logger log = LoggerFactory.getLogger(EvaluacionService.class);

    @Override
    public List<SolicitudPsicologica> listarSolicitudPsicologica() throws ExpertoServiceException {
        try {
            SolicitudPsicologicaDAO solicitudPsicologicaDao = SingletonDAOFactory.getDAOFactory().getSolicitudPsicologicaDAO();

            List<SolicitudPsicologica> solicitudes = solicitudPsicologicaDao.listar();

            if (solicitudes == null || solicitudes.isEmpty()) {
                throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.NO_EXISTE_SOLICITUD_PSICOLOGICA);
            }
            
            log.info("Listado de evaluaciones obtenidas [estado={}, tamanio={}]", new Object[]{null, solicitudes.size()});

            return solicitudes;

        } catch (DAOException e) {
            throw new ExpertoServiceException(Error.Codigo.GENERAL, Error.Mensaje.LISTAR_EVALUACIONES_ACOSO_ESCOLAR, e);
        }
    }

    

}
