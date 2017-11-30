(function (ng) {
var mod = ng.module("bicicletaModule");
    mod.constant("bicicletasEstacionContext", "bicicletas");
    mod.constant("biciEstContext", "api/estaciones");
    mod.controller('entregarCtrl', ['$scope', '$http', 'bicicletasEstacionContext', '$state', 'biciEstContext',
        function ($scope, $http, bicicletasEstacionContext, $state, biciEstContext) {
            $http.get('api/estaciones').then(function (response) {
                $scope.estacionesDisponiblesRecords = response.data;
            }),$scope.moverBicicleta = function () {
	                $http.put(biciEstContext + '/' + $scope.estacionId + '/' + bicicletasEstacionContext, {
	                    id: $state.params.id
	                }).then(function () {
	                    //Usuario created successfully
	                    $state.go(history.back(), {reload: true});
	                });
	            };
        }
    ]);
    }
)(angular);


