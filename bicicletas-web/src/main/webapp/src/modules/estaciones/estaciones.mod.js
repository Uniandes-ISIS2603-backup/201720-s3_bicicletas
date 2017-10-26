(function (ng) {
    var mod = ng.module("estacionModule", ['estacionModule','ui.router']);
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
                }
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

            });
        }]);
})(window.angular);
