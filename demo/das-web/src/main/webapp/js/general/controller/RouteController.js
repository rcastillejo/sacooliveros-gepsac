 angular.module('app.controllers')
.controller('routeController',
	['$scope', '$http', '$state', 'serviceUrl', 'sessionService', 'routeTexto', 'mensajeTexto',
		function($scope,  $http, $state, serviceUrl, sessionService, routeTexto, mensajeTexto){
	// establece el texto en la applicacion
			$scope.routeTexto = routeTexto;
			//variables de inicio
			$scope.listRoute = []; //almacena toda la data del servicio del objeto "Route"
			$scope.selectRoute = {}; //es parte No dependeiente de la data general, 
									 		//es usado para mostrar la data y ser enviado paraguardar

			$scope.listBalancer = []; //almacena toda la data del servicio del objeto "Balancer"
			$scope.constantStatus = [{"status":"AVAILABLE"},{"status":"UNAVAILABLE"}];

			$scope.responseRest = ""; //Mensaje de la respuesta generada por las consultas y validaciones
			$scope.responseRestTag = ""; //Estilo de la respuesta generada por las consultas y validaciones
			$scope.hideAlert = true; //Visualización de la alerta de respuesta(true: se muestra, false: se oculta)
			var stateNew = true; //Definir sie el proceso es un nuevo registo o una actualizacion
								 //(true: registro nuevo, false: registro a editar)

//***********Inicio de proceso por default
			readData();

//***********Inicio Consulta servicio rest
			function readData() {
				console.log("Estado Session:", sessionService.estadoSesion);
				if(sessionService.estadoSesion !== 00){
					jAlert(mensajeTexto.sesionExpirada, mensajeTexto.controlAcces, function () {
						$state.go('login');	
		        	});
				}else{
					readDataSel();
					readDataBalancer();
				}
			}

			function readDataSel() {
				console.log("readDataSel");
				$http({
					method: 'GET', 
					url: serviceUrl.urlServidor +  serviceUrl.urlRoute
				})
				.success(function(data){
					console.log("Routers", data);
					$scope.listRoute = data.routes;
				})
				.error(function(data, status) {
					responseDataError(data, status);
				});
			}

			function readDataBalancer() {
				console.log("readDataBalancer");
				$http({
					method: 'GET', 
					url: serviceUrl.urlServidor +  serviceUrl.urlBalancer
				})
				.success(function(data){
					console.log("Balanceadores", data);
					$scope.listBalancer = data.balancers;
				})
				.error(function(data, status) {
					responseDataError(data, status);
				});
			}

			function saveData(parData) {
				console.log("saveData" , parData);
				$http({
					method: 'POST', 
					url: serviceUrl.urlServidor +  serviceUrl.urlRoute,
					data: parData
				})
				.success(function(data){
					responseDataOK(data);
				})
				.error(function(data, status) {
			 		responseDataError(data, status);
				});
			}

			function updateData(parData) {
				console.log("updateData" , parData);
				$http({
					method: 'PUT', 
					url: serviceUrl.urlServidor +  serviceUrl.urlRoute,
					data: parData
				})
				.success(function(data){
					responseDataOK(data);
				})
				.error(function(data, status) {
			 		responseDataError(data, status);
				});
			}

			function deleteData(parId) {
				console.log("deleteData", parId);
				$http({
					method: 'DELETE', 
					url: serviceUrl.urlServidor +  serviceUrl.urlRoute + '/' + parId
				})
				.success(function(data){
					responseDataOK(data);
				})
				.error(function(data, status) {
			 		responseDataError(data, status);
				});
			}

//***********Inicio de metodos Fijos para mantener actualizado la informacion
			function responseDataOK(parData){
				if(parData.codigo == "00"){
					console.log("responseDataOK", parData);
					$scope.responseRestTag = "success";
					$scope.responseRest = parData.descripcion;
					$scope.hideAlert = false;
					readData();	
					limpiarCampos();
				}
			}

			function responseDataError(parData, status){
				console.log("responseDataError", status);
				if(status === 401){
					sessionService.estadoSesion = status;
					$state.go('login');
				}else{
					console.log("responseDataError", parData);
					$scope.responseRestTag = "danger";
					$scope.responseRest = parData.descripcion;
					$scope.hideAlert = false;
				}
			}

			function responseDataWarning(parDescripcion){
				console.log("responseDataWarning", parDescripcion);
				$scope.responseRest = parDescripcion;
				$scope.responseRestTag = "warning";
				$scope.hideAlert = false;
			}

			function refreshData (){
				console.log("Refrescando la data...");
				readData();	
				readDataBalancer();
				limpiarCampos();
			}

			function limpiarCampos(){
				stateNew = true;
				$scope.selectRoute.routeId = "";
				$scope.selectRoute.routeDesc = "";
				$scope.selectRoute.balancerId = "";
				$scope.selectRoute.status = "";
			}
//***********Inicio de metodos Fijos para mantener actualizado la informacion			
			
//***********Inicio de Botones de proceso, controles
			$scope.nuevo = function(){
				limpiarCampos();
				$scope.hideAlert = true 
			};

			$scope.refrescar = function(){
				stateNew = true;
				readData();
				readDataBalancer();
				$scope.hideAlert = true 
			};

			$scope.grabar = function(dataSave){
				console.log("grabar" , dataSave);
				var existId = false;
				angular.forEach($scope.listRoute, function(value, key){
				    if(value.routeId == dataSave.routeId){
				      	existId = true;
				    }
				});
				if(existId){
					if(!stateNew){
			      		console.log("Editar");
			      		updateData(dataSave);
		      		}else{
		      			responseDataWarning(mensajeTexto.idDuplicado);
		      		}
				}else{
					if(stateNew){
						console.log("Nuevo");
						saveData(dataSave);
					}else{
						responseDataWarning(mensajeTexto.idPerdido);
					}
				}

			};

			$scope.editar = function(valRow){
				console.log("editar", valRow);
				$scope.hideAlert = true 
				stateNew = false;
				$scope.selectRoute = angular.copy(valRow);
			};
			
			$scope.quitar = function(valId){
				console.log("quitar" , valId);
				jConfirm(mensajeTexto.pregEliminar, mensajeTexto.controlProceso,  
					function(result) {
				    if(result){
						deleteData(valId);
					}
				});
			};

		}
	]);



