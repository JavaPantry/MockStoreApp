Ext.define('QuotaKPI.view.siteManagement.StoreView' ,{
    extend: 'Ext.Panel',
    alias : 'widget.StoreView',
    layout: 'card',
    items:[	
		{xtype:'StoreGrid',anchor: '100% 100%'}
		,{xtype:'StoreForm',anchor: '100% 100%'}
		,{xtype:'StoreMangeProductsForm',anchor: '100% 100%'}
    ]
});