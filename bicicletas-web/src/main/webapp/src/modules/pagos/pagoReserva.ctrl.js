(function (ng) {
    var mod = ng.module("pagosModule");
    mod.constant("pagosContext1", "api/reservas");
    mod.constant("pagosContext2", "Pago");
    mod.constant("costoBicicleta", 1000);
    mod.controller('pagoReserva', ['$scope', '$http', 'pagosContext1', 'pagosContext2', '$state', 'costoBicicleta',
        function ($scope, $http, pagosContext1, pagosContext2, $state, costoBicicleta) {

            $scope.currentpago = 0;
            $scope.currentreserva = 0;
            $http.get(pagosContext1 + '/' + $state.params.id + '/' + pagosContext2).then(function (response) {
                $scope.currentpago = response.data;
            });
            $http.get('api/reservas/' + $state.params.id + '/').then(function (response) {
                $scope.currentreserva = response.data;
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
            
            $scope.darRestaDescuento = function () {
                return $scope.currentreserva.precioFinal*0.05;
            };

            /**
             * Retorno el costo total de un pago
             * @returns el costo total.
             */
            $scope.darCosto = function () {
                return $scope.currentreserva.precioFinal;
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
            
            /**
             * Método que calcula las horas estimadas
             * @returns horas estimadas
             */
            $scope.darHoras = function(){
                var fechaInicio = new Date($scope.currentreserva.fechaInicio);
                var fechaEntrega = new Date($scope.currentreserva.fechaEntrega);
                
                
                var horasInicio = fechaInicio.getHours();
                var horasEntrega = fechaEntrega.getHours();
                
                return horasEntrega - horasInicio + " horas";
            };
            
            
            /**
             * Método que calcula los minutos estimados.
             * @returns minutlos estimados
             */
            $scope.darMinutos = function(){
                var fechaInicio = new Date($scope.currentreserva.fechaInicio);
                var fechaEntrega = new Date($scope.currentreserva.fechaEntrega);
                
                var minutosInicio = fechaInicio.getMinutes();
                var minutosEntrega = fechaEntrega.getMinutes();
                var minutos = 0;
                
                if(minutosEntrega >= minutosInicio){
                    minutos = minutosEntrega - minutosInicio;
                } else{
                    minutos = minutosInicio - minutosEntrega;
                }
                
                var respuesta = "";
                
                if(minutos !== 0){
                    respuesta = " y " + minutos + " minutos"; 
                }
                
                return respuesta;
            };


        }
    ]);
}
)(angular);