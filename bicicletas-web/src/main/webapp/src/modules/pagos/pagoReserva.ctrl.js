(function (ng) {
    var mod = ng.module("pagosModule");
    mod.constant("pagosContext1", "api/reservas");
    mod.constant("pagosContext2", "Pago");
    mod.constant("costoBicicleta", 1000);
    mod.controller('pagoReserva', ['$scope', '$http', 'pagosContext1', 'pagosContext2', '$state', 'costoBicicleta',
        function ($scope, $http, pagosContext1, pagosContext2, $state, costoBicicleta) {

            $scope.currentpago =  0;
            $http.get(pagosContext1 + '/' + $state.params.id + '/' + pagosContext2).then(function (response) {
                    $scope.currentpago = response.data;
                });

            /**
             * Retorna el significado de la constante
             * @returns el significado de la constante del pago.
             */
            $scope.mapearEstado = function () {
                var respuesta = "Hola";
                if ($scope.currentpago.estado === 1) {
                    respuesta = "Esperando pago";
                } else if ($scope.currentpago.estado === 7) {
                    respuesta = "Procesando pago";
                } else if ($scope.currentpago.estado === 0) {
                    respuesta = "Pagado";
                } else if ($scope.currentpago.estado === 8) {
                    respuesta = "Procesando reembolso";
                } else if ($scope.currentpago.estado === 5) {
                    respuesta = "Reembolso parcial";
                } else if ($scope.currentpago.estado === 4) {
                    respuesta = "Reembolso total";
                }

                return respuesta;
            };

            /**
             * Informa si el pago esta en el estado "verificando pago"
             * @returns true si el pago esta en el estado "verificando pago",
             * false de lo contrario
             */
            $scope.estaProcesando = function () {
                var respuesta = false;
                if ($scope.currentpago.estado === 7) {
                    respuesta = true;
                }
                return respuesta;
            };

            /**
             * Informa si el pago esta en el estado "pagado"
             * @returns {Number}
             */
            $scope.estaPagado = function () {
                var respuesta = false;
                if ($scope.currentpago.estado === 0) {
                    respuesta = true;
                }
                return respuesta;
            };

            /**
             * Retorno el costo total de un pago
             * @returns el costo total.
             */
            $scope.darCosto = function () {
                return $scope.currentpago.bicicletasPendientes * costoBicicleta;
            };
            
            /**
             * Informa si el pago esta en el estado "esperandoPago"
             * @returns {Number}
             */
            $scope.estaEsperandoPago = function () {
                var respuesta = false;
                if ($scope.currentpago.estado === 1) {
                    respuesta = true;
                }
                return respuesta;
            };
            
            /**
             * Informa si el pago esta en el estado "pagado"
             * @returns {Number}
             */
            $scope.estaProcesandoReembolso = function () {
                var respuesta = false;
                if ($scope.currentpago.estado === 8) {
                    respuesta = true;
                }
                return respuesta;
            };
            
             /**
             * Informa si el pago esta en el estado "pagado"
             * @returns {Number}
             */
            $scope.estaReembolsadoParcial = function () {
                var respuesta = false;
                if ($scope.currentpago.estado === 5) {
                    respuesta = true;
                }
                return respuesta;
            };
            
              /**
             * Informa si el pago esta en el estado "pagado"
             * @returns {Number}
             */
            $scope.estaReembolsadoTotal = function () {
                var respuesta = false;
                if ($scope.currentpago.estado === 4) {
                    respuesta = true;
                }
                return respuesta;
            };
            
            
        }
    ]);
}
)(angular);