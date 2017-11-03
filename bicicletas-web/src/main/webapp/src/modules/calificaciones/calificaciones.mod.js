(function (ng) {
    var mod = ng.module("calificacionModule", ['estacionModule','usuarioModule', 'ui.router']);
    mod.constant("calificacionesContext", "calificaciones");
    mod.constant("estacionesContext", "api/estaciones");
    mod.constant("reservasContext", "reservas");
    mod.constant("usuariosContext", "api/usuarios");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/calificaciones/';
            $urlRouterProvider.otherwise("/calificacionesList");

        $stateProvider.state('calificaciones', {
                url: '/calificaciones',
                abstract: true,
                parent: 'estacionDetail',
                views: {
                    childrenView: {
                        templateUrl: basePath + 'calificaciones.html'
                    }
                }
            }).state('calificacionesReserva', {
                url: '/calificaciones',
                abstract: true,
                parent: 'reservaDetail',
                views: {
                    childrenView: {
                        templateUrl: basePath + 'calificaciones.html'
                    }
                }
            }).state('calificacionesEstacionList', {
                url: '/list',
                parent: 'calificaciones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'calificaciones.list.html',
                        controller: 'calificacionesCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('calificacionDetail', {
                url:'/usuarios/{idUsuario}/reservas/{idReserva}/calificacion/:cali',
                parent: 'calificacionesReserva',
                params:{
                    idUsuario: null,
                    idReserva: null
                },
                views: {
                    'listView':{
                        templateUrl: basePath + 'calificaciones.detail.html',
                        controller: 'calificacionesDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('calificacionesCreate', {
	                url: '/create/:cali',
	                parent: 'calificacionesReserva',
	                views: {
	                    'listView': {
	                        templateUrl: basePath + '/new/calificaciones.new.html',
	                        controller: 'calificacionNewCtrl'
	                    }
	                }
	            });
        }]);
})(window.angular);
