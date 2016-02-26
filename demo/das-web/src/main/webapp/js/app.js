'use strict';

// Declare app level module which depends on filters, and services
angular.module('ngdemo', ['ngdemo.filters', 'ngdemo.services', 'ngdemo.directives', 'ngdemo.controllers']).
        config(['$routeProvider', function ($routeProvider) {
                /*$routeProvider.when('/user-list', {templateUrl: 'partials/user-list.html', controller: 'UserListCtrl'});
                 $routeProvider.when('/user-detail/:id', {templateUrl: 'partials/user-detail.html', controller: 'UserDetailCtrl'});
                 $routeProvider.when('/user-creation', {templateUrl: 'partials/user-creation.html', controller: 'UserCreationCtrl'});*/
                $routeProvider.when('/experto/acoso-escolar-list', {templateUrl: 'partials/experto/listarAcosoEscolar.jsp', controller: 'AcosoEscolarListCtrl'});
                $routeProvider.when('/experto/acoso-escolar-explication/:id', {templateUrl: 'partials/experto/explicacionAcosoEscolarjsp', controller: 'AcosoEscolarExplicationCtrl'});
                $routeProvider.otherwise({redirectTo: '/dummy'});
            }]);
