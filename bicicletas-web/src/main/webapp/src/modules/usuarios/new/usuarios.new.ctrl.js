(function (ng) {
	    var mod = ng.module("usuarioModule");
	    mod.constant("usuariosContext", "api/usuarios");
	    mod.controller('usuarioNewCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'direccionsContext', '$rootScope',
	        function ($scope, $http, usuariosContext, $state, direccionsContext, $rootScope) {
	            $rootScope.edit = false;
	            $scope.createUsuario = function () {
	                $http.post(usuariosContext, {
	                    nombre: $scope.usuarioNombre,
	                    tipoId: $scope.usuarioTipoId,
	                    documentoUsuario: $scope.usuarioDocumentoUsuario,
                            fechaNacimiento: $scope.usuarioFechaNacimiento,
                            tarjetaCredito: $scope.usuarioTarjetaCredito,
                            numeroCsv: $scope.usuarioNumeroCsv,
	                    contraseniaPSE: $scope.usuarioContraseniaPSE
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go('usuariosList', {usuarioId: response.data.id}, {reload: true});
	                });
	            };
	        }
	    ]);
	}
	)(angular);