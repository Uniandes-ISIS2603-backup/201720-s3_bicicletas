(function (ng) {
var mod = ng.module("accesorioModule");
    mod.constant("accesoriosReservaContext", "accesorios");
    mod.constant("reservasAccesoriosContext", "api/reservas");
    mod.controller('asoAccesorioReservaCtrl', ['$scope', '$http', 'reservasAccesoriosContext', '$state', 'accesoriosReservaContext',
        function ($scope, $http, reservasAccesoriosContext, $state, accesoriosReservaContext) {
            $scope.asociarAccesorio = function () {
	                $http.put(reservasAccesoriosContext + '/' + $state.params.idReserva + '/' + accesoriosReservaContext, {
	                    id: $scope.accesorioId
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go('reservaDetail', {nombre: response.data.nombre}, {reload: true});
	                });
	            };
        }
    ]);
    }
)(angular);

