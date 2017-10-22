(function (ng) {
    var mod = ng.module("usuarioModule", ['ui.router']);
     mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/usuarios/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/usuariosList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('usuariosList', {
                // Url que aparecerá en el browser
                url: '/usuarios/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuarios.list.html',
                        controller: 'usuarioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);