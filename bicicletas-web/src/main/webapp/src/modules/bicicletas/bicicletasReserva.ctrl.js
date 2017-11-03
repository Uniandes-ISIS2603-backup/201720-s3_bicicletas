(function (ng) {
var mod = ng.module("bicicletaModule");
    mod.constant("bicicletasReservaContext", "bicicletas");
    mod.constant("reservasBicisContext", "api/reservas");
    mod.controller('bicicletasReservaCtrl', ['$scope', '$http', 'reservasBicisContext','estacionesContext', '$state', 'bicicletasReservaContext',
        function ($scope, $http, reservasBicisContext, $state, bicicletasReservaContext,estacionesContext) {
            $http.get(reservasBicisContext + '/' + $state.params.idReserva + '/' + bicicletasReservaContext).then(function (response) {
                $scope.bicicletaRecords = response.data;
            });
            $http.get(estacionesContext + '/' + $state.params.idEstacionSalida + '/' + bicicletasReservaContext).then(function (response) {
                $scope.bicicletaEstacionRecords = response.data;
            });
            
        }
    ]);
    }
)(angular);


