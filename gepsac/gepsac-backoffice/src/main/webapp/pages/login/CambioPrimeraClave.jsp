    <%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="principal.title"/></title>
        <link href="<%=request.getContextPath()%>/resources/css/skinSCA.css" rel="stylesheet" type="text/css" />
        <script src="<%=request.getContextPath()%>/resources/js/util.js"/>
        <html:base/>
    </head>
    <body oncontextmenu="return false;" onpaste="return false;">
    
    
			<div class="cnt-login">
				<div id="login-background">
					<div class="div-login">
					<html:form method="POST" action="/Login.do?method=cambiarPrimeraClave" >
						<table class="tbl-login">							
							<tr>							
								<td><span class="lbl-login"><bean:message key="login.formulario.nueva.clave" bundle="rsLogin" /> </span></td>
								<td>
									 <html:password property="nuevaClave" styleClass="input-login-usuario" 
                                                                                        maxlength="16" onkeypress="return esAlfaNumLogin(event)"></html:password>
								</td>
							</tr>
							<tr>							
								<td><span class="lbl-login"><bean:message key="login.formulario.confirmar.clave" bundle="rsLogin" /> </span></td>
								<td>								
									 <html:password property="confirmarClave" styleClass="input-login-usuario" 
                                                                                        maxlength="16" onkeypress="return esAlfaNumLogin(event)"></html:password>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td style="text-align: left;">
									<button type="submit" class="btn-background" >Enviar</button>
								</td>
							</tr>
							
					</table>
					</html:form>					
					</div>
				</div>
			</div>
</body>
</html:html>
