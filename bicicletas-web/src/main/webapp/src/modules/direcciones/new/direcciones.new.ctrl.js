(function (ng) {
    var mod = ng.module("direccionModule");
    mod.constant("direccionesContext", "api/direcciones");
    mod.controller('direccionNewCtrl', ['$scope', '$http', 'direccionesContext', '$state', 'direccionesContext', '$rootScope',
        function ($scope, $http, direccionesContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createDireccion = function () {
                $http.post('api/direcciones', {
                    name: $scope.direccionName,
                    codigoPostal: $scope.direccionCodigoPostal,
                    descripcion: $scope.direccionDescripcion,
                    ciudad: $scope.direccionCiudad
                }).then(function (response) {
                    //Direccion created successfully
                    $http.post("api/usuarios" + '/' + $state.params.documentoUsuario + '/' + direccionesContext+ '/' +response.data.id).then(function (response)
                    {
                        $state.go('direccionesList', {direccionId: response.data.id}, {reload: true});
                    });
                    
                     
                });
            };
        }
    ]);
}
)(angular);