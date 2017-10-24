(function (ng) {
    var mod = ng.module("accesorioModule", ['accesorioModule','ui.router']);
    mod.constant("accesoriosContext", "api/accesorios");
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

            });
        }]);
})(window.angular);
