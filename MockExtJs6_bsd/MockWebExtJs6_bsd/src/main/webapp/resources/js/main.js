var app = angular.module('codecraft', [
	'ngResource',
	'infinite-scroll',
	'angularSpinner',
	'jcs-autoValidate',
	'angular-ladda',
	'mgcrea.ngStrap',
	'toaster',
	'ngAnimate',
	'ui.router'
]);


app.config(function ($stateProvider, $urlRouterProvider) {
	$stateProvider
		.state('list', {
			url: "/",
			views: {
				'main': {
					templateUrl: _contextPath_+'/resources/js/templates/productList.html',
					controller: 'ProductListController'
				}/*,
				'search': {
					templateUrl: 'resources/js/templates/searchform.html',
					controller: 'PersonListController'
				}*/
			}
		})
		.state('orderlist', {
			url: "/",
			views: {
				'main': {
					templateUrl: _contextPath_+'/resources/js/templates/orderList.html',
					controller: 'OrderListController'
				}/*,
				'search': {
					templateUrl: 'resources/js/templates/searchform.html',
					controller: 'PersonListController'
				}*/
			}
		})
		/*.state('edit', {
			url: "/edit/:email",
			views: {
				'main': {
					templateUrl: 'templates/edit.html',
					controller: 'PersonDetailController'
				}
			}
		})
		.state('create', {
			url: "/create",
			views: {
				'main': {
					templateUrl: 'templates/edit.html',
					controller: 'PersonCreateController'
				}
			}
		})*/;

	$urlRouterProvider.otherwise('/');
});
app.config(function ($httpProvider, $resourceProvider, laddaProvider, $datepickerProvider) {
	$httpProvider.defaults.headers.common['Authorization'] = 'Token 6774df39a8c8e282ff86b652f4e1dd095e980e0a';
	$resourceProvider.defaults.stripTrailingSlashes = false;
	laddaProvider.setOption({
		style: 'expand-right'
	});
	angular.extend($datepickerProvider.defaults, {
		dateFormat: 'd/M/yyyy',
		autoclose: true
	});
});

/*app.factory("Product", function ($resource) {
	return $resource(_contextPath_+'/bsd/products', {id: '@id'}, {
		update: {
			method: 'PUT'
		}
	});
});
*/
/*app.factory("Order", function ($resource) {
	return $resource(_contextPath_+'/bsd/orders', {id: '@id'}, {
		update: {
			method: 'PUT'
		}
	});
});
*/
