Ext.define('QuotaKPI.view.siteManagement.ProductView' ,{
    extend: 'Ext.Panel',
    alias : 'widget.ProductView',
    layout: 'card',
    items:[	
		{xtype:'ProductGrid',anchor: '100% 100%'}
		,{xtype:'ProductForm',anchor: '100% 100%'}
    ]
});