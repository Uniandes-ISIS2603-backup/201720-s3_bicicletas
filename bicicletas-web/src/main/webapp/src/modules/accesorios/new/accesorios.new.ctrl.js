(function (ng) {
	    var mod = ng.module("accesorioModule");
	    mod.constant("accesoriosContext", "api/accesorios");
	    mod.controller('accesorioNewCtrl', ['$scope', '$http', '$state', '$rootScope',
	        function ($scope, $http, $state, $rootScope) {
	            $rootScope.edit = false;
	            $scope.createAccesorio = function () {
	                $http.post(accesoriosContext, {
	                    tipo: $scope.accesorioTipo,
	                    estacion:{
	                    	id: $scope.accesorioEstacion
	                    } 
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go('accesoriosList', {id: response.data.id}, {reload: true});
	                });
	            };
	        }
	    ]);
	}
	)(angular);