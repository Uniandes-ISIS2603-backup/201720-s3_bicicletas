(function (ng) {
    var mod = ng.module("estacionModule");
    mod.constant("estacionesContext", "api/estaciones");
    mod.controller('estacionCtrl', ['$scope', '$http', 'estacionesContext', '$state',
        function ($scope, $http, calificacionesContext, $state) {
            $http.get(estacionesContext).then(function (response) {
                $scope.estacionesRecords = response.data;
            });
            
            if ($state.params.idEstacion !== undefined) {
                $http.get(estacionesContext + '/' + $state.params.idEstacion).then(function (response) {
                    $scope.currentEstacion = response.data;
                });
            }
        }
    ]);
}
)(angular);