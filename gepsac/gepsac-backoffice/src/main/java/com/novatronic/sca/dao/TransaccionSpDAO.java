/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.dao;

import com.novatronic.estandares.helper.ResourceHelper;
import com.novatronic.sca.dao.exception.DAOException;
import com.novatronic.sca.model.Transaccion;
import com.pe.nova.components.bd.BDConnection;
import com.pe.nova.components.bd.BDConnectionFactory;
import com.pe.nova.components.bd.BDStoredProgramReq;
import com.pe.nova.components.bd.exception.BDException;
import com.pe.nova.components.bd.exception.CommitTransactionException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rcastillejo
 */
public class TransaccionSpDAO implements TransaccionDAO {

    private BDConnectionFactory factory;
    private Properties pool;
    Properties conf;
    BDConnection conn;

    private BDConnection iniciarCarga() {
        //BDConnection conn = null;
        try {

            conf = ResourceHelper.findAsProperties("reporte_bcamovil.properties");
            factory = BDConnectionFactory.createConnectionFactory(conf, pool);
            conn = factory.getConnection();

        } catch (BDException ex) {
            Logger.getLogger(TransaccionSpDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    @Override
    public Map buscarInicio(Map filtro) {
        Map response = null;
        BDConnection conn = null;
        BDStoredProgramReq req = new BDStoredProgramReq();

        try {
            Iterator it = filtro.entrySet().iterator();
            Map valuesIn = new LinkedHashMap();

            conn = iniciarCarga();
            String spName = conf.getProperty(StoredProcedureNames.SP_LISTAR);
            req.setSentencia(spName);
            Iterator itw = filtro.entrySet().iterator();

            while (itw.hasNext()) {
                Map.Entry e = (Map.Entry) itw.next();
                req.setParametro((String) e.getKey(), e.getValue());

            }
            conn.iniciarTransaccion();

            response = conn.execute(req);
            conn.commitTransaccion();

        } catch (BDException ex) {
            Logger.getLogger(TransaccionSpDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        }
        return response;
    }

    @Override
    public Map buscarComboInicio(Map filtro) {
        Map response = null;
        BDConnection conn = null;
        BDStoredProgramReq req = new BDStoredProgramReq();

        try {
            Iterator it = filtro.entrySet().iterator();
            Map valuesIn = new LinkedHashMap();

            conn = iniciarCarga();
            String spName = conf.getProperty(StoredProcedureNames.SP_BUSCAR);
            req.setSentencia(spName);

            Iterator itw = filtro.entrySet().iterator();

            while (itw.hasNext()) {
                Map.Entry e = (Map.Entry) itw.next();
                System.out.println((String) e.getKey() + "----------**********----------" + e.getValue());
                req.setParametro((String) e.getKey(), e.getValue());

            }
            conn.iniciarTransaccion();

            response = conn.execute(req);
            conn.commitTransaccion();

        } catch (BDException ex) {
            Logger.getLogger(TransaccionSpDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        }
        return response;
    }

    // private final CoreStoredProcedure instancia;
//    public TransaccionSpDAO(CoreStoredProcedure instancia) {
//        this.instancia = instancia;
//    }
    // private final CoreStoredProcedure instancia;
//    public TransaccionSpDAO(CoreStoredProcedure instancia) {
//        this.instancia = instancia;
//    }
    interface StoredProcedureNames {

        String SP_LISTAR = "spp.listar";
        String SP_BUSCAR = "spp.buscar";
        String SP_OBTENER = "spp.obtener";
        String SP_INGRESAR = "spp.ingresar";
        String SP_ACTUALIZAR = "spp.actualizar";
        String SP_ELIMINAR = "spp.eliminar";
        String SP_NOMBRE_SP = "spp.nombre.reporte";
    }

    interface Parameters {

        String RESULT_LIST = "result";
    }

    private List<Transaccion> createList(List<Map> maps) {
        List<Transaccion> models = new ArrayList<Transaccion>();
        for (Map map : maps) {
            models.add(create(map));
        }
        return models;
    }

    private Transaccion create(Map map) {
        Transaccion model = new Transaccion();
        return model;
    }

    @Override
    public Map listar(Map filtro) {
        Map response1 = null;
        Map response = null;
        BDConnection conn = null;
        String key_sp_name = "CAMPO0";
        BDStoredProgramReq req = new BDStoredProgramReq();

        try {

            conn = iniciarCarga();
            String spName = conf.getProperty(StoredProcedureNames.SP_NOMBRE_SP);

            req.setSentencia(spName);
            String nombre_sp = (String) filtro.get(key_sp_name);
            System.out.println("NNNNN:" + nombre_sp + ",dddddddd:" + key_sp_name);
            req.setParametro(key_sp_name, nombre_sp);
            conn.iniciarTransaccion();
            response = conn.execute(req);

        } catch (BDException ex) {
            Logger.getLogger(TransaccionSpDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        }
        return response;
    }

    @Override
    public Map buscarComoReporteInicio() {
        Map response = null;
        BDConnection conn = null;
        //System.out.println("LLEGOOOOOOOOOOOOOOOOOOOOOOOO");
        BDStoredProgramReq req = new BDStoredProgramReq();

        try {

            conn = iniciarCarga();
            String spName = conf.getProperty(StoredProcedureNames.SP_NOMBRE_SP);
            req.setParametro("", "");
            req.setSentencia(spName);
            conn.iniciarTransaccion();
            response = conn.execute(req);

        } catch (BDException ex) {
            Logger.getLogger(TransaccionSpDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        }
        return response;
    }

    public Map listarInterno(Map filtro) {
        Map response1 = null;
        Map response = null;
        BDConnection conn = null;
        String key_sp_name = "CAMPO0";
        BDStoredProgramReq req = new BDStoredProgramReq();

        try {

            conn = iniciarCarga();
            String spName = conf.getProperty(StoredProcedureNames.SP_NOMBRE_SP);

            req.setSentencia(spName);
            String nombre_sp = (String) filtro.get(key_sp_name);
            System.out.println("NNNNN:" + nombre_sp + ",dddddddd:" + key_sp_name);

            req.setParametro(key_sp_name, nombre_sp);
            conn.iniciarTransaccion();
            response = conn.execute(req);

        } catch (BDException ex) {
            Logger.getLogger(TransaccionSpDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        }
        return response;
    }

    @Override
    public Map listar() {
        Map response = null;
        BDConnection conn = null;
        BDStoredProgramReq req = new BDStoredProgramReq();
        try {

            Date fecha = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");

            String format = sdf.format(fecha);

            Map<String, Object> valuesIn = new LinkedHashMap<String, Object>();

            valuesIn.put("CAMPO1", "tp_operacion_diario");
            valuesIn.put("CAMPO2", "999978");
            valuesIn.put("CAMPO3", "421410");
            valuesIn.put("CAMPO4", "000001");
            valuesIn.put("CAMPO5", "20141112");

            conn = iniciarCarga();
            String spName = conf.getProperty(StoredProcedureNames.SP_LISTAR);

            req.setSentencia(spName);
            Iterator it = valuesIn.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                req.setParametro((String) e.getKey(), e.getValue());

            }

            conn.iniciarTransaccion();

            response = conn.execute(req);
            conn.commitTransaccion();

        } catch (BDException ex) {
            Logger.getLogger(TransaccionSpDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        }
        return response;

    }

    @Override
    public Map buscar(Map filtro) {
        Map response1 = null;
        Map response = null;
        BDConnection conn = null;
        String key_sp_name = "CAMPO0";
        BDStoredProgramReq req = new BDStoredProgramReq();

        try {
            conn = iniciarCarga();

            response1 = listarInterno(filtro);
            String nombre_sp_general = "";
            ArrayList list = (ArrayList) response1.get("oo_cur");
            for (int i = 0; i < list.size(); i++) {
                Map rs = (Map) list.get(i);
                nombre_sp_general = (String) rs.get("nombre_sp");
                System.out.println("MAPAAAAAAAA " + rs.get("nombre_sp"));

            }

            //System.out.println("-------------------------------------------" + nombre_sp_general);
            filtro.remove(key_sp_name);

            req.setSentencia(nombre_sp_general);

            Iterator itw = filtro.entrySet().iterator();
            while (itw.hasNext()) {
                Map.Entry e = (Map.Entry) itw.next();
                //System.out.println("ENTRADA DE VALORES");
                //System.out.println("Clave > " + e.getKey() + "-- Valor > " + e.getValue());
                req.setParametro((String) e.getKey(), e.getValue());

            }
            // System.out.println("CUANTOS PARAMETROS HAYYYYYYY > " + req.contarParametros());

            conn.iniciarTransaccion();

            response = conn.execute(req);
            conn.commitTransaccion();

        } catch (BDException ex) {
            Logger.getLogger(TransaccionSpDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        }
        return response;

    }

    @Override
    public Map obtener(Map filtro) {

        Map response = null;
//        Transaccion result = null;
//        try {
//
//            response = instancia.executeStoredProcedure(StoredProcedureNames.SP_BUSCAR, null);
//            result = create(response);
//
//        } catch (CoreSPException ex) {
//            throw new DAOException(ex);
//        }
        return response;
    }

    @Override
    public Map ingresar(Map filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map actualizar(Map model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map eliminar(Map model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
