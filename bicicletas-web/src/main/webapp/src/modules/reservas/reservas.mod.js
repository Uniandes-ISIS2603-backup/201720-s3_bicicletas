(function (ng) {
var mod = ng.module("reservaModule", ['usuarioModule', 'ui.router', 'pagosModule']);
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
	                url: '/{idUsuario:int}/reservas/{idReserva:int}/detail',
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
	            }).state('reservaCreate', {
	                url: '{idUsuario:int}/create',
	                parent: 'reservas',
                        param:{
                            idUsuario:null
                        },
	                views: {
	                    'detailView': {
	                        templateUrl: basePath + '/new/reservas.new.html',
	                        controller: 'reservaNewCtrl',
                                controllerAs: 'ctrl'
	                    }
	                }
	            }).state('reservaDelete', {
	                url: '/delete/{idReserva:int}',
	                parent: 'reservas',
	                param: {
	                    idReserva: null
	                },
	                views: {
	                    'detailView': {
	                        templateUrl: basePath + '/delete/reservas.delete.html',
	                        controller: 'reservaDeleteCtrl',
                                controllerAs: 'ctrl'
	                    }
	                }
	            });
        }]);
    })(window.angular);