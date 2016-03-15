<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<div class="cnt-menu">
    <!--<div class="sca-novatronic" >
        <img src="<%=request.getContextPath()%>/resources/images/novatronic.png" /> 
    </div>-->
    <ul id="accordion">
        <li>
            <div>Experto</div>
            <ul style="display: none;">
                <li>
                    <html:link page="/EvaluarPostulante.do?method=init" >
                        Evaluar Nuevos Alumnos
                    </html:link>
                </li>
                <li>
                    <html:link page="/GenerarExplicacion.do?method=init&profile=E" >
                        Generar Explicacion Resultado
                    </html:link>
                </li>
                <li>
                    <html:link page="/GenerarExplicacion.do?method=init&profile=P" >
                        Consultar Resultado Evaluacion Acoso Escolar
                    </html:link>
                </li>
            </ul>
        </li>
        <li>
            <div>Evaluacion</div>
            <ul style="display: none;">
                <li>
                    <html:link page="/ResolverAcosoEscolar.do?method=init" >
                        Resolver Evaluacion
                    </html:link>
                </li>
            </ul>
        </li>
        
    </ul>

</div>