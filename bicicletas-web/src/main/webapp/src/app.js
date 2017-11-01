(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        'ui.bootstrap',
        // Internal modules dependencies  
        'estacionModule',     
        'calificacionModule',
        'accesorioModule',
        'usuarioModule',
        'puntoModule',
        'direccionModule',
        'bicicletaModule',
        'reservaModule',
        'pagosModule'
    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);
//