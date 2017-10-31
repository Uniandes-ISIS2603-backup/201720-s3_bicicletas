(function (ng) {
    var mod = ng.module("estacionModule");
    mod.constant("estacionesContext", "api/estaciones");
    mod.controller('estacionDeleteCtrl', ['$scope', '$http', 'estacionesContext', '$state',
        function ($scope, $http, estacionesContext, $state) {
            var id = $state.params.id;
            $scope.deleteEstacion = function () {
                $http.delete(estacionesContext + '/' + id, {}).then(function (response) {
                    $state.go('estacionesList', {id: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);