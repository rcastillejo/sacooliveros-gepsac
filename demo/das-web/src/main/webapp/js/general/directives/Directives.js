angular.module('app.directives', [])

.directive('resizeHeight', ['$state', function($state) {
    return {
        link: function(scope, element) {
            scope.resize = function() {
                var minHeight = 0;
                if ($state.current.name === 'principal') {
                    element.parent().parent().parent().find('header').parent().removeClass('oculto');
                    element.parent().parent().parent().find('footer').parent().removeClass('oculto');
                    element.parent().parent().find('#menu').parent().removeClass('oculto');
                    // renderizar el tamanio del contenido central
                    element.parent().parent().find('#cuerpo').parent().removeClass('col-md-12');
                    element.parent().parent().find('#cuerpo').parent().addClass('col-md-10');
                } else if ($state.current.name === 'login') {
                    element.parent().parent().parent().find('header').parent().addClass('oculto');
                    element.parent().parent().parent().find('footer').parent().addClass('oculto');
                    element.parent().parent().find('#menu').parent().addClass('oculto');
                    // renderizar el tamanio del contenido central
                    element.parent().parent().find('#cuerpo').parent().addClass('col-md-12');
                    element.parent().parent().find('#cuerpo').parent().removeClass('col-md-10');
                }
            };
            scope.resize();
            $(window).resize(function() {
                scope.resize();
            });
        }
    };

}])
.directive('myEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            if(event.which === 13) {
                scope.$apply(function (){
                    scope.$eval(attrs.myEnter);
                });

                event.preventDefault();
            }
        });
    };
})
.directive('numbersOnly', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attrs, ctrl) {
            var validateNumber = function (inputValue) {
                var maxLength = 6;
                if (attrs.max) {
                    maxLength = attrs.max;
                }
                if (inputValue === undefined) {
                    return '';
                }
                var transformedInput = inputValue.replace(/[^0-9]/g, '');
                if (transformedInput !== inputValue) {
                    ctrl.$setViewValue(transformedInput);
                    ctrl.$render();
                }
                if (transformedInput.length > maxLength) {
                    transformedInput = transformedInput.substring(0, maxLength);
                    ctrl.$setViewValue(transformedInput);
                    ctrl.$render();
                }
                var isNotEmpty = (transformedInput.length === 0) ? true : false;
                ctrl.$setValidity('notEmpty', isNotEmpty);
                return transformedInput;
            };

            ctrl.$parsers.unshift(validateNumber);
            ctrl.$parsers.push(validateNumber);
            attrs.$observe('notEmpty', function () {
                validateNumber(ctrl.$viewValue);
            });
        }
    };
})
.directive('myMaxlength', function() {
  return {
    require: 'ngModel',
    link: function (scope, element, attrs, ngModelCtrl) {
      var maxlength = Number(attrs.myMaxlength);
      function fromUser(text) {
          if (text.length > maxlength) {
            var transformedInput = text.substring(0, maxlength);
            ngModelCtrl.$setViewValue(transformedInput);
            ngModelCtrl.$render();
            return transformedInput;
          } 
          return text;
      }
      ngModelCtrl.$parsers.push(fromUser);
    }
  }; 
})
.directive('tabControl', function() {
    return {
        restrict: 'E',
        templateUrl: 'tabControlTemplate',
        scope: {
            id: '@id',
            klass: '@class',
        },
        transclude: true,
        controller: ['$scope', function($scope) {
            $scope.tabs = []

            this.addTab = function(tab){
                $scope.tabs.push(tab);
            }

            $scope.selectTab = function(tab){
                for(var i=0; i<$scope.tabs.length; i++){
                    if(tab.name != $scope.tabs[i].name){
                        $scope.tabs[i].selected = false;
                    }
                    else {
                        $scope.tabs[i].selected = true;
                    }
                }
            }
        }]
    };
})
.directive('tab', function() {
    return {
        restrict: 'E',
        templateUrl: 'tabTemplate',
        transclude: true,
        replace: true,
        scope: {
            id: '@id',
            name: '@name',
        },
        require: '^tabControl',
        link: function(scope, element, attrs, ctrl) {
            scope.selected = attrs.selected == "" || attrs.selected == true
            ctrl.addTab(scope);
        }
    };
})
.directive('allowPattern', [allowPatternDirective]);                          
    function allowPatternDirective() {
    return {
        restrict: "A",
        compile: function(tElement, tAttrs) {
            return function(scope, element, attrs) {
        // I handle key events
                element.bind("keypress", function(event) {
                    var keyCode = event.which || event.keyCode; // I safely get the keyCode pressed from the event.
                    var keyCodeChar = String.fromCharCode(keyCode); // I determine the char from the keyCode.
          
          // If the keyCode char does not match the allowed Regex Pattern, then don't allow the input into the field.
                    if (!keyCodeChar.match(new RegExp(attrs.allowPattern, "i"))) {
            event.preventDefault();
                        return false;
                    }
          
                });
            };
            //EXAMPLE: allow-pattern="[a-z]", allow-pattern="\d", allow-pattern="(\d|[a-z])", allow-pattern="\W", allow-pattern="[ABCDEFG]"
        }
    };
};