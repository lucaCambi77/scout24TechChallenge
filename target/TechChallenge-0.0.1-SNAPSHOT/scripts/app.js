'use strict';

/**
 * @ngdoc overview
 * @name scout24
 * @description # scout24 tech challenge
 * 
 * Main module of the application.
 */
angular.module(
		'scout24',
		[ // module for translations, see translations.js
		'ngCookies', 'ngResource', 'ngRoute', 'ngSanitize', 'ngTouch',
				'ui.bootstrap', 'angular-loading-bar', 'ngAnimate', ]).config(
		function($routeProvider, $httpProvider) {

			delete $httpProvider.defaults.headers.common['X-Requested-With'];
			$routeProvider.when('/', {
				templateUrl : 'views/main.html',
				controller : 'MainCtrl',
				controllerAs : 'main'
			}).when('/about', {
				templateUrl : 'views/about.html',
				controller : 'AboutCtrl',
				controllerAs : 'about'
			}).otherwise({
				redirectTo : '/'
			});
		}).constant("constant", {

	// Content Type
	'JSONCONTENTTYPE' : {
		headers : {
			'Content-Type' : 'application/json;'
		}
	}
});
