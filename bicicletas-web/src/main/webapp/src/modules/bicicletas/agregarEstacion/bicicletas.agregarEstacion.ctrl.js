(function (ng) {
var mod = ng.module("bicicletaModule");
    mod.constant("bicicletasEstacionContext", "bicicletas");
    mod.constant("bicicletasEstacionContext", "api/estaciones");
    mod.controller('biciEstacionCtrl', ['$scope', '$http', 'bicicletasEstacionContext', '$state', 'bicicletasEstacionContext',
        function ($scope, $http, bicicletasEstacionContext, $state, bicicletasEstacionContext) {
            $scope.agregarBicicletaEstacion = function () {
	                $http.put(bicicletasEstacionContext + '/' + $state.params.idEstacion + '/' + bicicletasEstacionContext, {
	                    id: $scope.bicicletaId
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go('estacionDetail', {nombre: response.data.nombre}, {reload: true});
	                });
	            };
        }
    ]);
    }
)(angular);


