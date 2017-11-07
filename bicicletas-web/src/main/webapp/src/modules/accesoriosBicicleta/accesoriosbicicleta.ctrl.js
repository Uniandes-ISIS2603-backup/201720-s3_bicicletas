(function(ng){
        var mod = ng.module("accesorioBicicletaModule");
        mod.constant("biciContext","api/bicicletas");
        mod.constant("accesorioBicicletasContext","accesorioBicicleta");
        mod.controller('accesorioBicicletaCtrl',['$scope', '$http','biciContext', 'accesorioBicicletasContext', '$state',
            function ($scope, $http, accesorioBicicletasContext,biciContext, $state) {
            $http.get(accesorioBicicletasContext +'/'+$state.params.id +'/'+ biciContext).then(function (response) {
                $scope.accesorioBicicletaRecords = response.data;
            });
        }
        ]);
        
    }
)(angular);