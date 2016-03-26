
<script type='text/javascript'>
    var serviceIP = "<%=request.getLocalAddr()%>";
    var servicePath = "/regla";
    var servicePathPregunta = "/pregunta";
    var servicePathPerfil = "/perfil";
    var serviceUrl = "http://" + serviceIP + ":8180/gepsac-service/experto" + servicePath;
    var serviceUrlPregunta = "http://" + serviceIP + ":8180/gepsac-service/experto" + servicePathPregunta;
    var serviceUrlPerfil = "http://" + serviceIP + ":8180/gepsac-service/experto" + servicePathPerfil;
    var action = '/MantenerRegla.do';

    $(document).ready(function () {
        initPreguntas();
        initPerfiles();
        init();


        //Agregar Regla
        $("#btnAgregar").click(function (e) {
            e.preventDefault();
            var data = getData($("#editarRegla"));
            data.preguntas = obtenerPreguntas();
            agregar(data);
        });

        //Modificacion Regla
        $("#btnModificar").click(function (e) {
            e.preventDefault();
            var data = getData($("#editarRegla"));
            data.preguntas = obtenerPreguntas();
            editar(data);
        });

        //Cancelar Modificacion
        $("#btnCancelar").click(function (e) {
            e.preventDefault();
            reset();
        });

        //Cerrar Mantenimiento
        $("#btnCerrar").click(function (e) {
            e.preventDefault();
            location.assign("<%=request.getContextPath()%>");
        });
    });

    function obtenerPreguntas() {
        var listadoVal = [];
        var elPreguntas = $("select[id^='cboPregunta'][value!='']");

        console.log('elPreguntas', elPreguntas.length);
        elPreguntas.map(function (index, elem) {

            var preguntaRegla = {
                pregunta: {
                    codigo: $(this).val()
                }
            };

            listadoVal.push(preguntaRegla);
            console.log('listDetalle', listadoVal);
        });

        return listadoVal;
    }

    function getRequestParameter(name) {
        if (name = (new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)')).exec(location.search))
            return decodeURIComponent(name[1]);
    }

    function initPreguntas() {
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: serviceUrlPregunta
        }).done(function (listado) {
            console.log('listado', listado);
            cargarPreguntas(listado);
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, function () {
                location.assign("<%=request.getContextPath()%>");
            }, "MENSAJE");
        });
    }

    function cargarPreguntas(listado) {
        for (var i in listado) {
            var pregunta = listado[i];
            agregarPregunta(pregunta);
        }
    }

    function agregarPregunta(pregunta) {
        var option = $("<option>");
        option.val(pregunta.codigo);
        option.append(pregunta.enunciado);
        $("#cboPregunta0").append(option);

        var option2 = $("<option>");
        option2.val(pregunta.codigo);
        option2.append(pregunta.enunciado);
        $("#cboPregunta1").append(option2);

        var option3 = $("<option>");
        option3.val(pregunta.codigo);
        option3.append(pregunta.enunciado);
        $("#cboPregunta2").append(option3);

    }

    function initPerfiles() {
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: serviceUrlPerfil
        }).done(function (listado) {
            console.log('listado', listado);
            cargarPerfiles(listado);
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, function () {
                location.assign("<%=request.getContextPath()%>");
            }, "MENSAJE");
        });
    }

    function cargarPerfiles(listado) {
        for (var i in listado) {
            var perfil = listado[i];
            if (perfil.codigo !== 'P0000') {

                var table = $("#perfiles");

                var detalle = $("#tmpPerfil").find("div").clone();

                detalle.find("#lblPerfil").append(perfil.nombre);
                detalle.find("#rdPerfil").val(perfil.codigo);

                table.append(detalle);
            }
        }
    }

    function init() {
        $("#tblDetalle").find("tbody").empty();
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: serviceUrl
        }).done(function (listado) {
            console.log('listado', listado);
            cargarListado(listado);
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, null, "MENSAJE");
        });
    }


    function cargarListado(listado) {
        for (var i in listado) {
            cargarItem(listado[i]);
        }
    }

    function cargarItem(json) {
        console.log('cargando...', json);

        var table = $("#tblDetalle");

        var detalle = $("#rowDetalle").find("tbody tr").clone();

        detalle.find("#lblCodigo").append(json.codigo);

        var formula = "Si ";
        for (var i in json.preguntas) {

            var preguntaRegla = json.preguntas[i];
            formula += preguntaRegla.pregunta.enunciado;
            if (i < (json.preguntas.length - 1)) {
                formula += " y ";
            }
        }
        formula += " entonces " + json.perfil.nombre;

        detalle.find("#lblReglaFormula").append(formula);
        detalle.find("#lblEstado").append(json.deshabilitado ? 'Deshabilitado' : 'Habilitado');


        table.find("tbody").append(detalle);
        var lnkEditar = detalle.find("#lnkEditar");
        var lnkEliminar = detalle.find("#lnkEliminar");

        if (json.deshabilitado) {
            lnkEditar.remove();
            lnkEliminar.remove();
        } else {
            lnkEditar.click(function (e) {
                e.preventDefault();
                $("#opcAgregar").hide();
                $("#opcModificar").show();
                initEditar(json.codigo);
                return false;
            });

            lnkEliminar.click(function (e) {
                e.preventDefault();
                fn_mdl_confirma("¿Está seguro que desea eliminar la regla?",
                        function () {
                            eliminar(json.codigo);
                        }, null, null, "Confirmacion");
                return false;
            });
        }
    }

    function initEditar(codigo) {
        console.log('initEditar', codigo);
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: serviceUrl + '/' + codigo
        }).done(function (objeto) {
            console.log('objeto', objeto);
            cargar(objeto);
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, null, "MENSAJE");
        });
    }

    function agregar(data) {
        console.log('agregar', data);
        $.ajax({
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            url: serviceUrl
        }).done(function (result) {
            console.log('result', result);
            fn_mdl_alert(result, function () {
                init();
                reset();
            }, "CONFIRMACION");
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, null, "MENSAJE");
        });
    }

    function editar(data) {
        console.log('editar', data);
        $.ajax({
            type: "PUT",
            data: JSON.stringify(data),
            contentType: "application/json",
            url: serviceUrl
        }).done(function (result) {
            console.log('result', result);
            fn_mdl_alert(result, function () {
                init();
                reset();
            }, "CONFIRMACION");
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, null, "MENSAJE");
        });
    }

    function eliminar(codigo) {
        $.ajax({
            type: "DELETE",
            url: serviceUrl + '/' + codigo
        }).done(function (result) {
            console.log('result', result);
            fn_mdl_alert(result, function () {
                init();
                reset();
            }, "CONFIRMACION");
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, null, "MENSAJE");
        });
    }


    function cargar(objeto) {
        $("#hdnCodigo").val(objeto.codigo);
        //Seleccionando preguntas
        for (var i in objeto.preguntas) {
            var preguntaRegla = objeto.preguntas[i];
            $("[id='cboPregunta" + i + "']").val(preguntaRegla.pregunta.codigo);
        }
        //Seleccionando el perfil
        $("#perfiles input[name='codigoPerfil'][value='" + objeto.perfil.codigo + "']").prop('checked', true);
    }

    function reset() {
        //Habilitar Opciones
        $("#opcAgregar").show();
        $("#opcModificar").hide();

        $("#hdnCodigo").val('');
        //Seleccionando preguntas
        $("[id^='cboPregunta']").val('-');
        //Seleccionando el perfil
        $("#perfiles input[name='codigoPerfil']").prop('checked', false);
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


    function getData(jQueryEl) {
        var data = {};
        jQueryEl.find(".inputValue").each(function () {
            var t = $(this).attr("type");
            var k = $(this).data("name");
            var v;

            if (t === 'radio') {
                v = $(this).is(":checked") ? $(this).val() : '';
            } else {
                v = $(this).val() || $(this).text();
            }
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
</script>

<div class="div-pagina">
    <!-- INCIO PANEL-->
    <div id="div-busqueda" class="div-busqueda">
        <div id="div-busqueda-titulo" class="div-busqueda-titulo">
            Actualizar Reglas de Acoso Escolar
        </div>
    </div>

    <div id="div-resultado">

        <div id="editarRegla" >   
            <fieldset>
                <legend>Definición de Regla</legend>

                <input id="hdnCodigo" type="hidden" class="inputValue" data-name="codigo">

                <table>
                    <tr>
                        <td colspan="3">
                            <label>Si</label>
                            <select id="cboPregunta0" name="pregunta" data-name="pregunta.codigo">
                                <option value="">-</option>
                            </select>
                            <label>y</label>
                            <select id="cboPregunta1" name="pregunta" data-name="pregunta.codigo">
                                <option value="">-</option>
                            </select>
                            <label>y</label>
                            <select id="cboPregunta2" name="pregunta" data-name="pregunta.codigo">
                                <option value="">-</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 120px">
                            <label>Entonces</label>
                        </td>
                        <td id="perfiles">
                        </td>
                        <td style="width: 180px">
                            <div id="opcAgregar" style=" text-align: right;">
                                <button id="btnAgregar">Agregar</button>
                            </div>
                            <div id="opcModificar" style="display: none; text-align: right;">
                                <button id="btnModificar" >Modificar</button>
                                <button id="btnCancelar" >Cancelar</button>
                            </div>
                        </td>
                    </tr>
                </table>

            </fieldset>
        </div>

        <div id="tmpPerfil" style="display:none;">
            <div>
                <input id="rdPerfil" type="radio" name="codigoPerfil" class="inputValue" data-name="perfil.codigo">
                <label id="lblPerfil" for="perfil1"></label>
            </div>
        </div>

        <div id="rowDetalle" style="display:none;">
            <table>
                <tr>
                    <td>
                        <label id="lblCodigo" class="inputValue"></label>
                    </td>
                    <td>
                        <label id="lblReglaFormula" class="inputValue"></label>
                    </td>
                    <td>
                        <label id="lblEstado" class="inputValue"></label>
                    </td>
                    <td>
                        <a id="lnkEditar" href="#" >
                            Editar
                        </a>
                    </td>
                    <td>
                        <a id="lnkEliminar" href="#" >
                            Eliminar
                        </a>
                    </td>
                </tr>
            </table>
        </div>

        <div id="editarRegla" >   
            <fieldset>
                <legend>Reglas Formadas</legend>

                <table id="tblDetalle" border="0" cellpadding="3" cellspacing="0" class="css_grilla">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Regla</th>
                            <th>Estado</th>
                            <th>Editar</th>
                            <th>Elimnar</th>
                        </tr>	
                    </thead>
                    <tbody></tbody>
                </table>
            </fieldset>
        </div>

        <div id="opciones" style="text-align: center">
            <button id="btnCerrar">Cerrar</button>
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
</div>
