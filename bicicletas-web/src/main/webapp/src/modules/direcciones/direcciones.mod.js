(function (ng) {
var mod = ng.module("direccionModule", ['usuarioModule', 'ui.router']);
mod.constant("direccionesContext", "direcciones");
mod.constant("usuariosContext", "api/usuarios");

mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        var basePath = 'src/modules/direcciones/';
        $urlRouterProvider.otherwise("/direccionesList");

        $stateProvider.state('direcciones', {
            url: '/direcciones',
            abstract: true,
            parent: 'usuarioDetail',
            views: {
                childrenView: {
                    templateUrl: basePath + 'direcciones.html'
                }
            }
        }).state('direccionesList', {
            url: '/list',
            parent: 'direcciones',
            views: {
                'listView': {
                    templateUrl: basePath + 'direcciones.list.html',
                    controller: 'direccionesCtrl',
                    controllerAs: 'ctrl'
                }
            }
            }).state('direccionesCreate', {
                url: '/create',
                parent: 'direcciones',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/direcciones.new.html',
                        controller: 'direccionNewCtrl'
                    }
                }   
        }).state('direccionDelete', {
            url: '/delete/{direccionId:int}',
            parent: 'direcciones',
            param: {
                direccionId: null
            },
            views: {
                'detailView': {
                    templateUrl: basePath + '/delete/direccion.delete.html',
                    controller: 'direccionDeleteCtrl'
                }
            }
        });
    }]);
})(window.angular);