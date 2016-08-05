app.factory("Product", function ($resource) {
	return $resource(_contextPath_+'/bsd/products', {id: '@id'}, {
		update: {
			method: 'PUT'
		}
	});
});
