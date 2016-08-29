Ext.define('QuotaKPI.model.siteManagement.ProductPriceInStore', {
    extend: 'Ext.data.Model',
	fields: [
	     {name: 'sku', type: 'string'}
         ,{name: 'EProductName', type: 'string'}
         ,{name: 'EProductDescription', type: 'string'}
         ,{name: 'price', type: 'number'}
     ]
	,validations: [
	     {type: 'presence',  field: 'price'}
	     ]
});