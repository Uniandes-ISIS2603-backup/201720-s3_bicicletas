(function (ng) {
    var mod = ng.module("pagosModule");
    mod.constant("pagosContext", "api/pagos");
    mod.controller('pagosCtrl', ['$scope', '$http', 'pagosContext', '$state',
        function ($scope, $http, pagosContext, $state) {
            $http.get(pagosContext).then(function (response) {
                $scope.pagosRecords = response.data;
                //Cambiar las constantes por lo que corresponda
                for (i = 0; i < $scope.pagosRecords.length; i++)
                {
                    $scope.pagosRecords[i].estado = "Esperando pago";
                    var estado = $scope.pagosRecords[i];

                    if (estado == 1) {
                        $scope.pagosRecords[i].estado = "Esperando pago";
                    }
                    
                    else if(estado == 7){
                        $scope.pagosRecords[i].estado = "El pago se está procesando";
                    }
                }

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