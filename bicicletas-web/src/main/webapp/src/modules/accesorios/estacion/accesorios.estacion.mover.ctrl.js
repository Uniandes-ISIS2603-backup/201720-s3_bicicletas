(function (ng) {
var mod = ng.module("accesorioModule");
    mod.constant("accesoriosEstacionContext", "accesorios");
    mod.constant("estacionesAccesoriosContext", "api/accesorios");
    mod.controller('moverAccesoriosctrl', ['$scope', '$http', 'estacionesAccesoriosContext', '$state', '$rootScope', 'accesoriosEstacionContext',
        function ($scope, $http, estacionesAccesoriosContext, $state, accesoriosEstacionContext, $rootScope) {
            $rootScope.edit = false;
            $http.get('api/estaciones').then(function (response) {
                $scope.estacionesDisponiblesRecords = response.data;
            })
            $scope.moverAccesorio = function () {
	                $http.put(estacionesAccesoriosContext + '/' +$state.params.idAccesorio + '/mover', {
	                    estacion:{
                            id: $scope.estacionId
                        }
	                }).then(function () {
	                    //Usuario created successfully
	                    $state.go('accesoriosEstacionList', {id: $state.params.id}, {reload: true});
	                });
	            };
        }
    ]);
    }
)(angular);

