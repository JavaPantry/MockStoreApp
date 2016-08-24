Ext.define('QuotaKPI.model.siteManagement.BsdUserModel', {
    extend: 'Ext.data.Model',
	fields: [
	     {name: 'id', type: 'number'},
         {name: 'userId', type: 'string'},
         {name: 'firstName', type: 'string'},
         {name: 'lastName', type: 'string'},
         {name: 'email', type: 'string'}
     ]
});
