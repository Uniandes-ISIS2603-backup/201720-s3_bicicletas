(function (ng) {
var mod = ng.module("reservaModule");
    mod.constant("reservasEstacionContext", "reservas");
    mod.constant("estacionesContext", "api/estaciones");
    mod.controller('reservasEstacionCtrl', ['$scope', '$http', 'estacionesContext', '$state', 'reservasEstacionContext', '$rootScope',
        function ($scope, $http, estacionesContext, $state, reservasEstacionContext, $rootScope) {
            $rootScope.mostrar = true;
            $http.get(estacionesContext + '/' + $state.params.id + '/' + reservasEstacionContext).then(function (response) {
                $scope.reservasRecords = response.data;
            });
        }
    ]);
    }
)(angular);