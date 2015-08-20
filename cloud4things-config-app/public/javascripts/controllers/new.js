angular.module('app.controllers').controller('CreateCtrl', ['$scope', '$http', '$location', function($scope, $http, $location) {

    $scope.homeUrl = '/home';

    $scope.smart_place = {
        "name": "",
        "endpoint": "",
        "readers": [],
        "events": []
    };

    $scope.setSmartPlaceName = function () {
        $scope.smart_place["name"] = $scope.name;
    }

    $scope.setSmartPlaceUrl = function () {
        $scope.smart_place["endpoint"] = $scope.endpoint;
    }

    $scope.addReader = function () {
        var reader = {
            reader_name : $scope.reader_name
        };
        $scope.smart_place["readers"].push(reader);
    }

    $scope.addEvent = function () {
        var event = {
            "event_name": $scope.event_name,
            "associated_reader": $scope.associated_reader
        }
        $scope.smart_place["events"].push(event);
    }

    $scope.cancelConfiguration = function () {
        $scope.smart_place = {};
        $location = $scope.homeUrl;
    }

    $scope.createSmartPlaceConfiguration = function (newSmartPlaceUrl) {
        $http.post(newSmartPlaceUrl, $scope.smart_place).success(function(data, status, headers, config) {
            console.log(data.result)
        }).error(function(data, status, headers, success){
            console.log("Request Failed.");
        });
    }
}]);