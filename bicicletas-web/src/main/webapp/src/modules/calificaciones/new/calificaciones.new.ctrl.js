(function (ng) {
	    var mod = ng.module("calificacionModule");
	    mod.constant("calificacionesContext", "calificaciones");
            mod.constant("usuariosContext", "api/usuarios");
            mod.constant("reservasContext", "reservas");
	    mod.controller('calificacionNewCtrl', ['$scope', '$http', 'usuariosContext', 'reservasContext', '$state', 'calificacionesContext', '$rootScope',
	        function ($scope, $http, usuariosContext, reservasContext, $state, calificacionesContext, $rootScope) {
	            $rootScope.edit = false;
                    var usu = $state.params.idUsuario;
                    var califi = $state.params.cali;
	            $scope.createCalificacion = function () {
	                $http.post(usuariosContext + '/' + usu + '/'+ reservasContext + '/' + $state.params.idReserva + '/' + calificacionesContext + '/' + califi, {
	                    nota: $scope.nota,
	                    descripcion: $scope.descripcion	                
	                }).then(function (response) {
	                    //Calificacion created successfully
                            
	                    $state.go('calificacionDetail', {idUsuario: usu, idReserva: response.data.idReserva, cali: califi}, {reload: true});
	                });
	            };
	        }
	    ]);
	}
	)(angular);