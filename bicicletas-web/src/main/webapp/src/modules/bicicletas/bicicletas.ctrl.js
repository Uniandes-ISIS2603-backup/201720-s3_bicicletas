(function(ng){
        var mod = ng.module("bicicletaModule");
        mod.constant("bicicletasContext","api/bicicletas");
        mod.controller('bicicletaCtrl',['$scope', '$http', 'bicicletasContext', '$state',
            function ($scope, $http, bicicletasContext, $state) {
            $http.get(bicicletasContext).then(function (response) {
                $scope.bicicletaRecords = response.data;
            });

            if ($state.params.id !== undefined) {
                $http.get(bicicletasContext + '/' + $state.params.id).then(function (response) {
                    $scope.currentBicicleta = response.data;
                });
            }
        }
        ]);
        
    }
)(angular);