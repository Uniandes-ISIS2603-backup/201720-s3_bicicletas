(function (ng) {
var mod = ng.module("accesorioModule");
    mod.constant("accesoriosReservaContext", "accesorios");
    mod.constant("reservasAccesoriosContext", "api/reservas");
    mod.controller('quitarAccesorioReservaCtrl', ['$scope', '$http', 'reservasAccesoriosContext', '$state', 'accesoriosReservaContext',
        function ($scope, $http, reservasAccesoriosContext, $state, accesoriosReservaContext) {
            $scope.quitarAccesorio = function () {
	                $http.put(reservasAccesoriosContext + '/' + $state.params.idReserva + '/' + accesoriosReservaContext + '/quitar', {
	                    id: $state.params.idAccesorio
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go('reservaDetail', {nombre: response.data.nombre}, {reload: true});
	                });
	            };
        }
    ]);
    }
)(angular);

