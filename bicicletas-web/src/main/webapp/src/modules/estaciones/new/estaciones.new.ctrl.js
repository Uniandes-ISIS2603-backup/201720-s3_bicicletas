(function (ng) {
	    var mod = ng.module("estacionModule");
	    mod.constant("estacionesContext", "api/estaciones");
	    mod.controller('estacionNewCtrl', ['$scope', '$http', 'estacionesContext', '$state', 'direccionesContext', '$rootScope',
	        function ($scope, $http, estacionesContext, $state, $rootScope) {
	            $rootScope.edit = false;
	            $scope.createEstacion = function () {
	                $http.post(estacionesContext, {
	                    nombre: $scope.estacionNombre,
                            direccion: $scope.estacionDireccion,
                            ciudad: $scope.estacionCiudad
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go('estacionesList', {nombre: response.data.nombre}, {reload: true});
	                });
	            };
	        }
	    ]);
	}
	)(angular);