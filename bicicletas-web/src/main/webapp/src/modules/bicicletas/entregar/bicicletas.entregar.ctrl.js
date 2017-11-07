(function (ng) {
var mod = ng.module("bicicletaModule");
    mod.constant("bicicletasEstacionContext", "bicicletas");
    mod.constant("biciEstContext", "api/estaciones");
    mod.controller('entregarCtrl', ['$scope', '$http', 'bicicletasEstacionContext', '$state', 'biciEstContext',
        function ($scope, $http, bicicletasEstacionContext, $state, biciEstContext) {
            $scope.agregarBicicletaEstacion = function () {
	                $http.put(biciEstContext + '/' + $state.params.id + '/' + bicicletasEstacionContext, {
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


