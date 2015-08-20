angular.module('app.controllers').controller('LoginCtrl', ['$scope', '$http', '$location', function($scope, $http, $location) {

    $scope.feedback = '';

    $scope.loginUrl = '/login'

    $scope.successUrl = '/home';

    $scope.postLoginForm = function() {
        var data = {
            username : $scope.username,
            password : $scope.password
        };
        $http.post(loginUrl, data).success(function(data, status, headers, config) {
            if (data.valid) {
                $location.path($scope.successUrl);
            } else {
                $scope.feedback = "Invalid username / password. Try again.";
            }
        }).error(function(data, status, headers, config) {
            $scope.feedback = 'error: ' + data + ", " + status;
        });
    };
}]);