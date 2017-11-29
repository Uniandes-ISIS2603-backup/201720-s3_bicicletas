(function (ng) {
    var mod = ng.module("reservaModule");
    mod.constant("reservasContext", "reservas");
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('reservaNewCtrl', ['$scope', '$http', 'usuariosContext', '$state', '$rootScope', 'reservasContext',
        function ($scope, $http, usuariosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $http.get('api/estaciones').then(function (response) {
                $scope.estacionesDisponiblesRecords = response.data;
            }),
                    $scope.createReserva = function () {
                        $http.post(usuariosContext + '/' + $state.params.idUsuario + '/' + 'reservas', {
                            idUsuario:$scope.documentoUsuario,
                            fechaInicio:$scope.fechaSalida+$scope.hora ,
                            fechaEntrega:$scope.fechaLlegada+$scope.hora2,
                            estacionSalida: {
                                id: $scope.estacionId
                            }
                        }).then(function (response) {
                            //Reserva created successfully
                            $state.go('reservaDetail', {idUsuario: $state.params.idUsuario, idReserva: response.data.idReserva}, {reload: true});
                        });
                    };
        }
    ]);
}
)(angular);


