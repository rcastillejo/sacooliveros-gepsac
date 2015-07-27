<%
	int timeout = session.getMaxInactiveInterval();
//	response.setHeader("Refresh", timeout + "; URL = /sca/default.jsp");
	response.setHeader("Refresh", timeout + "; URL = /moduloReportes/sessionExpired.jsp");
%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>


<div class="separatorGreen"></div>
<div id="footer-info1">
    <div style="float:left">
        <p align="left" class="copyrights-footer">NOVATRONIC S.A.C. - COPYRIGHTS 2013 <br>
            Av. Galvez Barrenechea 1094 San Isidro <br>
            LIMA 27 - PER&Uacute; <br>
            Tel&eacute;fono (511) 415-2400	<br>
        </p>
    </div>
    <div style="float: right">
        <p align="right" class="copyrights-footer">
            P&aacute;gina Web : <a href="http://www.novatronic.com" target="_new">www.novatronic.com</a> <br>
            Informes : <a href="mailto:info@novatronic.com" target="_top">info@novatronic.com</a> <br>
            Soporte  : <a href="mailto:soporte@novatronic.com" target="_top">soporte@novatronic.com</a><br>							
        </p>
    </div>
</div>