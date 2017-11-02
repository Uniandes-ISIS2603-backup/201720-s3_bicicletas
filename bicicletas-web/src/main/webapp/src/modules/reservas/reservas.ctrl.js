(function (ng) {
var mod = ng.module("reservaModule");
    mod.constant("reservasContext", "reservas");
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('reservasCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'reservasContext',
        function ($scope, $http, usuariosContext, $state, reservasContext) {
            $http.get(usuariosContext + '/' +  $state.params.documentoUsuario  + '/' + reservasContext).then(function (response) {
            $scope.reservasRecords = response.data;
            });
        }
    ]);
    }
)(angular);