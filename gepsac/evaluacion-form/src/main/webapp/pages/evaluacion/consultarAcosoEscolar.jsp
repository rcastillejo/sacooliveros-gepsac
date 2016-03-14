

<script type = "text/javascript" >
    var fromUrl;
    var serviceUrl = "http://localhost:8180/gepsac-service/experto";
    var action = '/ConsultarExplicacion.do';
    var actionExplicacion = '/GenerarExplicacion.do';
    var codigoEvaluacion;
    var profile;
    
    $(document).ready(function () {
        fromUrl = getRequestParameter("fromUrl");
        
        codigoEvaluacion = getRequestParameter("codigo");
        
        profile = getRequestParameter("profile");
        
        init();

        //Cancelar la Evaluacion del Alumno Nuevo
        $("#btnCancelar").click(function (e) {
            e.preventDefault();
            location.assign(fromUrl + "&profile=" + profile);
        });

        //Invoca a Generar Explicacion
        $("#btnGenerarExplicacion").click(function (e) {
            e.preventDefault();
            
            var link = "<%=request.getContextPath()%>" + actionExplicacion + '?method=initExplicacion&codigo=' + codigoEvaluacion;
            var url = encodeURIComponent(window.location.href);
            console.log('fromUrl', url);
            
            link += ("&fromUrl=" + url);
            
            location.assign(link);
        });
    });


    function getRequestParameter(name) {
        if (name = (new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)')).exec(location.search))
            return decodeURIComponent(name[1]);
    }

    function init() {

        console.log("fromUrl", fromUrl);
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: serviceUrl + "/acosoEscolar/evaluado/" + codigoEvaluacion
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


        $("#txtNombre").val(objeto.alumno.nombres);
        $("#txtApellido").val(objeto.alumno.apellidoPaterno + ' ' + objeto.alumno.apellidoMaterno);


        $("#txtCodigo").val(objeto.codigo);
        
        var fecEva = new Date(objeto.fechaEvaluacion);
        $("#txtFecEva").val(getDateString(fecEva));

        $("#txtPerfil").val(objeto.perfil.nombre);

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
            cargarItem(listado[i]);
        }
    }

    function cargarItem(json) {
        console.log('cargando...', json);

        var table = $("#tblDetalle");

        var detalle = $("#rowDetalle").find("tbody tr").clone();

        detalle.find("#lblPregunta").append(json.pregunta.enunciado);
        detalle.find("#lblRespuesta").append(json.respuesta);

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
        Consulta de Evaluacion Acoso Escolar
    </div>
    <div id="dvData">
        <fieldset>
            <legend>
                <label>Datos del Alunno</label>
            </legend>

            <table>
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
        </fieldset>

        <fieldset>
            <legend>
                <label>Resultado de la Evaluacion</label>
            </legend>

            <table>
                <tr>
                    <td>
                        <label  class="inputValue">Codigo</label>        
                    </td>
                    <td>
                        <input id="txtCodigo" type="text" disabled="true" style="text-align: left">                     
                    </td>
                    <td>
                        <label  class="inputValue">Fecha Evaluacion</label>        
                    </td>
                    <td>
                        <input id="txtFecEva" type="text" disabled="true" style="text-align: left">                 
                    </td>
                </tr>
                <tr>
                    <td>
                        <label  class="inputValue">Perfil Obtenido</label>        
                    </td>
                    <td>
                        <input id="txtPerfil" type="text" disabled="true" style="text-align: left">             
                    </td>
                    <td>
                        <input type="button" id="btnGenerarExplicacion" value="Generar Explicacion" />
                    </td>
                </tr>
            </table>

            <div class="separator"/>


            <div id="rowDetalle" style="display:none;">   
                <table>
                    <tr>
                        <td>
                            <label id="lblPregunta" class="inputValue"></label>
                        </td>
                        <td>
                            <label id="lblRespuesta" class="inputValue"></label>
                        </td>
                    </tr>
                </table>
            </div>


            <table id="tblDetalle" border="0" cellpadding="3" cellspacing="0" class="css_grilla">

                <thead>
                <th>Pregunta</th>
                <th>Respuesta</th>
                </thead>

                <tbody>
                </tbody>

            </table>
        </fieldset>

    </div>
    <div style="align-content: center; text-align: center">
        <input type="button" id="btnCancelar" value="Cerrar" />
    </div>
</div>
