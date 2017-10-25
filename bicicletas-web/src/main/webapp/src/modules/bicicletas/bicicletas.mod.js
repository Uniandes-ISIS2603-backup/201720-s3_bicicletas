(function (ng) {
    var mod = ng.module("bicicletaModule", ['bicicletaModule','ui.router']);
    mod.constant("bicicletasContext", "api/bicicletas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/bicicletas/';
            $urlRouterProvider.otherwise("/bicicletasList");

            $stateProvider.state('bicicletas', {
                url: '/bicicletas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'bicicletas.html',
                        controller: 'bicicletaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('bicicletasList', {
                url: '/list',
                parent: 'bicicletas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'bicicletas.list.html'
                    }
                }
            }).state('bicicletaDetail', {
                url: '/{idBiccileta:Long}/detail',
                parent: 'bicicletas',
                param: {
                    bicicletaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'bicicletas.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'bicicletas.detail.html',
                        controller: 'bicicletaCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            });
        }]);
})(window.angular);
        