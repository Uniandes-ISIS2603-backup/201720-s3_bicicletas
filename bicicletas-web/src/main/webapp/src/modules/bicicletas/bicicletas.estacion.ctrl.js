(function (ng) {
var mod = ng.module("bicicletaModule");
    mod.constant("bicicletasEstacionContext", "bicicletas");
    mod.constant("estacionesContext", "api/estaciones");
    mod.controller('bicicletasEstacionCtrl', ['$scope', '$http', 'estacionesContext', '$state', 'bicicletasEstacionContext',
        function ($scope, $http, estacionesContext, $state, bicicletasEstacionContext) {
            $http.get(estacionesContext + '/' + $state.params.id + '/' + bicicletasEstacionContext).then(function (response) {
                $scope.bicicletaRecords = response.data;
            });
        }
    ]);
    }
)(angular);