<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="principal.title"/></title>
        <script src="<%=request.getContextPath()%>/resources/js/util.js"></script>
        <link href="<%=request.getContextPath()%>/resources/css/login.css" rel="stylesheet" type="text/css" />
        <html:base/>
        <script lang="Javascript" type="text/javascript">
            function resetLoginForm() {
                document.loginForm.reset();
            }
            function validarCampos() {
                if (typeof String.prototype.trim !== 'function') {
                    String.prototype.trim = function() {
                        return this.replace(/^\s+|\s+$/g, '');
                    };
                }
                var usu = document.getElementById('txtUsuario').value.trim();
                var clav = document.getElementById('txtClave').value.trim();

                if (usu.length > 0) {
                    document.getElementById('reqUsuario').style.display = 'none';
                    if (clav.length > 0) {
                        document.getElementById('reqClave').style.display = 'none';
                        return true;
                    } else {
                        document.getElementById('reqClave').style.display = '';
                        return false;
                    }
                } else {
                    if (clav.length > 0) {
                        document.getElementById('reqUsuario').style.display = '';
                        document.getElementById('reqClave').style.display = 'none';
                        return false;
                    } else {
                        document.getElementById('reqUsuario').style.display = '';
                        document.getElementById('reqClave').style.display = '';
                        return false;
                    }
                }
            }
        </script>
    </head>

    <body onload="resetLoginForm();" oncontextmenu="return false;" onpaste="return false;">

        <div class="cnt-login">
            <div id="login-background">
                <div class="div-loginIni">
                    <html:form method="POST" action="/Login.do?method=postLogin" >
                        <table class="tbl-login" >
                            <tr>							
                                <td class="td-login">
                                    <span class="lbl-login">
                                        <bean:message key="login.formulario.usuario" bundle="rsLogin" />
                                    </span>
                                </td>
                                <td>
                                    <html:text styleId="txtUsuario" property="input.usuario" styleClass="input-login-usuario" 
                                               maxlength="20" onkeypress="return esAlfaNumLogin(event)"></html:text>
                                    </td>
                                    <td> 
                                        <span id="reqUsuario" style="color: red; display: none">
                                        <bean:message key="login.usuario.required" bundle="rsLogin" />
                                    </span>
                                </td> 
                            </tr>
                            <tr>
                                <td class="td-login">
                                    <span class="lbl-login">
                                        <bean:message key="login.formulario.clave" bundle="rsLogin" /> 
                                    </span>
                                </td>
                                <td>
                                    <html:password styleId="txtClave" value="" property="input.clave" styleClass="input-login-usuario" 
                                                   maxlength="16" onkeypress="return esAlfaNumLogin(event)"></html:password>
                                    </td>
                                    <td>
                                        <span id="reqClave" style="color: red; display: none">
                                        <bean:message key="login.clave.required" bundle="rsLogin" />
                                    </span>
                                </td>                                     
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <button type="submit" class="btn-login" onclick="if (!validarCampos())
                                        return false" autofocus="true"></button>
                                </td>
                            </tr>
                        </table>
                        <div class="mensaje-error" >
                            <logic:present name="mensaje">
                                <span>
                                    <bean:write name="mensaje" />
                                </span>
                            </logic:present>
                        </div>  

                        <div class="olvide-clave">
                            <html:link action="/OlvideClave.do?method=olvideClave">
                                <bean:message key="login.formulario.olvide.clave" bundle="rsLogin"/>	
                            </html:link>
                        </div>
                    </html:form>
                </div>
            </div>
        </div>
    </body>
</html:html>
