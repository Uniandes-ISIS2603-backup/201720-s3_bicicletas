(function (ng) {
	    var mod = ng.module("reservaModule");
            mod.constant("reservasContext", "reservas");
	    mod.constant("usuariosContext", "api/usuarios");
	    mod.controller('reservaNewCtrl', ['$scope', '$http', 'usuariosContext', '$state','$rootScope','reservasContext',
	        function ($scope, $http, usuariosContext, $state,$rootScope) {
	            $rootScope.edit = false;
	            $scope.createReserva = function () {
	                $http.post(usuariosContext+'/'+$state.params.idUsuario+'/'+'reservas',{
	                    idUsuario : $scope.documentoUsuario,
	                    fechaInicio : $scope.fechaSalida+':00',
	                    fechaEntrega: $scope.fechaLlegada+':00',
                            estacionSalida: {
                                id : $scope.estacionId
                            }
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go('reservaDetail', {idUsuario: $state.params.idUsuario, idReserva: response.data.idReserva}, {reload: true});
	                });
	            };
	        }
	    ]);
	}
)(angular);


