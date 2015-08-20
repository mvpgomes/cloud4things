var app = angular.module('app', ['ngRoute', 'ngResource'])
    .constant("apiUrl", "/api")
    .config(["$routeProvider", function($routeProvider) {
        return $routeProvider.when("/", {
            templateUrl: "/views/login",
            controller: "LoginCtrl"
        }).when("/home", {
            templateUrl: "/views/home",
            controller: "HomeCtrl"
        }).when("/create", {
            templateUrl: "/views/create",
            controller: "CreateCtrl"
        }).when("/view/:id", {
            templateUrl: "/views/view",
            controller: "ViewCtrl"
        }).otherwise({
            redirectTo: "/"
        });
    }
    ])
    .config(["$locationProvider", function($locationProvider) {
        return $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        }).hashPrefix("!"); // enable the new HTML5 routing and history API
    }
    ]);


app.factory('selectedConfigurationFactory', function() {

    var selectedConfiguration = null;

    return {

        set : function(id) {
            selectedConfiguration = id;
        },
        get : function() {
            return selectedConfiguration;
        }
    };
});

// the global controller
app.controller("AppCtrl", ["$scope", "$location", function($scope, $location) {
    // the very sweet go function is inherited by all other controllers
    $scope.go = function (path) {
        $location.path(path);
    };
}]);

function LoginCtrl($scope, $http, apiUrl) {

    $scope.feedback = '';

    $scope.loginUrl = '/login'

    $scope.successUrl = '/home';

    $scope.postLoginForm = function() {
        var data = {
            username : $scope.username,
            password : $scope.password
        };
        $http.post(apiUrl + $scope.loginUrl, data).success(function(data, status, headers, config) {
            if (data.valid) {
                $scope.go($scope.successUrl);
            } else {
                $scope.feedback = "Invalid username / password. Try again.";
            }
        }).error(function(data, status, headers, config) {
            $scope.feedback = 'error: ' + data + ", " + status;
        });
    };
}

function HomeCtrl($scope, $http, apiUrl, selectedConfigurationFactory) {

    $scope.smartPlacesUrl = '/home';

    $scope.createSmartPlaceUrl = '/create';

    $scope.viewSmartPlaceUrl = '/view/';

    $scope.smart_places = {};

    console.log(selectedConfigurationFactory);

    $http.get(apiUrl + $scope.smartPlacesUrl)
        .success(function(data, status, headers, config){
            $scope.smart_places = data.smart_places;
        }).error(function(data, status, headers, config){

        });

    $scope.viewSmartPlaceConfiguration = function(smartPlaceId) {
        selectedConfigurationFactory.set(smartPlaceId);
        $scope.go($scope.viewSmartPlaceUrl + smartPlaceId);
    }

    $scope.createSmartPlaceConfiguration = function() {
        $scope.go($scope.createSmartPlaceUrl);
    };
}

function CreateCtrl($scope, $http, apiUrl) {

    $scope.createUrl = '/create';

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
            reader_name : $scope.reader_name,
            reader_description : $scope.reader_description
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
        $scope.go($scope.homeUrl);
    }

    $scope.createSmartPlaceConfiguration = function () {
        $http.post(apiUrl + $scope.createUrl, $scope.smart_place).success(function (data, status, headers, config) {
            $scope.smart_place = {};
            $scope.go($scope.homeUrl);
        }).error(function (data, status, headers, success){
            console.log("Request Failed.");
        });
    }
}

function ViewCtrl($scope, $http, apiUrl, selectedConfigurationFactory) {

    $scope.smartPlacesUrl = '/home';

    $scope.viewSmartPlaceUrl = '/view/'

    $scope.selectedConfiguration = selectedConfigurationFactory.get();

    console.log($scope.selectedConfiguration);

    $scope.smart_place = {};

    $http.get(apiUrl + $scope.viewSmartPlaceUrl + $scope.selectedConfiguration).success(function (data, status, headers, config) {
        console.log(data);
        $scope.smart_place = data;
    }).error(function (data, status, headers, config) {

    })

    $scope.goToHomePage = function () {
        $scope.go($scope.homeUrl);
    }

    $scope.deploySmartPlaceConfiguration = function() {

    }

    $scope.editSmartPlaceConfiguration = function() {

    }

    $scope.deleteSmartPlaceConfiguration = function() {

    }

}