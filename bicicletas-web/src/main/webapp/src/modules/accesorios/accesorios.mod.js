(function (ng) {
    var mod = ng.module("accesorioModule", ['ui.router']);
    mod.constant("accesoriosContext", "api/estaciones");
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
                }
            }).state('accesorioDetail', {
                url: '/{idaccesorio:Long}/detail',
                parent: 'accesorio',
                param: {
                    bookId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'accesorio.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'accesorio.detail.html',
                        controller: 'accesorioCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            });
        }]);
})(window.angular);