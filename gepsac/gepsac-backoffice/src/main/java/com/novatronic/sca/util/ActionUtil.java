/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.novatronic.pscabas.core.model.*;

//import com.novatronic.pscabas.core.model.DataAdapter;
import com.novatronic.pscabas.core.util.HoraAdapter;
import com.novatronic.sca.model.AbstractBusqueda;

/**
 *
 * @author Marco
 */
public final class ActionUtil {

    public static Logger logger = Logger.getLogger(ActionUtil.class);
    private static final String MESSAGE_PROPERTIES = "SCAmessages";
    private static final String MENSAJE_ERROR = "'{\"'{0}'\":\"'{1}'\"}";

    private ActionUtil() {
    }

     /**
     * Método que valida si el usuario logueado es superusuario
     * @param request Objeto que encapsula los datos de petición del cliente
     */
    public static void validarSuperUsuario(HttpServletRequest request) {
        Usuario usuario = ActionUtil.obtenerUsuarioLogeado(request);
        if (!usuario.isSuperUsuario()) {
            ActionUtil.setAtributoSuperUsuario(request);
        }
    }

    public static String enviarMensaje(String campo, String mensaje) {
        return MessageFormat.format(MENSAJE_ERROR, campo, mensaje);
    }

    public static void enviarJson(String json, HttpServletResponse response)
            throws IOException {
        try {
            response.setContentType(Constantes.CONTENT_TYPE);
            response.setHeader(Constantes.HEADER_PRO_CACHE,
                    Constantes.HEADER_CACHE);
            PrintWriter out = response.getWriter();
            out.print(json);
        } catch (IOException ex) {
            throw ex;
        }
    }

    /**
     * Método que retorna un tipo de documento
     * @param option Opcion del tipo de documento
     * @return tipo Tipo de documento
     */
    public static String retornarTipoDocumento(String option) {
        int iOpt = Integer.parseInt(option);

        TipoDocumento[] lstDoc = TipoDocumento.values();
        TipoDocumento tipo = null;
        for (int i = 0; i < lstDoc.length; i++) {
            tipo = lstDoc[i];
            if (iOpt == i) {
                break;
            }
        }
        if (tipo == null) {
            throw new RuntimeException();
        }
        return tipo.toString();
    }

    public static String retornarEstadoGeneral(String option) {
        int iOpt = Integer.parseInt(option);

        Estado[] lstEstado = Estado.values();
        Estado estado = null;
        for (int i = 0; i < lstEstado.length; i++) {
            estado = lstEstado[i];
            if (iOpt == i) {
                break;
            }
        }
        if (estado == null) {
            throw new RuntimeException();
        }
        return estado.toString();
    }

     /**
     * Método que retorna un estado
     * @param option Opcion del tipo de estado
     * @return estado 
     */
    public static String retornarEstado(String option) {
        int iOpt = Integer.parseInt(option);

        EstadoUsuario[] lstEstado = EstadoUsuario.values();
        EstadoUsuario estado = null;
        for (int i = 0; i < lstEstado.length; i++) {
            estado = lstEstado[i];
            if (iOpt == i) {
                break;
            }
        }
        if (estado == null) {
            throw new RuntimeException();
        }
        return estado.toString();
    }

    /**
     * Método que retorna un tipo de permiso
     * @param option Opcion del tipo de permiso
     * @return permiso 
     */
    public static String retornarTipoPermiso(String option) {
        int iOpt = Integer.parseInt(option);

        TipoPermiso[] lstPermiso = TipoPermiso.values();
        TipoPermiso permiso = null;
        if (iOpt == -1) {
            return null;
        }
        for (int i = 0; i < lstPermiso.length; i++) {
            permiso = lstPermiso[i];
            if (iOpt == i) {
                break;
            }
        }
        if (permiso == null) {
            throw new RuntimeException();
        }
        return permiso.toString();
    }

    /**
     * Método que retorna un tipo de template
     * @param option Opcion del tipo de template
     * @return template 
     */
    public static String retornarTipoTemplate(String option) {
        int iOpt = Integer.parseInt(option);

        TipoTemplate[] lstTemplate = TipoTemplate.values();
        TipoTemplate template = null;
        for (int i = 0; i < lstTemplate.length; i++) {
            template = lstTemplate[i];
            if (iOpt == i) {
                break;
            }
        }
        if (template == null) {
            throw new RuntimeException();
        }
        return template.toString();
    }

