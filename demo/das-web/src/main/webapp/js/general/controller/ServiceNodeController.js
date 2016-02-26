angular.module('app.controllers')
.controller('serviceNodeController',
	['$scope', '$http', '$state', 'serviceUrl', 'sessionService', 'serviceNodeTexto', 'mensajeTexto',
		function($scope,  $http, $state, serviceUrl, sessionService, serviceNodeTexto, mensajeTexto){
	// establece el texto en la applicacion
			$scope.serviceNodeTexto = serviceNodeTexto;
			//variables de inicio
			$scope.serviceNodeGeneral = []; //almacena toda la data del servicio del objeto "serviceNode"
			$scope.selectServiceNode = {}; //es parte No dependeiente de la data general, 
									 		//es usado para mostrar la data y ser enviado paraguardar

			$scope.listService = []; //Lista de la data servicio del objeto Service
			$scope.selectionService = [];//Valores seleccionados de Service

			listServiceChannel = [];
			

			$scope.responseRest = ""; //Mensaje de la respuesta generada por las consultas y validaciones
			$scope.responseRestTag = ""; //Estilo de la respuesta generada por las consultas y validaciones
			$scope.hideAlert = true; //Visualización de la alerta de respuesta(true: se muestra, false: se oculta)
			var stateNew = true; //Definir sie el proceso es un nuevo registo o una actualizacion
								 //(true: registro nuevo, false: registro a editar)

			/*
			$scope.tabs = [
                { name: 'Tab1', id: 'Tab1'},
                { name: 'Tab2', id: 'Tab2'},
                { name: 'Tab3', id: 'Tab3'}
            ]*/
            
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
					//readDataDriver();
					readDataService();
				}
			}

			function readDataSel() {
				console.log("readDataSel");
				$http({
					method: 'GET', 
					url: serviceUrl.urlServidor +  serviceUrl.urlServiceNode
				})
				.success(function(data){
					console.log("Servicios", data);
					$scope.serviceNodeGeneral = data.serviceNodes;
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
					url: serviceUrl.urlServidor +  serviceUrl.urlServiceNode,
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
					url: serviceUrl.urlServidor +  serviceUrl.urlServiceNode,
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
					url: serviceUrl.urlServidor +  serviceUrl.urlServiceNode + '/' + parId
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
				$scope.selectServiceNode.serviceNodeId = "";
				$scope.selectServiceNode.ipAddress = "";
				$scope.selectServiceNode.binMonitorPause = "";
			}		
			
//***********Inicio de Botones de proceso, controles
			$scope.nuevo = function(){
				limpiarCampos();
				$scope.hideAlert = true
			};

			$scope.refrescar = function(){
				stateNew = true;
				readData();
				$scope.hideAlert = true
			};

			$scope.grabar = function(dataSave){
				console.log("grabar" , dataSave);
				var existId = false;
				angular.forEach($scope.serviceNodeGeneral, function(value, key){
				    if(value.serviceNodeId == dataSave.serviceNodeId){
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
				$scope.selectServiceNode = angular.copy(valRow);
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



