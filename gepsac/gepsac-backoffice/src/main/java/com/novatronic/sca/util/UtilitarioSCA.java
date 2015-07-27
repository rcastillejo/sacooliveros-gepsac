/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import com.novatronic.components.aas.AAService;
import com.novatronic.components.aas.AAServiceFactory;
import com.novatronic.estandares.helper.ResourceHelper;

/**
 *
 * @author cruiz
 */
public class UtilitarioSCA {

    public static final String CODIGO_PRIMER_LOGIN = "1001";
    public static final String CODIGO_CLAVE_APUNTO_CADUCAR = "1002";
    public static final String CODIGO_USUARIO_Y_CLAVE_INCORRECTA = "1003";
    public static final String CODIGO_CUENTA_FUE_BLOQUEADA_X_EXCEDER_INTENTOS_FALLIDOS = "1004";
    public static final String CODIGO_CUENTA_BLOQUEADA_POR_SEGURIDAD = "1005";
    public static final String CODIGO_CLAVE_CADUCADA = "1006";
    public static final String CODIGO_ESCTRUCTURA_CLAVE_INSEGURA = "1007";
    public static final String CODIGO_LONGITUD_CLAVE_FUERA_DEL_RANGO_PERMITIDO = "1008";
    public static final String CODIGO_NO_UTILIZAR_CLAVE_ANTIGUA = "1009";
    public static final String CODIGO_LONGITUD_USUARIO_FUERA_DEL_RANGO_PERMITIDO = "1010";
    public static final String CODIGO_DATOS_INGRESADOR_INCORRECTOS = "1011";
    public static final String CODIGO_INGRESE_DATOS_REQUERIDOS = "1012";
    public static final String CODIGO_HORA_ACCESO_SISTEMA_RESTRINGIDO = "1013";
    public static final String CODIGO_DATOS_TABLA_HORARIO_FUERON_MANIPULADOS = "1014";
    public static final String CODIGO_DATOS_TABLA_USUARIO_FUERON_MANIPULADOS = "1015";
    public static final String CODIGO_ACCESO_RESTRINGIDO_AL_SISTEMA = "1016";
    public static final String CODIGO_APP_INGRESADA_INCORRECTA = "1017";
    public static final String CODIGO_ESCTRUCTURA_CLAVE_INSEGURA_INGRESE_CARACTERES_NO_CONSECUTIVOS = "1018";
    public static final String CODIGO_ESCTRUCTURA_CLAVE_INSEGURA_INGRESE_CARACTERES_DISTINTOS = "1019";
    public static final String CODIGO_ERROR_CONEXION_HSM = "1020";
    public static final String CODIGO_CUENTA_INHABILITADA = "1021";
    public static final String CODIGO_ERROR_SCA = "1111";
    public static final String CODIGOS_ERROR_TRAMA_RPTA = "9999";
    public static final String ATRIBUITOS = "ATRIBUITOS";
    public static final String RECURSOS = "RECURSOS";
    public static final String PERFILES = "PERFILES";
    public static final String TOKEN = "TOKEN";
    public static final String UBIGEO_DEP = "DEP";
    public static final String UBIGEO_PROV = "PROV";
    public static final String UBIGEO_DIST = "DIST";

    public static AAServiceFactory factory;
    public static AAService service;
    public static Map<String, Object> parameters;
    public static String token = "";
    private static Properties aasProp;

    public static void crearFactory() {
        factory = AAServiceFactory.fromProperties(aasProp);
        service = factory.getAAServiceInstance();
    }

    public static void iniciaServicio() {
        service.startup();
    }

    public static String generarToken(String app, String user, String password) {
        //Agregando los parametros a usar en la generacion de token
        parameters = new HashMap<String, Object>();
        parameters.put(AAService.APPLICATION_PARAM_KEY, app);
        parameters.put(AAService.PASSWORD_PARAM_KEY, password);
        parameters.put(AAService.USER_PARAM_KEY, user);
        token = service.generateToken(parameters);

        return token;
    }

    public static void cargaPropertiesSCA() {
        aasProp = ResourceHelper.findAsProperties("aas.properties");
    }

    /**
     * @return the aasProp
     */
    public static Properties getAasProp() {
        return aasProp;
    }

    /**
     * @param aAasProp the aasProp to set
     */
    public static void setAasProp(Properties aAasProp) {
        aasProp = aAasProp;
    }

}
