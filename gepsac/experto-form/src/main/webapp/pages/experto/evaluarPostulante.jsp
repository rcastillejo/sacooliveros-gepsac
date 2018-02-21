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
        initPerfiles();
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

    function noExisteAlumnoPostulante(){
        fn_util_CierraModal();
        location.assign("<%=request.getContextPath()%>");
    }

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
        if(validateUrl("<%=request.getContextPath()%>")){
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
                $('#btnBuscarAlumnoNuevo').attr('disabled', 'disabled');
                $('#btnEvaluar').attr('disabled', 'disabled');
                fn_mdl_alert(msg, function () {}, "CONFIRMACION");
            }).fail(function (error) {
                console.log('error', error);
                fn_mdl_alert(error.responseText, function () {
                    location.assign("<%=request.getContextPath()%>");
                }, "MENSAJE");
            });
        }
    }

    function cargarRespuestaEvaluacion(evaluacion) {
        var perfilMax;
        $("input[id^='P000']").removeClass('P0001 P0002 P0003 P0000');
        $("#codigoEvaluacion").val(evaluacion.codigo);
        for (var i in evaluacion.perfiles) {
            var perfilEval = evaluacion.perfiles[i];
            var el;
            if (perfilEval.perfil === undefined) {
                el = $("#mensaje");
            } /*else if (perfilEval.perfil.codigo === 'P0001') {
             el = $("#agresor");
             } else if (perfilEval.perfil.codigo === 'P0002') {
             el = $("#victima");
             } else if (perfilEval.perfil.codigo === 'P0003') {
             el = $("#testigo");
             }*/
            else {
                el = $("#" + perfilEval.perfil.codigo);
            }

            var probabilidad = new Number(perfilEval.probabilidad);
            var porcProbabilidad = (probabilidad * 100.0);
            porcProbabilidad = porcProbabilidad.toFixed(2);
            console.log('Perfil Evaluado', perfilEval, '%', porcProbabilidad);
            el.val(porcProbabilidad + '%');
            if (perfilEval.seleccionado && perfilEval.seleccionado === true) {
                perfilMax = perfilEval.perfil.codigo;
                $("#" + perfilMax).addClass(perfilMax);
            }
        }

        
    }

    function initEvaluarAlumno() {
        $("#mensajeError").empty();
        if(validateUrl("<%=request.getContextPath()%>")){            
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
    }
    
    var serviceIP = "<%=request.getLocalAddr()%>";
    var serviceUrl = "http://" + serviceIP + ":8180/gepsac-service/experto";
    function initPerfiles(){
        $.ajax({
            type: "GET",
            timeout:3000, 
            //dataType: 'json',
            url: serviceUrl + '/perfil'
        }).done(function (listado) {
            console.log('objeto', listado);
            cargarPerfiles(listado);
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, function () {
                location.assign("<%=request.getContextPath()%>");
            }, "MENSAJE");
        });
    }
    
    function cargarPerfiles(listado) {
        var table = $("#tblPerfiles tbody");
        for (var i in listado) {
            var perfil = listado[i];
            var perfilTd = $("#perfilClone").find("tbody tr").clone();
            perfilTd.find("label").append(perfil.nombre);
            perfilTd.find("input").attr("id", perfil.codigo);
            table.append(perfilTd);
        }
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
        //$("#codigoEvaluacion").val(objeto.codigo);
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

<style>
    .P0001{
        background-color:#B22222;
        color: white;
    }
    .P0002{
        background-color:#DAA520;
        color: white;
    }
    .P0003{
        background-color:#F0E68C;
        color: black;
    }
    .P0000{
        background-color:#46d246;
        color: white;
    }
</style>

<div class="div-pagina">
    <div id="div-pagina-titulo" class="div-pagina-titulo">
        Evaluar Nuevos Alumnos
    </div>
    <div id="dvData">
        <fieldset>
            <legend>Datos de la Evaluaci&oacute;n</legend>
            <table>
                <tr>
                    <td>C&oacute;digo</td><td>:</td>
                    <td><input id="codigoEvaluacion" type="text" disabled="true" class="inputValue" data-name="codigo"></td>
                    <td>Fecha Evaluaci&oacute;n</td><td>:</td> 
                    <td><input id="fechaEvaluacion" type="text" disabled="true" size="10"></td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
            <legend>Datos del Alumno</legend>
            <table>
                <tr>
                    <td colspan="6"><input type="button" id="btnBuscarAlumnoNuevo" value="Buscar Alumno Nuevo"></td>
                </tr>
                <tr>
                    <td>C&oacute;digo</td><td>:</td>
                    <td><input id="codigoAlumno" type="text" disabled="true" class="inputValue" data-name="alumno.codigo"></td>
                    <td>Nombres</td><td>:</td>
                    <td><input id="nombres" type="text" disabled="true" class="inputValue" data-name="alumno.nombres"></td>
                </tr>
                <tr>
                    <td>Apellido Paterno</td><td>:</td> 
                    <td><input id="apellidoPaterno" type="text" disabled="true" class="inputValue" data-name="alumno.apellidoPaterno"></td>
                    <td>Apellido Materno</td><td>:</td> 
                    <td><input id="apellidoMaterno" type="text" disabled="true" class="inputValue" data-name="alumno.apellidoMaterno"></td>
                </tr>
                <tr>
                    <td>G&eacute;nero</td><td>:</td> 
                    <td>
                        <input id="genero" type="text" disabled="true" class="inputValue" data-name="alumno.sexo.nombre">
                    </td>
                    <td>Edad</td><td>:</td> 
                    <td>
                        <input id="edad" type="text" disabled="true" class="inputValue" data-name="alumno.edad" size="5"> a&ntilde;os
                    </td>
                </tr>
                <tr>
                    <td>Contextura Corporal</td><td>:</td> 
                    <td>
                        <input id="contextura" type="text" disabled="true" class="inputValue" data-name="alumno.contextura.nombre">
                    </td>
                    <td>Estatura</td><td>:</td> 
                    <td>
                        <input id="estatura" type="text" disabled="true" class="inputValue" data-name="alumno.estatura.nombre">
                    </td>
                </tr>
                <tr>
                    <td>Direcci&oacute;n</td><td>:</td> 
                    <td colspan="7">
                        <input id="direccion" type="text" disabled="true" class="inputValue" data-name="alumno.direccion" size="68">
                    </td>
                </tr>
                <tr>
                    <td>Distrito</td><td>:</td> 
                    <td>
                        <input id="distrito" type="text" disabled="true" class="inputValue" data-name="alumno.distrito.nombre">
                    </td>
                    <td>Provincia</td><td>:</td> 
                    <td>
                        <input id="provincia" type="text" disabled="true" class="inputValue" data-name="alumno.provincia.nombre">
                    </td>
                </tr>
                <tr>		
                    <td>Departamento</td><td>:</td> 
                    <td><input id="departamento" type="text" disabled="true" class="inputValue" data-name="alumno.departamento.nombre"></td>
                    <td>Nacionalidad</td><td>:</td> 
                    <td><input id="nacionalidad" type="text" disabled="true" class="inputValue" data-name="alumno.nacionalidad.nombre"></td>
                </tr>
                <tr>	
                    <td>Religi&oacute;n</td><td>:</td> 
                    <td><input id="religion" type="text" disabled="true" class="inputValue" data-name="alumno.religion.nombre"></td>
                </tr>
                <tr>
                    <td>
                        Grado Escolar</td><td>:</td> 
                    <td> 
                        <input id="gradoEscolar" type="text" disabled="true" class="inputValue" data-name="alumno.gradoEscolar" size="5"> °
                    </td>
                    <td>Nivel Escolar</td><td>:</td> 
                    <td> 
                        <input id="nivelEscolar" type="text" disabled="true" class="inputValue" data-name="alumno.nivelEscolar.nombre">
                    </td>
                </tr>
                <tr>
                    <td>
                        Promedio Escolar</td><td>:</td> 
                    <td>
                        <input id="promedioEscolar" type="text" disabled="true" class="inputValue" data-name="alumno.promedioEscolar">
                    </td>
                    <td>
                        Cambios de colegios</td><td>:</td> 
                    <td>
                        <input id="cantCambioColegio" type="text" disabled="true" class="inputValue" data-name="alumno.cantCambioColegio" size="5"> veces
                    </td>
                </tr>
                <tr>
                    <td>
                        Tipo de Familia</td><td>:</td> 
                    <td> 
                        <input id="tipoFamilia" type="text" disabled="true" class="inputValue" data-name="alumno.tipoFamilia.nombre">
                    </td>
                </tr>
                <tr>
                    <td>
                        Orden de nacimiento</td><td>:</td> 
                    <td>
                        <input id="ordenNacimiento" type="text" disabled="true" class="inputValue" data-name="alumno.ordenNacimiento" size="5"> °
                    </td>
                    <td>
                        Cantidad de hermanos</td><td>:</td> 
                    <td>
                        <input id="cantHnos" type="text" disabled="true" class="inputValue" data-name="alumno.cantHnos">
                    </td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
            <legend>Resultado de la Evaluaci&oacute;n</legend>
            <table id="tblPerfiles" border="1">
                <thead>                    
                    <tr>
                        <th>Perfil</th> 
                        <th>Porcentaje Similitud (%)</th>  
                    </tr>
                </thead>
                <tbody></tbody>
                <!--<tr>
                    <td> Agresor</td>
                    <td><input id="P0001" type="text" disabled="true" style="text-align: right" value="0.00%"></td>  
                </tr>
                <tr>
                    <td> V&iacute;ctima</td> 
                    <td><input id="P0002" type="text" disabled="true" style="text-align: right" value="0.00%"></td>
                </tr>
                <tr>
                    <td> Testigo</td>
                    <td><input id="P0003" type="text" disabled="true" style="text-align: right" value="0.00%"></td>
                </tr>
                <tr>
                    <td> No Identificado</td>
                    <td><input id="P0000" type="text" disabled="true" style="text-align: right" value="0.00%"></td>
                </tr>-->
            </table>
        </fieldset>
        
        <div id="perfilClone" style="display:none">
            <table>
                <tr>
                    <td>
                        <label for="probPerfil" />
                    </td>
                    <td>
                        <input name="probPerfil" type="text" disabled="true" style="text-align: right" value="0.00%">
                    </td>                     
                </tr>
            </table>
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
    <div style="align-content: center; text-align: center">
        <input type="button" id="btnEvaluar" value="Evaluar" />
        <input type="button" id="btnCancelar" value="Cancelar" />
    </div>
</div>
