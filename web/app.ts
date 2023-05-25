import angular from 'angular';

angular.module(
    'scout24', ['ngRoute']).config(
    function ($routeProvider, $httpProvider) {

        delete $httpProvider.defaults.headers.common['X-Requested-With'];
        $routeProvider.when('/', {
            templateUrl: 'main.html',
            controller: 'MainCtrl',
            controllerAs: 'main'
        }).when('/about', {
            templateUrl: 'about.html',
            controller: 'AboutCtrl',
            controllerAs: 'about'
        }).otherwise({
            redirectTo: '/'
        });
    }).constant("constant", {

    // Content Type
    'JSONCONTENTTYPE': {
        headers: {
            'Content-Type': 'application/json;'
        }
    }
});
