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
    var fromUrl;
    var serviceUrl = "http://localhost:8180/gepsac-service/experto";
    var action = '/GenerarExplicacion.do';
    $(document).ready(function () {
        init();

        //Cancelar la Evaluacion del Alumno Nuevo
        $("#btnCancelar").click(function (e) {
            e.preventDefault();
            location.assign(fromUrl);
        });

        //Buscar al Alumno Nuevo
        $("#btnBuscarAlumnoNuevo").click(function (e) {
            e.preventDefault();
            fn_util_AbreModal("",
                    "<%=request.getContextPath()%>" + '/pages/experto/buscarAlumnoNuevo.jsp',
                    900, 600, null);
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
 

    function getRequestParameter(name){
        if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
           return decodeURIComponent(name[1]);
     }
     
    function init() {
        
        fromUrl = getRequestParameter("fromUrl");
        var codigoEvaluacion = getRequestParameter("codigo");
        console.log("fromUrl", fromUrl);
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: serviceUrl + "/explicacion/" + codigoEvaluacion
        }).done(function (objeto) {
            console.log('objeto', objeto);
            cargarExplicacion(objeto);
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, function () {
                location.assign("<%=request.getContextPath()%>");
            }, "MENSAJE");
        });
    }
 

    function cargarExplicacion(objeto) {
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
            <table border="1">
                <tr>
                    <td>Perfil</td> 
                    <td>Grado Similitud (%)</td>  
                </tr>
                <tr>
                    <td> Agresor</td>
                    <td><input id="agresor" type="text" disabled="true" style="text-align: right"></td>  
                </tr>
                <tr>
                    <td> V&iacute;ctima</td> 
                    <td><input id="victima" type="text" disabled="true" style="text-align: right"></td>
                </tr>
                <tr>
                    <td> Testigo</td>
                    <td><input id="testigo" type="text" disabled="true" style="text-align: right"></td>
                </tr>
            </table>
        </fieldset>
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
