Ext.define('QuotaKPI.view.ImportView' ,{
    extend: 'Ext.tab.Panel',
    alias : 'widget.ImportView',
    items:[	 
			{xtype: 'ImportCompany',title: 'Import company', html: 'import company'},
			{xtype: 'ImportBillTo',title: 'Import bill to', html: 'import bill to'},
			{xtype: 'ImportShipTo',title: 'Import ship to', html: 'import ship to'},
			{xtype: 'ImportMachine',title: 'Import machine', html: 'import machine'},
			{xtype: 'ImportDealer',title: 'Import Dealer', html: 'import dealer'},
			{xtype: 'ImportInvoice',title: 'Import Invoice', html: 'import invoice'}
    ]

});


