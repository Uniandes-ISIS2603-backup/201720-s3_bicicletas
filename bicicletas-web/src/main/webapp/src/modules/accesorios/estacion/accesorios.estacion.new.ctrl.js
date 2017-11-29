(function (ng) {
var mod = ng.module("accesorioModule");
    mod.constant("estacionesAccesoriosContext", "api/accesorios");
    mod.controller('accesoriosEstacionNewCtrl', ['$scope', '$http', 'estacionesAccesoriosContext', '$state',
        function ($scope, $http, estacionesAccesoriosContext, $state) {
            $scope.createAccesorio = function () {
	                $http.post(estacionesAccesoriosContext + '/', {
                        tipo: $scope.accesorioTipo,
	                    estacion:{
                            id: $state.params.id
                        }
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go('accesoriosEstacionList', {id: $state.params.id}, {reload: true});
	                });
	            };
        }
    ]);
    }
)(angular);

