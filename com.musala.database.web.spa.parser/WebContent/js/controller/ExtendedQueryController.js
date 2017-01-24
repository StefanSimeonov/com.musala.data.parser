app.controller('ExtendedQueryController', function($scope, $http,$rootScope) {
	if($rootScope.lastInvokedStep!=3){
		window.location.replace('index.html#');
	}
	$scope.connect = function() {
		// Logging scope data
		console.log($scope.id);
		console.log($scope.name);
		$http({
			url : BACKEND_URL,
			method : BACKEND_REQUEST_TYPE_POST,
			headers : {
				'Content-Type' : 'application/json'
			},
			data : {
				'id' : $scope.id,
				'name' : $scope.name,
				'funcRequest' : 'third',
			},
		}).then(function(response) {
			// Logging response data
			console.log(response.data.status);
			console.log(response.data.message);
			$scope.message = response.data.message;
			$('#message').show();
			if (response.data.status == false) {
				$rootScope.lastInvokedStep=1;
				setTimeout(function() {
					window.location.replace('index.html#/initialization');
				}, 2000);
			}
		})
	}
});