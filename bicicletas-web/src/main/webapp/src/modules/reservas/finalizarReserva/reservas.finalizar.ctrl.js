(function (ng) {
    var mod = ng.module("reservaModule");
    mod.constant("reservasContext", "reservas");
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('finalizarReservaCtrl', ['$scope', '$http', 'usuariosContext', '$state', '$rootScope', 'reservasContext',
        function ($scope, $http, reservasContext, $state, $rootScope) {
            $rootScope.edit = false;
            $http.get('api/estaciones').then(function (response) {
                $scope.estacionesDisponiblesRecords = response.data;
            }),
                    $scope.finalizarReserva = function () {
                        $http.put('api/'+'reservas' + '/' + $state.params.idReserva + '/bicicletas/' + 'entregar', {
                            id:$scope.estacionId
                        }).then(function() {
                            //Reserva created successfully
                            $state.go('reservaDetail', {idReserva: $state.params.idReserva}, {reload: true});
                        });
                    };
        }
    ]);
}
)(angular);


