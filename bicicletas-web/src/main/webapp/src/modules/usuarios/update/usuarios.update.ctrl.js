(
function (ng) {
            var mod = ng.module("usuarioModule");
            mod.constant("usuariosContext", "api/usuarios");
            mod.constant("direccionesContext", "api/direcciones");
            mod.controller('usuarioUpdateCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'direccionesContext', '$rootScope', '$filter',
                function ($scope, $http, usuariosContext, $state, direccionesContext, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var documentoUsuario = $state.params.documentoUsuario;


                    //Consulto el usuario a editar.
                    $http.get("api/usuarios" + '/' + documentoUsuario).then(function (response) {
                        var usuario = response.data;
                        $scope.usuarioNombre = usuario.nombre;
                        $scope.usuarioDocumentoUsuario = usuario.documentoUsuario;
                        $scope.usuarioTipoId = usuario.tipoId;
                        $scope.usuarioTarjetaCredito = usuario.tarjetaCredito;
                        $scope.usuarioNumeroCsv = usuario.numeroCsv;
                        $scope.usuarioContraseniaPSE = usuario.contraseniaPSE;
                    });


                    $scope.createUsuario = function () {
                        /*Se llama a la función newDirecciones() para buscar cada uno de los ids de los direcciones
                         en el array que tiene todos los direcciones y así saber como queda la lista final de los direcciones asociados al autor.
                         */
                        $http.put("api/usuarios" + "/" + documentoUsuario, {
                            usuarioNombre: $scope.usuarioNombre,
                            usuarioDocumentoUsuario: $scope.usuarioDocumentoUsuario,
                            usuarioTipoId: $scope.usuarioTipoId,
                            usuarioTarjetaCredito: $scope.usuarioTarjetaCredito,
                            usuarioNumeroCsv: $scope.usuarioNumeroCsv,
                            usuarioContraseniaPSE: $scope.usuarioContraseniaPSE
                        }).then(function (response) {
                            
                            //Usuario created successfully
                            $state.go('usuariosList', {documentoUsuario: response.data.documentoUsuario}, {reload: true});
                        });
                    };

                    
                }
            ]);
        }
)(angular);