(function (ng) {
var mod = ng.module("puntoModule", ['usuarioModule', 'ui.router']);
    mod.constant("puntosContext", "puntos");
    mod.constant("usuariosContext", "api/usuarios");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/puntos/';
            $urlRouterProvider.otherwise("/puntosList");

            $stateProvider.state('puntos', {
                url: '/puntos',
                abstract: true,
                parent: 'usuarioDetail',
                views: {
                    childrenView: {
                        templateUrl: basePath + 'puntos.html'
                    }
                }
            }).state('puntosList', {
                url: '/list',
                parent: 'puntos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'puntos.list.html',
                        controller: 'puntosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
    })(window.angular);