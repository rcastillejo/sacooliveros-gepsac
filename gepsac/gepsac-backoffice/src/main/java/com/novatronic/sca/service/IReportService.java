/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.service;

import java.util.Map;

/**
 *
 * @author rcastillejo
 * @param <T>
 */
public interface IReportService {

    Map listar();
    Map buscarComoReporteInicio();
    Map listar(String fecha);
    Map listar(Map lista);

    Map buscar(Map params);
    Map buscarInicio(Map params);
    Map buscarComboInicio(Map params);

}
