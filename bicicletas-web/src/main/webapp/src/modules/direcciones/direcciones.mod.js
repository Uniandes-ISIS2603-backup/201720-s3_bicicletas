(function (ng) {
    var mod = ng.module("direccionModule", ['ui.router']);
    mod.constant("direccionesContext", "api/direcciones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/direcciones/';
            $urlRouterProvider.otherwise("/direccionesList");

            $stateProvider.state('direcciones', {
                url: '/direcciones',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'direcciones.html',
                        controller: 'direccionCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('direccionesList', {
                url: '/list',
                parent: 'direcciones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'direcciones.list.html'
                    }
                }
            }).state('direccionDetail', {
                url: '/{direccionId:int}/detail',
                parent: 'direcciones',
                param: {
                    direccionId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'direcciones.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'direcciones.detail.html',
                        controller: 'direccionCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            });
        }]);
})(window.angular);