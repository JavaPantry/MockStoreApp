Ext.define('QuotaKPI.view.accounting.QuotaView' ,{
    extend: 'Ext.Panel',
    alias : 'widget.QuotaView',
    layout: 'anchor',

    items:[	
		/* This header panel used to display common for all budgets info
		{xtype: 'fieldcontainer',
		combineErrors: false,
		anchor: '100% 5%',
		layout:'hbox',
		defaults: {xtype: 'displayfield',margin:'5',labelPad:2,labelCls:'fast-label'}
		,items: [
		         	
		        	{hidden:true,itemId:'dealerId'}
		        	,{hidden:true,itemId:'Id'}
					,{fieldLabel: 'Dealer Code',itemId:'dealerCode',labelWidth:80}
					,{fieldLabel:'Dealer Description',itemId:'dealerName',labelWidth:120}
					,{fieldLabel:'Year',itemId:'Year',labelWidth:40}
				]
		},*/
		{xtype:'QuotaGrid',anchor: '100% 100%'}
    ]
});