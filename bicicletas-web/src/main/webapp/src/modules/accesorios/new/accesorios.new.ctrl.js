(function (ng) {
	    var mod = ng.module("accesorioModule");
	    mod.constant("accesoriosContext", "api/accesorios");
	    mod.controller('accesorioNewCtrl', ['$scope', '$http', 'accesoriosContext', '$state', '$rootScope',
	        function ($scope, $http, accesoriosContext, $state, $rootScope) {
	            $rootScope.edit = false;
	            $scope.createAccesorio = function () {
	                $http.post(accesoriosContext, {
	                    reservado: $scope.reservado,
	                    tipo: $scope.tipo
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go('accesoriosList', {id: response.data.id}, {reload: true});
	                });
	            };
	        }
	    ]);
	}
	)(angular);