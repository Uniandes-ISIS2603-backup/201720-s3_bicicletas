(function (ng) {
	    var mod = ng.module("bicicletaModule");
	    mod.constant("bicicletasContext", "api/bicicletas");
	    mod.controller('bicicletaNewCtrl', ['$scope', '$http', 'bicicletasContext', '$state', 'direccionesContext', '$rootScope',
	        function ($scope, $http, bicicletasContext, $state, $rootScope) {
	            $rootScope.edit = false;
	            $scope.createBicicleta = function () {
	                $http.post(bicicletasContext, {
	                    marca: $scope.bicicletaMarca,
	                    modelo: $scope.bicicletaModelo
	                }).then(function (response) {
	                  $http.post('api/estaciones' + '/' + $state.params.id + '/bicicletas/añadir',{
                              id: response.data.id
                          }).then(function (response)
                    {
                         $state.go('estacionList', {id: $state.params.id}, {reload: true});
                    });
	                    
	                });
	            };
	        }
	    ]);
	}
	)(angular);

