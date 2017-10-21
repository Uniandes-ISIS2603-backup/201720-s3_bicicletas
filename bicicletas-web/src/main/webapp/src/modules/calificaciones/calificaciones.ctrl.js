(function (ng) {
    var mod = ng.module("calificacionModule");
    mod.constant("calificacionesContext", "api/calificaciones");
    mod.controller('calificacionCtrl', ['$scope', '$http', 'calificacionesContext', '$state',
        function ($scope, $http, calificacionesContext, $state) {
            $http.get(calificacionesContext).then(function (response) {
                $scope.calificacionesRecords = response.data;
            });
            
            if ($state.params.idCalificacion !== undefined) {
                $http.get(calificacionesContext + '/' + $state.params.idCalificacion).then(function (response) {
                    $scope.currentCalificacion = response.data;
                });
            }
        }
    ]);
}
)(angular);