app.run(function($rootScope){
	$rootScope.lastInvokedStep=1;
});
app.config(function($routeProvider) {
	$routeProvider.when('/', {
		template : '<p>Click on start <abbr title="The link above"> Database Quering <abbr>button to start</p>'
	}).when('/initialization', {
		templateUrl : 'templates/initialization-templ.html',
		controller : 'ConnectionController'
	}).when('/simple-querying', {
		templateUrl : 'templates/simple-querying-templ.html',
		cotroller : 'SimpleQueryController'
	}).when('/extended-querying', {
		templateUrl : 'templates/extended-querying-templ.html',
		cotroller : 'ExtendedQueryController'
	});
});