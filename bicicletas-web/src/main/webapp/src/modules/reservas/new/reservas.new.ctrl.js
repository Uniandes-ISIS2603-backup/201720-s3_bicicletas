(function (ng) {
	    var mod = ng.module("reservaModule");
            mod.constant("reservasContext", "reservas");
	    mod.constant("usuariosContext", "api/usuarios");
	    mod.controller('reservaNewCtrl', ['$scope', '$http', 'usuariosContext', '$state','$rootScope','reservasContext',
	        function ($scope, $http, usuariosContext, $state,$rootScope) {
	            $rootScope.edit = false;
	            $scope.createUsuario = function () {
	                $http.post(usuariosContext+'/'+$scope.documentoUsuario+'/'+'reservas',{
	                    idUsuario : $scope.documentoUsuario,
	                    fechaInicio : $scope.fechaSalida+':00',
	                    fechaEntrega: $scope.fechaLlegada+':00',
                            estacionSalida: {
                                id : $scope.estacionId
                            }
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go("usuarioDetail({documentoUsuario:reserva.usuarioReserva.documentoUsuario})", {idUsuario: response.data.idUsuario}, {reload: true});
	                });
	            };
	        }
	    ]);
	}
)(angular);


