/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.rs.exception;

import com.sacooliveros.gepsac.service.experto.exception.ExpertoServiceException;
import com.sacooliveros.gepsac.service.experto.exception.ValidatorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Ricardo
 */
public class RestServiceException extends WebApplicationException{

    public RestServiceException(ValidatorException e) {
        super(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
    }
    public RestServiceException(ExpertoServiceException e) {
        super(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
    }
    
}
