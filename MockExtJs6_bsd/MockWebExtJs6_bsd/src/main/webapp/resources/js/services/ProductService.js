app.service('ProductService', function (Product, $q, toaster) {


	var self = {
		'addProduct': function (product) {
			this.products.push(product);
		},
		'page': 1,
		'hasMore': true,
		'isLoading': false,
		'isSaving': false,
		'selectedProduct': null,
		'products': [],
		'search': null,
		'doSearch': function (search) {
			self.hasMore = true;
			self.page = 1;
			self.products = [];
			self.search = search;
			self.loadProducts();
		},
		'doOrder': function (order) {
			self.hasMore = true;
			self.page = 1;
			self.products = [];
			self.ordering = order;
			self.loadProducts();
		},
		'loadProducts': function () {
			if (self.hasMore && !self.isLoading) {
				self.isLoading = true;

				var params = {
					'page': self.page,
					'search': self.search,
					'ordering': self.ordering
				};

				Product.get(params, function (response) {
					console.log('product loading');
					console.log(response);
					//angular.forEach(data.results, function (product) {
					angular.forEach(response.data, function (product) {
						self.products.push(new Product(product));
					});

/*					if (!data.next) {
						self.hasMore = false;
					}
*/					
					self.isLoading = false;
				});
			}

		},
		'loadMore': function () {
			if (self.hasMore && !self.isLoading) {
				self.page += 1;
				self.loadProducts();
			}
		},
		'updateProduct': function (product) {
			console.log("Service Called Update");
			self.isSaving = true;
			product.$update().then(function () {
				self.isSaving = false;
				toaster.pop('success', 'Updated ' + person.name);
			});
		},
		'removeProduct': function (person) {
			self.isDeleting = true;
			person.$remove().then(function () {
				self.isDeleting = false;
				var index = self.products.indexOf(person);
				self.products.splice(index, 1);
				self.selectedProduct = null;
				toaster.pop('success', 'Deleted ' + product.name);
			});
		},
		'createProduct': function (product) {
			var d = $q.defer();
			self.isSaving = true;
			Product.save(product).$promise.then(function () {
				self.isSaving = false;
				self.selectedProduct = null;
				self.hasMore = true;
				self.page = 1;
				self.products = [];
				self.loadProducts();
				toaster.pop('success', 'Created ' + product.name);
				d.resolve()
			});
			return d.promise;
		}


	};

	self.loadProducts();

	return self;

});