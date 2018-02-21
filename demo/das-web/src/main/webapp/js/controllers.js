'use strict';

/* Controllers */

var app = angular.module('ngdemo.controllers', []);


// Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
app.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});


app.controller('DummyCtrl', ['$scope', 'DummyFactory', function ($scope, DummyFactory) {
        $scope.bla = 'bla from controller';
        DummyFactory.get({}, function (dummyFactory) {
            $scope.firstname = dummyFactory.firstName;
        })
    }]);

app.controller('AcosoEscolarListCtrl', ['$scope', 'AcosoEscolarEvaluadosFactory', 'AcosoEscolarEvaluadoFactory', '$location',
    function ($scope, AcosoEscolarEvaluadosFactory, AcosoEscolarEvaluadoFactory, $location, alertService) {
        $scope.alerts = [];
        /*
         // callback for ng-click 'editUser':
         $scope.editUser = function (userId) {
         $location.path('/user-detail/' + userId);
         };
         
         // callback for ng-click 'deleteUser':
         $scope.deleteUser = function (userId) {
         UserFactory.delete({ id: userId });
         $scope.users = UsersFactory.query();
         };
         
         // callback for ng-click 'createUser':
         $scope.createNewUser = function () {
         $location.path('/user-creation');
         };*/

        // callback for ng-click 'createUser':
        $scope.generateExplication = function (acosoEscolarId) {
            $location.path('/experto/acoso-escolar-explication/' + acosoEscolarId);
        };

        $scope.users = AcosoEscolarEvaluadosFactory.query(function (data) {}, function (error) {
            var msg = error instanceof String ? error : 'error desconocido';
            console.log('error del servicio', msg);
            $scope.addAlert({type: 'danger', msg: msg});
        });

        $scope.addAlert = function (type, msg) {
            $scope.alerts.push({type: type, msg: msg});
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };

    }]);

app.controller('AcosoEscolarExplicationCtrl', ['$scope', '$routeParams', 'AcosoEscolarEvaluadoFactory', '$location',
    function ($scope, $routeParams, AcosoEscolarEvaluadoFactory, $location) {
        $scope.alerts = [];
        /*
         // callback for ng-click 'updateUser':
         $scope.updateUser = function () {
         AcosoEscolarEvaluadoFactory.update($scope.user);
         $location.path('/user-list');
         };
         
         // callback for ng-click 'cancel':
         $scope.cancel = function () {
         $location.path('/user-list');
         };*/

        $scope.user = AcosoEscolarEvaluadoFactory.show({id: $routeParams.id}, function (data) {}, function (error) {
            console.log('error del servicio', error);
            $scope.alerts.push({type: 'warning', msg: error});
        });
    }]);

app.controller('UserCreationCtrl', ['$scope', 'UsersFactory', '$location',
    function ($scope, UsersFactory, $location) {

        // callback for ng-click 'createNewUser':
        $scope.createNewUser = function () {
            UsersFactory.create($scope.user);
            $location.path('/user-list');
        }
    }]);