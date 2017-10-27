(function (ng) {
    var mod = ng.module("calificacionModule", ['estacionModule','ui.router']);
    mod.constant("calificacionesContext", "calificaciones");
    mod.constant("usuariosContext", "api/estaciones");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/calificaciones/';
            $urlRouterProvider.otherwise("/calificacionesList");

        $stateProvider.state('calificaciones', {
                url: '/calificaciones',
                abstract: true,
                parent: 'estacionDetail',
                views: {
                    childrenView: {
                        templateUrl: basePath + 'calificaciones.html'
                    }
                }
            }).state('calificacionesEstacionList', {
                url: '/list',
                parent: 'calificaciones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'calificaciones.list.html',
                        controller: 'calificacionesCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);
