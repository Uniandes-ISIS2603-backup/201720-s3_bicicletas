	(function (ng) {
	    var mod = ng.module("usuarioModule", ['ui.router']);
	    mod.constant("usuariosContext", "api/usuarios");
	    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
	            var basePath = 'src/modules/usuarios/';
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
	                    usuarioDocumentoUsuario: null
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
	                url: '/update/{documentoUsuario:int}',
	                parent: 'usuarios',
	                param: {
	                    usuarioDocumentoUsuario: null
	                },
	                views: {
	                    'detailView': {
	                        templateUrl: basePath + '/new/usuarios.new.html',
	                        controller: 'usuarioUpdateCtrl'
	                    }
	                }
	            }).state('usuarioDelete', {
	                url: '/delete/{documentoUsuario:int}',
	                parent: 'usuarios',
	                param: {
	                    usuarioDocumentoUsuario: null
	                },
	                views: {
	                    'detailView': {
	                        templateUrl: basePath + '/delete/usuario.delete.html',
	                        controller: 'usuarioDeleteCtrl'
	                    }
	                }
	            }).state('usuarioConsulta', {
	                url: '/consulta/{documentoUsuario:int}/',
	                parent: 'usuarios',
	                param: {
	                    usuarioDocumentoUsuario: null
	                },
	                views: {
	                    'listView': {
	                        templateUrl: basePath + 'consulta/usuarios.consulta.input.html',
	                        controller: 'usuarioConsultaCtrl'
	                    }
	                }
	            }).state('usuarioConsultaList', {
	                url: '/consulta/{documentoUsuario:int}/list',
	                parent: 'usuarios',
	                param: {
                            usuarioDocumentoUsuario: null,
	                    respuesta: null
	                },
	                views: {
	                    'listView': {
	                        templateUrl: basePath + 'consulta/usuarios.consulta.list.html',
	                        controller: 'usuarioConsultaCtrl'
	                    }
	                }
	            });
	        }]);
	})(window.angular);