(function (ng) {
var mod = ng.module("calificacionModule");
    mod.constant("calificacionesContext", "calificaciones");
    mod.constant("usuariosContext", "api/usuarios");
    mod.constant("reservasContext", "reservas");
    
    mod.controller('calificacionesDetailCtrl', ['$scope', '$http', 'usuariosContext', 'reservasContext', '$state', 'calificacionesContext',
        function ($scope, $http, usuariosContext, reservasContext, $state, calificacionesContext) {
            $scope.cali = $state.params.cali;
            $http.get(usuariosContext + '/' + $state.params.idUsuario + '/'+ reservasContext + '/' + $state.params.idReserva + '/' + calificacionesContext + '/' + $state.params.cali ).then(function (response) {
                $scope.currentCalificacion = response.data;
                $scope.idCali = 1;

            });
        }
    ]);
    }
)(angular);