(function (ng) {
    var mod = ng.module("accesorioModule");
    mod.constant("accesoriosContext", "api/accesorios");
    mod.controller('accesorioDeleteCtrl', ['$scope', '$http', 'accesoriosContext', '$state',
        function ($scope, $http, accesoriosContext, $state) {
            var id = $state.params.id;
            $scope.deleteAccesorio = function () {
                $http.delete(accesoriosContext + '/' + id, {}).then(function (response) {
                    $state.go('accesoriosList', {id: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);