    /**
     * Método que carga una lista de tipo de template
     * @return lista de tipos de template 
     */
  /*  private static List<DataAdapter> cargarTipoTemplate() {
        TipoTemplate[] lstTemplate = TipoTemplate.values();
        List<DataAdapter> templates = new ArrayList<DataAdapter>(lstTemplate.length);

        for (TipoTemplate template : lstTemplate) {
            templates.add(new DataAdapter(template.ordinal(), template.toString()));
        }
        return templates;
    }

    /**
     * Método que carga una lista de estados
     * @return lista de estados 
     */
 /*   private static List<DataAdapter> cargarEstados() {
        Estado[] lstEstado = Estado.values();
        List<DataAdapter> estados = new ArrayList<DataAdapter>(lstEstado.length);

        for (Estado estado : lstEstado) {
            estados.add(new DataAdapter(estado.ordinal(), estado.toString()));
        }
        return estados;
    }

    /**
     * Método que carga una lista de estados de usuario
     * @return lista de estados 
     */
   /* private static List<DataAdapter> cargarEstadosUsuarios() {
        EstadoUsuario[] lstEstado = EstadoUsuario.values();
        List<DataAdapter> estados = new ArrayList<DataAdapter>(lstEstado.length);

        for (EstadoUsuario estado : lstEstado) {
            estados.add(new DataAdapter(estado.ordinal(), estado.toString()));
        }
        return estados;
    }

    /**
     * Método que carga una lista de tipos de documento (mantenimiento de usuario)
     * @return lista de tipo de documentos 
     */
   /* private static List<DataAdapter> cargarTipoDocumentos() {
        TipoDocumento[] tipos = TipoDocumento.values();
        List<DataAdapter> tipoDocumentos = new ArrayList<DataAdapter>(
                tipos.length);
        for (com.novatronic.pscabas.core.model.TipoDocumento tipo : tipos) {
            tipoDocumentos.add(new DataAdapter(tipo.ordinal(), tipo.toString()));
        }
        return tipoDocumentos;
    }

    /*private static List<DataAdapter> cargarDataAdapter(String procedimiento) {
        List<DataAdapter> adapters = null;
        try {
            GenericoSP genericoSP = new GenericoSP();
            adapters = genericoSP.consultarAdapter(procedimiento);
        } catch (CoreSPException e) {
            logger.error("EX* CoreSPException : " + e.getMessage());
        }
        return adapters;
    }

    public static List<DataAdapter> cargarDataAdapter(String procedimiento, Integer tipo) {
        List<DataAdapter> adapters = null;
        try {
            GenericoSP genericoSP = new GenericoSP();
            adapters = genericoSP.consultarAdapter(procedimiento, tipo);
        } catch (CoreSPException e) {
            logger.error("EX* CoreSPException : " + e.getMessage());
        }
        return adapters;
    }

    /**
     * Método que carga las aplicaciones por empresa
     * @param empresa Empresa
     * @return lista de aplicaciones 
     */
 /*   public static List<DataAdapter> cargarAplicacionesPorEmpresa(String empresa) {
        List<DataAdapter> adapters = null;
        try {
            AplicacionSP aplicacionSP = new AplicacionSP();
            adapters = aplicacionSP.consultarAplicacionesPorEmpresa(empresa);
        } catch (CoreSPException e) {
            logger.error("EX* CoreSPException : " + e.getMessage());
        }
        return adapters;
    }

    /**
     * Método que carga los usuarios por empresa
     * @param empresa Empresa
     * @return lista de usuarios 
     */
 /*   public static List<DataAdapter> cargarUsuariosPorEmpresa(String empresa) {
        List<DataAdapter> adapters = null;
        try {
            UsuarioSP usuarioSP = new UsuarioSP();
            adapters = usuarioSP.cargarUsuariosPorEmpresa(empresa);
        } catch (CoreSPException e) {
            logger.error("EX* CoreSPException : " + e.getMessage());
        }
        return adapters;
    }
    
    public static List<DataAdapter> getBean(BeanEnum tipoBean) {
        List<DataAdapter> resultados = null;
        int tipoResultado = SCAConstants.TIPO_PAQUETE_DEFECTO;
        switch (tipoBean) {

            case ESTADO:
                resultados = cargarEstados();
                break;

            case ESTADO_USUARIO:
                resultados = cargarEstadosUsuarios();
                break;

            case TIPODOC:
                resultados = cargarTipoDocumentos();
                break;

            case TIPO_PERMISO:
                resultados = cargarTipoPermisos();
                break;

            case TIPO_TEMPLATE:
                resultados = cargarTipoTemplate();
                break;

            case EMPRESA:
                if (SCAConstants.DRIVER_NAME_POSTGRES.equals(SCASystemParam.getDriverName())) {
                    resultados = cargarDataAdapter(Constantes.SP_EMPRESAS_ADAPTER_POSTGRES, tipoResultado);
                    break;
                } else if (SCAConstants.DRIVER_NAME_ORACLE.equals(SCASystemParam.getDriverName())) {
                    resultados = cargarDataAdapter(Constantes.SP_EMPRESAS_ADAPTER, tipoResultado);
                    break;
                }

            case APLICACION:
                if (SCAConstants.DRIVER_NAME_POSTGRES.equals(SCASystemParam.getDriverName())) {
                    resultados = cargarDataAdapter(Constantes.SP_APLI_ADAPTER_POSTGRES, tipoResultado);
                    break;
                } else if (SCAConstants.DRIVER_NAME_ORACLE.equals(SCASystemParam.getDriverName())) {
                    resultados = cargarDataAdapter(Constantes.SP_APLI_ADAPTER, tipoResultado);
                    break;
                }

            case PERMISO:
                if (SCAConstants.DRIVER_NAME_POSTGRES.equals(SCASystemParam.getDriverName())) {
                    resultados = cargarDataAdapter(Constantes.SP_PERMISO_ADAPTER_POSTGRES);
                    break;
                } else if (SCAConstants.DRIVER_NAME_ORACLE.equals(SCASystemParam.getDriverName())) {
                    resultados = cargarDataAdapter(Constantes.SP_PERMISO_ADAPTER);
                    break;
                }

            case ROL:
                if (SCAConstants.DRIVER_NAME_POSTGRES.equals(SCASystemParam.getDriverName())) {
                    resultados = cargarDataAdapter(Constantes.SP_ROL_ADAPTER_POSTGRES);
                    break;
                } else if (SCAConstants.DRIVER_NAME_ORACLE.equals(SCASystemParam.getDriverName())) {
                    resultados = cargarDataAdapter(Constantes.SP_ROL_ADAPTER);
                    break;
                }

            case TIPO_HORARIO:
                resultados = cargarTiposHorario();
                break;

            case USUARIO:
                if (SCAConstants.DRIVER_NAME_POSTGRES.equals(SCASystemParam.getDriverName())) {
                    resultados = cargarDataAdapter(Constantes.SP_USUARIO_ADAPTER_POSTGRES);
                    break;
                } else if (SCAConstants.DRIVER_NAME_ORACLE.equals(SCASystemParam.getDriverName())) {
                    resultados = cargarDataAdapter(Constantes.SP_USUARIO_ADAPTER);
                    break;
                }

            case TIPO_CIFRADO:
                resultados = cargarTipoCifrado();
        }

        return resultados;
    }

    /**
     * Método que carga una lista de tipos de cifrado
     * @return lista de tipos de cifrado 
     */
 /*   private static List<DataAdapter> cargarTipoCifrado() {
        TipoCifrado[] tipos = TipoCifrado.values();
        List<DataAdapter> tiposCifrado = new ArrayList<DataAdapter>(tipos.length);
        for (TipoCifrado tipo : tipos) {
            tiposCifrado.add(new DataAdapter(String.valueOf(tipo.ordinal()), tipo.toString()));
        }
        return tiposCifrado;
    }

    /**
     * Método que carga una lista de tipos de horario
     * @return lista de tipos de horario 
     */
   /* private static List<DataAdapter> cargarTiposHorario() {
        TipoHorario[] tipos = TipoHorario.values();
        List<DataAdapter> tiposHorario = new ArrayList<DataAdapter>(
                tipos.length);
        for (TipoHorario tipo : tipos) {
            tiposHorario.add(new DataAdapter(tipo.ordinal(), tipo.toString()));
        }
        return tiposHorario;
    }

    /**
     * Método que carga una lista de tipos de permiso
     * @return lista de tipos de permiso 
     */
   /* private static List<DataAdapter> cargarTipoPermisos() {
        TipoPermiso[] tipos = TipoPermiso.values();
        List<DataAdapter> tipoPermisos = new ArrayList<DataAdapter>(
                tipos.length);
        for (TipoPermiso tipo : tipos) {
            tipoPermisos.add(new DataAdapter(tipo.ordinal(), tipo.toString()));
        }
        return tipoPermisos;
    }

    /**
     * Método que carga una lista de roles por aplicacion
     * @param aplicacion Aplicacion
     * @return lista de roles por aplicacion 
     */
   /* public static List<DataAdapter> cargarRolesPorAplicacion(String aplicacion) {
        List<DataAdapter> adapters = null;
        try {
            RolSP rolSP = new RolSP();
            adapters = rolSP.consultarRolesPorAplicacion(aplicacion);
        } catch (CoreSPException e) {
            logger.error("EX* CoreSPException : " + e.getMessage());
        }
        return adapters;
    }
*/
    public static String getMessage(String codigo) {
        ResourceBundle rb = ResourceBundle.getBundle(MESSAGE_PROPERTIES);
        return rb.getString(codigo);
    }

