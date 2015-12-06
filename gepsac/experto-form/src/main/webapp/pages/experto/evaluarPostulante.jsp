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
                    900, 500, null);
        });

        //Evaluar al Alumno Nuevo
        $("#btnEvaluar").click(function (e) {
            e.preventDefault();

            var sError = validarEvaluacion();

            if (sError === "") {
                evaluarAlumnoNuevo();
            } else {
                fn_mdl_alert(sError, null, "VALIDACIONES");
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
            fn_mdl_alert(msg, function () {}, "CONFIRMACION");
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, function () {
                location.assign("<%=request.getContextPath()%>");
            }, "CONFIRMACION");
        });

        //$("#cphCuerpo_txtDetalle").val(JSON.stringify(listado));
        //console.log('detalle json', $("#cphCuerpo_txtDetalle").val());

    }
    
    function cargarRespuestaEvaluacion(evaluacion){        
        for (var i in evaluacion.perfiles) {
            var perfilEval = evaluacion.perfiles[i];
            var el;            
            if(perfilEval.perfil === undefined){
                el = $("#mensaje");
            } else if(perfilEval.perfil.codigo === 'P0001'){
                el = $("#agresor");
            } else if(perfilEval.perfil.codigo === 'P0002'){
                el = $("#victima");
            } else if(perfilEval.perfil.codigo === 'P0003'){
                el = $("#testigo");
            }
            var probabilidad = new Number(perfilEval.probabilidad);
            var porcProbabilidad = (probabilidad * 100.0);
            console.log('Perfil Evaluado', perfilEval, '%',porcProbabilidad)
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
            }, "VALIDACIONES");
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
        $("#fechaEvaluacion").val($.datepicker.formatDate(formatDate, fechaEvaluacion));
        console.log('fechaEvaluacion', fechaEvaluacion);
    }

    function cargarAlumnoNuevo(json) {
        cargarAlumno(json);
        fn_util_CierraModal();

    }

    function cargarAlumno(json) {
            console.log('cargando...', json);

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
            $("#cantCambioColegio").val(json.nroCambioColegio);
            $("#tipoFamilia").val(json.tipoFamilia);
            $("#ordenNacimiento").val(json.ordenNacimiento);
            $("#cantHnos").val(json.numHnos);
  
    }

</script>

<div class="div-pagina">
    <div id="div-pagina-titulo" class="div-pagina-titulo">
        Evaluaci&oacute;n de Acoso Escolar del Alumno Postulante
    </div>
    <div id="dvData">
            <div>
                <div>
                    Datos de la Evaluaci&oacute;n
                </div>
                <div>
                    <div>
                        C&oacute;digo: 
                        <input id="codigoEvaluacion" type="text" disabled="true" class="inputValue" data-name="codigo">
                    </div>
                    <div>
                        Fecha: 
                        <input id="fecha" type="text" disabled="true" class="inputValue" data-name="fechaEvaluacion">
                    </div>
                </div>
                <div class="no-float"></div>
            </div>
            <div>
                <div>
                    <input type="button" id="btnBuscarAlumnoNuevo" value="Buscar Alumno Nuevo" />
                </div>
                <div>
                    Datos del Alumno Postulante
                </div>
                <div>
                    <div>
                        C&oacute;digo: 
                        <input id="codigoAlumno" type="text" disabled="true" class="inputValue" data-name="alumno.codigo">
                    </div>
                    <div>
                        Nombres: 
                        <input id="nombres" type="text" disabled="true" class="inputValue" data-name="alumno.nombres">
                    </div>
                    <div>
                        Apellido Paterno: 
                        <input id="apellidoPaterno" type="text" disabled="true" class="inputValue" data-name="alumno.apellidoPaterno">
                    </div>
                    <div>
                        Apellido Materno: 
                        <input id="apellidoMaterno" type="text" disabled="true" class="inputValue" data-name="alumno.apellidoMaterno">
                    </div>
                    <div>
                        G&eacute;nero 
                        <input id="genero" type="text" disabled="true" class="inputValue" data-name="alumno.sexo.nombre">
                    </div>
                    <div>
                        Edad: 
                        <input id="edad" type="text" disabled="true" class="inputValue" data-name="alumno.edad">
                    </div>
                    <div>
                        Contextura Corporal: 
                        <input id="contextura" type="text" disabled="true" class="inputValue" data-name="alumno.contextura.nombre">
                    </div>
                    <div>
                        Estatura: 
                        <input id="estatura" type="text" disabled="true" class="inputValue" data-name="alumno.estatura.nombre">
                    </div>
                    <div>
                        Direcci&oacute;n: 
                        <input id="direccion" type="text" disabled="true" class="inputValue" data-name="alumno.direccion">
                    </div>
                    <div>
                        Distrito: 
                        <input id="distrito" type="text" disabled="true" class="inputValue" data-name="alumno.distrito.nombre">
                    </div>
                    <div>
                        Provincia: 
                        <input id="provincia" type="text" disabled="true" class="inputValue" data-name="alumno.provincia.nombre">
                    </div>
                    <div>
                        Departamento: 
                        <input id="departamento" type="text" disabled="true" class="inputValue" data-name="alumno.departamento.nombre">
                    </div>
                    <div>
                        Nacionalidad: 
                        <input id="nacionalidad" type="text" disabled="true" class="inputValue" data-name="alumno.nacionalidad.nombre">
                    </div>
                    <div>
                        Religi&oacute;n: 
                        <input id="religion" type="text" disabled="true" class="inputValue" data-name="alumno.religion.nombre">
                    </div>
                    <div>
                        Nivel Escolar: 
                        <input id="nivelEscolar" type="text" disabled="true" class="inputValue" data-name="alumno.nivelEscolar.nombre">
                    </div>
                    <div>
                        Grado Escolar: 
                        <input id="gradoEscolar" type="text" disabled="true" class="inputValue" data-name="alumno.gradoEscolar">
                    </div>
                    <div>
                        Cantidad de cambios en colegios
                        <input id="cantCambioColegio" type="text" disabled="true" class="inputValue" data-name="alumno.cantCambioColegio">
                    </div>
                    <div>
                        Tipo de Familia: 
                        <input id="tipoFamilia" type="text" disabled="true" class="inputValue" data-name="alumno.tipoFamilia.nombre">
                    </div>
                    <div>
                        Orden de nacimiento: 
                        <input id="ordenNacimiento" type="text" disabled="true" class="inputValue" data-name="alumno.ordenNacimiento">
                    </div>
                    <div>
                        Cantidad de hermanos: 
                        <input id="cantHnos" type="text" disabled="true" class="inputValue" data-name="alumno.cantHnos">
                    </div>
                </div>
                <div class="no-float"></div>
            </div>
            <div>
                <div>
                    Resultados de la Evaluaci&oacute;n
                </div>
                <div>
                    <div>
                        Agresor: 
                        <input id="agresor" type="text" disabled="true" style="text-align: right">
                    </div>                    
                    <div>
                        V&iacute;ctima: 
                        <input id="victima" type="text" disabled="true" style="text-align: right">
                    </div>
                    <div>
                        Testigo: 
                        <input id="testigo" type="text" disabled="true" style="text-align: right">
                    </div>
                </div>
                <div class="no-float"></div>
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
    </div>
    <div>
       <input type="button" id="btnEvaluar" value="Evaluar" />
       <input type="button" id="btnCancelar" value="Cancelar" />
    </div>
</div>
