(function (ng) {
var mod = ng.module("reservaModule");
    mod.constant("reservasContext", "reservas");
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('reservasCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'reservasContext',
        function ($scope, $http, usuariosContext, $state, reservasContext) {
            $http.get(usuariosContext + '/' + $state.params.documentoUsuario + '/' + reservasContext).then(function (response) {
                $scope.reservasRecords = response.data;
            });
            
             if (  $state.params.idReserva !== null && $state.params.idReserva !== undefined && $state.params.documentoUsuario !== undefined) {
                $http.get(usuariosContext + '/' + $state.params.documentoUsuario+'/'+reservasContext+'/'+$state.params.idReserva ).then(function (response) {
                    $scope.currentReserva = response.data;
                });
            }
            
        }
    ]);
    }
)(angular);