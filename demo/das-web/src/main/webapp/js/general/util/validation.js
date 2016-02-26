angular.module('app.factories', [])
.factory('validation', function() {
	    
	    var interfaz = {
		    nombre: "Manolo",
		    getDescargas: function(){
		        return descargasRealizadas;
		    },
		    nuevaDescarga: function(descarga){
		        descargasRealizadas.push(descarga);
		    }
	    }
	    return interfaz;
});