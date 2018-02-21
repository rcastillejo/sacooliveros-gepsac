angular.module('app.controllers')
.controller('messageFormatController',
	['$scope', '$http', '$state', 'serviceUrl', 'sessionService', 'formatTexto', 'mensajeTexto',
		function($scope,  $http, $state, serviceUrl, sessionService, formatTexto, mensajeTexto){
	// establece el texto en la applicacion
			$scope.formatTexto = formatTexto;
			//variables de inicio
			$scope.listMessageFormat = []; //almacena toda la data del servicio del objeto "messageFormats"
			$scope.selectMessageFormat = {}; //es parte No dependeiente de la data general, 
									 		//es usado para mostrar la data y ser enviado paraguardar
			

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
				}
			}

			function readDataSel() {
				console.log("readDataSel");
				$http({
					method: 'GET', 
					url: serviceUrl.urlServidor +  serviceUrl.urlMessageFormat
				})
				.success(function(data){
					console.log("formatos", data);
					$scope.listMessageFormat = data.messageFormats;
				})
				.error(function(data, status) {
					responseDataError(data, status);
				});

			}

			function saveData(parData) {
				console.log("saveData" , parData);
				$http({
					method: 'POST', 
					url: serviceUrl.urlServidor +  serviceUrl.urlMessageFormat,
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
					url: serviceUrl.urlServidor +  serviceUrl.urlMessageFormat,
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
					url: serviceUrl.urlServidor +  serviceUrl.urlMessageFormat + '/' + parId
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
				$scope.selectMessageFormat.messageFormatId = "";
				$scope.selectMessageFormat.messageFormatDesc = "";
				$scope.selectMessageFormat.transformerConfigFile = "";
				$scope.selectMessageFormat.routerConfigFile = "";
				$scope.selectMessageFormat.transformerStruct = "";
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
				angular.forEach($scope.listMessageFormat, function(value, key){
				    if(value.messageFormatId == dataSave.messageFormatId){
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
				$scope.selectMessageFormat = angular.copy(valRow);
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



