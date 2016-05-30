var atmApp = angular.module('atmApplication', []);

atmApp.controller('atmController', function($scope, $http) {
	$scope.city = "";
	$scope.atms = undefined;

	var loadResults = function() {
		var url = "atm/";

		if ($scope.type == "option-1" && $scope.city != "") {
			url = url + $scope.city + "/true";
		} else if ($scope.type == "option-2" && $scope.city != "") {
			url = url + $scope.city + "/false";
		} else if ($scope.city != "") {
			url = url + $scope.city;
		}

		alert(url);
		$http.get(url).success(function(data) {
			console.log(data);
			$scope.atms = data;
		});
	}

	$scope.updateResults = function() {
		loadResults();
	}

});