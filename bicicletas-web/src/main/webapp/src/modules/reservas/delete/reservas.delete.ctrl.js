(function (ng) {
    var mod = ng.module("reservaModule");
    mod.constant("usuariosContext", "api/usuarios");
    mod.constant("reservasContext", "reservas");
    mod.controller('reservaDeleteCtrl', ['$scope', '$http', 'usuariosContext', '$state',
        function ($scope, $http, usuariosContext, $state) {
            var idReserva = $state.params.idReserva;
            $scope.deleteReserva = function () {
                $http.delete("api/reservas/"+ idReserva, {}).then(function (response) {
                    $state.go('usuariosList', {idResereva: response.data.idReserva}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);


