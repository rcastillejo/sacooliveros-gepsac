

<script type = "text/javascript" >
    var serviceIP = "<%=request.getLocalAddr()%>";
    var fromUrl;
    var serviceUrl = "http://" + serviceIP + ":8180/gepsac-service/evaluacion";
    var action = '/ResolverAcosoEscolar.do';
    var codigoEvaluacion;

    $(document).ready(function () {
        fromUrl = decodeURIComponent(getRequestParameter("fromUrl"));

        codigoEvaluacion = getRequestParameter("codigo");

        init();

        $("#btnCancelar").click(function (e) {
            e.preventDefault();
            location.assign("<%=request.getContextPath()%>");
        });
        
        $("#btnFinalizar").click(function (e) {
            e.preventDefault();
            resolverAcosoEscolar();
        });
    });

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

    function obtenerRespuestas() {
        var listadoVal = [];
        var elPreguntas = $("#tblDetalle tr[id^='tblPregunta']");


        elPreguntas.map(function (index, elem) {
            var pregunta = {};

            pregunta = getData($(this));
            
            pregunta.alternativas = [];
            
            var elAlternativas = $(this).find("input[id^='rdbAlternativa']");
            
            console.log('elAlternativas', elAlternativas);
            elAlternativas.map(function (idx, el) {
                console.log('elAlternativa', el);
                var alternativa = {
                      secuencia: $(el).attr('value')
                };
                var seleccionado = $(el).is(':checked');   
                pregunta.alternativas.push({
                    alternativa : alternativa,
                    seleccionado : seleccionado
                });
            });
            listadoVal.push(pregunta);
            
            console.log('listDetalle', listadoVal);
        });

        return listadoVal;
    }



    function resolverAcosoEscolar() {

        var data = getData($("#evaluacion"));
        data.preguntas = obtenerRespuestas();

        console.log('grabando', data);

        $.ajax({
            type: "POST",
            dataType: 'json',
            data: JSON.stringify(data),
            contentType: "application/json",
            url: serviceUrl + '/acosoEscolar/resolver'
        }).done(function (result) {
            console.log('result', result);
            fn_mdl_alert(result, function () {}, "CONFIRMACION");
        }).fail(function (error, status, a) {
            console.log('error', error, 'status', status, 'a', a);
            if (error && error.status === 400) {
                fn_mdl_alert(error.responseText, function () {}, "MENSAJE");
            } else {
                fn_mdl_alert(error.responseText, function () {
                    location.assign("<%=request.getContextPath()%>");
                }, "MENSAJE");
            }
        });
    }


    function getRequestParameter(name) {
        if (name = (new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)')).exec(location.search))
            return decodeURIComponent(name[1]);
    }

    function init() {

        console.log("fromUrl", fromUrl);
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: serviceUrl + "/acosoEscolar/" + codigoEvaluacion
        }).done(function (objeto) {
            console.log('objeto', objeto);
            cargar(objeto);
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, function () {
                location.assign("<%=request.getContextPath()%>");
            }, "MENSAJE");
        });
    }


    function cargar(objeto) {


        $("#hdnEvaluacionId").val(objeto.codigo);

        $("#txtNombre").val(objeto.alumno.nombres);
        $("#txtApellido").val(objeto.alumno.apellidoPaterno + ' ' + objeto.alumno.apellidoMaterno);


        $("#txtCodigo").val(objeto.codigo);

        var fecEva = new Date();
        $("#txtFecRes").val(getDateString(fecEva));



        cargarListado(objeto.preguntas);

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

    function cargarListado(listado) {
        for (var i in listado) {
            cargarItem(listado[i], i);
        }
    }

    function cargarItem(json, i) {
        console.log('cargando...', json);

        var table = $("#tblDetalle");

        var detalle = $("#rowDetalle").find("tbody tr").clone();

        detalle.attr('id', 'tblPregunta-' + json.pregunta.codigo);
        detalle.find("#hdnPreguntaId").val(json.pregunta.codigo);

        detalle.find("#lblNroPregunta").append(parseInt(i) + 1);
        
        detalle.find("#lblPregunta").append(json.pregunta.enunciado);
        
        /*var respuesta = detalle.find("[name='respuesta']");
        respuesta.attr('name', 'respuesta-' + json.pregunta.codigo);*/
        
        var detalleAlternativa = $("#rowDetalleAlternativa");
        var alternativas = json.alternativas;

        for (var i in alternativas) {
            var alternativaPregunta = alternativas[i];
            var lblAlternativa = detalleAlternativa.find("#lblAlternativa").clone();
            var rdbAlternativa = detalleAlternativa.find("#rdbAlternativa").clone();

            lblAlternativa.attr('for', 'alternativa-' + alternativaPregunta.codigoPregunta);
            rdbAlternativa.attr('name', 'alternativa-' + alternativaPregunta.codigoPregunta);

            lblAlternativa.attr('id', lblAlternativa.attr('id') + '-' + alternativaPregunta.codigoPregunta);
            rdbAlternativa.attr('id', rdbAlternativa.attr('id') + '-' + alternativaPregunta.codigoPregunta);

            console.log('[' + json.pregunta.codigo + '] cargando alternativas alternativa=', alternativaPregunta.alternativa.alternativa);

            lblAlternativa.append(alternativaPregunta.alternativa.alternativa);

            console.log('[' + json.pregunta.codigo + '] cargando alternativas codigoPregunta=', alternativaPregunta.codigoPregunta);


            console.log('[' + json.pregunta.codigo + '] cargando alternativas secuencia=', alternativaPregunta.alternativa.secuencia);

            rdbAlternativa.val(alternativaPregunta.alternativa.secuencia);
            console.log('[' + json.pregunta.codigo + '] alternativa seleccionada', alternativaPregunta.seleccionado);
            
            detalle.find("#alternativas").append(rdbAlternativa);
            detalle.find("#alternativas").append(lblAlternativa);
        }



        table.find("tbody").append(detalle);
    }
</script>

<style>
    .separator {
        border-top: 1px solid ;
        margin: 0 auto;
        margin-bottom: 5px;
        margin-top: 5px;
    }
</style>

<div class="div-pagina">
    <div id="div-pagina-titulo" class="div-pagina-titulo">
        Resolver Evaluacion Acoso Escolar
    </div>
    <div id="dvData">
        <fieldset>
            <legend>
                <label>Datos de la Evaluacion</label>
            </legend> 

            <table>
                <tr>
                    <td>
                        <label>Codigo</label>        
                    </td>
                    <td>
                        <input id="txtCodigo" type="text" disabled="true" style="text-align: left">                     
                    </td>
                    <td>
                        <label  class="inputValue">Fecha Evaluacion</label>        
                    </td>
                    <td>
                        <input id="txtFecRes" type="text" disabled="true" style="text-align: left">                 
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Apellidos</label>
                    </td>
                    <td>
                        <input id="txtApellido" type="text" disabled="true" style="text-align: left">                  
                    </td>
                    <td>
                        <label>Nombres</label>                    
                    </td>
                    <td>
                        <input id="txtNombre" type="text" disabled="true" style="text-align: left">                       
                    </td>
                </tr>
            </table>

            <div class="separator"></div>

            
            <div id="rowDetalleAlternativa" style="display:none;">                
                <label id="lblAlternativa" for="alternativa" ></label>
                <input id="rdbAlternativa" type="radio" name="alternativa">
            </div>

            <div id="rowDetalle" style="display:none;">   
                <table>
                    <tr>
                        <td>
                            <input id="hdnPreguntaId" type="hidden" class="inputValue" data-name="pregunta.codigo">
                            <label id="lblNroPregunta"></label>
                        </td>
                        <td>
                            <label id="lblPregunta"></label>
                        </td>
                        <td id="alternativas">
                        </td>
                    </tr>
                </table>
            </div>

            <div id="evaluacion">                
                <input id="hdnEvaluacionId" type="hidden" class="inputValue" data-name="codigo">
            </div>

            <div id="preguntas" >
                <table id="tblDetalle" border="0" cellpadding="3" cellspacing="0" class="css_grilla">

                    <thead>
                    <th>N�</th>
                    <th>Pregunta</th>
                    <th>Alternativas</th>
                    </thead>

                    <tbody>
                    </tbody>

                </table>
            </div>  
        </fieldset>

    </div>
    <div style="align-content: center; text-align: center">
        <input type="button" id="btnFinalizar" value="Finalizar" />
        <input type="button" id="btnCancelar" value="Cancelar" />
    </div>
</div>