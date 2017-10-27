(function (ng) {
var mod = ng.module("calificacionModule");
    mod.constant("calificacionesContext", "calificaciones");
    mod.constant("estacionesContext", "api/estaciones");
    mod.controller('calificacionesCtrl', ['$scope', '$http', 'estacionesContext', '$state', 'calificacionesContext',
        function ($scope, $http, estacionesContext, $state, calificacionesContext) {
            $http.get(estacionesContext + '/' + $state.params.id + '/' + calificacionesContext).then(function (response) {
                $scope.calificacionesRecords = response.data;
            });
        }
    ]);
    }
)(angular);