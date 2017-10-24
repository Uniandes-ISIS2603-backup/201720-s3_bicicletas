(
function (ng) {
            var mod = ng.module("usuarioModule");
            mod.constant("usuariosContext", "api/usuarios");
            mod.constant("direccionesContext", "api/direcciones");
            mod.controller('usuarioUpdateCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'direccionesContext', '$rootScope', '$filter',
                function ($scope, $http, usuariosContext, $state, direccionesContext, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idUsuario = $state.params.usuarioDocumentoUsuario;

                    // Este arreglo guardara los ids de los direcciones asociados y por asociar al usuario.
                    var idsDireccion = [];

                    // Este arreglo mostrará los direcciones una vez esten filtrados visualmente por lo que el usuario ya tiene asociado.
                    $scope.allDireccionesShow = [];

                    //Consulto el usuario a editar.
                    $http.get(usuariosContext + '/' + idUsuario).then(function (response) {
                        var usuario = response.data;
                        $scope.usuarioNombre = usuario.nombre;
                        $scope.usuarioDocumentoUsuario = usuario.documentoUsuario;
                        $scope.usuarioTipoId = usuario.tipoId;
                        $scope.usuarioTarjetaCredito = usuario.tarjetaCredito;
                        $scope.usuarioNumeroCsv = usuario.numeroCsv;
                        $scope.usuarioContraseniaPSE = usuario.contraseniaPSE;
                        $scope.allDireccionesUsuario = usuario.direcciones;
                        $scope.mergeDirecciones($scope.allDireccionesUsuario);
                    });

                    /*
                     * Esta función añade los ids de los direcciones que ya tiene el usuario asociado.
                     * @param {type} direcciones: Son los direcciones que ya tiene asociado el usuario.
                     * @returns {undefined}
                     */
                    $scope.mergeDirecciones = function (direcciones) {
                        for (var item in direcciones) {
                            idsDireccion.push("" + direcciones[item].id);
                        }
                        $scope.getDirecciones(direcciones);
                    };

                    /*
                     * Esta función recibe como param los direcciones que tiene el usuario para hacer un filtro visual con todos los direcciones que existen.
                     * @param {type} direcciones
                     * @returns {undefined}
                     */
                    $scope.getDirecciones = function (direcciones) {
                        $http.get(direccionesContext).then(function (response) {
                            $scope.Alldirecciones = response.data;
                            $scope.direccionesUsuario = direcciones;

                            var filteredDirecciones = $scope.Alldirecciones.filter(function (Alldirecciones) {
                                return $scope.direccionesUsuario.filter(function (direccionesUsuario) {
                                    return direccionesUsuario.id == Alldirecciones.id;
                                }).length == 0
                            });

                            $scope.allDireccionesShow = filteredDirecciones;

                        });
                    };


                    //funciones para el drag and drop de HTML5 nativo
                    $scope.allowDrop = function (ev) {
                        ev.preventDefault();
                    };

                    $scope.drag = function (ev) {
                        ev.dataTransfer.setData("text", ev.target.id);
                    };

                    $scope.dropAdd = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        //Cuando un direccion se añade al autor, se almacena su id en el array idsDireccion
                        idsDireccion.push("" + data);
                    };

                    $scope.dropDelete = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                        //Para remover el direccion que no se va asociar, por eso se usa el splice que quita el id del direccion en el array idsDireccion
                        var index = idsDireccion.indexOf(data);
                        if (index > -1) {
                            idsDireccion.splice(index, 1);
                        }
                    };

                    $scope.createUsuario = function () {
                        /*Se llama a la función newDirecciones() para buscar cada uno de los ids de los direcciones
                         en el array que tiene todos los direcciones y así saber como queda la lista final de los direcciones asociados al autor.
                         */
                        $scope.newDirecciones();
                        $http.put(usuariosContext + "/" + idUsuario, {
                            usuarioNombre: $scope.usuarioNombre,
                            usuarioDocumentoUsuario: $scope.usuarioDocumentoUsuario,
                            usuarioTipoId: $scope.usuarioTipoId,
                            usuarioTarjetaCredito: $scope.usuarioTarjetaCredito,
                            usuarioNumeroCsv: $scope.usuarioNumeroCsv,
                            usuarioContraseniaPSE: $scope.usuarioContraseniaPSE
                        }).then(function (response) {
                            if (idsDireccion.length >= 0) {
                                $http.put(usuariosContext + "/" + response.data.id + "/direcciones", $scope.allDireccionesUsuario).then(function (response) {
                                });
                            }
                            //Usuario created successfully
                            $state.go('usuariosList', {usuarioId: response.data.id}, {reload: true});
                        });
                    };

                    $scope.newDirecciones = function () {
                        $scope.allDireccionesUsuario = [];
                        for (var ite in idsDireccion) {
                            for (var all in $scope.Alldirecciones) {
                                if ($scope.Alldirecciones[all].id === parseInt(idsDireccion[ite])) {
                                    $scope.allDireccionesUsuario.push($scope.Alldirecciones[all]);
                                }
                            }
                        }
                    };
                }
            ]);
        }
)(angular);