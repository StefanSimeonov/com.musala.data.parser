app.controller('SimpleQueryController', function($scope, $http, $rootScope) {
	if (typeof $rootScope.lastInvokedStep != 'undefined' && $rootScope.lastInvokedStep != 2) {
		console.log("redirecting to index.html");
		window.location.replace('index.html#');
	}
	$scope.connect = function() {
		// Logging retrieved query types
		console.log($scope.queriesType);
		$http({
			url : BACKEND_URL,
			method : BACKEND_REQUEST_TYPE_POST,
			headers : {
				'Content-Type' : 'application/json'
			},
			data : {
				'queriesType' : $scope.queriesType,
				'tableName' : $scope.tableName,
				'properties' : $scope.properties,
				'funcRequest' : 'second',
			},
		}).then(function(response) {
			$rootScope.lastInvokedStep = 3;
			// Logging response data
			console.log(response.data.status);
			console.log(response.data.message);
			if (response.data.status) {
				$scope.trueMessage = response.data.message;
				$('#init-failure-box').hide();
				$('#init-success-box').show();
				setTimeout(function() {
					window.location.replace('index.html#/extended-querying');
				}, 2000);

			} else {
				$scope.falseMessage = response.data.message;
				$('#init-success-box').hide();
				$('#init-failure-box').show();
				$rootScope.lastInvokedStep = 1;
				setTimeout(function() {
					window.location.replace('index.html#/initialization');
				}, 2000);
			}
		},function(fail){
			$scope.falseMessage="The Server is not able to response";
			$('#init-success-box').hide();
			$('#init-failure-box').show();
		})
	}
});