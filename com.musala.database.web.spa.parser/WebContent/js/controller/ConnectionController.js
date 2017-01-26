app.controller('ConnectionController', function($scope, $http, $rootScope) {
	// Setting default input values
	$scope.serverName = 'localhost';
	$scope.databaseName = 'schools';
	$scope.userName = 'root';
	$scope.password = '';
	$scope.connect = function() {
		$http({
			url : BACKEND_URL,
			method : BACKEND_REQUEST_TYPE_POST,
			headers : {
				'Content-Type' : 'application/json'
			},
			data : {
				'serverName' : $scope.serverName,
				'databaseName' : $scope.databaseName,
				'username' : $scope.userName,
				'password' : $scope.password,
				'funcRequest' : 'first'
			},
		}).then(function(response) {
			// Logging response data status
			console.log(response.data.status);
			if (response.data.status) {
				$scope.trueMessage = response.data.status + ' operation';
				$('#init-failure-box').hide();
				$('#init-success-box').show();

			} else {
				$scope.falseMessage = response.data.status + ' operation';
				$('#init-success-box').hide();
				$('#init-failure-box').show();
			}
			if (response.data.status) {
				$rootScope.lastInvokedStep = 2;
				setTimeout(function() {
					window.location.replace('index.html#/simple-querying');
				}, 1000);
			}
		})
	}
});
