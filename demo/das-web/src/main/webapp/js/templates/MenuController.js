angular.module('app.controllers')
.controller('menuController',
	['$scope', '$http', '$state', 'botonTexto', 'sessionService', 
	function($scope, $http, $state, botonTexto, sessionService){
	// establece el texto en la applicacion
	$scope.botonTexto = botonTexto;

	$scope.menu = [
	{ nombre : 'Menu 1' },
	{ nombre : 'Menu 2' },
	{ nombre : 'Menu 3' },
	{ nombre : 'Menu 4' },
	{ nombre : 'Menu 5' }];

	$scope.viewFormateador = function(){
		$state.go('messageFormat');
	};
	$scope.viewCola = function(){
		$state.go('adminQueue');
	};
	$scope.viewDriver = function(){
		$state.go('driver');
	};
	$scope.viewBalanceador = function(){
		$state.go('balancer');
	};
	$scope.viewRouter = function(){
		$state.go('route');
	};
	$scope.viewServicio = function(){
		$state.go('service');
	};
	$scope.viewNodo = function(){
		$state.go('serviceNode');
	};
	$scope.viewPerfil = function(){
		$state.go('profile');
	};
	$scope.viewSixadcCliente = function(){
		$state.go('sixadcClient');
	};
	$scope.viewSalir = function(){
		sessionService.estadoSesion = 99;
		console.log("La sesion fue cerrada:", sessionService.estadoSesion);
		$state.go('login');
	};
	
}]);