(function (ng) {
    var mod = ng.module("reservaModule");
    mod.constant("reservasContext", "reservas");
    mod.constant("usuariosContext", "api/usuarios");
    mod.constant("bicicletasContext", "bicicletas");
    mod.controller('reservasDetailCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'reservasContext', 'bicicletasContext',
        function ($scope, $http, usuariosContext, $state, reservasContext, bicicletasContext) {

            //Solicitud de reserva
            $http.get(usuariosContext + '/' + $state.params.idUsuario + '/' + reservasContext + '/' + $state.params.idReserva).then(function (response) {
                $scope.currentReserva = response.data;
            });

            //Solicitud de bicicletas
            $http.get('api/' + reservasContext + '/' + $state.params.idReserva + '/bicicletas').then(function (response) {
                $scope.bicicletas = response.data;
            });

            //Solicitud del pago
            $http.get('api/' + reservasContext + '/' + $state.params.idReserva + '/Pago/existePago').then(function (response) {
                $scope.pago = response.data;
            }, function (response) {
                $scope.pago = null;
            });


            $scope.irAPagar = function () {
                //Confirmar cuando el pago no existe
                if ($scope.pago === null) {

                    if ($scope.bicicletas.length > 0) {
                        if (confirm("¿Esta seguro que desea pagar? Ya no podrá asociar más bicicletas a la reserva")) {
                            $state.go("pagoReserva", {id: $scope.currentReserva.idReserva}, {reload: true});
                        }
                    } else {
                        confirm("Agregue bicicletas antes de continuar");
                    }
                }

                //Si el pago ya existe
                else {
                    $state.go("pagoReserva", {id: $scope.currentReserva.idReserva}, {reload: true});
                }
            };

            $scope.verTransaccion = function () {
                //Hace la solicitud de la traansaccion
                $http.get('api/' + reservasContext + '/' + $state.params.idReserva + '/transaccion').then(function (response) {
                    $scope.currenttransaccion = response.data;

                    var texto = "Las bicicletas fueron entregadas a tiempo";
                    
                    if ($scope.currenttransaccion.tipo === 0) {
                        texto = "Se debe reembolsar un monto de: " + $scope.currenttransaccion.valor+"$";
                    } else if ($scope.currenttransaccion.tipo === 1) {
                        texto = "Se debe pagar un excedente de: " + $scope.currenttransaccion.valor+"$";
                    }

                    confirm(texto);

                });
            };
            
           $scope.iniciarReserva = function () {
                //Hace la solicitud de la traansaccion
                $http.get('api/' + reservasContext + '/' + $state.params.idReserva + '/transaccion').then(function (response) {
                    $scope.currenttransaccion = response.data;

                    var texto = "Las bicicletas fueron entregadas a tiempo";
                    
                    if ($scope.currenttransaccion.tipo === 0) {
                        texto = "Se debe reembolsar un monto de: " + $scope.currenttransaccion.valor+"$";
                    } else if ($scope.currenttransaccion.tipo === 1) {
                        texto = "Se debe pagar un excedente de: " + $scope.currenttransaccion.valor+"$";
                    }

                    confirm(texto);

                });
            };
        }
    ]);
}
)(angular);

