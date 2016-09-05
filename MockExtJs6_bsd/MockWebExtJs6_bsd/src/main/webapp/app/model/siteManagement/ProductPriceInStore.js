/*GsonTest.testClientSerialization() productPriceInStore1 = 
{
  "pk": {
    "product": {
      "sku": "tst1",
      "EProductName": "test product",
      "FProductName": "test product (fr)"
    }
  },
  "price": 9.99,
  "priceScheduled": 15.99,
  "priceSchedule": 1473009955015
}*/
Ext.define('QuotaKPI.model.siteManagement.ProductPriceInStore', {
    extend: 'Ext.data.Model',
	fields: [
	     {name: 'sku', type: 'string',mapping:'pk.product.sku'}
         ,{name: 'EProductName', type: 'string',mapping:'pk.product.EProductName'}
         ,{name: 'EProductDescription', type: 'string',mapping:'pk.product.EProductDescription'}

         ,{name: 'price', type: 'number'}
         ,{name: 'priceScheduled', type: 'number'}
         ,{name: 'priceSchedule', type: 'date', dateFormat:'m-d-Y'}
     ]
//QuotaKPI.model.siteManagement.ProductPriceInStore: validations has been deprecated. Please use validators instead.
	/*,validators: [
	     {type: 'presence',  field: 'price'}
	     ]*/
});