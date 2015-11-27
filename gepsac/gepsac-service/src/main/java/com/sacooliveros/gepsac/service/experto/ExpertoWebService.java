/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.experto;

import com.sacooliveros.gepsac.model.experto.Alumno;
import com.sacooliveros.gepsac.model.experto.EvaluacionPostulante;
import com.sacooliveros.gepsac.service.exception.ServiceException;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author Ricardo
 */
@WebService(serviceName = "ExpertoService")
public class ExpertoWebService extends ExpertoService {

    @Override
    @WebMethod(operationName = "evaluarAlumno")
    public EvaluacionPostulante evaluarAlumno(Alumno alumno) throws ServiceException {
        return super.evaluarAlumno(alumno);
    }

}
