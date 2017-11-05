(function (ng) {
var mod = ng.module("bicicletaModule");
    mod.constant("bicicletasReservaContext", "bicicletas");
    mod.constant("reservasBicisContext", "api/reservas");
    mod.controller('asoBiciCtrl', ['$scope', '$http', 'reservasBicisContext', '$state', 'bicicletasReservaContext',
        function ($scope, $http, reservasBicisContext, $state, bicicletasReservaContext) {
            $scope.asociarBicicleta = function () {
	                $http.put(reservasBicisContext + '/' + $state.params.idReserva + '/' + bicicletasReservaContext, {
	                    id: $scope.bicicletaId
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go('reservaDetail', {nombre: response.data.nombre}, {reload: true});
	                });
	            };
        }
    ]);
    }
)(angular);

