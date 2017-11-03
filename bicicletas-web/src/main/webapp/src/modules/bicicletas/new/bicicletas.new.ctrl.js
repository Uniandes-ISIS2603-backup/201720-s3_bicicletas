(function (ng) {
	    var mod = ng.module("bicicletaModule");
	    mod.constant("bicicletasContext", "api/bicicletas");
	    mod.controller('bicicletaNewCtrl', ['$scope', '$http', 'bicicletasContext', '$state', 'direccionesContext', '$rootScope',
	        function ($scope, $http, bicicletasContext, $state, direccionesContext, $rootScope) {
	            $rootScope.edit = false;
	            $scope.createBicicleta = function () {
	                $http.post(bicicletasContext, {
	                    marca: $scope.bicicletaMarca,
	                    modelo: $scope.bicicletaModelo
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go('bicicletasList', {id: response.data.id}, {reload: true});
	                });
	            };
	        }
	    ]);
	}
	)(angular);