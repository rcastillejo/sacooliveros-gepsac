/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sacooliveros.gepsac.service.rs;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Ricardo
 */
public class ApplicationConfig extends Application{
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.sacooliveros.gepsac.service.rs.resource.ExpertoRestService.class); 
        resources.add(com.sacooliveros.gepsac.service.rs.filter.ResponseCorsFilter.class);
        resources.add(com.sacooliveros.gepsac.service.rs.filter.LoggingFilter.class);
        resources.add(com.sacooliveros.gepsac.service.rs.interceptor.LoggingInterceptor.class);
    }
    
}
