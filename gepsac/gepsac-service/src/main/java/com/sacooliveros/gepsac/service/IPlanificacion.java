/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service;

import com.sacooliveros.gepsac.model.Plan;
import com.sacooliveros.gepsac.service.exception.ServiceException;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Ricardo
 */
public interface IPlanificacion {
    
    @WebMethod(operationName = "configurar")
    List<Plan> listar() throws ServiceException;
    
    @WebMethod(operationName = "configurar")
    String configurar(@WebParam(name = "plan")Plan plan) throws ServiceException;
    
    @WebMethod(operationName = "aperturar")
    String aperturar(@WebParam(name = "plan")Plan plan) throws ServiceException;
    
    @WebMethod(operationName = "generar")
    String generar(@WebParam(name = "plan")Plan plan) throws ServiceException;
    
}
