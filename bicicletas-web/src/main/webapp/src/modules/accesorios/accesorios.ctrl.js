(function (ng) {
    var mod = ng.module("accesorioModule");
    mod.constant("accesoriosContext", "api/accesorios");
    mod.controller('accesorioCtrl', ['$scope', '$http', 'accesoriosContext', '$state',
        function ($scope, $http, calificacionesContext, $state) {
            $http.get(accesoriosContext).then(function (response) {
                $scope.accesoriosRecords = response.data;
            });
            
            if ($state.params.idAccesorio !== undefined) {
                $http.get(accesoriosContext + '/' + $state.params.idAccesorio).then(function (response) {
                    $scope.currentAccesorio = response.data;
                });
            }
        }
    ]);
}
)(angular);