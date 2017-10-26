(function (ng) {
	    var mod = ng.module("direccionModule");
	    mod.constant("direccionesContext", "api/direcciones");
	    mod.controller('direccionCtrl', ['$scope', '$http', 'direccionesContext', '$state',
	        function ($scope, $http, direccionesContext, $state) {
	            $http.get(direccionesContext).then(function (response) {
	                $scope.direccionesRecords = response.data;
	            });
	            
	            if ($state.params.direccionId !== undefined) {
	                $http.get(direccionesContext + '/' + $state.params.direccionId).then(function (response) {
	                    $scope.currentDireccion = response.data;
	                });
	            }
	        }
	    ]);
	}
)(angular);