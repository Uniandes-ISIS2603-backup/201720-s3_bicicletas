(function (ng) {
    var mod = ng.module("pagosModule");
    mod.constant("pagosContext", "api/pagos");
    mod.controller('pagosCtrl', ['$scope', '$http', 'pagosContext', '$state',
        function ($scope, $http, pagosContext, $state) {
            $http.get(pagosContext).then(function (response) {
                $scope.pagosRecords = response.data;
            });
            
            if ($state.params.id !== undefined) {
                $http.get(pagosContext + '/' + $state.params.id).then(function (response) {
                    $scope.currentpago = response.data;
                });
            }
        }
    ]);
}
)(angular);