angular.module('app.controllers')
.controller('profileController',
	['$scope', '$http', '$state', 'serviceUrl', 'sessionService', 'profileTexto', 'mensajeTexto',
		function($scope,  $http, $state, serviceUrl, sessionService, profileTexto, mensajeTexto){
	// establece el texto en la applicacion
			$scope.profileTexto = profileTexto;
			//variables de inicio
			$scope.profileGeneral = {}; //almacena toda la data del servicio del objeto "profiles"
			$scope.selectProfile = { profileDrivers:{driverIds:[]},
									authServices:{serviceIds:[]}
								 }; //es parte No dependeiente de la data general(profiles), 
									 //es usado para mostrar la data y ser enviado para guardar
			
			$scope.listDriver = []; //Lista de la data servicio del objeto Driver
			$scope.selectionDriver = [];//Valores seleccionados de Driver

			$scope.listService = []; //Lista de la data servicio del objeto Service
			$scope.selectionService = [];//Valores seleccionados de Service

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
					readDataDriver();
					readDataService();
				}
			}

			function readDataSel() {
				console.log("readDataSel");
				$http({
					method: 'GET', 
					url: serviceUrl.urlServidor +  serviceUrl.urlProfile
				})
				.success(function(data){
					console.log("Profiles", data);
					$scope.profileGeneral = data.profiles;
				})
				.error(function(data, status) {
					responseDataError(data, status);
				});
			}
			function readDataDriver() {
				console.log("readDataDriver");
				$http({
					method: 'GET', 
					url: serviceUrl.urlServidor +  serviceUrl.urlDriver
				})
				.success(function(data){
					console.log("Drivers:", data);
					$scope.listDriver = data.drivers;
				})
				.error(function(data, status) {
					responseDataError(data, status);
				});
			}
			function readDataService() {
				console.log("readDataService");
				$http({
					method: 'GET', 
					url: serviceUrl.urlServidor +  serviceUrl.urlService
				})
				.success(function(data){
					console.log("Service", data);
					$scope.listService = data.services;
				})
				.error(function(data, status) {
					responseDataError(data, status);
				});
			}

			function saveData(parData) {
				console.log("saveData" , parData);
				$http({
					method: 'POST', 
					url: serviceUrl.urlServidor +  serviceUrl.urlProfile,
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
					url: serviceUrl.urlServidor +  serviceUrl.urlProfile,
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
					url: serviceUrl.urlServidor +  serviceUrl.urlProfile + '/' + parId
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

			function limpiarCampos(){
				stateNew = true;
				$scope.selectProfile.profileId = "";
				$scope.selectProfile.profileDesc = "";
				$scope.selectionDriver = [];
				$scope.selectionService = [];
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
				readDataDriver();
				readDataService();
				$scope.hideAlert = true 
			};

			$scope.grabar = function(dataSave){
				$scope.selectProfile.profileDrivers.driverIds = $scope.selectionDriver;
				$scope.selectProfile.authServices.serviceIds = $scope.selectionService;
				console.log("grabar" , dataSave);
				var existId = false;
				angular.forEach($scope.profileGeneral, function(value, key){
				    if(value.profileId == dataSave.profileId){
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
				$scope.selectProfile = angular.copy(valRow);

				if(!$scope.selectProfile.profileDrivers.driverIds){
					$scope.selectProfile.profileDrivers.driverIds = [];
				}
				if(!$scope.selectProfile.authServices.serviceIds){
					$scope.selectProfile.authServices.serviceIds = [];
				} 

				$scope.selectionDriver = $scope.selectProfile.profileDrivers.driverIds;
				$scope.selectionService = $scope.selectProfile.authServices.serviceIds;
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

			$scope.getSelectionDriver = function getSelectionDriver(filter) {
			    var idy = $scope.selectionDriver.indexOf(filter);
			    if (idy > -1) {
			    	$scope.selectionDriver.splice(idy, 1);
			    }else {
			    	$scope.selectionDriver.push(filter);
			    }
			    console.log("drivers: ", $scope.selectionDriver);
			};

			$scope.getSelectionService = function getSelectionService(filter) {
			    var idy = $scope.selectionService.indexOf(filter);
			    if (idy > -1) {
			    	$scope.selectionService.splice(idy, 1);
			    }else {
			    	$scope.selectionService.push(filter);
			    }
			    console.log("Services: ", $scope.selectionService);
			};
			
		}
	]);