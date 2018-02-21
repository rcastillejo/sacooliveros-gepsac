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
    var action = '/EvaluarPostulante.do';
    var item;

    $(document).ready(function () {
        initBuscarAlumnoNuevo();
    });
    
    function getRequestParameter(name){
        if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
           return decodeURIComponent(name[1]);
     }

    function initBuscarAlumnoNuevo() {
        $.ajax({
            type: "POST",
            dataType: 'json',
            url: "<%=request.getContextPath()%>" + action + '?method=initBuscarAlumnoNuevo'
        }).done(function (listado) {
            console.log('listado', listado);
            cargarListado(listado);
        }).fail(function (error) {
            console.log('error', error);
            //$("#mensajeError").append(error);
            fn_mdl_alert(error.responseText, parent.fn_util_CierraModal, "MENSAJE");
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
        detalle.find("#lblNombres").append(json.nombres);
        detalle.find("#lblApellidos").append(json.apellidoPaterno+' '+json.apellidoMaterno);
        detalle.find("#lblEdad").append(json.edad + ' años');
        detalle.find("#lblDistrito").append(json.distrito);
        detalle.find("#lblDomicilio").append(json.domicilio);

        table.find("tbody").append(detalle);

        detalle.find("#chkId").click(function () {
            fn_checkListadoItem($(this).get(0), json);
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
            Buscar Alumno Nuevo
        </div>
        <div id="div-busqueda-filtros" class="div-busqueda-filtros">
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
        </div>
    </div>

    <div id="div-resultado">
        <div id="rowDetalle" style="display:none;">   
            <table>
                <tr>
                    <td>
                        <input id="chkId" type="checkbox" />
                    </td>
                    <td>
                        <label id="lblCodigo" class="inputValue"></label>
                    </td>
                    <td>
                        <label id="lblNombres" class="inputValue"></label>
                    </td>
                    <td>
                        <label id="lblApellidos" class="inputValue"></label>
                    </td>
                    <td>
                        <label id="lblEdad" class="inputValue"></label>
                    </td>
                    <td>
                        <label id="lblDomicilio" class="inputValue"></label>
                    </td>
                    <td>
                        <label id="lblDistrito" class="inputValue"></label>
                    </td>
                </tr>
            </table>
        </div>

        <table id="tblDetalle" border="0" cellpadding="3" cellspacing="0" class="css_grilla">
            <thead>
                <tr>
                    <th>_</th>
                    <th>Código</th>	
                    <th>Nombres</th>
                    <th>Apellidos</th>
                    <th>Edad</th>
                    <th>Distrito</th>	
                    <th>Domicilio</th>
                </tr>	
            </thead>
            <tbody></tbody>
        </table>

    </div>
    <div align="center">
        <table style="align-content: center; text-align: center">
            <tr>
                <td><a href="javascript:fn_seleccionar();">
                    <img src="<%=request.getContextPath()%>/resources/images/iconos/ico_mdl_dominio.jpg" border="0" /><br />
                    Aceptar
                    </a>
                </td>
                <td><a href="javascript:parent.fn_util_CierraModal();">
                    <img src="<%=request.getContextPath()%>/resources/images/iconos/ico_btn_cancelar.jpg" border="0" /><br />
                    Cancelar
                    </a>
                </td>
            </tr>
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
