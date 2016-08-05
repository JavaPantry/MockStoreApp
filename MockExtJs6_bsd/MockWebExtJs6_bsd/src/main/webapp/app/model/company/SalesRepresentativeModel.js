Ext.define('QuotaKPI.model.company.SalesRepresentativeModel', {
    extend: 'Ext.data.Model',
    fields: [	{name: 'salesRepresentativeId'},
				{name: 'salesRepresentativeName'},
				{name: 'userId',mapping:'user.employeeId'},
				{name: 'userName',mapping:'user.name'},
				{name: 'managerId',mapping:'managers[0].employeeId'},//.managerId
				{name: 'managerName',mapping:'managers[0].name'},
				{name: 'managers',mapping:'managers'},
				{name: 'tocId',mapping:'tocs[0].tocId'},
				{name: 'tocName',mapping:'tocs[0].tocName'},
				{name: 'tocs',mapping:'tocs'},
				{name: 'allAccess'}
        	]
});

