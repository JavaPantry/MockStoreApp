Ext.define('QuotaKPI.model.siteManagement.ProductPriceInStore', {
    extend: 'Ext.data.Model',
	fields: [
	     {name: 'sku', type: 'string'}
         ,{name: 'EProductName', type: 'string'}
         ,{name: 'EProductDescription', type: 'string'}
         ,{name: 'price', type: 'number'}
         
         ,{name: 'priceScheduled', type: 'number'}
         ,{name: 'priceSchedule', type: 'date', dateFormat:'m-d-Y'}
     ]
//QuotaKPI.model.siteManagement.ProductPriceInStore: validations has been deprecated. Please use validators instead.
	/*,validators: [
	     {type: 'presence',  field: 'price'}
	     ]*/
});