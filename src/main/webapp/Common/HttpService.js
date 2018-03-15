angular
    .module('myApp')
    .factory('HttpService', HttpService);

HttpService.$inject = ['$http', '$q', '$location'];

function HttpService($http, $q, $location) {
    var vm = this;
    vm.url = $location.absUrl().split('#')[0];

    return {
        //FUNCTION THAT HANDLES THE GET REQUEST
        get: function (resourceURI) {
            return $http.get(resourceURI)
                .then(
                    function (resp) {
                        return resp.data;
                    },
                    function (err) {
                        return $q.reject(err);
                    }
                );
        },

        //FUNCTION THAT HANDLES THE POST OPERATION
        post: function (resourceURI, data) {
            return $http.post(resourceURI, data)
                .then(
                    function (resp) {
                        return resp.data;
                    },
                    function (err) {
                        return $q.reject(err);
                    }
                );
        },
        //FUNCTION THAT HANDLES THE UPDATE OPERATION
        put: function (resourceURI, user, id) {
            return $http.put(resourceURI+id, user)
                .then(
                    function (resp) {
                        return resp.data;
                    },
                    function (err) {
                        return $q.reject(err);
                    }
                );
        },

        //FUNCTION THAT HANDLES THE GET REQUEST FOR SEARCHRESULTS SERVICE
        getSearchResults: function (resourceURI, searchTxt, country) {
            return $http.get(resourceURI+country+ '/' +searchTxt)
                .then(
                    function (resp) {
                        return resp.data;
                    },
                    function (err) {
                        return $q.reject(err);
                    }
                );
        },

        //FUNCTION THAT HANDLES THE IMAGE POST OPERATION
        postProfilePic: function (resourceURI, username, fileType, image) {
            return $http.post(resourceURI+username+'/'+fileType, image)
                .then(
                    function (resp) {
                        return resp.data;
                    },
                    function (err) {
                        return $q.reject(err);
                    }
                );
        }
    }

}
