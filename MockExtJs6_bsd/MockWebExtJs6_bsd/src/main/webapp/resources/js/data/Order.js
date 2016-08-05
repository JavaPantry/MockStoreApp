app.factory("Order", function ($resource) {
	return $resource(_contextPath_+'/bsd/orders', {id: '@id'}, {
		update: {
			method: 'PUT'
		}
	});
});
