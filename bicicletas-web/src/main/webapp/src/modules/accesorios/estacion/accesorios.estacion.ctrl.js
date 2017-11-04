(function (ng) {
var mod = ng.module("accesorioModule");
    mod.constant("accesoriosEstacionContext", "accesorios");
    mod.constant("estacionesContext", "api/estaciones");
    mod.controller('accesoriosEstacionCtrl', ['$scope', '$http', 'estacionesContext', '$state', 'accesoriosEstacionContext',
        function ($scope, $http, estacionesContext, $state, accesoriosEstacionContext) {
            $http.get(estacionesContext + '/' + $state.params.id + '/' + accesoriosEstacionContext).then(function (response) {
                $scope.accesoriosRecords = response.data;
            });
        }
    ]);
    }
)(angular);