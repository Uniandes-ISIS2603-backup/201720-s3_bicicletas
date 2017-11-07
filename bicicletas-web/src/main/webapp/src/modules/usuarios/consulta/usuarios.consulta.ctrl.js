(function (ng) {
    var mod = ng.module("usuarioModule");
    mod.constant("usuariosReservasContext", "api/reservas");
    mod.controller('usuarioConsultaCtrl', ['$scope', '$http', 'usuariosReservasContext', '$state',
        function ($scope, $http, usuariosReservasContext, $state) {
            $scope.consultaUsuario= function(){
                var documentoUsuario = $state.params.documentoUsuario;
                $http.put(usuariosReservasContext + '/' + documentoUsuario + '/consulta', {
                        stringInicio: $scope.fechaSalida+":00",
                        stringFinal: $scope.fechaLlegada+":00"
                    }).then(function (response) {
                        $state.go('usuarioConsultaList', {respuesta: response.data, usuarioDocumentoUsuario: documentoUsuario}, {reload: true});
                    });
            };
        }
    ]);
}
)(angular);