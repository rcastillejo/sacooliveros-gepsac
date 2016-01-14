/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.rs.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Ricardo
 */
public class ServiceException extends WebApplicationException {

    public ServiceException(String message) {
        super(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build());
    }

    public ServiceException(Throwable cause) {
        this(cause.getMessage());
    }

}
