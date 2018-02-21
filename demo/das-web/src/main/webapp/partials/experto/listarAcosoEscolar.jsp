
<script type='text/javascript'>
    var serviceUrl = "http://localhost:8180/gepsac-service/experto";
    var action = '/GenerarExplicacion.do';
    var item;

    $(document).ready(function () {
        //init();
    });

    function init() {
        $.ajax({
            type: "GET",
            dataType: 'json',
            url: serviceUrl + "/acosoEscolar/evaluado"
        }).done(function (listado) {
            console.log('listado', listado);
            cargarListado(listado);
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, "MENSAJE");
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
        detalle.find("#lblAlumno").append(json.alumno.nombres + ' ' + json.alumno.apellidoPaterno + ' ' + json.alumno.apellidoMaterno);
        if (json.perfil) {
            detalle.find("#lblPerfil").append(json.perfil.nombre);
        }
        detalle.find("#lblEstado").append(json.estado.nombre);

        table.find("tbody").append(detalle);
        var chkId = detalle.find("#chkId");
        var link = chkId.attr('href') + action + '?method=initExplicacion&codigo=' + json.codigo;
        link += "&fromUrl=" + window.location.href;
        chkId.attr("href", link);
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

<uib-alert template-url="alert.html" ng-repeat="alert in alerts" type="{{alert.type}}" close="closeAlert($index)" >{{alert.msg}}</uib-alert>

<div  class="container" style="width: 100%">


    <div class="panel panel-primary">


        <!-- INCIO PANEL-->
        <div class="panel-heading">
            <div class="row" >
                <div class="col-md-8">
                    <h3 class="panel-title">Evaluaciones Acoso Escolar Evaluadas</h3>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>


        <div class="panel-body">
            <!--
            <div id="div-resultado"> 

                <table id="tblDetalle" border="0" cellpadding="3" cellspacing="0" class="css_grilla">
                    <thead>
                        <tr>
                            <th>Código Evaluación</th>	
                            <th>Alumno</th>
                            <th>Perfil</th>
                            <th>Estado</th>	
                            <th>Explicacion</th>
                        </tr>	
                    </thead>
                    <tbody>
                        <tr ng-repeat="user in users">
                            <td>{{ user.codigo}}</td>
                            <td>{{ user.alumno.nombres + ' ' + user.alumno.apellidoPaterno + ' ' + user.alumno.apellidoMaterno}}</td>
                            <td></td>
                            <td>{{ user.estado.nombre}}</td>
                            <td><a ng-click="generateExplication(user.id)" class="btn btn-small btn-primary">Generar Explicacion</a></td>                    
                        </tr>
                    </tbody>
                </table>

            </div> 
            -->
        </div>

    </div>


</div>
