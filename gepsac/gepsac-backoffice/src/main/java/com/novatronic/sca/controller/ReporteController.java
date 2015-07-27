/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.controller;

import com.novatronic.sca.model.Transaccion;
import com.novatronic.sca.service.reporte.ITransaccionService;
import com.novatronic.sca.service.reporte.TransaccionService;
import com.pe.nova.components.bd.exception.BDException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rcastillejo
 */
public class ReporteController {

    private static final Logger log = LoggerFactory.getLogger(ReporteController.class);
    private final ITransaccionService transaccionService;

    public ReporteController() throws IOException, BDException {
        transaccionService = new TransaccionService();
    }

    public Map listarTransaccion() {
        Map transacciones;
        log.debug("Listar Transacciones 1");

        transacciones = transaccionService.listar();

        Iterator it = transacciones.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            //valuesIn.put((String) e.getKey(), e.getValue());
            log.debug("Resultado [{}]", e.getKey(), e.getValue());

        }
        log.debug("Resultado [{}]", transacciones);

        return transacciones;
    }

    public Map buscarTransaccion(String prcode, String fechaTransaccion, String param3) {
        Map transacciones;
        log.debug("Parametros [param1={},param2={},param3={}]",
                new Object[]{prcode, fechaTransaccion, param3});
        Map filtro = new HashMap();

        transacciones = transaccionService.buscar(filtro);

        log.debug("Resultado [{}]", transacciones);

        return transacciones;
    }

    public Map buscarTransaccion(Map filtro) {
        Map transacciones;
        //log.debug("Parametrosbuscar [{}]", new Object[]{filtro});
        System.out.println("CONTROL" + filtro);
        transacciones = transaccionService.buscar(filtro);

        log.debug("Resultado [{}]", transacciones);

        return transacciones;
    }

    public Map buscarTransaccionInicio(Map filtro) {
        Map transacciones;
        //log.debug("Parametrosbuscar [{}]", new Object[]{filtro});
        // System.out.println("CONTROL"+filtro);
        transacciones = transaccionService.buscarInicio(filtro);

        log.debug("Resultado [{}]", transacciones);

        return transacciones;
    }

    public Map buscarComboInicio(Map filtro) {
        Map transacciones;
        //log.debug("Parametrosbuscar [{}]", new Object[]{filtro});
        System.out.println("CONTROL" + filtro);
        transacciones = transaccionService.buscarComboInicio(filtro);

        log.debug("Resultado [{}]", transacciones);

        return transacciones;
    }

    public Map buscarComboReporte() {
        Map transacciones;
        log.debug("buscarComboReporte ");

        //log.debug("Parametrosbuscar [{}]", new Object[]{filtro});
        transacciones = transaccionService.buscarComoReporteInicio();

        log.debug("Resultado [{}]", transacciones);

        return transacciones;
    }

    public List<Object> buscarPagina(String param1, String param2, String param3) {

        log.debug("Parametros [param1={},param2={},param3={}]",
                new Object[]{param1, param2, param3});

        log.debug("Resultado [{}]",
                "");

        return new ArrayList<Object>();
    }
}
