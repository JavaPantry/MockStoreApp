Ext.define('QuotaKPI.view.accounting.BudgetView' ,{
    extend: 'Ext.Panel',
    alias : 'widget.BudgetView',
    layout: 'anchor',

    items:[
		/* This header panel used to display common for all budgets info
		{xtype: 'fieldcontainer',
		combineErrors: false,
		anchor: '100% 5%',
		layout:'hbox',
		defaults: {xtype: 'displayfield',margin:'5',labelPad:2,labelCls:'fast-label'}
		,items: [    
					{hidden:true,fieldLabel:'Id',itemId:'Id',labelWidth:40}
					,{fieldLabel:'Year',itemId:'Year',labelWidth:40}
				]
		},*/
		{xtype:'BudgetGrid',anchor: '100% 100%'}
    ]
});