<%@page import="com.novatronic.sca.util.Resultado"%>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"  %>


<script src="<%=request.getContextPath()%>/resources/js/util.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/canvasjs.min.js"></script>
<script type="text/javascript">
    window.onload = function() {
        $("#pieContainer").CanvasJSChart({
            title: {
                text: "Worldwide Smartphone sales by brand - 2012",
                fontSize: 24
            },
            axisY: {
                title: "Products in %"
            },
            legend: {
                verticalAlign: "center",
                horizontalAlign: "right"
            },
            data: [
                {
                    type: "pie",
                    showInLegend: true,
                    toolTipContent: "{label} <br/> {y} %",
                    indexLabel: "{y} %",
                    dataPoints: [
                        {label: "Samsung", y: 30.3, legendText: "Samsung"},
                        {label: "Apple", y: 19.1, legendText: "Apple"},
                        {label: "Huawei", y: 4.0, legendText: "Huawei"},
                        {label: "LG", y: 3.8, legendText: "LG Electronics"},
                        {label: "Lenovo", y: 3.2, legendText: "Lenovo"},
                        {label: "Others", y: 39.6, legendText: "Others"}
                    ]
                }
            ]
        });
    }
</script> 
<script>


    function popularFormulario(json) {
        var elem = $("form[name='" + nameForm + "']");
        $(elem).find("[name^='" + prefix + "']").each(function() {
            var name = $(this).attr("name");
            console.log(name);
            var value = (json == null) ? '' : json[name.substring(name.indexOf('.') + 1)];
            $(this).val(value);
        });
    }
    function IniciarDibujo(dat) {
        var j = dat;

        var chart = new CanvasJS.Chart("pieContainer",
                {
                    title: {
                        text: "Pie"
                    },
                    legend: {
                        verticalAlign: "bottom",
                        horizontalAlign: "center"
                    },
                    data: [
                        {
                            type: "pie",
                            showInLegend: true,
                            toolTipContent: "{legendText}: <strong>{y}%</strong>",
                            indexLabel: "{label} {y}%",
                            dataPoints: [
                                {y: 35, legendText: "<bean:message key="usuario.buscar.col.1" bundle="rsUsuario"/>", exploded: true, label: "Health"},
                                {y: 20, legendText: "Consulta Saldo Cuenta Principal", label: "Finance"},
                                {y: 18, legendText: "Career", label: "Career"},
                                {y: 15, legendText: "Education", label: "Education"},
                                {y: 5, legendText: "Family", label: "Family"},
                                {y: 7, legendText: "Real Estate", label: "Real Estate"}
                            ]
                        }
                    ]
                });
        chart.render();

        var chartLine = new CanvasJS.Chart("LineContainer",
                {
                    theme: "theme3",
                    title: {
                        text: "Reporte Lineal",
                        fontSize: 30
                    },
                    toolTip: {
                        shared: true
                    },
                    axisX: {
                        title: "Source: U.S. Energy Information Administration"
                    },
                    axisY: {
                        title: "billion of barrels"
                    },
                    axisY2: {
                        title: "million barrels/day"
                    },
                    legend: {
                        verticalAlign: "top",
                        horizontalAlign: "center"
                    },
                    data: [
                        {
                            type: "column",
                            name: "Proven Oil Reserves (bn)",
                            legendText: "Proven Oil Reserves",
                            showInLegend: true,
                            dataPoints: [
                                {label: "Saudi", y: 262},
                                {label: "Venezuela", y: 211},
                                {label: "Canada", y: 175},
                                {label: "Iran", y: 137},
                                {label: "Iraq", y: 115},
                                {label: "Kuwait", y: 104},
                                {label: "UAE", y: 97.8},
                                {label: "Russia", y: 60},
                                {label: "US", y: 23.3},
                                {label: "China", y: 20.4}


                            ]
                        },
                        {
                            type: "column",
                            name: "Oil Production (million/day)",
                            legendText: "Oil Production",
                            axisYType: "secondary",
                            showInLegend: true,
                            dataPoints: [
                                {label: "<bean:message key="usuario.buscar.col.0" bundle="rsUsuario"/>", y: 11.15},
                                {label: "Venezuela", y: 2.5},
                                {label: "Canada", y: 3.6},
                                {label: "Iran", y: 4.2},
                                {label: "Iraq", y: 2.6},
                                {label: "Kuwait", y: 2.7},
                                {label: "UAE", y: 3.1},
                                {label: "Russia", y: 10.23},
                                {label: "US", y: 10.3},
                                {label: "China", y: 4.3}


                            ]
                        }

                    ],
                    legend:{
                        cursor: "pointer",
                        itemclick: function(e) {
                            if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                                e.dataSeries.visible = false;
                            }
                            else {
                                e.dataSeries.visible = true;
                            }
                            chartLine.render();
                        }
                    },
                });

        chartLine.render();

        // popularFormulario(j);

