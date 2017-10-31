(function (ng) {
    var mod = ng.module("direccionModule");
    mod.constant("direccionesContext", "api/direcciones");
    mod.controller('direccionDeleteCtrl', ['$scope', '$http', 'direccionesContext', '$state',
        function ($scope, $http, direccionesContext, $state) {
            var idDireccion = $state.params.direccionId;
            $scope.deleteDireccion = function () {
                $http.delete(direccionesContext + '/' + idDireccion, {}).then(function (response) {
                    $state.go('direccionesList', {direccionId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);