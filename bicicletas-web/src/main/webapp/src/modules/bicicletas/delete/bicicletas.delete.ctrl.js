(function (ng) {
    var mod = ng.module("bicicletaModule");
    mod.constant("bicicletasContext", "api/bicicletas");
    mod.controller('bicicletaDeleteCtrl', ['$scope', '$http', 'bicicletasContext', '$state',
        function ($scope, $http, bicicletasContext, $state) {
            var id = $state.params.id;
            $scope.deleteBicicleta = function () {
                $http.delete(bicicletasContext + '/' + id, {}).then(function (response) {
                    $state.go(history.back(), {reload: true});
                });
            };
        }
    ]);
}
)(angular);
