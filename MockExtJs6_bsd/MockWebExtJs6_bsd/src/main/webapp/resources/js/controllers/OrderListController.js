app.controller('OrderListController', function ($scope, $modal, OrderService) {

	$scope.search = "";
	$scope.order = "email";
	$scope.ordersSrv = OrderService;

	$scope.loadMore = function () {
		console.log("Load More!!!");
		$scope.ordersSrv.loadMore();
	};
	
	//'${pageContext.request.contextPath}/resources/js/
	$scope.showCreateModal = function () {
		$scope.ordersSrv.selectedProduct = {};
		$scope.createModal = $modal({
			scope: $scope,
			template: 'resources/js/templates/modal.create.tpl.html',
			show: true
		})
	};

	$scope.createOrder = function () {
		console.log("createOrder");
		$scope.ordersSrv.createOrder($scope.supplies.selectedOrder)
			.then(function () {
				$scope.createModal.hide();
			})
	};

	$scope.$watch('search', function (newVal, oldVal) {
		if (angular.isDefined(newVal)) {
			$scope.ordersSrv.doSearch(newVal);
		}
	})

	$scope.$watch('order', function (newVal, oldVal) {
		if (angular.isDefined(newVal)) {
			$scope.ordersSrv.doOrder(newVal);
		}
	})

});

