(function (ng) {
    var mod = ng.module("usuarioModule");
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('usuariosCtrl', ['$scope', '$http', 'usuariosContext',
        function ($scope, $http, usuariosContext) {
            $http.get('data/usuarios.json').then(function (response) {
                $scope.usuariosRecords = response.data;
            });
        }
    ]);
}
)(windows.angular);