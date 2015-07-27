/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novatronic.sca.xml;

import org.apache.log4j.Logger;

/**
 *
 * @author Marco
 */
public class GeneradorXML {

    public static Logger logger = Logger.getLogger(GeneradorXML.class);

//    public Document generarXML(TipoTemplate tipoTemplate, Empresa empresa, Aplicacion aplicacion) throws NumberFormatException, CoreSPException {
//        Document document = null;
//        try {
//            switch (tipoTemplate) {
//                case APLICACION:
//                    document = templateAplicacion(empresa, aplicacion);
//                    break;
//                case EMPRESA:
//                    document = templateEmpresa(empresa);
//                    break;
//            }
//        } catch (CoreSPException ex) {
//            logger.error("EX* CoreSPException : " + ex.getMessage());
//        } catch (Exception ex) {
//            logger.error("EX* Exception : " + ex.getMessage());
//        }
//        return document;
//    }

   

//    public Document templateAplicacion(Empresa empresa, Aplicacion aplicacion) throws CoreSPException {
//        logger.info("templateAplicacion");
//        return new Document(generaXMLAplicacion(empresa, aplicacion));
//    }

//    private Element generaXMLEmpresa(List<Aplicacion> listApp, List<Usuario> users, Empresa empresa) throws CoreSPException {
//        Element elementEmp = new Element("EMPRESA");
//        logger.info("generaXMLEmpresa");
//
//        if (empresa != null) {
//            elementEmp.setAttribute("nombre", empresa.getNombre());
//            elementEmp.setAttribute("mnemonico", empresa.getMnemonico());
//            elementEmp.setAttribute("cifrado", empresa.getCifrado());
//
//            for (Aplicacion aplicacion : listApp) {
//                elementEmp.addContent(generaXMLAplicacion(empresa, aplicacion));
//            }
//
//            RolSP rolSP = new RolSP();
//            List<Data> listRol = null;
//            for (Usuario usuario : users) {
//                Element elementUser = new Element("USUARIO");
//                elementUser.setAttribute("nombre", usuario.getNombre());
//                elementUser.setAttribute("usuario", usuario.getUsuario());
//                elementUser.setAttribute("apellidopaterno", usuario.getApellidoPaterno());
//                elementUser.setAttribute("apellidomaterno", usuario.getApellidoMaterno());
//                elementUser.setAttribute("telefono", usuario.getTelefono());
//                elementUser.setAttribute("tipodocumento", usuario.getTipoDocumento());
//                elementUser.setAttribute("numerodocumento", usuario.getNumeroDocumento());
//                elementUser.setAttribute("correo", usuario.getCorreo());
//                elementUser.setAttribute("usuario", usuario.getUsuario());
//                elementUser.setAttribute("contrasena", usuario.getContrasena());
//                listRol = rolSP.consultarRolesPorUsuario(empresa.getId(), usuario.getId());
//                for (Data usuarioRol : listRol) {
//                    Element elementRol = new Element("USUARIOROL");
//                    elementRol.setAttribute("nombre", usuarioRol.getNombre());
//                    elementRol.setAttribute("mnemonico", usuarioRol.getMnemonico());
//                    elementUser.addContent(elementRol);
//                }
//                elementEmp.addContent(elementUser);
//            }
//        }
//        return elementEmp;
//    }

//    private Element generaXMLAplicacion(Empresa empresa, Aplicacion aplicacion) throws CoreSPException {
//        Element elementApp = new Element("APLICACION");
//        logger.info("generaXMLAplicacion");
//
//        if (aplicacion != null) {
//            RolSP rolSP = new RolSP();
//            PermisoSP permisoSP = new PermisoSP();
//
//            elementApp.setAttribute("nombre", aplicacion.getNombre());
//            elementApp.setAttribute("mnemonico", aplicacion.getMnemonico());
//
//            List<Data> listPermiso = permisoSP.consultarDataPorAplicacion(aplicacion.getId());
//
//            if (listPermiso != null) {
//                for (Data permiso : listPermiso) {
//                    Element elementPer = new Element("PERMISO");
//                    elementPer.setAttribute("nombre", permiso.getNombre());
//                    elementPer.setAttribute("mnemonico", permiso.getMnemonico());
//                    elementApp.addContent(elementPer);
//                }
//            }
//
//            List<Data> listRol = rolSP.consultarDataPorAplicacion(aplicacion.getId());
//
//            if (!listRol.isEmpty()) {
//                for (Data rol : listRol) {
//                    Element elementRol = new Element("ROL");
//                    elementRol.setAttribute("nombre", rol.getNombre());
//                    elementRol.setAttribute("mnemonico", rol.getMnemonico());
//                    Long id = Long.parseLong(rol.getId());
//                    List<Data> listPermisoRol = permisoSP.consultarDataPermisosPorRol(id);
//                    for (Data rolPermiso : listPermisoRol) {
//                        Element elementRolPermiso = new Element("ROLPERMISO");
//                        elementRolPermiso.setAttribute("nombre", rolPermiso.getNombre());
//                        elementRolPermiso.setAttribute("mnemonico", rolPermiso.getMnemonico());
//                        elementRol.addContent(elementRolPermiso);
//                    }
//                    elementApp.addContent(elementRol);
//                }
//            }
//        }
//        return elementApp;
//    }
}
