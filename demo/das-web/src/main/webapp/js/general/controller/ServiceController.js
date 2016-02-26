angular.module('app.controllers')
.controller('serviceController',
	['$scope', '$http', '$state', 'serviceUrl', 'sessionService', 'serviceTexto', 'mensajeTexto',
		function($scope,  $http, $state, serviceUrl, sessionService, serviceTexto, mensajeTexto){
	// establece el texto en la applicacion
			$scope.serviceTexto = serviceTexto;
			//variables de inicio
			$scope.serviceGeneral = {}; //almacena toda la data del servicio del objeto "services"
			$scope.selectService = { authorizationBins:{bin:[]},
									routes:{routeId:[]}
								 }; //es parte No dependeiente de la data general(services.services), 
									 //es usado para mostrar la data y ser enviado para guardar
			
			$scope.listRoute = []; //Lista de la data servicio del objeto Routes
			$scope.selectionRoute = [];//Valores seleccionados de route

			$scope.responseRest = ""; //Mensaje de la respuesta generada por las consultas y validaciones
			$scope.responseRestTag = ""; //Estilo de la respuesta generada por las consultas y validaciones
			$scope.hideAlert = true; //Visualización de la alerta de la respuesta(true: se muestra, false: se oculta)
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
					readDataRoute();
				}
			}

			function readDataSel() {
				console.log("readDataSel");
				$http({
					method: 'GET', 
					url: serviceUrl.urlServidor +  serviceUrl.urlService
				})
				.success(function(data){
					console.log("Servicios:", data);
					$scope.serviceGeneral = data;
				})
				.error(function(data, status) {
					responseDataError(data, status);
				});
			}
			function readDataRoute() {
				console.log("readDataRoute");
				$http({
					method: 'GET', 
					url: serviceUrl.urlServidor +  serviceUrl.urlRoute
				})
				.success(function(data){
					console.log("Routes:", data);
					$scope.listRoute = data.routes;
				})
				.error(function(data, status) {
					responseDataError(data, status);
				});
			}

			function saveData(parData) {
				console.log("saveData" , parData);
				$http({
					method: 'POST', 
					url: serviceUrl.urlServidor +  serviceUrl.urlService,
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
					url: serviceUrl.urlServidor +  serviceUrl.urlService,
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
					url: serviceUrl.urlServidor +  serviceUrl.urlService + '/' + parId
				})
				.success(function(data){
					responseDataOK(data);
				})
				.error(function(data, status) {
			 		responseDataError(data, status);
				});
			}
//***********Inicio Consulta servicio rest

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

			function limpiarCampos(){
				stateNew = true;
				$scope.selectService.serviceId = "";
				$scope.selectService.serviceDesc = "";
				$scope.selectService.discriminationRules = "";
				$scope.selectionRoute = [];
				$scope.selectService.authorizationBins.bin = [];
				$scope.valAddTxt = '';
			}
//***********Fin de metodos Fijos para mantener actualizado la informacion
			
//***********Inicio de Botones de proceso, controles
			$scope.nuevo = function(){
				limpiarCampos();
				$scope.hideAlert = true 
			};

			$scope.refrescar = function(){
				stateNew = true;
				readData();
				readDataRoute();
				$scope.hideAlert = true 
				//Prueba con textarea
				$scope.valor = {};
				console.log("Datos del textarea: ", $scope.binAutorizador);
			};

			$scope.grabar = function(dataSave){
				$scope.selectService.routes.routeId = $scope.selectionRoute;
				console.log("grabar" , dataSave);
				var existId = false;
				angular.forEach($scope.serviceGeneral.services, function(value, key){
				    if(value.serviceId == dataSave.serviceId){
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
				$scope.selectService = angular.copy(valRow);

				if(!$scope.selectService.routes.routeId){
					$scope.selectService.routes.routeId = [];
				} 

				$scope.selectionRoute = $scope.selectService.routes.routeId;
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

			$scope.createOption = function() {
				if(!$scope.selectService.authorizationBins.bin){
					$scope.selectService.authorizationBins.bin = [];
				}
		        $scope.selectService.authorizationBins.bin.push( {binId: $scope.valAddTxt, binStatus:'AVAILABLE'});
		        $scope.valAddTxt = '';
		    };

		    $scope.deleteOption = function(parId) {
		    	console.log("deleteOption:" , parId);
			    var valores = $scope.selectService.authorizationBins.bin;
		        $scope.selectService.authorizationBins.bin = [];
		        angular.forEach(valores, function(valBin) {
		            if (valBin.binId != parId){
		               $scope.selectService.authorizationBins.bin.push(valBin);
		           }
				});
				console.log("Bins: ", $scope.selectService.authorizationBins.bin);
		    };
		      
			$scope.getSelectionRoute = function getSelectionRoute(filter) {
			    var idy = $scope.selectionRoute.indexOf(filter);
			    if (idy > -1) {
			    	$scope.selectionRoute.splice(idy, 1);
			    }else {
			    	$scope.selectionRoute.push(filter);
			    }
			    console.log("Routers: ", $scope.selectionRoute);
			};
			
		}
	]);