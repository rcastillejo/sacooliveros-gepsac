/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service;

import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.service.exception.ServiceException;
import com.sacooliveros.gepsac.service.experto.ExpertoService;
import com.sacooliveros.gepsac.service.experto.Experto;
import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;

/**
 *
 * @author Ricardo
 */
@WebService(serviceName = "BOService")
public class BOService {

    @WebMethod(operationName = "evaluarAlumno")
    public EvaluacionPostulante evaluarAlumno(@WebParam(name = "evaluacionPostulante") EvaluacionPostulante evaluacion) {
        try {
            Experto service = new ExpertoService();
            return service.evaluarAlumno(evaluacion);
        } catch (ExpertoServiceException e) {
            throw new ServiceException(e.getCode(), e.getMessage(), e);
        }
    }

}
