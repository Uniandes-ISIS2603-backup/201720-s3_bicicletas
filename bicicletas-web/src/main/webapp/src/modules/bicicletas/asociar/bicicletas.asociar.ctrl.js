(function (ng) {
var mod = ng.module("bicicletaModule");
    mod.constant("bicicletasReservaContext", "bicicletas");
    mod.constant("reservasBicisContext", "api/reservas");
    mod.controller('asoBiciCtrl', ['$scope', '$http', 'reservasBicisContext', '$state', 'bicicletasReservaContext',
        function ($scope, $http, reservasBicisContext, $state, bicicletasReservaContext) {
            $http.get('api/estaciones/'+$state.params.idEstacion+'/bicicletas/disponibles').then(function (response) {
                $scope.estacionBicicletasDisponiblesRecords = response.data;
            }),
            $scope.asociarBicicleta = function () {
	                $http.put(reservasBicisContext + '/' + $state.params.idReserva + '/' + bicicletasReservaContext, {
	                    id: $scope.bicicletaId
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go('bicicletasReservaList', {idReserva: response.data.idReserva}, {reload: true});
	                });
	            };
        }
    ]);
    }
)(angular);

