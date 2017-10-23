	(function (ng) {
	    var mod = ng.module("usuarioModule", ['ui.router']);
	    mod.constant("usuariosContext", "api/usuarios");
	    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
	            var basePath = 'src/modules/usuarios/';
	            var basePathDirecciones = 'src/modules/direcciones/';
	            $urlRouterProvider.otherwise("/usuariosList");

	            $stateProvider.state('usuarios', {
	                url: '/usuarios',
	                abstract: true,
	                views: {
	                    'mainView': {
	                        templateUrl: basePath + 'usuarios.html',
	                        controller: 'usuarioCtrl',
	                        controllerAs: 'ctrl'
	                    }
	                }
	            }).state('usuariosList', {
	                url: '/list',
	                parent: 'usuarios',
	                views: {
	                    'listView': {
	                        templateUrl: basePath + 'usuarios.list.html'
	                    }
	                }
	            }).state('usuarioDetail', {
	                url: '/{documentoUsuario:int}/detail',
	                parent: 'usuarios',
	                param: {
	                    usuarioId: null
	                },
	                views: {
	                    'detailView': {
	                        templateUrl: basePath + 'usuarios.detail.html',
	                        controller: 'usuarioCtrl',
	                        controllerAs: 'ctrl'
	                    }
	                }
	            }).state('usuariosCreate', {
	                url: '/create',
	                parent: 'usuarios',
	                views: {
	                    'detailView': {
	                        templateUrl: basePath + '/new/usuarios.new.html',
	                        controller: 'usuarioNewCtrl'
	                    }
	                }
	            }).state('usuarioUpdate', {
	                url: '/update/{usuarioId:long}',
	                parent: 'usuarios',
	                param: {
	                    usuarioId: null
	                },
	                views: {
	                    'detailView': {
	                        templateUrl: basePath + '/new/usuarios.new.html',
	                        controller: 'usuarioUpdateCtrl'
	                    }
	                }
	            }).state('usuarioDelete', {
	                url: '/delete/{usuarioId:long}',
	                parent: 'usuarios',
	                param: {
	                    usuarioId: null
	                },
	                views: {
	                    'detailView': {
	                        templateUrl: basePath + '/delete/usuario.delete.html',
	                        controller: 'usuarioDeleteCtrl'
	                    }
	                }
	            });
	        }]);
	})(window.angular);