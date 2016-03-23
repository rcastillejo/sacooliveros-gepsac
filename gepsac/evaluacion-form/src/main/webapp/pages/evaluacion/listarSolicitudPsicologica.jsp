<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    var formatDate = 'dd/mm/yy';
    var table = '#div-resultado';
    var action = '/EvaluarPostulante.do';


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
        initEvaluarAlumno();

        //Cancelar la Evaluacion del Alumno Nuevo
        $("#btnCancelar").click(function (e) {
            e.preventDefault();
            location.assign("<%=request.getContextPath()%>");
        });

        //Buscar al Alumno Nuevo
        $("#btnBuscarAlumnoNuevo").click(function (e) {
            e.preventDefault();
            fn_util_AbreModal("",
                    "<%=request.getContextPath()%>" + '/pages/experto/buscarAlumnoNuevo.jsp',
                    900, 600, null);
        });

        //Evaluar al Alumno Nuevo
        $("#btnEvaluar").click(function (e) {
            e.preventDefault();
            var sError = validarEvaluacion();
            if (sError === "") {
                evaluarAlumnoNuevo();
            } else {
                fn_mdl_alert(sError, null, "MENSAJE");
            }
        });
    });

    function validarEvaluacion() {
        var sError = "";
        var codigoAlumno = $("#codigoAlumno").val();
        if (codigoAlumno.length === 0) {
            sError = sError + "   - Debe seleccionar un alumno. <br/>";
        }
        return sError;
    }

    function serializeEvaluacionAlumnoNuevo() {
        var dataSerialize = {};
        dataSerialize = getData($("#dvData"));
        console.log("dataSerialize", dataSerialize);
        return dataSerialize;
    }

    function getData(jQueryEl) {
        var data = {};
        jQueryEl.find(".inputValue").each(function () {
            var k = $(this).data("name");
            var v = $(this).val() || $(this).text();
            if (v && v !== '') {
                var ks = k.split(".");
                if (ks.length === 1) {
                    data[ks[0]] = v;
                } else if (ks.length === 2) {
                    var d = data[ks[0]] || {};
                    d[ks[1]] = v;
                    data[ks[0]] = d;
                } else {//3
                    var d = data[ks[0]] || {};
                    var d1 = d[ks[1]] || {};
                    d1[ks[2]] = v;
                    d[ks[1]] = d1;
                    data[ks[0]] = d;
                }
            }
            console.log('data', data);
        });
        return data;
    }

    function evaluarAlumnoNuevo() {
        var data = serializeEvaluacionAlumnoNuevo();
        $.ajax({
            type: "POST",
            dataType: 'json',
            url: "<%=request.getContextPath()%>" + action + '?method=evaluarAlumno&evaluacion=' + JSON.stringify(data)
        }).done(function (result) {
            var evaluacion = result[0];
            var msg = result[1];
            console.log('evaluacion', evaluacion);
            console.log('msg', msg);
            cargarRespuestaEvaluacion(evaluacion);
            $('#btnBuscarAlumnoNuevo').attr('disabled','disabled');
            $('#btnEvaluar').attr('disabled','disabled');
            fn_mdl_alert(msg, function () {}, "CONFIRMACION");
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, function () {
                location.assign("<%=request.getContextPath()%>");
            }, "MENSAJE");
        });

        //$("#cphCuerpo_txtDetalle").val(JSON.stringify(listado));
        //console.log('detalle json', $("#cphCuerpo_txtDetalle").val());

    }

    function cargarRespuestaEvaluacion(evaluacion) {
        for (var i in evaluacion.perfiles) {
            var perfilEval = evaluacion.perfiles[i];
            var el;
            if (perfilEval.perfil === undefined) {
                el = $("#mensaje");
            } else if (perfilEval.perfil.codigo === 'P0001') {
                el = $("#agresor");
            } else if (perfilEval.perfil.codigo === 'P0002') {
                el = $("#victima");
            } else if (perfilEval.perfil.codigo === 'P0003') {
                el = $("#testigo");
            }
            var probabilidad = new Number(perfilEval.probabilidad);
            var porcProbabilidad = (probabilidad * 100.0);
            porcProbabilidad = porcProbabilidad.toFixed(2);
            console.log('Perfil Evaluado', perfilEval, '%', porcProbabilidad);
            el.val(porcProbabilidad + '%');
        }
    }

    function initEvaluarAlumno() {
        $("#mensajeError").empty();
        $.ajax({
            type: "POST",
            dataType: 'json',
            url: "<%=request.getContextPath()%>" + action + '?method=initEvaluarAlumno'
        }).done(function (objeto) {
            console.log('objeto', objeto);
            cargarEvaluacion(objeto);
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, function () {
                location.assign("<%=request.getContextPath()%>");
            }, "MENSAJE");
        });
    }

    function cargarPlanEstrategia(plan) {

        cargarPlan(plan);

        for (var i in plan.estrategiasSeleccionadas) {
            var estrategia = plan.estrategiasSeleccionadas[i];
            cargarEstrategia(estrategia);

            for (var j in estrategia.actividadesSeleccionadas) {
                var actividad = estrategia.actividadesSeleccionadas[j];
                cargarActividadIndicadores(actividad, actividad.indicadoresSeleccionados);
            }

        }
    }

    function cargarEvaluacion(objeto) {
        $("#codigoEvaluacion").val(objeto.codigo);
        var fechaEvaluacion = new Date();
        var twoDigitMonth = fechaEvaluacion.getMonth() + 1 + "";
        if (twoDigitMonth.length === 1)
            twoDigitMonth = "0" + twoDigitMonth;
        var twoDigitDate = fechaEvaluacion.getDate() + "";
        if (twoDigitDate.length === 1)
            twoDigitDate = "0" + twoDigitDate;
        var fechaActual = twoDigitDate + '/' + twoDigitMonth + '/' + fechaEvaluacion.getFullYear();
        console.log('fechaActual', fechaActual);
        $("#fechaEvaluacion").val(fechaActual);
        console.log('fechaEvaluacion', fechaEvaluacion);
    }

    function cargarAlumnoNuevo(json) {
        cargarAlumno(json);
        fn_util_CierraModal();

    }

    function cargarAlumno(json) {
        console.log('cargando...', json);
        var promedioEscolar = json.promedioEscolar;
        promedioEscolar = promedioEscolar.toFixed(2);

        $("#codigoAlumno").val(json.codigo);
        $("#nombres").val(json.nombres);
        $("#apellidoPaterno").val(json.apellidoPaterno);
        $("#apellidoMaterno").val(json.apellidoMaterno);
        $("#genero").val(json.genero);
        $("#edad").val(json.edad);
        $("#contextura").val(json.contextura);
        $("#estatura").val(json.altura);
        $("#direccion").val(json.domicilio);
        $("#distrito").val(json.distrito);
        $("#provincia").val(json.provincia);
        $("#departamento").val(json.departamento);
        $("#nacionalidad").val(json.nacionalidad);
        $("#religion").val(json.religion);
        $("#nivelEscolar").val(json.nivelEscolar);
        $("#gradoEscolar").val(json.gradoEscolar);
        $("#promedioEscolar").val(promedioEscolar);
        $("#cantCambioColegio").val(json.nroCambioColegio);
        $("#tipoFamilia").val(json.tipoFamilia);
        $("#ordenNacimiento").val(json.ordenNacimiento);
        $("#cantHnos").val(json.numHnos);

    }

