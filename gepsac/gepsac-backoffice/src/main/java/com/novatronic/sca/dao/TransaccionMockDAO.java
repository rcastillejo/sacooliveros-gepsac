/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.dao;

import com.novatronic.sca.model.Transaccion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rcastillejo
 */
public class TransaccionMockDAO implements TransaccionDAO {

    public TransaccionMockDAO() {
    }

    private Map createList() {
        
        Map models = new LinkedHashMap();
        Map model;

        for (int i = 0; i < 10; i++) {
            model = create();
            models.put(i, model);
        }

        return models;
    }

    private Map create() {
        Map model = new HashMap();
        model.put("CodigoTransaccion","123456");
        model.put("FechaTransaccion","20141204");
        model.put("Monto","123");
        return model;
    }

    @Override
    public Map listar() {
        return createList();
    }

    @Override
    public Map buscar(Map filtro) {
        String prcode = (String) filtro.get("filtro");
        String fechaTransaccion = (String) filtro.get("fectransaccion");
        Map list = createList();
        
        return list;
    }

    @Override
    public Map obtener(Map id) {
        return id;
    }

    @Override
    public Map ingresar(Map model) {
        ///model.setTrace(0);
        return model;
    }

    @Override
    public Map actualizar(Map model) {
        return model;
    }

    @Override
    public Map eliminar(Map model) {
        return model;
    }

    @Override
    public Map listar(Map fecha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map buscarInicio(Map params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map buscarComboInicio(Map params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map buscarComoReporteInicio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