//        var data = JSON.stringify(j)
//        popularFormulario(data);
//        console.log(data);
////        var json =JSON.parse(localStorage["importe"])
//        //      alert("JSON : " + json);
//        //alert("DATA : " + data.bin_procesador);
//        var i = 0;
//        for (var attr in data) {
//            //console.log(attr);
//            i++;
//        }
//        alert("Hay " + i + " atributos.");
//        var pieData = [
//            {
//                value: 40,
//                color: "#0b82e7",
//                highlight: "#0c62ab",
//                label: "Consulta Lista Cuenta Principal"
//            },
//            {
//                value: 16,
//                color: "#e3e860",
//                highlight: "#a9ad47",
//            },
//            {
//                value: 11,
//                color: "#eb5d82",
//                highlight: "#b74865",
//            },
//            {
//                value: 10,
//                color: "#5ae85a",
//                highlight: "#42a642",
//            },
//            {
//                value: 8.6,
//                color: "#e965db",
//                highlight: "#a6429b",
//                //label: "Safari"
//            }
//        ];
//
//        var barChartData = {
//            labels: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio"],
//            datasets: [
//                {
//                    fillColor: "#6b9dfa",
//                    strokeColor: "#ffffff",
//                    highlightFill: "#1864f2",
//                    highlightStroke: "#ffffff",
//                    data: [90, 30, 10, 80, 15, 5, 15]
//                },
//                {
//                    fillColor: "#e9e225",
//                    strokeColor: "#ffffff",
//                    highlightFill: "#ee7f49",
//                    highlightStroke: "#ffffff",
//                    data: [40, 50, 70, 40, 85, 55, 15]
//                }
//            ]
//
//        }
//
//        var ctx = document.getElementById("chart-area").getContext("2d");
//        var ctx3 = document.getElementById("chart-area3").getContext("2d");
//        window.myPie = new Chart(ctx).Pie(pieData);
//        window.myPie = new Chart(ctx3).Bar(barChartData, {responsive: true});
    }


</script>



