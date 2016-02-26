angular.module('app.controllers')
.controller('clientController',
	['$scope', '$http', '$state', 'serviceUrl', 'sessionService', 'clientTexto', 'mensajeTexto',
		function($scope,  $http, $state, serviceUrl, sessionService, clientTexto, mensajeTexto){
	// establece el texto en la applicacion
			$scope.clientTexto = clientTexto;
			$scope.sortType = 'sixadcClientId';
			$scope.sortReverse = false;
			$scope.searchAll = '';
			//variables de inicio
			$scope.clientGeneral = []; //almacena toda la data del servicio
			$scope.selectClient = { atributos:{atributo:[]},
									ipAddresses:{ipAddresses:[]},
									sixadcClientProfiles:{profileId:[]}
								 }; //es parte No dependeiente de la data general, 
									 //es usado para mostrar la data y ser enviado para guardar
			
			$scope.constantEnabled = [{"enabled":"true"},{"enabled":"false"}];

			$scope.listProfile = []; //Lista de la data servicio del objeto Profile
			$scope.selectionProfile = [];//Valores seleccionados de profile

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
					readDataProfile();
				}
			}

			function readDataSel() {
				console.log("readDataSel");
				$http({
					method: 'GET', 
					url: serviceUrl.urlServidor +  serviceUrl.urlSixadcClient
				})
				.success(function(data){
					console.log("Clientes", data);
					$scope.clientGeneral = data.sixadcClients;
				})
				.error(function(data, status) {
					responseDataError(data, status);
				});
			}
			function readDataProfile() {
				console.log("readDataProfile");
				$http({
					method: 'GET', 
					url: serviceUrl.urlServidor +  serviceUrl.urlProfile
				})
				.success(function(data){
					console.log("Profiles", data);
					$scope.listProfile = data.profiles;
				})
				.error(function(data, status) {
					responseDataError(data, status);
				});
			}

			function saveData(parData) {
				console.log("saveData" , parData);
				$http({
					method: 'POST', 
					url: serviceUrl.urlServidor +  serviceUrl.urlSixadcClient,
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
					url: serviceUrl.urlServidor +  serviceUrl.urlSixadcClient,
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
					url: serviceUrl.urlServidor +  serviceUrl.urlSixadcClient + '/' + parId
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
				$scope.selectClient.sixadcClientId = "";
				$scope.selectClient.sixadcClientDesc = "";

				$scope.selectClient.sixHostname = "";
				$scope.selectClient.sixUsername = "";
				$scope.selectClient.sixPassword = "";
				$scope.selectClient.enabled = "";
				$scope.selectionProfile = [];
				$scope.selectClient.ipAddresses.ipAddresses = [];
				$scope.selectClient.atributos.atributos = [];
				$scope.valAddTxt = '';
				$scope.valAddName = '';
		        $scope.valAddValor = '';
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
				readDataProfile();
				$scope.hideAlert = true
			};

			$scope.grabar = function(dataSave){
				$scope.selectClient.sixadcClientProfiles.profileId = $scope.selectionProfile;
				console.log("grabar" , dataSave);
				var existId = false;
				angular.forEach($scope.clientGeneral, function(value, key){
				    if(value.sixadcClientId == dataSave.sixadcClientId){
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
				$scope.selectClient = angular.copy(valRow);

				if(!$scope.selectClient.sixadcClientProfiles.profileId){
					$scope.selectClient.sixadcClientProfiles.profileId = [];
				} 

				$scope.selectionProfile = $scope.selectClient.sixadcClientProfiles.profileId;
				$scope.valAddTxt = '';
				$scope.valAddName = '';
		        $scope.valAddValor = '';
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

			$scope.createOptionIp = function() {
				if(!$scope.selectClient.ipAddresses.ipAddresses){
					$scope.selectClient.ipAddresses.ipAddresses = [];
				}
		        $scope.selectClient.ipAddresses.ipAddresses.push($scope.valAddTxt);
		        $scope.valAddTxt = '';
		    };

		    $scope.createOptionAtrib = function() {
				if(!$scope.selectClient.atributos.atributos){
					$scope.selectClient.atributos.atributos = [];
				}
		        $scope.selectClient.atributos.atributos.push({ nombre: $scope.valAddName, valor : $scope.valAddValor});
		        $scope.valAddName = '';
		        $scope.valAddValor = '';
		    };

		    $scope.deleteOptionIp = function(parId) {
		    	console.log("deleteOptionIp" , parId);
			    var valores = $scope.selectClient.ipAddresses.ipAddresses;
		        $scope.selectClient.ipAddresses.ipAddresses = [];
		        angular.forEach(valores, function(valIps) {
		            if (valIps != parId){
		               $scope.selectClient.ipAddresses.ipAddresses.push(valIps);
		           }
				});
				console.log("Lista Ips: ", $scope.selectClient.ipAddresses.ipAddresses);
		    };

		    $scope.deleteOptionAtrib = function(parNombre) {
		    	console.log("deleteOptionAtrib" , parNombre);
			    var valores = $scope.selectClient.atributos.atributos;
		        $scope.selectClient.atributos.atributos = [];
		        angular.forEach(valores, function(valAtributo) {
		            if (valAtributo.nombre != parNombre){
		               $scope.selectClient.atributos.atributos.push(valAtributo);
		           }
				});
				console.log("Atributos: ", $scope.selectClient.atributos.atributos);
		    };

			$scope.getSelectionProfile = function getSelectionProfile(filter) {
			    var idy = $scope.selectionProfile.indexOf(filter);
			    if (idy > -1) {
			    	$scope.selectionProfile.splice(idy, 1);
			    }else {
			    	$scope.selectionProfile.push(filter);
			    }
			    console.log("Profiles: ", $scope.selectionProfile);
			};
			
		}
	]);