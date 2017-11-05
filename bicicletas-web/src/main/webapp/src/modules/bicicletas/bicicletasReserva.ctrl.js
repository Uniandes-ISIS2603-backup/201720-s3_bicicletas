(function (ng) {
var mod = ng.module("bicicletaModule");
    mod.constant("bicicletasReservaContext", "bicicletas");
    mod.constant("reservasBiciContext", "api/reservas");
    mod.controller('bicicletasReservaCtrl', ['$scope', '$http', 'reservasBiciContext', '$state', 'bicicletasReservaContext',
        function ($scope, $http, reservasBiciContext, $state, bicicletasReservaContext) {
            $http.get(reservasBiciContext + '/' + $state.params.idReserva + '/' + bicicletasReservaContext).then(function (response) {
                $scope.bicicletaRecords = response.data;
            });
        }
    ]);
    }
)(angular);