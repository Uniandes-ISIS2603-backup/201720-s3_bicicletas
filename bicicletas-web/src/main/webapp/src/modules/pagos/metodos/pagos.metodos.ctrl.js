(function (ng) {
    var mod = ng.module("pagosModule");
    mod.constant("pagosContext1", "api/reservas");
    mod.constant("pagosContext2", "Pago");


    mod.controller('metodosCtrl', ['$scope', '$http', 'pagosContext1', 'pagosContext2', '$state', '$rootScope',
        function ($scope, $http, pagosContext1, pagosContext2, $state, $rootScope) {

            $rootScope.edit = false;

            $scope.reembolso = false;
            $scope.noReembolso = false;
            $scope.currentpago = function () {
                this.idUsuario = 0;
            };
            $scope.currentusuario = 0;
            $scope.puntos = 0;

            if ($state.params.id !== undefined) {
                $http.get(pagosContext1 + '/' + $state.params.id + '/' + pagosContext2).then(function (response) {
                    $scope.currentpago = response.data;
                    $http.get('api/usuarios' + '/' + $scope.currentpago.idUsuario).then(function (response) {
                        $scope.currentusuario = response.data;

                        if ($state.current.name === 'pagoConPuntos') {
                            $http.get('api/usuarios' + '/' + $scope.currentusuario.documentoUsuario + '/puntos').then(function (response) {

                                
                                $scope.puntos = response.data.length;

                            });
                        }
                    });
                });



            }

            /**
             * Función con la que se paga con tarjeta
             */
            $scope.pagarConTarjeta = function () {
                $http({
                    method: 'PUT',
                    url: 'http://localhost:8080/bicicletas-web/api/reservas/' + $state.params.id + '/Pago/pagar?metodo=1',
                    data: $scope.contrasenia,
                    headers: {
                        'Content-Type': 'text/plain'
                    }}).then(
                        function (response) {
                            //El pago fue efectuado correctamente
                            $state.go('pagoReserva', {id: $state.params.id}, {reload: true});
                        });
            };

            /**
             * Función con la que se paga con tarjeta
             */
            $scope.pagarConPSE = function () {
                $http({
                    method: 'PUT',
                    url: 'http://localhost:8080/bicicletas-web/api/reservas/' + $state.params.id + '/Pago/pagar?metodo=2',
                    data: $scope.contraseniaPSE,
                    headers: {
                        'Content-Type': 'text/plain'
                    }}).then(
                        function (response) {
                            //El pago fue efectuado correctamente
                            $state.go('pagoReserva', {id: $state.params.id}, {reload: true});
                        });
            };
            /**
             * Función con la que se paga una bicicleta con puntos.
             */
            $scope.pagarConPuntos = function () {
                $http.put('http://localhost:8080/bicicletas-web/api/reservas/' + $state.params.id + '/Pago/pagarConPuntos').then(
                        function (response) {
                            //El pago fue efectuado correctamente
                            $state.go('pagoReserva', {id: $state.params.id}, {reload: true});
                        });
            };


            /**
             * Retorna los puntos restantes.
             * @returns los puntos restantes
             */
            $scope.darPuntosRestantes = function () {
                return $scope.puntos - 10;
            };


            /**
             * Función con la que se solicita un reembolso.
             */
            $scope.solicitarReembolso = function () {
                if ($scope.noReembolso) {
                    $state.go('pagoReserva', {id: $state.params.id}, {reload: true});
                } else if ($scope.reembolso) {
                    $http.put('http://localhost:8080/bicicletas-web/api/reservas/' + $state.params.id + '/Pago/solicitarReembolso').then(
                            function (response) {
                                //El pago fue efectuado correctamente
                                $state.go('pagoReserva', {id: $state.params.id}, {reload: true});
                            });
                }
            };

        }
    ]);
}
)(angular);