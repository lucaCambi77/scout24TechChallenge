import angular from 'angular';

angular
    .module('scout24')
    .controller(
        'MainCtrl',
        function ($http, constant) {

            var main = this;
            main.errors = [];

            main.submit = function () {

                var testUrl = {
                    url: undefined
                };
                testUrl.url = main.url;

                /*
                 * This could be in a factory
                 */
                $http
                    .post('http://localhost:8080/urlContent',
                        testUrl, constant.JSONCONTENTTYPE)
                    .then(
                        /*
                         * rest Success
                         */
                        function (response) {

                            main.errors = [];

                            main.urlContent = response.data.entity;
                            console.log(response.data.entity);
                            main.hyperLinksMap = {}

                            /*
                             * Create hyperlinks map from list
                             * of map retrieved from backend
                             */
                            for (var element in main.urlContent.hyperLinksMap) {

                                for (var key in main.urlContent.hyperLinksMap[element]) {
                                    if (main.urlContent.hyperLinksMap[element]
                                        .hasOwnProperty(key)) {

                                        main.hyperLinksMap[key] = main.urlContent.hyperLinksMap[element][key];
                                    }
                                }
                            }

                        },
                        /*
                         * rest Error
                         */
                        function (response) {
                            for (var element in response.data.errorMessage) {

                                main.errors
                                    .push(response.data.errorMessage[element]);
                            }

                        }, function (value) {

                        })
            }

        })