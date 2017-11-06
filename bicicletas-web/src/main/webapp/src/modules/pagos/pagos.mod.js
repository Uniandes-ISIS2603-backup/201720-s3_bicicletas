(function (ng) {
    var mod = ng.module("pagosModule", ['ui.router']);
    mod.constant("pagosContext", "api/pagos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pagos/';
            var basePathMetodo = 'src/modules/pagos/metodos/';
            
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
            }).state('pagoDetail', {
                url: '/{id:int}/detail',
                parent: 'pagos',
                param: {
                    id: null
                }, 
                views:{
                    
                    'detailView':{
                        templateUrl: basePath + 'pagos.detail.html',
                        controller: 'pagosCtrl',
                        controllerAs: 'ctrl'
                    }
                } 
            }).state('pagoReserva', {
                url: '/{id:int}/Pago',
                parent: 'pagos',
                param: {
                    id: null
                }, 
                views:{
                    
                    'detailView':{
                        templateUrl: basePath + 'pagos.detail.html',
                        controller: 'pagoReserva',
                        controllerAs: 'ctrl'
                    }
                } 
            }).state('pagoConTarjeta', {
                url: '/{id:int}/Pago/pagoTarjeta',
                parent: 'pagos',
                param: {
                    id: null
                }, 
                views:{
                    'detailView':{
                        templateUrl: basePathMetodo + 'pagos.tarjeta.html',
                        controller: 'metodosCtrl',
                        controllerAs: 'ctrl'
                    }
                } 
            }).state('pagoConPSE', {
                url: '/{id:int}/Pago/pagoPSE',
                parent: 'pagos',
                param: {
                    id: null
                }, 
                views:{
                    'detailView':{
                        templateUrl: basePathMetodo + 'pagos.PSE.html',
                        controller: 'metodosCtrl',
                        controllerAs: 'ctrl'
                    }
                } 
            }).state('pagoConPuntos', {
                url: '/{id:int}/Pago/pagoConPuntos',
                parent: 'pagos',
                param: {
                    id: null
                }, 
                views:{
                    'detailView':{
                        templateUrl: basePathMetodo + 'pagos.puntos.html',
                        controller: 'metodosCtrl',
                        controllerAs: 'ctrl'
                    }
                } 
            }).state('solicitarReembolso', {
                url: '/{id:int}/Pago/solicitarReembolso',
                parent: 'pagos',
                param: {
                    id: null
                }, 
                views:{
                    'detailView':{
                        templateUrl: basePathMetodo + 'pagos.reembolso.html',
                        controller: 'metodosCtrl',
                        controllerAs: 'ctrl'
                    }
                } 
            });
        }]);
})(window.angular);
