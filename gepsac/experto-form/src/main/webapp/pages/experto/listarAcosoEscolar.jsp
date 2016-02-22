<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"  %>

<script src="<%=request.getContextPath()%>/resources/js/jquery-1.8.3.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery-ui-1.9.2.css" />
<script src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.9.2.js"></script>

<!--<link href="<%=request.getContextPath()%>/resources/css/skinSCA.css" rel="stylesheet" type="text/css" />-->
<link href="<%=request.getContextPath()%>/resources/css/jquery-combobox.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/sca-template.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/jquery-override.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resources/css/jquery.dataTables.css" rel="stylesheet" type="text/css" />

<script src="<%=request.getContextPath()%>/resources/js/jquery.iframe-transport.js"></script>

<script src="<%=request.getContextPath()%>/resources/js/jquery.fileupload.js"></script>       
<script src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/util.js"></script>

<script type='text/javascript'>
    var serviceUrl = "http://localhost:8180/gepsac-service/experto";
    var action = '/GenerarExplicacion.do';
    var item;

    $(document).ready(function () {
        init();
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
        detalle.find("#lblAlumno").append(json.nombres + ' ' + json.apellidoPaterno+' '+json.apellidoMaterno);
        if(json.perfil){
            detalle.find("#lblPerfil").append(json.perfil.nombre);
        }
        detalle.find("#lblEstado").append(json.estado.nombre);
        
        table.find("tbody").append(detalle);
        var chkId = detalle.find("#chkId");
        var link = chkId.attr('href') + action + '?method=initExplicacion&codigo=' + json.codigo;
        link += "&fromUrl="+window.location.href;
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
