

<script type = "text/javascript" >
    var fromUrl;
    var serviceUrl = "http://localhost:8180/gepsac-service/experto";
    var action = '/GenerarExplicacion.do';
    $(document).ready(function () {
        init();

        //Cancelar la Evaluacion del Alumno Nuevo
        $("#btnCancelar").click(function (e) {
            e.preventDefault();
            location.assign(fromUrl);
        });
    });


    function getRequestParameter(name){
        if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
           return decodeURIComponent(name[1]);
     }
     
    function init() {
        
        fromUrl = getRequestParameter("fromUrl");
        var codigoEvaluacion = getRequestParameter("codigo");
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
        $("#lblPerfil").append(objeto.perfil.nombre);
        
        
        for (var i in objeto.preguntas) {
            cargarPregunta(objeto.preguntas[i]);
            if(i < objeto.preguntas.length - 1){
                $("#lblPerguntasAfirmadas").append(" > ");
            }
        }
        
    }
        
    function cargarPregunta(json) {
        console.log('cargando...', json);
        $("#lblPerguntasAfirmadas").append(json.pregunta.alias);
    }
 
</script>

<div class="div-pagina">
    <div id="div-pagina-titulo" class="div-pagina-titulo">
        Generador de Explicaciones de Resultado
    </div>
    <div id="dvData">
        
        <label for="lblAlumno" >Alunno</label>
        <input id="lblAlumno" type="text" disabled="true" >
        
        <label>El resultado obtenido fue</label>
        <br/>
        <h2><label id="lblPerfil"></label></h2>
        <br/>
           
        <p>Para obtener el resultado anterior se ha sumado la cantidad de aciertos que ha tenido para cada perfil, se concluye las siguientes premisas:</p>
        
        <h2><label id="lblPerguntasAfirmadas"></label></h2>
        
        <br/>
        
        <!--<p>Con estas permisas verdaderas se recorre la base de conocimientos en busca de reglas que cumplan con dichas permisas, la(s) regla(s) encontradas son:</p>
        
        <h1><label id="lblReglas"></label></h1>-->
        
        <p>El sistema analiza las conclusiones de este conjunto de reglas y busca las apariciones de aquellos perfiles, 
            se calcula el porcentaje de aparicion en el conjunto de reglas y finalmente llegaria al resultado mostrado. Por ejemplo, 
            el perfil victima se repite 1 vez por lo que su porcentaje de aparicion es 100% dentro del total de reglas que contiene los perfiles</p>        
    </div>
    <div style="align-content: center; text-align: center">
        <input type="button" id="btnCancelar" value="Cerrar" />
    </div>
</div>
