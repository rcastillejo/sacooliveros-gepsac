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
        $("#btnGuardarConfiguracion").click(function (e) {
            e.preventDefault();

            var sError = validarConfiguracion();

            if (sError == "") {
                fn_mdl_confirma("¿Está seguro que desea guardar la configuracion del Plan?",
                        function () {
                            guardarConfiguracion();
                        },
                        null,
                        null,
                        "CONFIRMACIÓN"
                        );
            } else {
                fn_mdl_alert(sError, null, "VALIDACIONES");
            }
        });


        //$('.tooltip').tooltip();

    });
    //TODO: validarConfiguracion, guardarConfiguracion

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
        $("#tblDetalle #tblDetalleEstrategia-" + value + " tbody tr").map(function (index, elem) {
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
    
    function cargarEstrategia(json) {

        if (json.estado.codigo === "EST0002") {

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

                /*detalle.find("#chkId").click(function () {
                 fn_checkListaItemDetalle($(this).get(0), json);
                 });*/
                detalle.find("#lnkEliminar").click(function () {
                    fn_mdl_confirma("¿Está seguro que desea eliminar la estrategia?",
                            function () {
                                detalle.remove();
                            },
                            null,
                            null,
                            "CONFIRMACIÓN"
                            );
                });
                detalle.find("#btnConsultarActividad").click(function () {
                    fn_util_AbreModal("",
                            "<%=request.getContextPath()%>" + '/pages/common/consultarActividad.jsp?codigoEstrategia=' + json.codigo,
                            900, 500, null);
                });
            }

        } else {
            fn_mdl_alert("No puede se puede agregar la estrategia ya que no está configurado", null, "VALIDACIONES");
        }

        fn_util_CierraModal();

    }
    
    function cargarActividad(json) {

        var valid = validarItemActividadDuplicado('codigoActividad', json.actividad.codigo, json.codigoEstrategia);
        if (!valid) {
            fn_mdl_alert("La actividad ya se encuentra agregada en la estrategia " + json.codigoEstrategia, null, "VALIDACIONES");
        } else {

            console.log('cargando...', json);

            var table = $("#tblDetalleEstrategia-" + json.codigoEstrategia + " #tblDetalleActividad");

            var detalle = $("#rowDetalleActividad").find("tbody tr").clone();

            detalle.find("#lblCodigoActividad").append(json.actividad.codigo);
            detalle.find("#lblNombreActividad").append(json.actividad.nombre);

            for (var i in json.indicadores) {
                var indicador = json.indicadores[i];
                //var tooltip = $('<a href="#" class="tooltip" ></a>').attr('title', indicador.codigo + '<br/>' + indicador.nombre).append(indicador.nombre);
                detalle.find("#lblIndicadores").append(indicador.nombre);
                detalle.find("#lblIndicadores").append(',');
            }

            table.find("tbody").append(detalle);

            detalle.find("#lnkEliminarActividad").click(function () {
                fn_mdl_confirma("¿Está seguro que desea eliminar la actividad?",
                        function () {
                            detalle.remove();
                        },
                        null,
                        null,
                        "CONFIRMACIÓN"
                        );
                //fn_checkListaItemActividad($(this).get(0), detalle);
            });
        }
        //objItemSeleccionado === null;
        //objItemActividadSeleccionado === null;
        fn_util_CierraModal();

    }

</script>

<div class="div-pagina">

    <div id="div-pagina-titulo" class="div-pagina-titulo">
        <%--<bean:message key="empresa.titulo" bundle="rsEmpresa" />--%>
    </div>
    <div>
        <asp:Button ID="btnGrabar" runat="server" Text="" OnClientClick="return cargarDetallePresupuesto();" OnClick="btnGrabar_Click" ClientIDMode="Static" Style="display: none;" />

        <input type="button" id="btnConsultarEstrategia" value="Consultar Estrategia" />
        <input type="button" id="btnGuardarConfiguracion" value="Guardar Configuración" />
        <input type="button" id="btnAgregarEstrategia" value="Agregar Estrategia" />
    </div>
    <div id="dvData">
        <html:form styleId="frmReporte" action="ConfigurarEstrategia.do?method=busqueda" method="POST">
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
                                <label id="lblCodigoActividad" class="inputValue" data-name="codigoActividad"></label>
                            </td>
                            <td>
                                <label id="lblNombreActividad"></label>
                            </td>
                            <td>
                                <label id="lblIndicadores" class="inputValue" ></label>
                            </td>
                            <td>
                                <button id="lnkEliminarActividad" type="button" value="Eliminar">
                                    <img src="<%=request.getContextPath()%>/resources/images/delete-icon.gif" border="0" />                                        
                                </button>
                            </td>
                        </tr>
                    </table>
                </div>


                <div id="tblDetalleEstrategia" style="display: none;">
                    <table border="0" cellpadding="3" cellspacing="0" class="css_grilla">
                        <thead>
                            <tr>
                                <th>N°</th>
                                <th>Estrategia</th>
                            </tr>	
                        </thead>                        
                        <tbody>
                            <tr>
                                <td>
                                    <label id="lblCodigo" class="inputValue" data-name="codigoEstrategia"></label>
                                </td>
                                <td>
                                    <label id="lblNombre"></label>
                                </td>
                                <td>
                                    <input type="button" id="btnConsultarActividad" value="Consultar Actividad">
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
