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

<script type = "text/javascript" >
    //alert('COLORRRR' + gOptions.color);
    var table = '#div-resultado';
    var action = '/ConfigurarEstrategia.do';


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
    var dialog;

    $(document).ready(function () {
        obtenerPlanRegistrado();

        //CargarModal ConsultarEstrategia
        $("#btnConsultarEstrategia").click(function (e) {
            e.preventDefault();
            fn_util_AbreModal("",
                    "<%=request.getContextPath()%>" + '/pages/common/consultarEstrategia.jsp',
                    900, 500, null);
        });
        
        //CargarModal ConsultarEstrategia
        $("#btnConsultarActividad").click(function (e) {
            e.preventDefault();
            
            if(objItemSeleccionado && objItemSeleccionado !== null){
                fn_util_AbreModal("",
                        "<%=request.getContextPath()%>" + '/pages/common/consultarActividad.jsp?codigoEstrategia='+objItemSeleccionado.codigo,
                        900, 500, null);
            } else{
                fn_mdl_alert("Debe seleccionar una estrategia", null, "VALIDACIONES");
            }
            
        });
    });

    function obtenerPlanRegistrado() {
        $("#mensajeError").empty();
        $.ajax({
            type: "POST",
            dataType: 'json',
            url: "<%=request.getContextPath()%>" + action + '?method=obtenerPlanRegistrado'
        }).done(function (plan) {
            console.log('plan', plan);
            cargarPlan(plan);
        }).fail(function (error) {
            console.log('error', error);
            errorFormulario();
        });
    }

    function cargarPlan(plan) {
        $("#codigo").val(plan.codigo);
        $("#fecInicio").val(plan.fecInicio.year + '-' + plan.fecInicio.month + '-' + plan.fecInicio.day);
        $("#fecFin").val(plan.fecFin.year + '-' + plan.fecFin.month + '-' + plan.fecFin.day);
    }

    function validarItemDuplicado(name, value) {
        var isValid = true;
        $("#tblDetalle #tblDetalleEstrategia-"+value+" tbody tr").map(function (index, elem) {
            if (isValid) {
                $('.inputValue', this).each(function () {
                    var k = $(this).data("name");
                    var v = $(this).val() || $(this).text();
                    if (isValid && k == name && v == value) {
                        isValid = false;
                    }
                });
            }
        });
        return isValid;
    }
    function validarItemActividadDuplicado(name, value, estrategia) {
        var isValid = true;
        $("#tblDetalleEstrategia-" + estrategia + " #tblDetalleActividad tbody tr").map(function (index, elem) {
            if (isValid) {
                $('.inputValue', this).each(function () {
                    var k = $(this).data("name");
                    var v = $(this).val() || $(this).text();
                    if (isValid && k == name && v == value) {
                        isValid = false;
                    }
                });
            }
        });
        return isValid;
    }

    var objItemSeleccionado;
    var objItemActividadSeleccionado;

    function fn_checkListaItemDetalle(objCheck, item) {
        $('input[id*="chkEliminar"]').prop('checked', false);
        console.log('obj', objCheck);
        objCheck.checked = true;
        //objCheck.prop('checked', false);
        objItemSeleccionado = item;
    }

    function fn_seleccionarItemDetalle() {
        console.log('objItemSeleccionado', objItemSeleccionado);
        if (objItemSeleccionado && objItemSeleccionado !== null) {
            objItemSeleccionado.remove();
            objItemSeleccionado = undefined;
        } else {
            fn_mdl_alert("Debe seleccionar un registro", null, "VALIDACIONES");
        }
    }


    function cargarEstrategia(json) {

        var valid = validarItemDuplicado('codigoEstrategia', json.codigo);
        if (!valid) {
            fn_mdl_alert("La estrategia ya se encuetra agregada", null, "VALIDACIONES");
        } else {
            console.log('cargando estrategia...', json);

            var table = $("#tblDetalle");
            var detalle = $("#tblDetalleEstrategia").clone();
            
            detalle.attr('id', 'tblDetalleEstrategia-' + json.codigo);
            detalle.show();
            
            detalle.find("#lblCodigo").append(json.codigo);
            detalle.find("#lblNombre").append(json.nombre);

            table.append(detalle);

            detalle.find("#chkId").click(function () {
                fn_checkListaItemDetalle($(this).get(0), json);
            });
            detalle.find("#lnkEliminar").click(function () {
                //fn_checkListaItemDetalle($(this).get(0), json);
                detalle.remove();
            });
        }

        fn_util_CierraModal();

    }
