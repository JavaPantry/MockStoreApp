Ext.define('QuotaKPI.model.accounting.QuotaModel', {
    extend: 'Ext.data.Model',
	fields: [
	     {name: 'id', type: 'number'},
         {name: 'salesRepresentative.salesRepresentativeId', type: 'string', mapping:'salesRepresentativeId'},
         {name: 'salesRepresentative.salesRepresentativeName', type: 'string', mapping:'salesRepresentativeName'},
         {name: 'category.categoryId', type: 'string', mapping:'categoryId'},
         {name: 'category.categoryName', type: 'string', mapping:'categoryName'},
         {name: 'amountType', type: 'number'},
         {name: 'year', type: 'number'},

         {name: 'value1', type: 'number'},
         {name: 'value2', type: 'number'},
         {name: 'value3', type: 'number'},
         {name: 'value4', type: 'number'},
         {name: 'value5', type: 'number'},
         {name: 'value6', type: 'number'},
         {name: 'value7', type: 'number'},
         {name: 'value8', type: 'number'},
         {name: 'value9', type: 'number'},
         {name: 'value10', type: 'number'},
         {name: 'value11', type: 'number'},
         {name: 'value12', type: 'number'},
         {name: 'total', type: 'number'}
     ]
});