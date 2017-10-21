(function (ng) {
    var mod = ng.module("calificacionModule", ['ui.router']);
    mod.constant("calificacionesContext", "api/calificaciones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/calificaciones/';
            $urlRouterProvider.otherwise("/calificacionesList");

            $stateProvider.state('calificaciones', {
                url: '/calificaciones',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'calificaciones.html',
                        controller: 'calificacionCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('calificacionesList', {
                url: '/list',
                parent: 'calificaciones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'calificaciones.list.html'
                    }
                }
            }).state('calificacionDetail', {
                url: '/{idCalificacion:Long}/detail',
                parent: 'calificaciones',
                param: {
                    bookId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'calificaciones.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'calificaciones.detail.html',
                        controller: 'calificacionCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            });
        }]);
})(window.angular);