    public static String getMessage(String codigo, String pathMessage) {
        ResourceBundle rb = ResourceBundle.getBundle(pathMessage);
        return rb.getString(codigo);
    }

    public static boolean estaLogeado(HttpServletRequest request) {

        HttpSession httpSession = request.getSession(false);
        Usuario usuario = (Usuario) httpSession.getAttribute(Constantes.USUARIO_SESSION);
        if (usuario != null) {
            return true;
        }
        return false;
    }

    public static String retornarStringEmpresa(AbstractBusqueda busqueda, HttpServletRequest request) {
        return String.valueOf(retornarLongEmpresa(busqueda, request));
    }

    public static Long retornarLongEmpresa(AbstractBusqueda busqueda, HttpServletRequest request) {
        Long idEmpresa = null;
        Usuario usuario = ActionUtil.obtenerUsuarioLogeado(request);
        if (usuario.isSuperUsuario()) {
            if (busqueda.getEmpresa() != null) {
                idEmpresa = Long.parseLong(busqueda.getEmpresa());
            } else {
                idEmpresa = Constantes.BUSQUEDA_DEFAULT_LON;
            }
        } else {
            idEmpresa = usuario.getEmpresaId();
        }
        return idEmpresa;
    }

    /**
     * Método que obitene el usuario logeado
     * @param request Objeto que encapsula los datos de petición del cliente
     * @return Usuario Usuario logeado 
     */
    public static Usuario obtenerUsuarioLogeado(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        Usuario usuario = (Usuario) httpSession.getAttribute(Constantes.USUARIO_SESSION);
        if (usuario != null) {
            return usuario;
        } else {
            return null;
        }
    }

