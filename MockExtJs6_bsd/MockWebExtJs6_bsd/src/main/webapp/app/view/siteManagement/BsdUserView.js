Ext.define('QuotaKPI.view.siteManagement.BsdUserView' ,{
    extend: 'Ext.Panel',
    alias : 'widget.BsdUserView',
    layout: 'card',
    items:[	
		{xtype:'BsdUserGrid',anchor: '100% 100%'}
		,{xtype:'BsdUserForm',anchor: '100% 100%'}
    ]
});