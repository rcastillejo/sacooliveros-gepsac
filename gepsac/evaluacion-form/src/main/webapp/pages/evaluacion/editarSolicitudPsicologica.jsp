
<%@page import="com.sacooliveros.gepsac.model.comun.Usuario"%>
<script type = "text/javascript" >
    //alert('COLORRRR' + gOptions.color);
    var formatDate = 'dd/mm/yy';
    var table = '#div-resultado';
    var action = '/RegistrarSolicitudPsicologica.do';

    var serviceIP = "<%=request.getLocalAddr()%>";

    var serviceUrl = "http://" + serviceIP + ":8180/gepsac-service/evaluacion";
    var codigoSolicitud;
    var alumnos = [];

    $(document).ready(function () {
        codigoSolicitud = getRequestParameter("codigo");

        init();

        //Cancelar la Evaluacion del Alumno Nuevo
        $("#btnCancelar").click(function (e) {
            e.preventDefault();
            location.assign("<%=request.getContextPath()%>" + action + '?method=init');
        });

        //Buscar al Alumno Nuevo
        $("#btnBuscarAlumnoNuevo").click(function (e) {
            e.preventDefault();
            fn_util_AbreModal("",
                    "<%=request.getContextPath()%>" + '/pages/experto/buscarAlumnoNuevo.jsp',
                    900, 600, null);
        });

        $("#btnGuardar").click(function (e) {
            e.preventDefault();
            editarSolictudPsicologica();
        });


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

    });

    function getRequestParameter(name) {
        if (name = (new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)')).exec(location.search))
            return decodeURIComponent(name[1]);
    }

    function init() {
        $("#motivoSolicitud").on('change', function () {
            var motivo = $(this).val();
            console.log('motivo', motivo);
            if (motivo === '1') {
                $("#alumnosInvolucrados").show();
            } else {
                $("#alumnosInvolucrados").hide();
            }
        });

        initEditar(codigoSolicitud);

    }

    function initEditar(codigo) {
        console.log('initEditar', codigo);
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: serviceUrl + '/solicitudPsicologica/editar=' + codigo
        }).done(function (objeto) {
            console.log('objeto', objeto);
            cargar(objeto);
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, function(){
                location.assign("<%=request.getContextPath()%>" + action + '?method=init');
            }, "MENSAJE");
        });
    }


    function cargar(objeto) {
        $("#hdnCodigo").val(objeto.codigo);
        var alumnosInvolucrados;
        $.ajax({
            async: false,
            type: "POST",
            dataType: 'json',
            url: "<%=request.getContextPath()%>" + '/EvaluarPostulante.do?method=initBuscarAlumnoNuevo'
        }).done(function (listado) {
            console.log('listado', listado);
            alumnosInvolucrados = listado;
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert('Ocurrio un error al consultar los alumnos involucrados', function(){
                location.assign("<%=request.getContextPath()%>" + action + '?method=init');
            }, "MENSAJE");
        });

        console.log('involucrados', alumnosInvolucrados);

        if (objeto.fechaSolicitud) {
            $("#fechaRegistro").val(getDateString(new Date(objeto.fechaSolicitud)));
        }

        //Seleccionando el motivo
        $("#motivoSolicitud option[value='" + objeto.motivo + "']").prop('selected', 'selected');

        $("#descripcionSolicitud").val(objeto.descripcion);


        var alumnosWS = [];
        //Agregando Alumnos
        for (var i in objeto.alumnoInvolucrado) {
            var alumno = objeto.alumnoInvolucrado[i];
            if (alumno.dirigido) {
                var alumnoWS = obtenerInvolucrado(alumnosInvolucrados, alumno);
                if (alumnoWS) {
                    //Cargando Alumno Dirigido
                    cargarAlumnoDirigido(alumnoWS);
                }

            } else {
                var alumnoWS = obtenerInvolucrado(alumnosInvolucrados, alumno);
                alumnosWS.push(alumnoWS);
            }
        }
        if (alumnosWS.length > 0) {         
            //Cargando Alumnos Involucrados
            cargarAlumnoInvolucrados(alumnosWS);
        }
        
        if(objeto.motivo === 1){            
            $("#alumnosInvolucrados").show();   
        }else{
            $("#alumnosInvolucrados").hide();   
        }
    }

    function obtenerInvolucrado(alumnosInvolucrados, alumno) {

        var alumnoWS;
        for (var j in alumnosInvolucrados) {
            var involucrado = alumnosInvolucrados[j];
            if (involucrado && involucrado.codigo === alumno.alumno.codigo) {
                alumnoWS = involucrado;
                break;
            }
        }
        return alumnoWS;

    }

    function editarSolictudPsicologica() {

        var data = getData($("#tblSolicitud"));
        data.alumnoInvolucrado = alumnos;

        console.log('grabando', data);

        $.ajax({
            type: "PUT",
            data: JSON.stringify(data),
            contentType: "application/json",
            url: serviceUrl + '/solicitudPsicologica'
        }).done(function (result) {
            console.log('result', result);
            fn_mdl_alert(result, function () {                
                location.assign("<%=request.getContextPath()%>" + action + '?method=init');
            }, "CONFIRMACION");
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

    function getAlumnoDTO(json) {
        var alumno = {
            codigo: json.codigo,
            nombres: json.nombres,
            apellidoPaterno: json.apellidoPaterno,
            apellidoMaterno: json.apellidoMaterno,
            sexo: {
                nombre: json.genero
            },
            edad: json.edad,
            contextura: {
                nombre: json.contextura
            },
            estatura: {
                nombre: json.altura
            },
            direccion: json.domicilio,
            distrito: {
                nombre: json.distrito
            },
            provincia: {
                nombre: json.provincia
            },
            departamento: {
                nombre: json.departamento
            },
            nacionalidad: {
                nombre: json.nacionalidad
            },
            religion: {
                nombre: json.religion
            },
            gradoEscolar: json.gradoEscolar,
            nivelEscolar: {
                nombre: json.nivelEscolar
            },
            promedioEscolar: json.promedioEscolar,
            cantCambioColegio: json.nroCambioColegio,
            tipoFamilia: {
                nombre: json.tipoFamilia
            },
            ordenNacimiento: json.ordenNacimiento,
            cantHnos: json.numHnos
        };

        return alumno;
    }

    function cargarAlumnoDirigido(json) {
        if (!alumnoEstaAgregado(json)) {
            cargarAlumno($("#tblAlumnoDirigido"), json);


            alumnos.push({
                alumno: getAlumnoDTO(json),
                dirigido: true
            });
            fn_util_CierraModal();
        } else {
            fn_mdl_alert('El alumno ya se encuentra agregado en la solicitud', fn_util_CierraModal, "MENSAJE");
        }

    }

    function cargarAlumno(el, json) {
        console.log('cargando...', json);
        var promedioEscolar = json.promedioEscolar;
        promedioEscolar = promedioEscolar.toFixed(2);

        el.find("#codigoAlumno").val(json.codigo);

        el.find("#nombrecompletoAlumno").val(json.nombres + ' ' + json.apellidoPaterno + ' ' + json.apellidoMaterno);

        el.find("#direccionAlumno").val(json.domicilio);
        el.find("#edadAlumno").val(json.edad);
        el.find("#distritoAlumno").val(json.distrito);


        /*$("#genero").val(json.genero);
         $("#contextura").val(json.contextura);
         $("#estatura").val(json.altura);
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
         $("#cantHnos").val(json.numHnos);*/
    }

    function alumnoEstaAgregado(item) {
        for (var i in alumnos) {
            var solicitudAlumno = alumnos[i];
            if (solicitudAlumno && solicitudAlumno.alumno && solicitudAlumno.alumno.codigo === item.codigo) {
                return true;
            }
        }
        return false;
    }

    function quitarAlumno(item) {

        console.log('eliminando de lista', item);
        for (var i in alumnos) {
            var solicitudAlumno = alumnos[i];
            console.log('comparando alumno', solicitudAlumno, 'item', item);
            if (solicitudAlumno && solicitudAlumno.alumno && solicitudAlumno.alumno.codigo === item.codigo) {
                alumnos.splice(i, 1);
                console.log('eliminado alumno', alumnos);
                return true;
            }
        }
        return false;
    }

    function cargarAlumnoInvolucrados(listado) {
        var msg = '';
        for (var i in listado) {
            var item = listado[i];

            if (!alumnoEstaAgregado(item)) {
                cargarAlumnoInvolucrado(listado[i], parseInt(i) + 1);
                alumnos.push({
                    /*alumno: {
                     codigo: item.codigo
                     }*/
                    alumno: getAlumnoDTO(item),
                    dirigido: false
                });
            } else {
                msg += '<p>' + item.codigo + ': ' + item.nombres + ' ' + item.apellidoPaterno + ' ' + item.apellidoMaterno + '</p>';
            }
        }

        fn_util_CierraModal();

        if (msg !== '') {
            fn_mdl_alert('No se pudo agregar a los siguientes alumnos, ya que se encontraban en la solicitud:' + msg, null, "MENSAJE");
        }
    }


    function cargarAlumnoInvolucrado(json, idx) {

        var table = $("#tblDetalle");
        var detalle = $("#rowDetalle").find("tbody tr").clone();

        detalle.find("#hdnCodigo").val(json.codigo);
        detalle.find("#lblNro").append(json.codigo);
        detalle.find("#lblGrado").append(json.gradoEscolar + '° ' + json.nivelEscolar);
        detalle.find("#lblAlumno").append(json.nombres + ' ' + json.apellidoPaterno + ' ' + json.apellidoMaterno);


        table.find("tbody").append(detalle);

        var chkIdQuitar = detalle.find("#chkIdQuitar");

        chkIdQuitar.click(function (e) {
            e.preventDefault();
            fn_mdl_confirma("¿Está seguro de eliminar el alumno involucrado?<br/>" + json.nombres + ' ' + json.apellidoPaterno + ' ' + json.apellidoMaterno,
                    function () {
                        detalle.remove();
                        var resultado = quitarAlumno(json);
                        console.log('resultado de la eliminacion', resultado, 'alumnos', alumnos);
                    }, null, null, "Confirmacion");
            return false;
        });

    }

    function getDateString(fechaEvaluacion) {
        var twoDigitMonth = fechaEvaluacion.getMonth() + 1 + "";
        if (twoDigitMonth.length === 1)
            twoDigitMonth = "0" + twoDigitMonth;
        var twoDigitDate = fechaEvaluacion.getDate() + "";
        if (twoDigitDate.length === 1)
            twoDigitDate = "0" + twoDigitDate;
        var fechaActual = twoDigitDate + '/' + twoDigitMonth + '/' + fechaEvaluacion.getFullYear();
        return fechaActual;
    }

</script>

<div class="div-pagina">
    <div id="div-pagina-titulo" class="div-pagina-titulo">
        Editar Solicitud Psicol&oacute;gica
    </div>
    <div id="dvData">
        <fieldset>
            <legend>Datos de la Solicitud</legend>
            <table id="tblSolicitud">
                <tr>
                <input id="hdnCodigo" type="hidden" class="inputValue" data-name="codigo">
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


                        <input id="solicitanteSolicitud" type="text" disabled="true"
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
                    <td><textarea id="descripcionSolicitud" class="inputValue" data-name="descripcion" rows="4" cols="50" maxlength="200"></textarea></td>                   
                </tr>
            </table>
        </fieldset>
        <br/>
        <fieldset>
            <legend>Datos del Alumno</legend>
            <table id="tblAlumnoDirigido">
                <tr>
                    <td>C&oacute;digo</td><td>:</td>
                    <td><input id="codigoAlumno" type="text" disabled="true"  data-name="alumno.codigo"></td>
                    <td colspan="2"><input type="button" id="btnBuscarAlumno" value="Buscar Alumno" /></td>
                </tr>
                <tr>
                    <td>Alumno</td><td>:</td> 
                    <td><input id="nombrecompletoAlumno" type="text" disabled="true" data-name="alumno.nombrecompleto"></td>
                    <td>Edad</td><td>:</td> 
                    <td><input id="edadAlumno" type="text" disabled="true" data-name="alumno.edad"></td>
                </tr>
                <tr>
                    <td>Direcci&oacute;n</td><td>:</td> 
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
        <br/>
        <fieldset id="alumnosInvolucrados" hidden="true">
            <legend>Datos de Alumnos Involucrados</legend>
            <div id="rowDetalle" style="display:none;">   
                <table>
                    <tr>
                        <td>
                            <input id="hdnCodigo" type="hidden" data-name="usuario.codigo"></label>
                            <label id="lblNro" ></label>
                        </td>
                        <td>
                            <label id="lblAlumno" ></label>
                        </td>
                        <td>
                            <label id="lblGrado" ></label>
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
                <thead>    
                    <tr>
                        <td>C&oacute;digo</td> 
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
