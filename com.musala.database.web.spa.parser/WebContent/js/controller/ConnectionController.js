app.controller('ConnectionController', function($scope, $http) {
	//Setting default input values
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
			console.log(response.data.status)
			$scope.message = response.data.status + ' operation';
			if (response.data.status) {
				setTimeout(function() {
					window.location.replace('index.html#/simple-querying');
				}, 1000);
			}
		})
	}
});