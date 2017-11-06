(function (ng) {
    var mod = ng.module("pagosModule");
    mod.constant("pagosContext", "api/pagos");
    mod.constant("costoBicicleta", 1000);
    mod.controller('pagosCtrl', ['$scope', '$http', 'pagosContext', '$state', 'costoBicicleta',
        function ($scope, $http, pagosContext, $state, costoBicicleta) {
            $http.get(pagosContext).then(function (response) {
                $scope.pagosRecords = response.data;
            });

  

            /**
             * Mapear el estado de un pago, cambiando su constante por su significado
             * @param pago que se quiere mapear
             * @returns el significado del estado en el que se encuentra el pago
             */
           $scope.mapearEstado = function (pago) {
                var respuesta = "Hola";
                if(pago.estado === 1){
                    respuesta = "Esperando pago";
                }
                
                else if(pago.estado === 7){
                    respuesta = "Procesando pago";
                }
                
                else if(pago.estado === 0){
                    respuesta = "Pagado"
                }
                
                else if(pago.estado ===8){
                    respuesta = "Procesando reembolso";
                }
                
                else if(pago.estado === 5){
                    respuesta = "Reembolso parcial";
                }
                
                else if(pago.estado === 4){
                    respuesta = "Reembolso total";
                }
                
                return respuesta;
            };

            /**
             * 
             * @param {type} pago
             * @returns {pagos.ctrlL#1.pagos.ctrlL#1#L#5.$scope.estaProcesando.respuesta}
             */
            $scope.estaProcesando = function (pago) {
                var respuesta = false;
                if (pago.estado === 7) {
                    respuesta = true;
                }
                return respuesta;
            };
            
            /**
             * Informa si el pago esta en el estado "pagado"
             * @param pago para el cual se quiere saber su estado
             * @returns {Number}
             */
            $scope.estaPagado = function(pago){
                var respuesta = false;
                if(pago.estado === 0){
                    respuesta = true;
                }
                return respuesta;
            };
            
            
            /**
             * Retorno el costo total de un pago
             * @param pago al que se le quiere calcular el costo.
             * @returns el costo total.
             */
            $scope.darCosto = function (pago){
                return pago.bicicletasPendientes*costoBicicleta;
            };
            
           /**
            * Función con la que se verifica si un pago fue efectivamente pagado
            * @param pago que se quiere verificar
            */
            $scope.verificarPago = function (pago) {
                $http.put('http://localhost:8080/bicicletas-web/api/pagos/' + pago.id + '/verificarPago').then(
                        function (response) {
                            //Se hizo la verificación correctamente.
                            $state.go($state.current, {}, {reload: true});
                        });
            };
            
            /**
            * Función con la que se verifica si un pago fue efectivamente pagado
            * @param pago que se quiere verificar
            */
            $scope.verificarReembolso = function (pago) {
                $http.put('http://localhost:8080/bicicletas-web/api/pagos/' + pago.id + '/verificarReembolso').then(
                        function (response) {
                            //Se hizo la verificación correctamente.
                            $state.go($state.current, {}, {reload: true});
                        });
            };
        }
    ]);
}
)(angular);