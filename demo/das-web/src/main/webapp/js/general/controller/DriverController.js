angular.module('app.controllers')
.controller('driverController',
	['$scope', '$http', '$state', 'serviceUrl', 'sessionService', 'driverTexto', 'mensajeTexto',
		function($scope,  $http, $state, serviceUrl, sessionService, driverTexto, mensajeTexto){
//**********Establece el texto en la applicacion
			$scope.driverTexto = driverTexto;
			//variables de inicio
			$scope.driverGeneral = {}; //almacena toda la data del servicio del objeto "AdminQueues"
			$scope.selectDriver = {
									filters: {inFilters: {inFilter:[]},
												outFilters: {outFilter:[]}
												}
									}; //es parte No dependeiente de la data general(AdminQueues.drivers), 
									 //es usado para mostrar la data y ser enviado paraguardar
			
			$scope.listAdminQueue = []; //Lista de la data servicio del objetoMEssageFormat
			$scope.statusType = [{"type":"ASYNCRONOUS"},{"type":"SIXSYNCRONOUS"},{"type":"SYNCRONOUS"}];
			$scope.statusValidarIp = [{"status":"true"},{"status":"false"}];
			$scope.statusPermiso = angular.copy($scope.statusValidarIp);

			//Valores fijos para los filtros de entrada
			$scope.optionInFilter = ['com.novatronic.loadbalancer.driver.filters.FromBinaryFrameFilter',
								   'com.novatronic.loadbalancer.driver.filters.PutBinAdqFilter',
								   'com.novatronic.loadbalancer.driver.filters.PutTokenBinAdqFilter'];
			$scope.selectionInFilter = [];//Valores seleccionados de las entradas

			//Valores fijos para los filtros de salida
			$scope.optionOutFilter = ['com.novatronic.loadbalancer.driver.filters.ToBinaryFrameFilter',
								   'com.novatronic.loadbalancer.driver.filters.RemoveTokenFilter'];
			$scope.selectionOutFilter = [];//Valores seleccionados de las salidas

			$scope.responseRest = ""; //Mensaje de la respuesta generada por las consultas y validaciones
			$scope.responseRestTag = ""; //Estilo de la respuesta generada por las consultas y validaciones
			$scope.hideAlert = true; //Visualización de la alerta de la respuesta(true: se muestra, false: se oculta)
			var stateNew = true; //Definir sie el proceso es un nuevo registo o una actualizacion
								 //(true: registro nuevo, false: registro a editar)

//**********Inicio de proceso por default
			readData();
			
//**********Inicio Consulta servicio rest
			function readData() {
				console.log("Estado Session:", sessionService.estadoSesion);
				if(sessionService.estadoSesion !== 00){
					jAlert(mensajeTexto.sesionExpirada, mensajeTexto.controlAcces, function () {
						$state.go('login');	
		        	});
				}else{
					readDataSel();
					readDataQueue();
				}
			}

			function readDataSel() {
				console.log("readDataSel");
				$http({
					method: 'GET', 
					url: serviceUrl.urlServidor +  serviceUrl.urlDriver
				})
				.success(function(data){
					console.log("Drivers", data);
					$scope.driverGeneral = data;
				})
				.error(function(data, status) {
					responseDataError(data, status);
				});
			}
			function readDataQueue() {
				console.log("readDataQueue");
				$http({
					method: 'GET', 
					url: serviceUrl.urlServidor +  serviceUrl.urlAdminQueue
				})
				.success(function(data){
					console.log("Colas", data);
					$scope.listAdminQueue = data.adminQueues;
				})
				.error(function(data, status) {
					responseDataError(data, status);
				});
			}

			function saveData(parData) {
				console.log("saveData" , parData);
				$http({
					method: 'POST', 
					url: serviceUrl.urlServidor +  serviceUrl.urlDriver,
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
					url: serviceUrl.urlServidor +  serviceUrl.urlDriver,
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
					url: serviceUrl.urlServidor +  serviceUrl.urlDriver + '/' + parId
				})
				.success(function(data){
					responseDataOK(data);
				})
				.error(function(data, status) {
					responseDataError(data, status);
				});
			}

//**********Inicio de metodos Fijos para mantener actualizado la informacion
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
				$scope.selectDriver.driverId = "";
				$scope.selectDriver.adminQueueId = "";
				$scope.selectDriver.type = "";
				$scope.selectDriver.timeOutConnect = "";
				$scope.selectDriver.timeOutRead = "";
				$scope.selectDriver.retries = "";
				$scope.selectDriver.timeOutQueueRead = "";

				$scope.selectDriver.name = "";
				$scope.selectDriver.port = "";
				$scope.selectDriver.maxConcurrentConnections = "";
				$scope.selectDriver.forwardProcess = "";
				$scope.selectDriver.validaIp = "";
				$scope.selectDriver.concurrentUserSupport = "";
				$scope.selectionInFilter = [];
				$scope.selectionOutFilter = [];
			}
			
//**********Inicio de Botones de proceso, controles
			$scope.nuevo = function(){
				limpiarCampos();
				$scope.hideAlert = true
			};

			$scope.refrescar = function(){
				stateNew = true;
				readData();
				readDataQueue();
				$scope.hideAlert = true
			};

			$scope.grabar = function(dataSave){
				$scope.selectDriver.filters.inFilters.inFilter = $scope.selectionInFilter;
				$scope.selectDriver.filters.outFilters.outFilter = $scope.selectionOutFilter;
				console.log("grabar" , dataSave);
				var existId = false;
				angular.forEach($scope.driverGeneral.drivers, function(value, key){
				    if(value.driverId == dataSave.driverId){
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
				limpiarCampos();
				$scope.selectDriver = angular.copy(valRow);
				if(!$scope.selectDriver.filters.inFilters.inFilter){
					$scope.selectDriver.filters.inFilters.inFilter = [];
				}
				if(!$scope.selectDriver.filters.outFilters.outFilter){
					$scope.selectDriver.filters.outFilters.outFilter = [];
				}

				$scope.selectionInFilter = $scope.selectDriver.filters.inFilters.inFilter;
				$scope.selectionOutFilter = $scope.selectDriver.filters.outFilters.outFilter;
				
				stateNew = false;
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

			$scope.getSelectionInFilter = function getSelectionInFilter(filter) {
			    var idx = $scope.selectionInFilter.indexOf(filter);
			    if (idx > -1) {
			    	$scope.selectionInFilter.splice(idx, 1);
			    }else {
			    	$scope.selectionInFilter.push(filter);
			    }
			    console.log("Filter In: ", $scope.selectionInFilter);
			};

			$scope.getSelectionOutFilter = function getSelectionOutFilter(filter) {
			    var idy = $scope.selectionOutFilter.indexOf(filter);
			    if (idy > -1) {
			    	$scope.selectionOutFilter.splice(idy, 1);
			    }else {
			    	$scope.selectionOutFilter.push(filter);
			    }
			    console.log("Filter Out: ", $scope.selectionOutFilter);
			};

		}
	]);