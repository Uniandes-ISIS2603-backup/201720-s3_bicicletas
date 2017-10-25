(function (ng) {
    var mod = ng.module("reservasModule");
    mod.constant("reservasContext", "reservas");
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('reservasCtrl', ['$scope', '$http', 'reservasContext', '$state',
         function ($scope, $http, usuariosContext, $state, reservasContext) {
            $http.get(usuariosContext + '/' + $state.params.documentoUsuario + '/' + reservasContext).then(function (response){
                $scope.reservasRecords = response.data;
            });
//            if ($state.params.reservaId !== undefined) {
//                $http.get(reservasContext + '/' + $state.params.reservaId).then(function (response) {
//                    $scope.currentReservas = response.data;
//                });
//            }
        }
    ]);
}
)(angular);
