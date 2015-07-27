<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="principal.title"/></title>
        <script src="<%=request.getContextPath()%>/resources/js/jquery-1.8.3.js"></script>
        <script src="<%=request.getContextPath()%>/resources/js/util.js"></script>
        <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet" type="text/css" />
        <html:base/>
        <script type="text/javascript">
            $(document).ready(function() {
                resetForm();
                nameForm = "olvideClaveForm";
                function resetForm() {
                    document.olvideClaveForm.reset();
                }
                $("#selectTipoDocumento").on('change', function() {
                    if (this.value == '0') {
                        $("#textNumeroDocumento").attr('maxlength', 8);
                    } else if (this.value == '1') {
                        $("#textNumeroDocumento").attr('maxlength', 12);
                    } else if (this.value == '-1') {
                        $("#textNumeroDocumento").attr('maxlength', 8);
                    }
                });
            });
            function ingresar(){
                if (event.keyCode === 13 || event.which === 13) {
                    document.getElementById("formLogin").submit();
                }
            }
        </script>
    </head>
    <body oncontextmenu="return false;" onpaste="return false;" onkeypress="ingresar();">
        <div class="cnt-login">
            <div id="login-background">
                <div class="div-login">
                    <html:form styleId="formLogin" method="POST" action="/OlvideClave.do?method=enviarCorreoNuevaClave" >
                        <table class="tbl-login" style="width: 500px;">
                            <tr>
                                <td style="width: 140px;">
                                    <span class="lbl-login">
                                        <bean:message key="login.formulario.usuario" bundle="rsLogin" />
                                    </span>
                                </td>
                                <td style="width: 130px;">
                                    <html:text property="usuario" styleClass="input-login-usuario" 
                                               maxlength="20" onkeypress="return esAlfaNum(event)" style="width: 145px;"></html:text>
                                </td>
                                <td style="width: 230px;">
                                        <div class="mensaje-error" >
                                        <logic:present name="mensajeUsuario">
                                            <span>
                                                <bean:write name="mensajeUsuario" />
                                            </span>
                                        </logic:present>
                                    </div>   
                                </td>   
                            </tr>

                            <tr>
                                <td><span class="lbl-login"><bean:message key="login.formulario.tipodocumento" bundle="rsLogin" /> </span></td>
                                <td>
                                    <html:select property="tipoDocumento" styleClass="input-login-usuario" styleId="selectTipoDocumento" style="width: 145px;">
                                        <html:option value="-1" key="sistema.seleccione" />
                                        <html:optionsCollection property="tipoDocumentos" value="lId" label="nombre" />
                                    </html:select>
                                </td>
                                <td>
                                    <div class="mensaje-error" >
                                        <logic:present name="mensajeTipo">
                                            <span>
                                                <bean:write name="mensajeTipo" />
                                            </span>
                                        </logic:present>
                                    </div>   
                                </td>  
                            </tr>

                            <tr>
                                <td>
                                    <span class="lbl-login">
                                        <bean:message key="login.formulario.nrocumento" bundle="rsLogin" />
                                    </span>
                                </td>
                                <td>
                                    <html:text property="numeroDocumento" styleClass="input-login-usuario" 
                                               onkeypress="return esNumero(event)" maxlength="8" styleId="textNumeroDocumento" style="width: 145px;"></html:text>
                                    </td>
                                    <td>
                                        <div class="mensaje-error" >
                                        <logic:present name="mensajeDocumento">
                                            <span>
                                                <bean:write name="mensajeDocumento" />
                                            </span>
                                        </logic:present>
                                    </div>   
                                </td>   
                            </tr>


                            <tr>
                                <td>
                                    <span class="lbl-login">
                                        <bean:message key="login.formulario.correo" bundle="rsLogin" />
                                    </span>
                                </td>
                                <td>
                                    <html:text property="correo" styleClass="input-login-usuario" maxlength="50" style="width: 145px;"></html:text>
                                    </td>
                                    <td>
                                        <div class="mensaje-error" >
                                        <logic:present name="mensajeCorreo">
                                            <span>
                                                <bean:write name="mensajeCorreo" />
                                            </span>
                                        </logic:present>
                                    </div>   
                                </td>  
                            </tr>

                            <tr>
                                <td>&nbsp;</td>
                                <td style="text-align: left;">
                                    <button type="submit" class="btn-background" >Enviar</button>
                                </td>
                                <td style="text-align: center;"><span style="color: red;">
                                        ${mensajeOlvideClave}
                                    </span></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <p class="parrafo">Se le enviara a su correo una nueva clave <br> para que pueda ingresar temporalmente</p>
                                </td>
                                <td><p class="parrafo"> <a href="Login.jsp"> Volver a Login</a> </p></td>
                            </tr>
                        </table>
                    </html:form>
                </div>
            </div>
        </div>
    </body>

</html:html>
