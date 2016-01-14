/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.rs.filter;

import java.io.IOException;
import java.util.Map.Entry;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ricardo
 */
@Provider
public class LoggingFilter implements
        ContainerRequestFilter,
        ContainerResponseFilter {

    private final static Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        log(requestContext);
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {

        log(responseContext);
    }

    void log(ContainerRequestContext requestContext) {
        UriInfo uriInfo = requestContext.getUriInfo();
        String method = requestContext.getMethod();

        log.debug(">> {} {}", new Object[]{method, uriInfo.getRequestUri()});

        for (Entry e : requestContext.getHeaders().entrySet()) {
            log.debug(">> {}: {}", new Object[]{e.getKey(), e.getValue()});
        }
    }

    void log(ContainerResponseContext responseContext) {
        Response.StatusType status = responseContext.getStatusInfo();

        log.debug("<< Status: {} {}", new Object[]{status.getStatusCode(), status.getReasonPhrase()});

        for (Entry e : responseContext.getHeaders().entrySet()) {
            log.debug("<< {}: {}", new Object[]{e.getKey(), e.getValue()});
        }
    }

}
