(function (ng) {
    var mod = ng.module("pagosModule", ['ui.router']);
    mod.constant("pagosContext", "api/pagos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pagos/';
            $urlRouterProvider.otherwise("/pagosList");

            $stateProvider.state('pagos', {
                url: '/pagos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'pagos.html',
                        controller: 'pagosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('pagosList', {
                url: '/list',
                parent: 'pagos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'pagos.list.html'
                    }
                }
            });
        }]);
})(window.angular);
