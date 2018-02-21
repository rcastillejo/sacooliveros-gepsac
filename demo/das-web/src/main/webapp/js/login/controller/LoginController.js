angular.module('app.controllers', [])
.controller('loginController', 
	['$scope', '$http', '$state', 'loginTextos', 'serviceUrl', 'queryRuta', 'sessionService',
	function($scope, $http, $state, loginTextos, serviceUrl, $queryRuta, sessionService){
		
//************Establece el texto en la applicacion
		$scope.loginTextos = loginTextos;
		$scope.loginPermisos = {permisos:[]};
		$scope.txtLogin = {user:"admin", pass: "Admin2"};

//***********Servicos rest
		function autenticarAcceso(parData) {
			console.log("Procesando consulta de acceso...:" , parData);
			$http({
				method: 'POST', 
				url: serviceUrl.urlServidor +  serviceUrl.urlControlAcceso,
				headers: {'Authorization': incriptarAcceso(parData)}
				//headers: {'Authorization': 'YWRtaW46QWRtaW4x'}
			})
			.success(function(data){
				console.log("OK, Permisos obtenidos:", data);
				sessionService.estadoSesion = 00;
				controlSesion(data);
			})
			.error(function(data, status) {
		 		console.log("Salida de status", status);
		 		sessionService.estadoSesion = status;
				responseData(data);
			});
		}

//***********funciones personalizadas
		function controlSesion(lista){
			$scope.loginPermisos = lista.permisos;
			console.log("Cantidad de permsisos:" , lista.permisos.length);
			if(lista.permisos.length > 0){
				$state.go('principal');	
			}
		}

		function responseData(parData){
			if(sessionService.estadoSesion !== 00){
				jAlert(parData, "Control de Acceso", function () {
		        });
		        limpiarCampo();
			}
		}

		function incriptarAcceso(parData){
			var formato = parData.user + ':' + parData.pass;
			console.log("formato a incriptar: ", formato);
			var incriptado = window.btoa(unescape(encodeURIComponent(formato)));
			console.log("incriptado: ", incriptado);
			return  incriptado;
		}

		function limpiarCampo(){
			$scope.txtLogin.pass = "";
		}		
		
//**********Botones de proceso
		$scope.ingresar = function(parData){
			console.log("Inicio de evento auntenticar...:" , parData);
			console.log("Estado Acceso-Login:", sessionService.estadoSesion);
			autenticarAcceso(parData);
			//console.log("Permisos desde controller", LoginService.autenticarUsuario(parData);
		};
	
}]);