</script>

<div class="div-pagina">
    <div id="div-pagina-titulo" class="div-pagina-titulo">
        Listado Solicitud Psicol&oacute;gica
    </div>
    <div id="dvData">
        <fieldset>
            <legend>Criterios de b&uacute;squeda</legend>
            <table>
                <tr>
                    <td>C&oacute;digo</td><td>:</td>
                    <td><input id="codigoSolicitud" type="text" disabled="true" class="inputValue"></td>
                    <td>Motivo</td><td>:</td>
                    <td><select id="motivoSolicitud" data-name="motivo">
                        <option value="" selected="selected">-- Seleccionar --</option>    
                        <option value="AE">Agresi&oacute;n</option>
                        <option value="BR">Bajo Rendimiento</option>
                        <option value="CS">Comportamiento Sospechoso</option>
                      </select></td>
                    <td>Estado</td><td>:</td>
                    <td><select id="estadoSolicitud" data-name="estado">
                        <option value="" selected="selected">-- Todos --</option>   
                        <option value="EP">En proceso</option>
                        <option value="PA">Por Atender</option>
                        <option value="AT">Atendido</option>
                      </select></td>
                    <td><input type="button" id="btnBuscar" value="Buscar"/></td>
                </tr>
            </table>
        </fieldset>
        <div style="align-content: left; text-align: center">
            <input type="button" id="btnRegistrar" value="Registrar" />
        </div>
        <table>
                        <tr>
                            <td>Fecha Registro</td> 
                            <td>Nro</td> 
                            <td>Motivo</td>
                            <td>Estado</td> 
                            <td>Opciones</td> 
                        </tr>
                        <tr>
                            <td></td> 
                            <td></td> 
                            <td></td> 
                            <td></td> 
                            <td><a href="#">Ver</a> | <a href="#">Editar</a> | <a href="#">Eliminar</a></td> 
                        </tr>
        </table>
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
    </div>
    
</div>
