(function (ng) {
    var mod = ng.module("estacionModule", ['ui.router']);
    mod.constant("estacionesContext", "api/estaciones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/estaciones/';
            $urlRouterProvider.otherwise("/estacionesList");

            $stateProvider.state('estaciones', {
                url: '/estaciones',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'estaciones.html',
                        controller: 'estacionCtrl',
                        controllerAs: 'ctrl'
                    }
                    
                }
                
            }).state('estacionesList', {
                url: '/list',
                parent: 'estaciones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'estaciones.list.html'
                    }
                }, data: {
                    requireLogin: true,
                    roles: ["administrador"]}
            }).state('estacionDetail', {
                url: '/{id:int}/detail',
                parent: 'estaciones',
                param: {
                    id: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'estaciones.detail.html',
                        controller: 'estacionCtrl',
                        controllerAs: 'ctrl'
                    }
                }
                , data: {
                    requireLogin: true,
                    roles: ["administrador"]}
            }).state('estacionesCreate',{
                url: '/create',
                    parent: 'estaciones',
                    views: {
                        'detailView': {
                            templateUrl: basePath + '/new/estaciones.new.html',
                            controller: 'estacionNewCtrl',
                            controllerAs: 'ctrl'
                        }
                    }
            }).state('estacionDelete', {
                    url: '/delete/{id:int}',
                    parent: 'estaciones',
                    param: {
                        id: null
                    },
                    views: {
                        'detailView': {
                            templateUrl: basePath + 'delete/estaciones.delete.html',
                            controller: 'estacionDeleteCtrl'
                        }
                    }
                });
        }]);
})(window.angular);
