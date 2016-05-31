var atmApp = angular.module('atmApplication', []);

atmApp.controller('atmController', function($scope, $http) {
	$scope.city = "";
	$scope.atms = undefined;

	var loadResults = function() {
		var url = "v1/atm";

		if ($scope.api !== undefined && $scope.api === "2") {
			url = "v2/atm";
		}

		if ($scope.type === "option-1" && $scope.city !== "") {
			url = url + "/" + $scope.city + "/true";
		} else if ($scope.type === "option-2" && $scope.city !== "") {
			url = url + "/" + $scope.city + "/false";
		} else if ($scope.city !== "") {
			url = url + "/" + $scope.city;
		}

		$http.get(url).success(function(data) {
			$scope.atms = data;
		});
	}

	$scope.updateResults = function() {
		loadResults();
	}

});