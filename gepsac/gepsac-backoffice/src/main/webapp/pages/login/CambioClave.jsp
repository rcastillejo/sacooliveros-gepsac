<%@page import="com.novatronic.pscabas.core.model.Usuario"%>
<%@page import="com.novatronic.sca.util.Constantes"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<% Usuario usuario = (Usuario) request.getSession(false).getAttribute(Constantes.USUARIO_SESSION);
    if (usuario != null) {%>
<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="principal.title"/></title>
        <link href="<%=request.getContextPath()%>/resources/css/login.css" rel="stylesheet" type="text/css" />
        <script src="<%=request.getContextPath()%>/resources/js/util.js"></script>
        <html:base/>
        <script lang="Javascript" type="text/javascript">
            function resetForm() {
                document.cambioClaveForm.reset();
            }
            function validarCampos() {
                /**Se utiliza para fixear la validación de usuario y clave para IE ya que  no reconoce el trim()*/
                if (typeof String.prototype.trim !== 'function') {
                    String.prototype.trim = function() {
                        return this.replace(/^\s+|\s+$/g, '');
                    };
                }
                var claveAct = document.getElementById('txtClvActual').value.trim();
                var claveNuev = document.getElementById('txtClvNueva').value.trim();
                var claveConf = document.getElementById('txtClvConf').value.trim();

                if (claveAct.length > 0) {
                    document.getElementById('reqClaveActual').style.display = 'none';
                    if (claveNuev.length > 0) {
                        document.getElementById('reqNuevaClave').style.display = 'none';
                        if (claveConf.length > 0) {
                            document.getElementById('reqConfClave').style.display = 'none';
                            return true;
                        } else {
                            document.getElementById('reqConfClave').style.display = '';
                        }
                    } else {
                        document.getElementById('reqNuevaClave').style.display = '';
                        if (claveConf.length > 0) {
                            document.getElementById('reqConfClave').style.display = 'none';
                        } else {
                            document.getElementById('reqConfClave').style.display = '';
                            return false;
                        }
                    }
                } else {
                    document.getElementById('reqClaveActual').style.display = '';
                    if (claveNuev.length > 0) {
                        document.getElementById('reqNuevaClave').style.display = 'none';
                        if (claveConf.length > 0) {
                            document.getElementById('reqConfClave').style.display = 'none';
                        } else {
                            document.getElementById('reqConfClave').style.display = '';
                            return false;
                        }
                    } else {
                        document.getElementById('reqNuevaClave').style.display = '';
                        if (claveConf.length > 0) {
                            document.getElementById('reqConfClave').style.display = 'none';
                            return false;
                        } else {
                            document.getElementById('reqConfClave').style.display = '';
                            return false;
                        }
                    }
                    return false;
                }
            }
        </script>
    </head>
    <body onload="resetForm();"  oncontextmenu="return false;" onpaste="return false;">
        <div class="cnt-login">
            <div id="login-background">
                <div class="div-login">
                    <html:form method="POST" action="/CambioClave.do?method=cambiarNuevaClave" >
                        <table class="tbl-login" style="width:500px;">
                            <tr>
                                <td class="td-login" style="width:140px;">
                                    <span class="lbl-login">
                                        <bean:message key="login.formulario.clave.actual" bundle="rsLogin" />
                                    </span>
                                </td>
                                <td style="width:180px;">
                                    <html:password styleId="txtClvActual"  value="" property="claveActual" styleClass="input-login-usuario" 
                                                   onkeypress="return esAlfaNumLogin(event)" maxlength="16"></html:password>
                                    </td>
                                    <td> 
                                        <span id="reqClaveActual" style="color: red; display: none">
                                        <bean:message key="login.formulario.clvact.required" bundle="rsLogin" />
                                    </span>
                                </td>    
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <label id="claveActual.error" class="error"></label>
                                </td>
                            </tr>
                            <tr>
                                <td class="td-login"><span class="lbl-login">
                                        <bean:message key="login.formulario.nueva.clave" bundle="rsLogin" /> </span>
                                </td>
                                <td>
                                    <html:password styleId="txtClvNueva" value="" property="nuevaClave" styleClass="input-login-usuario" 
                                                   onkeypress="return esAlfaNumLogin(event)" maxlength="16"></html:password>
                                    </td>
                                    <td> 
                                        <span id="reqNuevaClave" style="color: red; display: none">
                                        <bean:message key="login.formulario.clvnuev.required" bundle="rsLogin" />
                                    </span>
                                </td> 
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <label id="nuevaClave.error" class="error"></label>
                                </td>
                            </tr>
                            <tr>
                                <td class="td-login"><span class="lbl-login">
                                        <bean:message key="login.formulario.confirmar.clave" bundle="rsLogin" /> </span>
                                </td>
                                <td>
                                    <html:password styleId="txtClvConf" value="" property="confirmarClave" styleClass="input-login-usuario" 
                                                   onkeypress="return esAlfaNumLogin(event)" maxlength="16"></html:password>
                                    </td>
                                    <td> 
                                        <span id="reqConfClave" style="color: red; display: none">
                                        <bean:message key="login.formulario.clvconf.required" bundle="rsLogin" />
                                    </span>
                                </td> 
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <label id="confirmarClave.error" class="error"></label>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <button type="submit" class="btn-background" onclick="if (!validarCampos())
                                                return false">Enviar</button>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"  style="text-align: center;"><span style="color: red;font-size: 11px">
                                        ${mensajeCambioClave}
                                    </span>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <p class="parrafo"> 
                                        <a href="/moduloReportes/pages/login/Login.jsp">
                                            Volver a Login.
                                        </a>
                                    </p>
                                </td>
                            </tr>
                        </table>
                    </html:form>
                </div>
            </div>
        </div>
    </body>
</html:html>

<%
    } else {
        session.invalidate();
        //response.sendRedirect("/sca/default.jsp");
        response.sendRedirect("/moduloReportes/sessionExpired.jsp");
    }
%>