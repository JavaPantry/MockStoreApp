app.service('OrderService', function (Order, $q, toaster) {


	var self = {
		'addOrder': function (order) {
			this.orders.push(order);
		},
		'page': 1,
		'hasMore': true,
		'isLoading': false,
		'isSaving': false,
		'selectedOrder': null,
		'orders': [],
		'search': null,
		'doSearch': function (search) {
			self.hasMore = true;
			self.page = 1;
			self.orders = [];
			self.search = search;
			self.loadOrders();
		},
		'doOrder': function (order) {
			self.hasMore = true;
			self.page = 1;
			self.orders = [];
			self.ordering = order;
			self.loadOrders();
		},
		'loadOrders': function () {
			if (self.hasMore && !self.isLoading) {
				self.isLoading = true;

				var params = {
					'page': self.page,
					'search': self.search,
					'ordering': self.ordering
				};

				Order.get(params, function (response) {
					console.log('order loading');
					console.log(response);
					angular.forEach(response.data, function (order) {
						self.orders.push(new Order(order));
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
				self.loadOrders();
			}
		},
		'updateProduct': function (order) {
			console.log("Service Called Update");
			self.isSaving = true;
			order.$update().then(function () {
				self.isSaving = false;
				toaster.pop('success', 'Updated ' + order.name);
			});
		},
		'removeProduct': function (order) {
			self.isDeleting = true;
			order.$remove().then(function () {
				self.isDeleting = false;
				var index = self.orders.indexOf(order);
				self.orders.splice(index, 1);
				self.selectedOrder = null;
				toaster.pop('success', 'Deleted ' + order.name);
			});
		},
		'createOrder': function (order) {
			var d = $q.defer();
			self.isSaving = true;
			Order.save(order).$promise.then(function () {
				self.isSaving = false;
				self.selectedOrder = null;
				self.hasMore = true;
				self.page = 1;
				self.orders = [];
				self.loadOrders();
				toaster.pop('success', 'Created ' + order.name);
				d.resolve()
			});
			return d.promise;
		}


	};

	self.loadOrders();

	return self;

});