/*
    function fn_checkListaItemActividad(objCheck, item) {
        $('input[id*="chkEliminarActividad"]').prop('checked', false);
        console.log('obj', objCheck);
        objCheck.checked = true;
        //objCheck.prop('checked', false);
        objItemActividadSeleccionado = item;
    }

    function fn_seleccionarItemActividad() {
        console.log('objItemActividadSeleccionado', objItemActividadSeleccionado);
        if (objItemActividadSeleccionado && objItemActividadSeleccionado != null) {
            objItemActividadSeleccionado.remove();
            objItemActividadSeleccionado = undefined;
        } else {
            fn_mdl_alert("Debe seleccionar un registro", null, "VALIDACIONES");
        }
    }

    function cargarActividad(json) {

        var valid = validarItemDuplicado('codigoActividad', json.listaPrecioId);
        if (!valid) {
            fn_mdl_alert("El servicio ya se encuetra agregado", null, "VALIDACIONES");
        } else {

            console.log('cargando...', json);

            var table = $("#tblDetalle");

            var detalle = $("#tblDetalleEstrategia").clone();

            detalle.find("#lblCodigo").append(json.codigo);
            detalle.find("#lblNombre").append(json.nombre);

            table.find("tbody").append(detalle);

            detalle.find("#chkEliminar").click(function () {
                fn_checkListaItemDetalle($(this).get(0), detalle);
            });

            calcularTotalesDetalle();

        }

        fn_util_CierraModal();

    }
*/
</script>

<div class="div-pagina">

    <div id="div-pagina-titulo" class="div-pagina-titulo">
        <%--<bean:message key="empresa.titulo" bundle="rsEmpresa" />--%>
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
                    Configurar Estrategia de Plan
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

                <div id="rowDetalleActividad" style="display:none;">   
                    <table>    
                        <tr>                
                            <td>
                                <input id="chkEliminarActividad" type="checkbox"/>
                            </td>
                            <td>
                                <label id="lblCodigoActividad" class="inputValue" data-name="codigoActividad"></label>
                            </td>
                            <td>
                                <label id="lblNombreActividad"></label>
                            </td>
                            <td>
                                <label id="lblIndicacores" class="inputValue" ></label>
                            </td>
                        </tr>
                    </table>
                </div>


                <div id="tblDetalleEstrategia" style="display: none;">
                    <table border="0" cellpadding="3" cellspacing="0" class="css_grilla">
                        <thead>
                            <tr>
                                <th>-</th>
                                <th>N°</th>
                                <th>Estrategia</th>
                            </tr>	
                        </thead>                        
                        <tbody>
                            <tr>
                                <td>
                                    <input id="chkId" type="checkbox"/>
                                </td>
                                <td>
                                    <label id="lblCodigo" class="inputValue" data-name="codigoEstrategia"></label>
                                </td>
                                <td>
                                    <label id="lblNombre"></label>
                                </td>
                                <td>
                                    <button id="lnkEliminar" type="button" value="Eliminar">
                                        <img src="<%=request.getContextPath()%>/resources/images/delete-icon.gif" border="0" />                                        
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <br/>
                    <table id="tblDetalleActividad" border="0" cellpadding="3" cellspacing="0" class="css_grilla">
                        <thead>
                            <tr>
                                <th>-</th>
                                <th>N°</th>
                                <th>Actividad</th>
                                <th>Indicadores</th>
                            </tr>	
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>

                <div id="tblDetalle">
                    
                </div>
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

        </html:form>
    </div>

</div>
