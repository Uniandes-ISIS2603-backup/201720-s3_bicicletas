(function(ng){
        var mod = ng.module("accesorioBicicletaModule");
        mod.constant("accBicicletaContext","api/bicicletas");
        mod.constant("accesorioBicicletasContext","accesorioBicicleta");
        mod.controller('accesorioBicicletaDeleteCtrl',['$scope', '$http','accBicicletaContext', 'accesorioBicicletasContext', '$state',
            function ($scope, $http, accesorioBicicletasContext,accBicicletaContext, $state) {
	            $scope.deleteAccesorioBicicleta = function () {
	                $http.delete(accesorioBicicletasContext + '/' +$state.params.id + '/' + accBicicletaContext + '/' + $state.params.idAccesorioBicicleta, {                           
                        }).then(function () {
	                    $state.go('accesoriosBicicletaBicicleta', {id: $state.params.id}, {reload: true});
	                });
	            };
        }
        ]);
        
    }
)(angular);

