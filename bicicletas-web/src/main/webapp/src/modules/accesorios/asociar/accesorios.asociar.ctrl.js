(function (ng) {
var mod = ng.module("accesorioModule");
    mod.constant("accesoriosReservaContext", "accesorios");
    mod.constant("reservasAccesoriosContext", "api/reservas");
    mod.controller('asoAccesorioReservaCtrl', ['$scope', '$http', 'reservasAccesoriosContext', '$state', '$rootScope', 'accesoriosReservaContext',
        function ($scope, $http, reservasAccesoriosContext, $state, $rootScope, accesoriosReservaContext) {
            $rootScope.edit = false;
            $http.get('api/estaciones'+'/'+$state.params.idEstacion+'/'+accesoriosReservaContext).then(function (response) {
                arreglo = [];
                for (var i = 0; i < response.data.length; i++) {
                    var objetoA = {};
                    objetoA.tipo=" ";
                    objetoA.id= 0;
                    if(response.data[i].reservado===0){
                        if(response.data[i].tipo===1){
                        objetoA.tipo="Casco";
                        objetoA.id=response.data[i].id
                    }
                        else if(response.data[i].tipo===2){
                        objetoA.tipo="Chaleco";
                        objetoA.id=response.data[i].id
                      }
                    }
                    arreglo.push(objetoA);
                }
                $scope.accesoriosDisponiblesRecords = arreglo;
            })
            $scope.asociarAccesorio = function () {
	                $http.put(reservasAccesoriosContext + '/' + $state.params.idReserva + '/' + accesoriosReservaContext , {
	                    id: $scope.accesorioId
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go('accesoriosReservaList', {idReserva: response.data.idReserva }, {reload: true});
	                });
	            };
        }
    ]);
    }
)(angular);

