(function (ng) {
    var mod = ng.module("estacionModule");
    mod.constant("estacionesContext", "api/estaciones");
    mod.controller('estacionCtrl', ['$scope', '$http', 'estacionesContext', '$state',
        function ($scope, $http, estacionesContext, $state) {
            $http.get(estacionesContext).then(function (response) {
                $scope.estacionesRecords = response.data;
            });
            
            if ($state.params.id !== undefined) {
                $http.get(estacionesContext + '/' + $state.params.id).then(function (response) {
                    $scope.currentEstacion = response.data;
                });
            }
        }
    ]);
}
)(angular);