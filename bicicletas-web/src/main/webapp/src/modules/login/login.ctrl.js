(function (ng) {
    var mod = ng.module("loginModule");
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('loginCtrl', ['$scope', '$http', '$state', '$rootScope', 'usuariosContext',
        function ($scope, $http, $state, $rootScope, usuariosContext) {

            $scope.user = {};
            $scope.usuarioBuscado = {};
            $scope.usuarioBuscado.password = null;
            
            $scope.data = {};
            $scope.data.username = null;
            $scope.data.password = null;

            $scope.autenticar = function (id) {
                
                var flag = false;

                $http.get('data/administradores.json').then(function (response) {
                    $scope.administradores = response.data;
                });


                $http.get(usuariosContext + '/' + id).then(function (response) {
                    $scope.usuarioBuscado = response.data;
                    
                    if ($scope.usuarioBuscado !== undefined && $scope.usuarioBuscado.password === $scope.data.password) {
                    var esAdministrador = false;
                    for (var item in $scope.administradores) {
                        if ($scope.administradores[item].id == id) {
                            esAdministrador = true;
                            break;
                        }
                    }
                    if ((esAdministrador && $scope.data.role === 'administrador') || (!esAdministrador && $scope.data.role === 'cliente')) {
                        flag = true;
                        $scope.user = $scope.usuarioBuscado;
                        $state.go('usuarioDetail', {documentoUsuario: id}, {reload: true});
                    }
                }

                if (!flag) {
                    $rootScope.alerts.push({type: "danger", msg: "El usuario o la contraseña son incorrectos."});
                } else {
                    sessionStorage.token = $scope.user.token;
                    sessionStorage.setItem("username", $scope.user.documentoUsuario);
                    sessionStorage.setItem("name", $scope.user.nombre);
                    sessionStorage.setItem("rol", $scope.data.role);

                    if ($scope.data.role === 'administrador') {
                        sessionStorage.setItem("administrador", $scope.data.rol);
                    }
                    $rootScope.currentUser = $scope.user.name;
                }
                   
                });
                
                
               
                
            };
        }
    ]);
}
)(window.angular);
