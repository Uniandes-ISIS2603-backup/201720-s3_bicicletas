(function (ng) {
	    var mod = ng.module("calificacionModule");
	    mod.constant("calificacionesContext", "calificaciones");
            mod.constant("usuariosContext", "api/usuarios");
            mod.constant("reservasContext", "reservas");
	    mod.controller('calificacionUpdateCtrl', ['$scope', '$http', 'usuariosContext', 'reservasContext', '$state', 'calificacionesContext', '$rootScope',
	        function ($scope, $http, usuariosContext, reservasContext, $state, calificacionesContext, $rootScope) {
	            $rootScope.edit = true;
                    $scope.cali = $state.params.cali;
	            $scope.createCalificacion = function () {
                        $scope.cali = $state.params.cali;
	                $http.put(usuariosContext + '/' + $state.params.idUsuario + '/'+ reservasContext + '/' + $state.params.idReserva + '/' + calificacionesContext + '/' + $state.params.cali, {
	                    nota: $scope.nota,
	                    descripcion: $scope.descripcion	                
	                }).then(function (response) {
	                    //Calificacion updated successfully
                            
	                    $state.go('calificacionDetail', {idUsuario: $state.params.idUsuario, idReserva: response.data.idReserva, cali: $state.params.cali}, {reload: true});
	                });
	            };
	        }
	    ]);
	}
	)(angular);