    public static boolean esUsuarioSuperUsuario(HttpServletRequest request) {
        Usuario usuario = obtenerUsuarioLogeado(request);
        boolean resultado = false;
        if (usuario != null) {
            resultado = usuario.isSuperUsuario();
        }
        return resultado;
    }

    public static void setAtributoSuperUsuario(HttpServletRequest request) {
        Usuario usuario = obtenerUsuarioLogeado(request);
        String resultado = null;
        if (usuario != null) {
            resultado = usuario.isSuperUsuario() ? Constantes.ACTIVO : null;
        }
        request.setAttribute(Constantes.USUARIO_ADMIN, resultado);
    }

    /**
     * Método que obitene el usuario logeadi
     * @param request Objeto que encapsula los datos de petición del cliente
     * @return Usuario Usuario logeado 
     */
    public static boolean eliminarUsuarioEnSession(HttpServletRequest request) {

        HttpSession httpSession = request.getSession(false);
        Usuario usuario = (Usuario) httpSession.getAttribute(Constantes.USUARIO_SESSION);
        if (usuario != null) {
            httpSession.removeAttribute(Constantes.USUARIO_SESSION);
            httpSession.invalidate();

            return true;
        } else {
            return false;
        }
    }

    public static void redirectionarALogin(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("mensaje", ActionUtil.getMessage(Constantes.CODIGO_MENSAJE_2007));
        try {
            request.getRequestDispatcher(Constantes.PATH_REDIRECT).forward(request, response);
        } catch (ServletException e) {
            logger.info("error: " + e.getMessage());
        } catch (IOException e) {
            logger.info("Error :" + e.getMessage());
        }

    }

