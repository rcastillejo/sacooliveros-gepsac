/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.service.reporte;

import com.novatronic.sca.dao.DAOFactory;
import com.novatronic.sca.dao.TransaccionDAO;
import com.novatronic.sca.model.Transaccion;
import com.novatronic.sca.service.exception.ServiceException;
import com.pe.nova.components.bd.exception.BDException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rcastillejo
 */
public class TransaccionService implements ITransaccionService {

    private final DAOFactory factory;

    public TransaccionService() throws IOException, BDException {
        factory = DAOFactory.getDAOFactory();
    }

    @Override
    public Map listar(String fecha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map buscarInicio(Map filtro) {
        TransaccionDAO tranDao = factory.getTransaccionDAO();
        //System.out.println("TRSER" + filtro);
        Map listado = tranDao.buscarInicio(filtro);

        if (listado == null || listado.isEmpty()) {
            throw new ServiceException(Messages.MENSAJE_BUSQUEDA_NO_ENCONTRADA);
        }

        return listado;
    }
    @Override
    public Map buscarComboInicio(Map filtro) {
        TransaccionDAO tranDao = factory.getTransaccionDAO();
        //System.out.println("TRSER" + filtro);
        Map listado = tranDao.buscarComboInicio(filtro);

        if (listado == null || listado.isEmpty()) {
            throw new ServiceException(Messages.MENSAJE_BUSQUEDA_NO_ENCONTRADA);
        }

        return listado;
    }
    @Override
    public Map buscarComoReporteInicio() {
        TransaccionDAO tranDao = factory.getTransaccionDAO();
        //System.out.println("TRSER" + filtro);
        Map listado = tranDao.buscarComoReporteInicio();

        if (listado == null || listado.isEmpty()) {
            throw new ServiceException(Messages.MENSAJE_BUSQUEDA_NO_ENCONTRADA);
        }

        return listado;
    }

    @Override
    public Map listar(Map lista) {
        TransaccionDAO tranDao = factory.getTransaccionDAO();
        //System.out.println("TRSER" + filtro);
        Map listado = tranDao.listar(lista);

        if (listado == null || listado.isEmpty()) {
            throw new ServiceException(Messages.MENSAJE_BUSQUEDA_NO_ENCONTRADA);
        }

        return listado;    }


    private interface Messages {

        String MENSAJE_BUSQUEDA_NO_ENCONTRADA = "No existe información que coincida con lo ingresado";
        String MENSAJE_NO_DISPONIBLE = "El Transaccion no se encuentra disponible";

        String MENSAJE_REGISTRADO = "La presupuesto se registró con éxito: {0}";
        String MENSAJE_ACTUALIZADO = "La presupuesto se actualizó con éxito: {0}";
    }

    @Override
    public Map listar() {
        TransaccionDAO tranDao = factory.getTransaccionDAO();
        System.out.println("listar Paso 1");
        Map listado = tranDao.listar();
        System.out.println("listar Paso 2");

        if (listado == null || listado.isEmpty()) {
            throw new ServiceException(Messages.MENSAJE_BUSQUEDA_NO_ENCONTRADA);
        }

        return listado;
    }

    @Override
    public Map buscar(Map filtro) {
        TransaccionDAO tranDao = factory.getTransaccionDAO();
        System.out.println("TRSER" + filtro);
        Map listado = tranDao.buscar(filtro);

        if (listado == null || listado.isEmpty()) {
            throw new ServiceException(Messages.MENSAJE_BUSQUEDA_NO_ENCONTRADA);
        }

        return listado;
    }

}
