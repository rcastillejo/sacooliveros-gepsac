<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.novatronic.sca.util.Resultado"%>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"  %>

<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/resources/js/ZeroClipboard.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/resources/js/TableTools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/config.js"></script>
<script type="text/javascript">
    //alert('COLORRRR' + gOptions.color);
    var table = '#div-resultado'; 


    var combo = '#div-combo';
    var comboprocesador = '#div-procesador';
    var comboadquiriente = '#div-adquiriente';
    var comboautorizador = '#div-autorizador';
    var comboreporte = '#div-reporte';

    var tipo_procesador = '2';
    var tipo_adquiriente = '1';
    var tipo_autorizador = '3';
    var cod_autorizador = '421410';
    var codadquiriente;
    var codautorizador;
    var codprocesador;
    var codprpt;
    //var codReporte = 'rpt_general';
    var lista_descripcion = [];
    var myColumnsReq = [];

    $(function () {
        cargarListado();

    });

    function initDataTable(id, data) {
        var table = $('<table id="tbl-resultado"></table>');
        console.log('Armando la vista listado', id, 'datos', data);
        table.dataTable({
            "bFilter": false,
            "bDestroy": true,
            //"sDom": 'T<"clear">lfrtip',
            "bLengthChange": false,
            "bSortClasses": false,
            "aaData": data,
            "bInfo": false,
            "sPaginationType": "full_numbers",
            //"aaSorting": [[1, 'asc']],
            "aaSorting": [[1, 'desc']],
            "sScrollY": "auto",
            "sScrollX": "930px",
            "sScrollXInner": "110%",
            "oLanguage": {
                "sUrl": "../resources/language/dataTables.spanish.txt"
            },
            "aoColumns": myColumnsReq,
            "fnDrawCallback": function (oSettings) {
                if (oSettings.bSorted || oSettings.bFiltered) {
                    for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
                        $('td:eq(0)', oSettings.aoData[oSettings.aiDisplay[i]].nTr).html(i + 1);
                    }
                }
            }
        });
        $(id).append(table);
    }


    function initDataCombo(id, data) {
        console.log('Armando la vista listado Combo', id, 'datos', data);
        jQuery.each(data, function (i, val) {
            $(id).append('<option value="' + val.codigo + '">' + val.descripcion + '</option>');
        });
    }
    function initDataComboReporte(id, data) {
        console.log('Armando la vista listado Combo Reporte', id, 'datos', data);
        jQuery.each(data, function (i, val) {
            lista_descripcion[i] = val.nombre_columnas;
            $(id).append('<option value="' + val.id_reporte + '">' + val.descripcion_reporte + '</option>');
        });
    }

    function cargarListado() {
        $("#mensajeError").empty();
        $.ajax({
            type: "POST",
            dataType: 'json',
            url: "<%=request.getContextPath()%>" + action + '?method=obtenerPlanRegistrado'
        }).done(function (plan) {
            console.log('plan', plan);
            setPlan(plan);
        }).fail(function (error) {
            console.log('error', error);
            errorFormulario();
        });
    }

    function setPlan(plan) {
        $("#codigo").val(plan.codigo);
        $("#fecInicio").val(plan.fecInicio.year + '-' + plan.fecInicio.month + '-' + plan.fecInicio.day);
        $("#fecFin").val(plan.fecFin.year + '-' + plan.fecFin.month + '-' + plan.fecFin.day);
    }

    function cargarConsultarEstrategia() {
        $("#mensajeError").empty();
        $.ajax({
            type: "POST",
            dataType: 'json',
            url: "<%=request.getContextPath()%>" + action + '?method=consultarEstrategia'
        }).done(function (estrategias) {
            console.log('estrategias', estrategias);
        }).fail(function (error) {
            console.log('error', error);
            errorFormulario();
        });
    }

    function cargarConsultarActividad() {
        $("#mensajeError").empty();
        $.ajax({
            type: "POST",
            dataType: 'json',
            url: "<%=request.getContextPath()%>" + action + '?method=consultarActividad'
        }).done(function (actividades) {
            console.log('actividades', actividades);
        }).fail(function (error) {
            console.log('error', error);
            errorFormulario();
        });
    }
</script>

<div class="div-pagina">

    <div id="div-pagina-titulo" class="div-pagina-titulo">
        <%--<bean:message key="empresa.titulo" bundle="rsEmpresa" />--%>
        Configurar Estrategia
    </div>
    <div>
        <input type="button" id="btnConsultarEstrategia" value="Consultar Estrategia" />
        <input type="button" id="btnConsultarActividad" value="Consultar Actividad" />
        <input type="button" id="btnAgregarEstrategia" value="Agregar Estrategia" />
    </div>
    <div id="dvData">
        <html:form styleId="frmReporte" action="ConfigurarEstrategia.do?method=busqueda" method="POST">
            <%--<html:form styleId="frmReporte" action="TransaccionReporte.do?method=postBuscar">--%>
            <input id="hdnBuscast" type="submit" style="display: none;" />
            <!-- INCIO PANEL-->
            <div id="div-busqueda" class="div-busqueda">
                <div id="div-busqueda-titulo" class="div-busqueda-titulo">
                    Plan
                </div>

                <div id="div-busqueda-filtros" class="div-busqueda-filtros">                        


                    <div class="div-busqueda-filtro">
                        Codigo: 
                        <input id="codigo" type="text" disabled="true">
                    </div>
                    <div class="div-busqueda-filtro">
                        Fecha Inicio: 
                        <input id="fecInicio" type="text" disabled="true">
                    </div>
                    <div class="div-busqueda-filtro">
                        Fecha Fin: 
                        <input id="fecFin" type="text" disabled="true">
                    </div>

                </div>

                <div class="no-float"></div>

            </div>
            <!-- FIN PANEL-->

            <div id="div-resultado">

            </div>

            <div class="mensaje" >
                <span>
                    <label id="mensaje" />                
                </span>
            </div>  
            <div class="mensaje-error" >
                <span>
                    <label id="mensajeError" />
                </span>
            </div>  

            <div>
                <html:link page="index.jsp" >
                    Regresar	
                </html:link>
            </div>

        </html:form>
    </div>

</div>
