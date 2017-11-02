(function (ng) {
var mod = ng.module("direccionModule");
    mod.constant("direccionesContext", "direcciones");
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('direccionesCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'direccionesContext',
        function ($scope, $http, usuariosContext, $state, direccionesContext) {
            $http.get(usuariosContext + '/' + $state.params.documentoUsuario + '/' + direccionesContext).then(function (response) {
                $scope.direccionesRecords = response.data;
            });
        }
    ]);
    }
)(angular);