    /**
     * Método que reotnr el tipo de horario
     * @param option Tipo de horario
     * @return Usuario Usuario logeado 
     */
    public static String retornarTipoHorario(String option) {
        int iOpt = Integer.parseInt(option);

        TipoHorario[] lstTpoHorario = TipoHorario.values();
        TipoHorario tipoHorario = null;
        for (int i = 0; i < lstTpoHorario.length; i++) {
            tipoHorario = lstTpoHorario[i];
            if (iOpt == i) {
                break;
            }
        }
        if (tipoHorario == null) {
            throw new RuntimeException();
        }
        return tipoHorario.toString();
    }

    public static List<HoraAdapter> obtenerHorarioInicio() {
        List<HoraAdapter> horarios = new ArrayList<HoraAdapter>();

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        int cont = 0;
        while (cont < 24) {
            String hora = TiposUtil.formatoSimpleFecha(cal.getTime(), Constantes.FORMATO_HORA);
            horarios.add(new HoraAdapter(String.valueOf(cont), hora));
            cont++;
            cal.add(Calendar.HOUR, 1);
        }

        return horarios;
    }
    
    public static List<HoraAdapter> obtenerHorario() {
        List<HoraAdapter> horarios = new ArrayList<HoraAdapter>();

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        int cont = 0;
        while (cont <= 24) {
            String hora = TiposUtil.formatoSimpleFecha(cal.getTime(), Constantes.FORMATO_HORA);
            horarios.add(new HoraAdapter(String.valueOf(cont), hora));
            cont++;
            cal.add(Calendar.HOUR, 1);
        }

        return horarios;
    }

    public static void setCamposAuditoriaCreacion(Auditor auditor, String usuario) {
        String fecha = DateUtil.obtenerFechaActualtoString();
        auditor.setAudFecCreac(fecha);
        auditor.setAudUsuCreac(usuario);
    }

    public static void setCamposAuditoriaModificacion(Auditor auditor, String usuario) {
        String fecha = DateUtil.obtenerFechaActualtoString();
        auditor.setAudFecModif(fecha);
        auditor.setAudUsuModif(usuario);
    }

    public static void setUsuarioEnSession(HttpServletRequest request, Usuario usuario) {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(Constantes.USUARIO_SESSION, usuario);
    }

    public static void setRolEnSession(HttpServletRequest request, List<String> rol) {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(Constantes.ROLES_POR_USUARIO_SESSION, rol);

    }

    public static void setPermisoEnSession(HttpServletRequest request, List<String> permisos) {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(Constantes.PERMISOS_POR_USUARIO_SESSION, permisos);

    }

    @SuppressWarnings("unchecked")
    public static boolean existePermisos(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        List<String> permisos = (List<String>) httpSession.getAttribute(Constantes.PERMISOS_POR_USUARIO_SESSION);
        if (permisos.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void setPermisoTotalEnSession(HttpServletRequest request) {
//        List<DataAdapter> permisosTotales = getBean(BeanEnum.PERMISO);
       // List<String> permisos = Util.copiaSoloNombrePermisoDeAdapter(permisosTotales);
        HttpSession httpSession = request.getSession();
     //   httpSession.setAttribute(Constantes.PERMISOS_GLOBAL_SESSION, permisos);

    }

    public static Empresa obtenerEmpresaUsuario(HttpServletRequest request) {
        Empresa empresa = null;
        Usuario usuario = ActionUtil.obtenerUsuarioLogeado(request);
        if (!usuario.isSuperUsuario()) {
            empresa = new Empresa(usuario.getEmpresaId(), usuario.getEmpresa());
        }

        return empresa;
    }

    public static Empresa obtenerEmpresaUsuario(Usuario usuario) {
        Empresa empresa = null;
        if (!usuario.isSuperUsuario()) {
            empresa = new Empresa(usuario.getEmpresaId(), usuario.getEmpresa());
        }
        return empresa;
    }



    public static String obtenerUsuarioAuditor(HttpServletRequest request) {
        Usuario usuario = ActionUtil.obtenerUsuarioLogeado(request);
        return String.valueOf(usuario.getUsuario());
    }
}
