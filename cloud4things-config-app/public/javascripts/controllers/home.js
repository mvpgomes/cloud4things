angular.module('app.controllers').controller('HomeCtrl', ['$scope', '$http', '$location', function($scope, $http, $location) {

    $scope.getSmartPlacesUrl = '@routes.HomeController.home';

    $scope.createSmartPlaceUrl = '/create';

    $scope.smart_places = []

    $http.get(getSmartPlacesUrl, {cache: true})
        .success(function(data, status, headers, config){
            $scope.smart_places = data;
    }).error(function(data, status, headers, config){

    });

    $scope.deploySmartPlaceConfiguration = function() {

    }

    $scope.editSmartPlaceConfiguration = function() {

    }

    $scope.deleteSmartPlaceConfiguration = function() {

    }

    $scope.createSmartPlaceConfiguration = function() {
        $location = $scope.createSmartPlaceUrl;
    };
}]);