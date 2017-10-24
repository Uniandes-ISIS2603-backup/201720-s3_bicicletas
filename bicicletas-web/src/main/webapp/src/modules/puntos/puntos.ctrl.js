(function (ng) {
var mod = ng.module("puntoModule");
    mod.constant("puntosContext", "puntos");
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('puntosCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'puntosContext',
        function ($scope, $http, usuariosContext, $state, puntosContext) {
            $http.get(usuariosContext + '/' + $state.params.documentoUsuario + '/' + puntosContext).then(function (response) {
                $scope.puntosRecords = response.data;
            });
        }
    ]);
    }
)(angular);