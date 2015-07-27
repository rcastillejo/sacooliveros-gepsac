<%
	int timeout = session.getMaxInactiveInterval();
//	response.setHeader("Refresh", timeout + "; URL = /sca/default.jsp");
	response.setHeader("Refresh", timeout + "; URL = /moduloReportes/sessionExpired.jsp");
%>

<%@page import="com.novatronic.sca.util.Constantes"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<div id="grid">

    <div class="container">

			
    </div>
</div>