(function (ng) {
var mod = ng.module("reservaModule");
    mod.constant("reservasContext", "reservas");
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('reservasDetailCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'reservasContext',
        function ($scope, $http, usuariosContext, $state, reservasContext) {
            
             $http.get(usuariosContext + '/' + $state.params.idUsuario+'/'+reservasContext+'/'+$state.params.idReserva ).then(function (response) {
             $scope.currentReserva = response.data;
             });
        }
    ]);
    }
)(angular);

