(function (ng) {
    var mod = ng.module("accesorioBicicletaModule", ['bicicletaModule','ui.router']);
    mod.constant("accesoriosBicicletaContext","accesorioBicicleta");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/accesoriosBicicleta/';
            $urlRouterProvider.otherwise("/accesoriosBicicletaList");

            $stateProvider.state('accesoriosBicicletaList', {
                url: '/accesorios',
                abstract: true,
                parent: 'bicicletaDetail',
                views: {
                    childrenView: {
                        templateUrl: basePath + 'accesoriosBicicleta.html'
                    }
                }
            }).state('accesoriosBicicletaBicicleta', {
                url: '/{id:int}/list',
                parent: 'accesoriosBicicletaList',
                views: {
                    'listView': {
                        templateUrl: basePath + 'accesoriosBicicleta.list.html',
                        controller: 'accesorioBicicletaCtrl',
                        controllerAs: 'ctrl'
                    }
                    ,
                    data: {
                    requireLogin: true,
                    roles: ["administrador"]
                }
                    
                }
            }).state('createAccesorioBicicleta', {
	                url: '/{id:int}/create',
	                parent: 'accesoriosBicicletaList',
	                views: {
	                    'detailView': {
	                        templateUrl: basePath + '/new/accesoriosBicicleta.new.html',
	                        controller: 'accesorioBicicletaNewCtrl',
                                controllerAs: 'ctrl'
	                    }
	                }
	    }).state('deleteAccesorioBicicleta', {
	                url: '/{id:int}/delete/{idAccesorioBicicleta:int}',
	                parent: 'accesoriosBicicletaList',
	                views: {
	                    'detailView': {
	                        templateUrl: basePath + '/delete/accesoriosBicicleta.delete.html',
	                        controller: 'accesorioBicicletaDeleteCtrl',
                                controllerAs: 'ctrl'
	                    }
	                }
	    });                   
        }]);
})(window.angular);
        