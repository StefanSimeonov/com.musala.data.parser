app.controller('ExtendedQueryController', function($scope, $http, $rootScope) {
	if ($rootScope.lastInvokedStep != 3) {
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
			if(response.data.status){
				$scope.successMessage = response.data.message;
				$('#init-success-box').show();
			} else {
				$scope.falseMessage = response.data.message;
				$('#init-failure-box').show();
			}
			$rootScope.lastInvokedStep = 1;
			setTimeout(function() {
				window.location.replace('index.html#/initialization');
			}, 2000);
		},function(fail){
			$scope.falseMessage="The Server is not able to response";
			$('#init-success-box').hide();
			$('#init-failure-box').show();
		})
	}
});