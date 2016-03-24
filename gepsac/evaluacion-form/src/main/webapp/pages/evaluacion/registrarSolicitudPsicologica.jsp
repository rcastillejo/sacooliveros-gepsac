
<%@page import="com.sacooliveros.gepsac.model.comun.Usuario"%>
<script type = "text/javascript" >
    //alert('COLORRRR' + gOptions.color);
    var formatDate = 'dd/mm/yy';
    var table = '#div-resultado';
    var action = '/SolicitudPsicologica.do';

    var serviceIP = "<%=request.getLocalAddr()%>";
    var fromUrl;
    var serviceUrl = "http://" + serviceIP + ":8180/gepsac-service/evaluacion";
    var codigoEvaluacion;

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

        $("#btnCancelar").click(function (e) {
            e.preventDefault();
            location.assign("<%=request.getContextPath()%>");
        }

        $("#btnRegistrar").click(function (e) {
        e.preventDefault();
        /*var sError = validarResolver();
         if (sError === "") {
         resolverAcosoEscolar();
         } else {
         fn_mdl_alert(sError, null, "MENSAJE");
         }*/
        registrarSolictudPsicologica();
    });
    });
            function registrarSolictudPsicologica() {

                var data = getData($("#evaluacion"));
                data.alumnoInvolucrado = obtenerAlumnoInvolucrados();

                console.log('grabando', data);

                $.ajax({
                    type: "POST",
                    dataType: 'json',
                    data: JSON.stringify(data),
                    contentType: "application/json",
                    url: serviceUrl + '/solicitudPsicologica'
                }).done(function (result) {
                    console.log('result', result);
                    fn_mdl_alert(result, function () {}, "CONFIRMACION");
                }).fail(function (error, status, a) {
                    console.log('error', error, 'status', status, 'a', a);
                    if (error && error.status === 400) {
                        fn_mdl_alert(error.responseText, function () {}, "MENSAJE");
                    } else {
                        fn_mdl_alert(error.responseText, function () {
                            location.assign("<%=request.getContextPath()%>" + action + '?method=init');
                        }, "MENSAJE");
                    }
                });
            }

    //obtenerAlumnoInvolucrados

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
            $('#btnBuscarAlumnoNuevo').attr('disabled', 'disabled');
            $('#btnEvaluar').attr('disabled', 'disabled');
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

    //Buscar al Alumno Nuevo
    $("#btnBuscarAlumno").click(function (e) {
        e.preventDefault();
        fn_util_AbreModal("",
                "<%=request.getContextPath()%>" + '/pages/evaluacion/buscarAlumno.jsp',
                900, 600, null);
    });

    $("#btnBuscarAlumnoInvolucrado").click(function (e) {
        e.preventDefault();
        fn_util_AbreModal("",
                "<%=request.getContextPath()%>" + '/pages/evaluacion/buscarAlumnoInvolucrado.jsp',
                900, 600, null);
    });

    function cargarAlumno(json) {
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

    function cargarAlumnoInvolucrado(json) {
        cargarAlumnoInvolucrado(json);
        fn_util_CierraModal();

    }

    function cargarAlumnoInvolucrado(json) {
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
        Registrar Solicitud Psicol&oacute;gica
    </div>
    <div id="dvData">
        <fieldset>
            <legend>Datos de la Solicitud</legend>
            <table>
                <tr>
                    <td>Fecha Registro</td><td>:</td> 
                    <td><input id="fechaRegistro" type="text" disabled="true" size="10" ></td>
                </tr>
                <tr>
                    <td>Solicitante</td><td>:</td>
                    <%
                        Usuario usuario = (Usuario) session.getAttribute("session");
                    %>
                    <td>
                        <input id="solicitante" type="hidden" value="<%= usuario.getCodigo()%>" 
                               class="inputValue" data-name="solicitante.codigo">


                        <input id="solicitanteSolicitud" type="text" disabled="true" class="inputValue" 
                               value="<%= usuario.getNombres() + ' ' + usuario.getApellidos()%>">
                    </td>
                </tr>
                <tr>
                    <td>Motivo</td><td>:</td>
                    <td><select id="motivoSolicitud" class="inputValue" data-name="motivo">
                            <option value="0" selected="selected">-- Seleccionar --</option>    
                            <option value="1">Agresi&oacute;n</option>
                            <option value="2">Bajo Rendimiento</option>
                            <option value="3">Comportamiento Sospechoso</option>
                        </select></td>
                </tr>
                <tr>
                    <td>Descripci&oacute;n</td><td>:</td>
                    <td><textarea id="descripcionSolicitud" class="inputValue" data-name="descripcion" rows="4" cols="50"></textarea></td>                   
                </tr>
            </table>
        </fieldset>
        <fieldset>
            <legend>Datos del Alumno</legend>
            <table>
                <tr>
                    <td>C&oacute;digo</td><td>:</td>
                    <td><input id="codigoAlumno" type="text" disabled="true" class="inputValue" data-name="alumno.codigo"></td>
                    <td colspan="2"><input type="button" id="btnBuscarAlumno" value="Buscar Alumno" /></td>
                </tr>
                <tr>
                    <td>Alumno</td><td>:</td> 
                    <td><input id="nombrecompletoAlumno" type="text" disabled="true" data-name="alumno.nombrecompleto"></td>
                    <td>Edad</td><td>:</td> 
                    <td><input id="edadAlumno" type="text" disabled="true" data-name="alumno.edad"></td>
                </tr>
                <tr>
                    <td>Direcci&eacute;n</td><td>:</td> 
                    <td>
                        <input id="direccionAlumno" type="text" disabled="true" data-name="alumno.direccion">
                    </td>
                    <td>Distrito</td><td>:</td> 
                    <td>
                        <input id="distritoAlumno" type="text" disabled="true" data-name="alumno.distrito">
                    </td>
                </tr>
            </table>
        </fieldset>
        <fieldset id="alumnosInvolucrados" hidden="true">
            <legend>Datos de Alumnos Involucrados</legend>
            <div id="rowDetalle" style="display:none;">   
                <table>
                    <tr>
                        <td>
                            <label id="lblNro" class="inputValue"></label>
                        </td>
                        <td>
                            <label id="lblAlumno" class="inputValue"></label>
                        </td>
                        <td>
                            <label id="lblGrado" class="inputValue"></label>
                        </td>
                        <td>
                            <a id="chkIdQuitar" href="<%=request.getContextPath()%>" >
                                Quitar
                            </a>
                        </td>
                    </tr>
                </table>
            </div>
            <input type="button" id="btnBuscarAlumnoInvolucrado" value="Agregar Alumno Involucrado">
            <table id="tblDetalle" border="0" cellpadding="3" cellspacing="0" class="css_grilla">
                <thead>    <tr>
                        <td>Nro</td> 
                        <td>Alumno</td> 
                        <td>Grado</td> 
                        <td>Opci&oacute;n</td> 
                    </tr> </thead>
                <tbody></tbody>
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
        <input type="button" id="btnGuardar" value="Guardar" />
        <input type="button" id="btnCancelar" value="Cancelar" />
    </div>
</div>
