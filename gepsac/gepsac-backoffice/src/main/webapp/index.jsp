<%
	int timeout = session.getMaxInactiveInterval();
	response.setHeader("Refresh", timeout + "; URL = /sca/sessionExpired.jsp");
%>

<%@page import="com.novatronic.sca.util.Constantes"%>
<%@page import="com.novatronic.pscabas.core.model.Usuario"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%

Usuario usuario=(Usuario)request.getSession(false).getAttribute(Constantes.USUARIO_SESSION);
if(usuario!=null){%>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insert page="/templates/principal.jsp" flush="true">
	<tiles:put name="header" value="/templates/header.jsp" />
	<tiles:put name="menu" value="/templates/menu.jsp" />
	<tiles:put name="content" value="/templates/content.jsp" />
	<tiles:put name="footer" value="/templates/footer.jsp" />
</tiles:insert>

<%
}else{
session.invalidate();		
//response.sendRedirect("/sca/default.jsp");
response.sendRedirect("/moduloReportes/sessionExpired.jsp");
}
%>