<script type="text/javascript">

    path = "/ReporteCierre.do";
    nameForm = "reporteCierreForm";
    prefix = "usuario";
    prefixFiltro = "usuBusqueda";
    tituloEditar = '<bean:message key="usuario.editar.titulo" bundle="rsUsuario"/>';
    tituloEliminar = '<bean:message key="usuario.eliminar.titulo" bundle="rsUsuario"/>';
    tituloInsertar = '<bean:message key="usuario.agregar.titulo" bundle="rsUsuario"/>';
    mensajeEliminar = '<bean:message key="usuario.eliminar.mensaje" bundle="rsUsuario"/>';
    mensajeResetear = '<bean:message key="usuario.resetear.confirmacion" bundle="rsUsuario"/>';
    var context = "<%=request.getContextPath()%>";
    timeout = null;
    delay = 1000;
    cargando = false;
    disableParam = "usuario.usuario";

    var oPick = null;
    var oTable = null;

    function configurarDimension() {
        popupSetDimension(form, 610, 380);
    }




    function documento_maxvalue() {
        var documento = $('#selectDoc1').val();
        var tipoDocumento = document.getElementById("numeroDocumento");
        if (documento == '-1') {
            tipoDocumento.value = "";
        } else if (documento == '0') {
            tipoDocumento.value = "";
            tipoDocumento.setAttribute('maxlength', 8);
            tipoDocumento.focus();
        } else if (documento == '1') {
            tipoDocumento.value = "";
            tipoDocumento.setAttribute('maxlength', 12);
            tipoDocumento.focus();
        }
    }
    ;

    function documento_precarga_maxvalue() {
        var numeroDocumento = document.getElementById("numeroDocumento");
        if (document.getElementById("selectDoc1").value === '0') {
            numeroDocumento.setAttribute('maxlength', 8);
        } else if (document.getElementById("selectDoc1").value === '1') {
            numeroDocumento.setAttribute('maxlength', 12);
        }
    }


    function initDataTable(data) {
       // IniciarDibujo(data);
        oTable = $(table).dataTable({
            "bFilter": false,
            "bLengthChange": false,
            "bSortClasses": false,
            "bInfo": false,
            "aaData": data,
            "sPaginationType": "full_numbers",
            "aaSorting": [[1, 'asc']],
            "sScrollY": "auto",
            "sScrollX": "930px",
            "sScrollXInner": "110%",
            "oLanguage": {
                "sUrl": "../resources/language/dataTables.spanish.txt"
            },
            "aoColumns": [
                {
                    "sTitle": "<bean:message key="usuario.buscar.col.0" bundle="rsUsuario"/>",
                    "mData": null,
                    "bSortable": false
                },
                {
                    "sTitle": "<bean:message key="usuario.buscar.col.1" bundle="rsUsuario"/>",
                    "mData": "bin_procesador"
                },
                {
                    "sTitle": "<bean:message key="usuario.buscar.col.2" bundle="rsUsuario"/>",
                    "mData": "binadquiriente"
                },
                {
                    "sTitle": "<bean:message key="usuario.buscar.col.3" bundle="rsUsuario"/>",
                    "mData": "bin_autorizador"
                }
                ,
                {
                    "sTitle": "<bean:message key="usuario.buscar.col.4" bundle="rsUsuario"/>",
                    "mData": "ind_term_trans"
                },
                {
                    "sTitle": "<bean:message key="usuario.buscar.col.5" bundle="rsUsuario"/>",
                    "mData": "fechalogica"
                },
                {
                    "sTitle": "<bean:message key="usuario.buscar.col.6" bundle="rsUsuario"/>",
                    "mData": "tip_flujo"
                            //"sClass": "btn-action"
                },
                {
                    "sTitle": "<bean:message key="usuario.buscar.col.7" bundle="rsUsuario"/>",
                    "mData": "des_flujo"
                            //"sClass": "btn-action"
                },
                {
                    "sTitle": "<bean:message key="usuario.buscar.col.8" bundle="rsUsuario"/>",
                    "mData": "cod_trans_flujo"
                            //"sClass": "btn-action"
                },
                {
                    "sTitle": "<bean:message key="usuario.buscar.col.9" bundle="rsUsuario"/>",
                    "mData": "nro_trans"
                            //"sClass": "btn-action"
                },
                {
                    "sTitle": "<bean:message key="usuario.buscar.col.10" bundle="rsUsuario"/>",
                    "mData": "importe"
                            //"sClass": "btn-action"
                }
            ],
            "fnDrawCallback": function(oSettings) {
                if (oSettings.bSorted || oSettings.bFiltered) {
                    for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
                        $('td:eq(0)', oSettings.aoData[oSettings.aiDisplay[i]].nTr).html(i + 1);
                    }
                }
            }
        });
    }




    $(function() {

        var _url = context + path + "?method=inicializarBusqueda";
        $.ajax({
            type: "POST",
            url: _url,
            success: function(response) {
                initDataTable(response);
            },
            error: function(e) {
                errorFormulario();
            }
        });




    });


    function setDialog(formulario, titulo) {
        popupOpen(formulario);
        popupTitulo(formulario, titulo);
    }

    function cancelarForm() {
        path = '/ReporteCierre.do';
    }

</script>

<div id="div-formulario">
    <font color="red">
    <html:errors/>
    </font>


</div>
<div id="canvas-holder">

    <table style="width:100%">
        <tr>
            <td><div id="pieContainer" style="height: 300px; width: 100%;"></div></td>
            <td><div id="LineContainer" style="height: 300px; width: 100%;"></div></td>
        </tr>

    </table>
</div>
<div id="div-resultado">
    <table id="tbl-resultado">

    </table>
</div>
<div id="chartContainer1">
    <table id="chartContainer1">

    </table>
</div>
<div id="div-confirmacion" class="div-content-center div-confirmacion">
    <label id="lblMensaje" > Operacion satisfactoria</label>
</div>


