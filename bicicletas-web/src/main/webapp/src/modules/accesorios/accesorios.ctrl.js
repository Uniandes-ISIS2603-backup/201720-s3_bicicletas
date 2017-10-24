(function (ng) {
    var mod = ng.module("accesorioModule");
    mod.constant("accesoriosContext", "api/accesorios");
    mod.controller('accesorioCtrl', ['$scope', '$http', 'accesoriosContext', '$state',
        function ($scope, $http, accesoriosContext, $state) {
            $http.get(accesoriosContext).then(function (response) {
                $scope.accesoriosRecords = response.data;
            });
            
            if ($state.params.id !== undefined) {
                $http.get(accesoriosContext + '/' + $state.params.id).then(function (response) {
                    $scope.currentaccesorio = response.data;
                });
            }
        }
    ]);
}
)(angular);