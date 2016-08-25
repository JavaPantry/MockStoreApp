Ext.define('QuotaKPI.model.siteManagement.StoreModel', {
    extend: 'Ext.data.Model',
	fields: [
	     {name: 'id', type: 'number'},
         {name: 'storeName', type: 'string'},
         {name: 'clientName', type: 'string'},
         {name: 'storeDescription', type: 'string'}
     ]
});