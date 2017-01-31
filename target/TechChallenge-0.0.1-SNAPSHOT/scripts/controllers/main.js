'use strict';

/**
 * @ngdoc function
 * @name developmentApp.controller:MainCtrl
 * @description # MainCtrl Controller of the developmentApp
 */
angular
		.module('scout24')
		.controller(
				'MainCtrl',
				function($http, $uibModal, $log, $scope, constant) {

					var main = this;
					main.errors = [];

					main.submit = function() {

						var testUrl = {};
						testUrl.url = main.url;

						/*
						 * This could be in a factory
						 */
						$http
								.post('/TechChallenge/rest/urlContent',
										testUrl, constant.JSONCONTENTTYPE)
								.then(
										function(response) {

											main.errors = [];

											main.urlContent = response.data.entity;
											console.log(response.data.entity);
											main.hyperLinksMap = {}

											for ( var element in main.urlContent.hyperLinksMap) {

												for ( var key in main.urlContent.hyperLinksMap[element]) {
													if (main.urlContent.hyperLinksMap[element]
															.hasOwnProperty(key)) {

														main.hyperLinksMap[key] = main.urlContent.hyperLinksMap[element][key];
													}
												}
											}

										},
										function(response) {
											for ( var element in response.data.errorMessage) {

												main.errors
														.push(response.data.errorMessage[element]);
											}

										}, function(value) {

										})
					}

				})