
<script type='text/javascript'>
    var serviceIP = "<%=request.getLocalAddr()%>";
    var serviceUrl = "http://" + serviceIP + ":8180/gepsac-service/evaluacion";
    var action = '/RegistrarSolicitudPsicologica.do';
    var item;

    $(document).ready(function () {
        init();


        $("#btnRegistrar").click(function (e) {
            e.preventDefault();
            location.assign("<%=request.getContextPath()%>" + action + '?method=initRegistrar');
            return false;
        });

    });

    function getRequestParameter(name) {
        if (name = (new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)')).exec(location.search))
            return decodeURIComponent(name[1]);
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

    function getTimeString(fechaEvaluacion) {
        console.log('fecha evaluacion', fechaEvaluacion);
    }

    function init() {
        $("#mensaje").empty();
        $("#tblDetalle").hide();
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: serviceUrl + "/solicitudPsicologica/gepsac"
        }).done(function (listado) {
            console.log('listado', listado);
            $("#tblDetalle").show();
            cargarListado(listado);
        }).fail(function (error) {
            console.log('error', error);
            if (error && error.status === 400) {
                $("#mensaje").append(error.responseText);
            } else {
                fn_mdl_alert(error.responseText, function () {
                    location.assign("<%=request.getContextPath()%>");
                }, "MENSAJE");
            }
        });
    }


    function cargarListado(listado) {
        $("#tblDetalle tbody").empty();
        for (var i in listado) {
            cargarItem(listado[i]);
        }
    }

    function cargarItem(json) {
        console.log('cargando...', json);

        var table = $("#tblDetalle");
        var detalle = $("#rowDetalle").find("tbody tr").clone();

        detalle.find("#lblCodigo").append(json.codigo);
        detalle.find("#lblSolicitante").append(json.solicitante.nombres + ' ' + json.solicitante.apellidos);
        var motivo;
        if (json.motivo === 1) {
            motivo = 'Agresión';
        } else if (json.motivo === 2) {
            motivo = 'Bajo Rendimiento';
        } else if (json.motivo === 3) {
            motivo = 'Comportamiento Sospechoso';
        } else {
            motivo = '-';
        }

        detalle.find("#lblMotivo").append(motivo);

        var fechaSolicitud = new Date(json.fechaSolicitud);
        detalle.find("#lblFechaRegistro").append(getDateString(fechaSolicitud));
        getTimeString(fechaSolicitud);
        var fechaAtencion;
        if (json.fechaAtencion) {
            fechaAtencion = getDateString(new Date(json.fechaAtencion));
            detalle.find("#lblFechaAtencion").append();
        } else {
            fechaAtencion = '-';
        }
        detalle.find("#lblFechaAtencion").append(fechaAtencion);

        detalle.find("#lblEstado").append(json.estado.nombre);

        table.find("tbody").append(detalle);
        var chkIdConsultar = detalle.find("#chkIdConsultar");
        var chkIdEditar = detalle.find("#chkIdEditar");
        var chkIdEliminar = detalle.find("#chkIdEliminar");

        var linkConsultar = chkIdConsultar.attr('href') + action + '?method=initConsultar&codigo=' + json.codigo;
        chkIdConsultar.attr("href", linkConsultar);
        var linkEditar = chkIdEditar.attr('href') + action + '?method=initEditar&codigo=' + json.codigo;
        chkIdEditar.attr("href", linkEditar);

        chkIdEliminar.click(function (e) {
            e.preventDefault();
            fn_mdl_confirma("¿Está seguro que desea eliminar la solicitud?",
                    function () {
                        eliminar(json.codigo);
                    }, null, null, "Confirmacion");
            return false;
        });
    }


    function eliminar(codigo) {
        $.ajax({
            type: "DELETE",
            url: serviceUrl + '/solicitudPsicologica/' + codigo
        }).done(function (result) {
            console.log('result', result);
            fn_mdl_alert(result, function () {
                init();
            }, "CONFIRMACION");
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, init, "MENSAJE");
        });
    }


    function fn_checkListadoItem(objCheck, json) {
        $('input[id*="chkId"]').prop('checked', false);
        objCheck.checked = true;
        item = json;
    }

    function fn_seleccionar() {
        if (item && item !== null) {
            console.log("itemSeleccionado", item);
            parent.cargarAlumnoNuevo(item);
        } else {
            fn_mdl_alert("Debe seleccionar un alumno", null, "MENSAJE");
        }
    }

    function fn_buscar() {
        var codigo = $("#codigo").val();
        var nombres = $("#nombres").val();
        var apellidos = $("#apellidos").val();
        $.ajax({
            type: "POST",
            dataType: 'json',
            url: "<%=request.getContextPath()%>" + action + '?method=buscarAlumnoNuevo&codigo=' + codigo + '&nombres=' + nombres + '&apellidos=' + apellidos
        }).done(function (listado) {
            console.log('listado', listado);
            $("#tblDetalle tbody").empty();
            cargarListado(listado);
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, function () {}, "MENSAJE");
        });
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
        <div style="align-content: left;">
            <input type="button" id="btnRegistrar" value="Registrar">
        </div>
        <div id="rowDetalle" style="display:none;">   
            <table>
                <tr>
                    <td>
                        <label id="lblCodigo" class="inputValue"></label>
                    </td>
                    <td>
                        <label id="lblSolicitante" class="inputValue"></label>
                    </td>
                    <td>
                        <label id="lblMotivo" class="inputValue"></label>
                    </td>
                    <td>
                        <label id="lblFechaRegistro" class="inputValue"></label>
                    </td>
                    <td>
                        <label id="lblFechaAtencion" class="inputValue"></label>
                    </td>
                    <td>
                        <label id="lblEstado" class="inputValue"></label>
                    </td>
                    <td>
                        <a id="chkIdConsultar" href="<%=request.getContextPath()%>" >
                            Consultar
                        </a>
                    </td>
                    <td>
                        <a id="chkIdEditar" href="<%=request.getContextPath()%>" >
                            Editar
                        </a>
                    </td>
                    <td>
                        <a id="chkIdEliminar" href="<%=request.getContextPath()%>" >
                            Eliminar
                        </a>
                    </td>
                </tr>
            </table>
        </div>
        <table id="tblDetalle" border="0" cellpadding="3" cellspacing="0" class="css_grilla" style="display: none">
            <thead>    <tr>
                    <td>Codigo</td> 
                    <td>Solicitante</td> 
                    <td>Motivo</td>
                    <td>Fecha Solicitud</td> 
                    <td>Fecha Atencion</td> 
                    <td>Estado</td> 
                    <td colspan="3">Opciones</td> 
                </tr> </thead>
            <tbody></tbody>
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
