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
        'pagosModule',
        'accesorioBicicletaModule',
        'loginModule'
    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
    
    //Codigo empleado para inicio de sesión.
    app.run(['$rootScope', '$transitions', function ($rootScope, $transitions) {

            $transitions.onSuccess({to: '*'}, function (trans) {

                var $state = trans.router.stateService;
                var requireLogin = $state.current.data.requireLogin
                var roles = $state.current.data.roles
               

                $rootScope.isAuthenticated = function () {

                    if (sessionStorage.getItem("username") != null) {
                        $rootScope.currentUser = sessionStorage.getItem("name");
                        return true;
                    } else {
                        return false;
                    }
                };
                
                $rootScope.hasPermissions = function () {
                    if (($rootScope.isAuthenticated) && (roles.indexOf(sessionStorage.getItem("rol")) > -1)) {
                        return true;
                    } else {
                        return false;
                    }
                };


                if (requireLogin && (sessionStorage.getItem("username") === null)) {
                    event.preventDefault();
                    $state.go('login', $state.params);
                }

            });

        }]);
    
    
    
})(window.angular);
//