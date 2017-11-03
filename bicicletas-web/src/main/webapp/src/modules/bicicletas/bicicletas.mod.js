(function (ng) {
    var mod = ng.module("bicicletaModule", ['estacionModule','ui.router']);
    mod.constant("bicicletasContext", "api/bicicletas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/bicicletas/';
            $urlRouterProvider.otherwise("/bicicletasList");

            $stateProvider.state('bicicletas', {
                url: '/bicicletas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'bicicletas.html',
                        controller: 'bicicletaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('bicicletasList', {
                url: '/list',
                parent: 'bicicletas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'bicicletas.list.html'
                    }
                }
            }).state('bicicletaDetail', {
                url: '/{id:int}/detail',
                parent: 'bicicletas',
                param: {
                    id: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'bicicletas.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'bicicletas.detail.html',
                        controller: 'bicicletaCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            }).state('bicicletasCreate', {
	                url: '/create',
	                parent: 'bicicletas',
	                views: {
	                    'detailView': {
	                        templateUrl: basePath + '/new/bicicletas.new.html',
	                        controller: 'bicicletaNewCtrl'
	                    }
	                }
	    }).state('bicicletaDelete', {
	                url: '/delete/{id:int}',
	                parent: 'bicicletas',
	                param: {
	                    id: null
	                },
	                views: {
	                    'detailView': {
	                        templateUrl: basePath + '/delete/bicicletas.delete.html',
	                        controller: 'bicicletaDeleteCtrl'
	                    }
	                }
	            }).state('bicicletasEstacion', {
                url: '/bicicletas',
                abstract: true,
                parent: 'estacionDetail',
                views: {
                    childrenView: {
                        templateUrl: basePath + 'bicicletas.html'
                    }
                }
            }).state('bicicletasEstacionList', {
                url: '/{id:int}/list',
                parent: 'bicicletasEstacion',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'bicicletas.list.html',
                        controller: 'bicicletasEstacionCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('bicicletasReserva', {
                url: '/bicicletas',
                abstract: true,
                parent: 'reservaDetail',
                views: {
                    childrenView: {
                        templateUrl: basePath + 'bicicletas.html'
                    }
                }
            }).state('bicicletasReservaList', {
                url: '/{idReserva:int}/estacion/{idEstacionSalida:int}/list',
                parent: 'bicicletasReserva',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'reservaBicicletas.html',
                        controller: 'bicicletasReservaCtrl',
                        controllerAs: 'ctrl'
                    }
                    
                }
            }).state('asociarBicicleta', {
	                url: '/create',
	                parent: 'bicicletasReserva',
	                views: {
	                    'detailView': {
	                        templateUrl: basePath + '/asociar/bicicletas.asociar.html',
	                        controller: 'bicicletasReservaCtrl'
	                    }
	                }
	    });                   
        }]);
})(window.angular);
        