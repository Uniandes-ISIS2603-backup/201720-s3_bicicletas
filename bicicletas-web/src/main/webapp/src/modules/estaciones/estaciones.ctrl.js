(function (ng) {
    var mod = ng.module("estacionModule");
    mod.constant("estacionesContext", "api/estaciones");
    mod.controller('estacionCtrl', ['$scope', '$http', 'estacionesContext', '$state',
        function ($scope, $http, estacionesContext, $state) {
            $http.get(estacionesContext).then(function (response) {
                $scope.estacionesRecords = response.data;
            });
            
            if ($state.params.idestacion !== undefined) {
                $http.get(estacionesContext + '/' + $state.params.idestacion).then(function (response) {
                    $scope.currentestacion = response.data;
                });
            }
        }
    ]);
}
)(angular);