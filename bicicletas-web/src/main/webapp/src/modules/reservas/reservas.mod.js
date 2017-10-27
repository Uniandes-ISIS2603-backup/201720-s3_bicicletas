(function (ng) {
var mod = ng.module("reservaModule", ['usuarioModule', 'ui.router']);
    mod.constant("reservasContext", "reservas");
    mod.constant("usuariosContext", "api/usuarios");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/reservas/';
            $urlRouterProvider.otherwise("/reservasList");

            $stateProvider.state('reservas', {
                url: '/reservas',
                abstract: true,
                parent: 'usuarioDetail',
                views: {
                    childrenView: {
                        templateUrl: basePath + 'reservas.html'
                    }
                }
            }).state('reservasList', {
                url: '/list',
                parent: 'reservas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'reservas.list.html',
                        controller: 'reservasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('reservaDetail', {
	                url: '{idUsuario:int}/{idReserva:int}/detail',
	                parent: 'usuarios',
	                param: {
                            idReserva:null,
                            idUsuario: null
	                },
	                views: {
	                    'detailView': {
	                        templateUrl: basePath + 'reservas.detail.html',
	                        controller: 'reservasDetailCtrl',
	                        controllerAs: 'ctrl'
	                    }
	                }
	            });
        }]);
    })(window.angular);