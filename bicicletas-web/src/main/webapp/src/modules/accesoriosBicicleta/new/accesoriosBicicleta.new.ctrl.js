(function(ng){
        var mod = ng.module("accesorioBicicletaModule", []);
        mod.constant("accBicicletaContext","api/bicicletas");
        mod.constant("accesorioBicicletasContext","accesorioBicicleta");
        mod.controller('accesorioBicicletaNewCtrl',['$scope', '$http','accBicicletaContext', 'accesorioBicicletasContext', '$state',
            function ($scope, $http, accesorioBicicletasContext,accBicicletaContext, $state) {
	            $scope.createAccesorioBicicleta = function () {
	                $http.post(accesorioBicicletasContext +'/'+$state.params.id +'/'+ accBicicletaContext, {
	                    nombre: $scope.accesorioBicicletaNombre,
	                    descripcion: $scope.accesorioBicicletaDescripcion
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go('bicicletaDetail', {id: response.data.id}, {reload: true});
	                });
	            };
        }
        ]);
        
    }
)(angular);

