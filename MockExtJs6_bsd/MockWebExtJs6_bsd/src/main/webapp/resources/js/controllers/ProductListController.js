app.controller('ProductDetailController', function ($scope, ProductService) {
	$scope.supplies = ProductService;


	$scope.save = function () {
		$scope.supplies.updateProduct($scope.supplies.selectedProduct)
	};

	$scope.remove = function () {
		$scope.supplies.removeProduct($scope.supplies.selectedProduct)
	}
});

app.controller('ProductListController', function ($scope, $modal, ProductService) {

	$scope.search = "";
	$scope.order = "email";
	$scope.supplies = ProductService;

	$scope.loadMore = function () {
		console.log("Load More!!!");
		$scope.supplies.loadMore();
	};
	
	//'${pageContext.request.contextPath}/resources/js/
	$scope.showCreateModal = function () {
		$scope.supplies.selectedProduct = {};
		$scope.createModal = $modal({
			scope: $scope,
			template: 'resources/js/templates/modal.create.tpl.html',
			show: true
		})
	};

	$scope.createProduct = function () {
		console.log("createProduct");
		$scope.supplies.createProduct($scope.supplies.selectedProduct)
			.then(function () {
				$scope.createModal.hide();
			})
	};

	$scope.$watch('search', function (newVal, oldVal) {
		if (angular.isDefined(newVal)) {
			$scope.supplies.doSearch(newVal);
		}
	})

	$scope.$watch('order', function (newVal, oldVal) {
		if (angular.isDefined(newVal)) {
			$scope.supplies.doOrder(newVal);
		}
	})

});
