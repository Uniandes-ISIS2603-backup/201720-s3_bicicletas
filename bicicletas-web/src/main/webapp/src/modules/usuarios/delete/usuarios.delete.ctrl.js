(function (ng) {
    var mod = ng.module("usuarioModule");
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('usuarioDeleteCtrl', ['$scope', '$http', 'usuariosContext', '$state',
        function ($scope, $http, usuariosContext, $state) {
            var documentoUsuario = $state.params.documentoUsuario;
            $scope.deleteUsuario = function () {
                $http.delete(usuariosContext + '/' + documentoUsuario, {}).then(function (response) {
                    $state.go('usuariosList', {documentoUsuario: response.data.documentoUsuario}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);