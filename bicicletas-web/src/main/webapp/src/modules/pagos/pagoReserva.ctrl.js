(function (ng) {
    var mod = ng.module("pagosModule");
    mod.constant("pagosContext1", "api/reservas");
    mod.constant("pagosContext2", "Pago");
    mod.controller('pagoReserva', ['$scope', '$http', 'pagosContext1','pagosContext2' ,'$state',
        function ($scope, $http, pagosContext1, pagosContext2, $state) {
            
            if ($state.params.id !== undefined) {
                $http.get(pagosContext1 + '/' + $state.params.id + '/' + pagosContext2).then(function (response) {
                    $scope.currentpago = response.data;
                });
            }


        }
    ]);
}
)(angular);