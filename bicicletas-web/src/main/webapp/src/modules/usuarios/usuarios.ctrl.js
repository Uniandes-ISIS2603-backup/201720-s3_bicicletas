(function (ng) {
    var mod = ng.module("usuarioModule");
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('usuarioCtrl', ['$scope', '$http', 'usuariosContext', '$state',
        function ($scope, $http, usuariosContext, $state) {
            $http.get(usuariosContext).then(function (response) {
                $scope.usuariosRecords = response.data;
            });

            if ($state.params.documentoUsuario !== undefined) {
                $http.get(usuariosContext + '/' + $state.params.documentoUsuario).then(function (response) {
                    $scope.currentUsuario = response.data;
                });
            }
        }
    ]);
    window.setTimeout(function() {
    $(".alert").fadeTo(500, 0).slideUp(500, function(){
        $(this).remove(); 
    });
    }, 1500);
}
)(angular);