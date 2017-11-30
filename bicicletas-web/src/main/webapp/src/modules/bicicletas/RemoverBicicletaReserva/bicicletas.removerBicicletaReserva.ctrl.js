(function (ng) {
var mod = ng.module("bicicletaModule");
    mod.constant("bicicletasEstacionContext", "bicicletas");
    mod.constant("biciResvContext", "api/reservas");
    mod.controller('desAsoCtrl', ['$scope', '$http', 'bicicletasEstacionContext', '$state', 'biciResvContext',
        function ($scope, $http, bicicletasEstacionContext, $state, biciResvContext) {
            $scope.desAsociarBicicleta = function () {
	                $http.put(biciResvContext + '/' + $state.params.idReserva + '/' + bicicletasEstacionContext+'/'+'desAsociar', {
	                    id: $state.params.idBicicleta
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go('bicicletasReservaList', {id: response.data.idReserva}, {reload: true});
	                });
	            };
        }
    ]);
    }
)(angular);

