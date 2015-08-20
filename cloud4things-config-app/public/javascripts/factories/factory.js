angular.module('app.factories').factory('SelectedConfiguration', function() {

    var selectedConfiguration = {};

    selectedConfiguration.set = function(id) {
        selectedConfiguration.id = id;
    };

    selectedConfiguration.get = function() {
        return selectedConfiguration.id;
    };

    return selectedConfiguration;
});