(function (ng) {
    var mod = ng.module("loginModule");
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('loginCtrl', ['$scope', '$http', '$state', '$rootScope', 'usuariosContext',
        function ($scope, $http, $state, $rootScope, usuariosContext) {

            $scope.user = {};
            $scope.data = {};

            $scope.autenticar = function (id) {
                var flag = false;
                
               $http.get('data/administradores.json').then(function (response){
                   $scope.administradores = response.data;
               });


                $http.get(usuariosContext + '/' + id).then(function (response) {
                    $scope.usuarioBuscado = response.data;
                });

                if ($scope.usuarioBuscado !== undefined && $scope.usuarioBuscado.password === $scope.data.password) {
                    var esAdministrador = false;
                    for(var item in $scope.administradores){
                    if($scope.administradores[item].id == id){
                        esAdministrador = true;
                        break;
                    }
                }
                console.log($scope.data.role);
                    if((esAdministrador && $scope.data.role === 'administrador') || (!esAdministrador && $scope.data.role === 'cliente')){
                    flag = true;
                    $scope.user = $scope.usuarioBuscado;
                    $state.go('usuarioDetail', {documentoUsuario: id}, {reload: true});
                }
            }

                if (!flag) {
                    $rootScope.alerts.push({type: "danger", msg: "El usuario o la contraseña son incorrectos."});
                } else {
                    sessionStorage.token = $scope.user.token;
                    sessionStorage.setItem("username", $scope.user.user);
                    sessionStorage.setItem("name", $scope.user.name);
                    sessionStorage.setItem("rol", $scope.user.rol);
                    
                    if($scope.data.role === 'administrador'){
                    sessionStorage.setItem("administrador",  $scope.user.rol);
                }
                    $rootScope.currentUser = $scope.user.name;
                }
            };
        }
    ]);
}
)(window.angular);
