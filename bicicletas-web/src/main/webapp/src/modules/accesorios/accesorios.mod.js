(function (ng) {
    var mod = ng.module("accesorioModule", ['estacionModule','ui.router']);
    mod.constant("accesoriosContext", "api/accesorios");
    mod.constant("estacionesContext", "api/estaciones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/accesorios/';
            $urlRouterProvider.otherwise("/accesoriosList");

            $stateProvider.state('accesorios', {
                url: '/accesorios',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'accesorios.html',
                        controller: 'accesorioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('accesoriosList', {
                url: '/list',
                parent: 'accesorios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'accesorios.list.html'
                    }
                    ,
                    data: {
                    requireLogin: true,
                    roles: ["administrador"]
                }
                }
            }).state('accesorioDetail', {
                url: '/{id:int}/detail',
                parent: 'accesorios',
                param: {
                    id: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'accesorios.detail.html',
                        controller: 'accesorioCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            }).state('accesoriosCreate', {
                    url: '/create',
                    parent: 'accesorios',
                    views: {
                        'detailView': {
                            templateUrl: basePath + '/new/accesorios.new.html',
                            controller: 'accesorioNewCtrl'
                        }
                    }
                }).state('accesoriosEstacion', {
                url: '/accesorios',
                abstract: true,
                parent: 'estacionDetail',
                views: {
                    childrenView: {
                        templateUrl: basePath + 'accesorios.html'
                    }
                }
            }).state('accesoriosEstacionList', {
                url: '/{id:int}/list',
                parent: 'accesoriosEstacion',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/estacion/estacion.accesorios.list.html',
                        controller: 'accesoriosEstacionCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('moverAccesorio', {
                url: '/{idAccesorio:int}/mover',
                parent: 'accesoriosEstacion',
                params:{
                    idAccesorio: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'estacion/accesorios.estacion.mover.html',
                        controller: 'moverAccesoriosctrl',
                        controllerAs: 'ctrl'
                    }
                    
                }
            }).state('accesoriosEstacionCreate', {
                url: '/{id:int}/createAccesorio',
                parent: 'accesoriosEstacion',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'estacion/accesorios.estacion.new.html',
                        controller: 'accesoriosEstacionNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('accesorioDelete', {                  
                    parent: 'accesorios',
                    url: '/{idEstacion:int}/delete/{id:int}',
                    param: {
                        id: null,
                        idEstacion: null
                    },
                    views: {
                        'detailView': {
                            templateUrl: basePath + 'delete/accesorio.delete.html',
                            controller: 'accesorioDeleteCtrl'
                        }
                   } 
                }).state('accesoriosReserva', {
                url: '/accesorios',
                abstract: true,
                parent: 'reservaDetail',
                views: {
                    childrenView: {
                        templateUrl: basePath + 'accesorios.html'
                    }
                }
            }).state('accesoriosReservaList', {
                url: '/{idReserva:int}/list',
                parent: 'accesoriosReserva',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'reservaAccesorios.html',
                        controller: 'accesoriosReservaCtrl',
                        controllerAs: 'ctrl'
                    }
                    
                }
            }).state('asociarAccesorio', {
                    url: '/asociar',
                    parent: 'accesoriosReserva',
                    params:{
                        idEstacion: null
                    },
                    views: {
                        'detailView': {
                            templateUrl: basePath + '/asociar/accesorios.asociar.html',
                            controller: 'asoAccesorioReservaCtrl'
                        }
                    }
            }).state('quitarAccesorio', {
                    url: '/quitar/{id:int}',
                    parent: 'accesoriosReserva',
                    param: {
                        id: null
                    },
                    views: {
                        'detailView': {
                            templateUrl: basePath + '/quitar/accesorios.quitar.html',
                            controller: 'quitarAccesorioReservaCtrl'
                        }
                    }
        });
        }]);
})(window.angular);
