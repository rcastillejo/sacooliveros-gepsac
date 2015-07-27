<%
	int timeout = session.getMaxInactiveInterval();
//	response.setHeader("Refresh", timeout + "; URL = /sca/default.jsp");
	response.setHeader("Refresh", timeout + "; URL = /moduloReportes/sessionExpired.jsp");
%>

<%@page import="com.novatronic.pscabas.core.model.Usuario"%>
<%@page import="com.novatronic.sca.util.Menu"%>
<%@page import="com.novatronic.sca.util.Constantes"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%

Usuario usuario=(Usuario)request.getSession(false).getAttribute(Constantes.USUARIO_SESSION);
if(usuario!=null){

    List<String> permisosUsuario = (List<String>) request.getSession(false).getAttribute(Constantes.PERMISOS_POR_USUARIO_SESSION);
%>
<div class="cnt-menu">
    <div class="sca-novatronic" >
        <img src="<%=request.getContextPath()%>/resources/images/novatronic.png" /> 
    </div>
    <ul id="accordion">
<!--     EMPRESA -->

        <li>
            <div>Reportes</div>
            <ul style="display: none;">
                <li>
                    <%--<html:link page="/TransaccionReporte.do?method=inicializarBusqueda" >
                        Reporte Transacciones
                    </html:link>--%>
                    <html:link page="/TransaccionReporte.do?method=init" >
                        Reporte Transacciones
                    </html:link>
                <li> 
            </ul>
        </li>
        
   <% if(usuario.isSuperUsuario()||permisosUsuario!=null&&permisosUsuario.contains("menu.empresa")){ %>
        <li>
            <div id="scaPageContent"><bean:message key="menu.modulo.empresa"/></div>
            <ul style="display: none;">
                <li>
                    <html:link page="/Empresa.do?method=init">
                        <bean:message key="menu.modulo.empresa.buscar"/>
                    </html:link>
                <li> 
            </ul>
        </li>
      <% }
	   	    if(usuario.isSuperUsuario()||permisosUsuario!=null&&permisosUsuario.contains("menu.aplicacion")){ %>     
        <li>
            <div><bean:message key="menu.modulo.aplicacion"/></div>
            <ul style="display: none;">
                <li>
                    <html:link page="/Aplicacion.do?method=init" >
                        <bean:message key="menu.modulo.aplicacion.buscar"/>
                    </html:link>
                <li> 
            </ul>
        </li>
        <% }
     	    if(usuario.isSuperUsuario()||permisosUsuario!=null&&permisosUsuario.contains("menu.rol")){ %>    
        <li>
            <div><bean:message key="menu.modulo.rol"/></div>
            <ul style="display: none;">
                <li>
                    <html:link page="/Rol.do?method=init" >
                        <bean:message key="menu.modulo.rol.buscar"/>
                    </html:link>
                <li> 
            </ul>
        </li>
        <% } 
    	    if(usuario.isSuperUsuario()||permisosUsuario!=null&&permisosUsuario.contains("menu.permiso")){ %>        
        <li>
            <div ><bean:message key="menu.modulo.permiso"/></div>
            <ul style="display: none;">
                <li>
                    <html:link page="/Permiso.do?method=init">
                        <bean:message key="menu.modulo.permiso.buscar"/>
                    </html:link>
                <li> 
            </ul>
        </li>
        <%  } %> 
    	 
    	 
        <% if(usuario.isSuperUsuario()||permisosUsuario!=null&&permisosUsuario.contains("menu.usuario")){ %>
        <li>
            <div><bean:message key="menu.modulo.usuario"/></div>
            <ul style="display: none;">
                <li>
                    <html:link page="/Usuario.do?method=init" >
                        <bean:message key="menu.modulo.usuario.buscar"/>
                    </html:link>
                <li> 
            </ul>
        </li>
        <% } %>
        <% if(usuario.isSuperUsuario()||permisosUsuario!=null&&permisosUsuario.contains("menu.politica")){ %>
        <li>
            <div>
                <bean:message key="menu.modulo.politica" />
            </div>
            <ul style="display: none;">
                <li><html:link page="/Politica.do?method=init">
                        <bean:message key="menu.modulo.politica.buscar" />
                    </html:link>
                <li> 
            </ul>
        </li>
        <% } %>
        <% if(usuario.isSuperUsuario()||permisosUsuario!=null&&permisosUsuario.contains("menu.bloqueo")){ %>
        <li>
            <div>
                <bean:message key="menu.modulo.bloqueo" />
            </div>
            <ul style="display: none;">
                <li><html:link page="/Bloqueo.do?method=init">
                        <bean:message key="menu.modulo.bloqueo.buscar" />
                    </html:link>
                <li> 
            </ul>
        </li>
        <% } %>
        <% if(usuario.isSuperUsuario()||permisosUsuario!=null&&permisosUsuario.contains("menu.desbloqueo")){ %>
        <li>
            <div>
                <bean:message key="menu.modulo.desbloqueo" />
            </div>
            <ul style="display: none;">
                <li><html:link page="/Desbloqueo.do?method=init">
                        <bean:message key="menu.modulo.desbloqueo.buscar" />
                    </html:link>
                <li> 
            </ul>
        </li>
        <% } %>
        <% if(usuario.isSuperUsuario()||permisosUsuario!=null&&permisosUsuario.contains("menu.template")){ %>
        <li>
            <div>
                <bean:message key="menu.modulo.template" />
            </div>
            <ul style="display: none;">
                <% if(usuario.isSuperUsuario()||permisosUsuario!=null&&permisosUsuario.contains("submenu.gestionar")){ %>
                <li>
                    <html:link page="/Template.do?method=init">
                        <bean:message key="menu.modulo.template.buscar" />
                    </html:link>
                <li>
                <% } %>
                <% if(usuario.isSuperUsuario()||permisosUsuario!=null&&permisosUsuario.contains("submenu.importar")){ %>
                <li>
                    <html:link page="/TemplateImportar.do?method=init">
                        <bean:message key="menu.modulo.templateImportar.buscar" />
                    </html:link>
                <li>
                <% } %>
            </ul>
        </li>
        <% } %>
        <% if(usuario.isSuperUsuario()||permisosUsuario!=null&&permisosUsuario.contains("menu.reporte")){ %>
        <li>
            <div ><bean:message key="menu.modulo.auditoria"/></div>
            <ul style="display: none;">
                <li>
                    <html:link page="/Auditoria.do?method=init">
                        <bean:message key="menu.modulo.auditoria.buscar"/>
                    </html:link>
                <li> 
            </ul>
        </li>
        <% } %>
        <% if(usuario.isSuperUsuario()||permisosUsuario!=null&&permisosUsuario.contains("menu.horario")){ %>
        <li>
            <div ><bean:message key="menu.modulo.horario"/></div>
            <ul style="display: none;">
                <li>
                    <html:link page="/Horario.do?method=init">
                        <bean:message key="menu.modulo.horario.buscar"/>
                    </html:link>
                <li> 
            </ul>
        </li>
	<%}
   %>  
    </ul>

</div>

<%
}else{
session.invalidate();		
//response.sendRedirect("/sca/default.jsp");
response.sendRedirect("/moduloReportes/sessionExpired.jsp");
}
%>