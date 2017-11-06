(function (ng) {
var mod = ng.module("accesorioModule");
    mod.constant("accesoriosReservaContext", "accesorios");
    mod.constant("reservasAccesorioContext", "api/reservas");
    mod.controller('accesoriosReservaCtrl', ['$scope', '$http', 'reservasAccesorioContext', '$state', 'accesoriosReservaContext',
        function ($scope, $http, reservasAccesorioContext, $state, accesoriosReservaContext) {
            $http.get(reservasAccesorioContext + '/' + $state.params.idReserva + '/' + accesoriosReservaContext).then(function (response) {
                $scope.accesorioRecords = response.data;
            });
        }
    ]);
    }
)(angular);