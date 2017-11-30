(function (ng) {
    var mod = ng.module("loginModule");
    mod.controller('logoutCtrl', ['$rootScope', '$state', function ($rootScope, $state) {
            if (sessionStorage.getItem("username")) {
                sessionStorage.clear();
            } else {
                $state.go('booksList', {}, {reload: true});
            }
        }
    ]);
    window.setTimeout(function() {
    $(".alert-sucess").fadeTo(500, 0).slideUp(500, function(){
        $(this).remove(); 
    });
    }, 1500);
}
)(window.angular);

