Ext.define('QuotaKPI.model.company.ProductLineModel', {
    extend: 'Ext.data.Model',
//fields: ['salesRepresentativeId', 'managerId', 'code', 'description', 'exists'] //, type: 'boolean'    
    fields: [	{name: 'salesRepresentativeId'},
				{name: 'managerId'},
				{name: 'code'},
				{name: 'description'},
				{name: 'exists'}
        	]
});

