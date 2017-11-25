(function (ng) {
var mod = ng.module("bicicletaModule");
    mod.constant("bicicletasEstacionContext", "bicicletas");
    mod.constant("biciEstContext", "api/estaciones");
    mod.controller('biciEstacionCtrl', ['$scope', '$http', 'bicicletasEstacionContext', '$state', 'biciEstContext',
        function ($scope, $http, bicicletasEstacionContext, $state, biciEstContext) {
            $scope.agregarBicicletaEstacion = function () {
	                $http.put(biciEstContext + '/' + $state.params.id + '/' + bicicletasEstacionContext+'/'+'añadir', {
	                    id: $scope.bicicletaId
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go('bicicletasEstacionList', {id: $state.params.id}, {reload: true});
	                });
	            };
        }
    ]);
    }
)(angular);


