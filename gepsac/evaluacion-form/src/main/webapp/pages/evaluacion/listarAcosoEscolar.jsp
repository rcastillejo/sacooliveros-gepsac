
<script type='text/javascript'>
    var serviceUrl = "http://localhost:8180/gepsac-service/experto";
    var action = '/GenerarExplicacion.do';
    var actionConsultar = '/ConsultarExplicacion.do';
    var item;
    var profile;

    $(document).ready(function () {        
        profile = getRequestParameter('profile');
        init();
    });
    
    function getRequestParameter(name){
        if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
           return decodeURIComponent(name[1]);
     }
    
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
        detalle.find("#lblAlumno").append(json.alumno.nombres + ' ' + json.alumno.apellidoPaterno+' '+json.alumno.apellidoMaterno);
        if(json.perfil){
            detalle.find("#lblPerfil").append(json.perfil.nombre);
        }
        detalle.find("#lblEstado").append(json.estado.nombre);
        
        table.find("tbody").append(detalle);
        var chkId = detalle.find("#chkId");
        var lnkConsultar = detalle.find("#lnkConsultar");
        
        var fromUrl =  "&fromUrl="+window.location.href;
        
        console.log('fromUrl', fromUrl);
        if( profile === 'E' ){
            /**
             * 
             * Link Generar Explicacion Acoso Escolar
             */
            var link = chkId.attr('href') + action + '?method=initExplicacion&codigo=' + json.codigo;
            chkId.attr("href", link + fromUrl);
            
            //Ocultar Link Generar Explicacion
            lnkConsultar.hide();
        } else if( profile === 'P' ){
            /**
             * 
             * Link Consultar Acoso Escolar
             */
            var linkConsultar = lnkConsultar.attr('href') + actionConsultar + '?method=init&codigo=' + json.codigo;
            lnkConsultar.attr("href", linkConsultar + fromUrl);
            
            //Ocultar Link Generar Explicacion
            chkId.hide();
        }
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
            url: "<%=request.getContextPath()%>" + action + '?method=buscarAlumnoNuevo&codigo='+codigo+'&nombres='+nombres+'&apellidos='+apellidos
        }).done(function (listado) {
            console.log('listado', listado);
            $("#tblDetalle tbody").empty();
            cargarListado(listado);
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, function(){}, "MENSAJE");
        });        
    }

</script>

<div class="div-pagina">
    <!-- INCIO PANEL-->
    <div id="div-busqueda" class="div-busqueda">
        <div id="div-busqueda-titulo" class="div-busqueda-titulo">
            Evaluaciones Acoso Escolar Evaluadas
        </div>
        <!--div id="div-busqueda-filtros" class="div-busqueda-filtros">
            <fieldset>
                <legend>Criterios de B&uacute;squeda</legend>
                <table>
                    <tr>
                        <td>C&oacute;digo</td><td>:</td>
                        <td><input id="codigo" type="text"></td>
                        <td>Nombres</td><td>:</td>
                        <td><input id="nombres" type="text"></td>
                        <td>Apellidos</td><td>:</td> 
                        <td><input id="apellidos" type="text"></td>
                        <td>
                            <a href="javascript:fn_buscar();">
                                <img src="<%=request.getContextPath()%>/resources/images/iconos/ico_btn_buscar.jpg" border="0"/><br />
                                Buscar
                            </a>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div-->
    </div>

    <div id="div-resultado">
        <div id="rowDetalle" style="display:none;">   
            <table>
                <tr>
                    <td>
                        <label id="lblCodigo" class="inputValue"></label>
                    </td>
                    <td>
                        <label id="lblAlumno" class="inputValue"></label>
                    </td>
                    <td>
                        <label id="lblPerfil" class="inputValue"></label>
                    </td>
                    <td>
                        <label id="lblEstado" class="inputValue"></label>
                    </td>
                    <td>
                        <a id="chkId" href="<%=request.getContextPath()%>" >
                            Generar Explicacion
                        </a>
                        <a id="lnkConsultar" href="<%=request.getContextPath()%>" >
                            Consultar
                        </a>
                    </td>
                </tr>
            </table>
        </div>

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
            <tbody></tbody>
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
