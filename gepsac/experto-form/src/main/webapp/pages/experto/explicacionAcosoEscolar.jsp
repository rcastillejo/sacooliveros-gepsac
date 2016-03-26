

<script type = "text/javascript" >
    var serviceIP = "<%=request.getLocalAddr()%>";
    var fromUrl;
    var serviceUrl = "http://" + serviceIP + ":8180/gepsac-service/experto";
    var action = '/GenerarExplicacion.do';
    var codigoEvaluacion;
    var profile;

    $(document).ready(function () {
        fromUrl = decodeURIComponent(getRequestParameter("fromUrl"));

        codigoEvaluacion = getRequestParameter("codigo");

        profile = getRequestParameter("profile");

        init();

        //Cancelar la Evaluacion del Alumno Nuevo
        $("#btnCancelar").click(function (e) {
            e.preventDefault();
            location.assign(fromUrl + "&profile=" + profile);
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
            url: serviceUrl + "/explicacion/" + codigoEvaluacion
        }).done(function (objeto) {
            console.log('objeto', objeto);
            cargarExplicacion(objeto);
        }).fail(function (error) {
            console.log('error', error);
            fn_mdl_alert(error.responseText, function () {
                location.assign("<%=request.getContextPath()%>");
            }, "MENSAJE");
        });
    }


    function cargarExplicacion(objeto) {


        $("#lblAlumno").val(objeto.alumno.nombres + ' ' + objeto.alumno.apellidoPaterno + ' ' + objeto.alumno.apellidoMaterno);

        cargarPerfil(objeto.reglas);

        for (var i in objeto.premisas) {
            cargarPremisa(objeto.premisas[i]);
        }

        for (var i in objeto.reglas) {
            cargarRegla(objeto.reglas[i]);
        }

    }

    function cargarPerfil(reglas) {

        var perfiles = {};

        for (var i in reglas) {
            var regla = reglas[i];
            var cant = perfiles[regla.perfil.nombre] ? perfiles[regla.perfil.nombre] : 0;
            cant++;
            perfiles[regla.perfil.nombre] = cant;
            console.log(regla.perfil.nombre, cant);
        }

        console.log('perfiles', perfiles);
        var perfilMax;
        var max = 0;
        var cantMax;

        for (var nomPerfil in perfiles) {
            console.log('nomPerfil', nomPerfil);

            var value = perfiles[nomPerfil];

            console.log('value', value);

            var porcentajeDecimal = parseInt(value) / (reglas.length * 1.00);
            console.log("Porcentaje del perfil", nomPerfil, porcentajeDecimal);

            var porcentaje = (porcentajeDecimal * 100.0);
            porcentaje = porcentaje.toFixed(2);

            var elPerfil = $("#divHidden").find("#lblPerfil").clone();

            var mostrarPerfil = porcentaje + "% " + nomPerfil;
            console.log("porcentaje", porcentajeDecimal, "perfil", nomPerfil);

            elPerfil.append(mostrarPerfil);

            $("#perfiles").append(elPerfil);
            $("#perfiles").append($("<br>"));

            if (max < porcentaje) {
                max = porcentaje;
                perfilMax = nomPerfil;
                cantMax = value;
            }
        }

        $("#lblPerfilMencionado").append(perfilMax);
        $("#lblVecesPerfilMencionado").append(parseInt(cantMax) > 1 ? (cantMax + ' veces') : (cantMax + ' vez'));
        $("#lblPorcentajePerfilMencionado").append(max + '%');

        $("#" + perfilMax).addClass(perfilMax);
    }

    function cargarPremisa(premisa) {

        var table = $("#permisas");

        var elPremisa = $("#divHidden").find("#lblPremisa").clone();

        elPremisa.append(premisa);

        table.append(elPremisa);
        table.append($("<br>"));

    }

    function cargarRegla(regla) {

        var table = $("#reglas");

        var elRegla = $("#divHidden").find("#lblRegla").clone();

        var formula = regla.codigo;
        formula += ": Si ";
        for (var i in regla.preguntas) {

            var preguntaRegla = regla.preguntas[i];
            formula += preguntaRegla.pregunta.enunciado.toUpperCase();
            if (i < (regla.preguntas.length - 1)) {
                formula += " y ";
            }
        }
        formula += " entonces " + regla.perfil.nombre.toUpperCase();

        elRegla.append(formula);
        table.append(elRegla);
        table.append($("<br>"));
    }

    function cargarPregunta(json) {
        console.log('cargando...', json);
        $("#lblPerguntasAfirmadas").append(json.pregunta.alias);
    }

</script>


<style>
    .Agresor{
        background-color:#B22222;
        color: white;
    }
    .Victima{
        background-color:#DAA520;
        color: white;
    }
    .Testigo{
        background-color:#F0E68C;
        color: black;
    }
</style>

<div class="div-pagina">
    <div id="div-pagina-titulo" class="div-pagina-titulo">
        Generador de Explicaciones de Resultado
    </div>
    <div id="dvData">

        <div id="divHidden" style="display: none">
            <label id="lblPerfil" style="font-weight: bold"></label>
            <label id="lblPremisa" style="font-weight: bold"></label>
            <label id="lblRegla" style="font-weight: bold"></label>
        </div>

        <table id="tblExplicacion">
            <tr>
                <td>
                    <label for="lblAlumno" >Alunno</label>
                </td>
                <td>
                    <input id="lblAlumno" type="text" disabled="true" >
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <label>El resultado obtenido fue:</label>
                </td>
            </tr>
            <tr>
                <td id="perfiles"  colspan="2" style="text-align: center">                    
                </td>
            </tr>
            <tr>
                <td  colspan="2">                    

                    <p>Para obtener el resultado anterior se ha sumado la cantidad de aciertos que ha tenido para cada perfil, se concluye las siguientes premisas:</p>

                </td>
            </tr>
            <tr>
                <td id="permisas"  colspan="2" style="text-align: center">                    
                </td>
            </tr>
            <tr>
                <td  colspan="2">                    

                    <p>Con estas permisas verdaderas se recorre la base de conocimientos en busca de reglas que cumplan con dichas permisas, la(s) regla(s) encontradas son:</p>

                </td>
            </tr>
            <tr>
                <td id="reglas"  colspan="2" style="text-align: center">                    
                </td>
            </tr>
            <tr>
                <td  colspan="2">                    

                    <!--<p>Para obtener el resultado anterior se ha sumado la cantidad de aciertos que ha tenido para cada perfil, se concluye las siguientes premisas:</p>-->
                    <p>El sistema analiza las conclusiones de este conjunto de reglas y busca las apariciones de
                        aquellos perfiles a evaluar teniendo un conjunto de perfiles se calcula el porcentaje de
                        aparicion en el conjunto de reglas y finalmente llegaria al resultado mostrado
                        Por ejemplo, el perfil <label id="lblPerfilMencionado" style="font-weight: bold"></label> se repite 
                        <label id="lblVecesPerfilMencionado" style="font-weight: bold"></label> por lo que su porcentaje de aparicion es de 
                        <label id="lblPorcentajePerfilMencionado" style="font-weight: bold"></label>
                        dentro del total de reglas que lo contienen perfiles</p>

                </td>
            </tr>
        </table>

    </div>
    <div style="align-content: center; text-align: center">
        <input type="button" id="btnCancelar" value="Cerrar" />
    </div>
</div>
