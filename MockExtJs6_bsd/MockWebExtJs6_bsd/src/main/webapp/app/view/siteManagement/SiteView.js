Ext.define('QuotaKPI.view.siteManagement.SiteView' ,{
	extend: 'Ext.tab.Panel',
    alias : 'widget.SiteView',
    items:[	
		{iconCls: 'icon-organisation', title: 'Bsd Site Admin', xtype:'SiteGrid'}
		,{iconCls: 'icon-organisation', title: 'Products', xtype:'ProductGrid'}
		,{iconCls: 'icon-organisation', title: 'Bsd Store Admin', xtype:'panel'}
		,{iconCls: 'icon-organisation', title: 'Users', xtype:'panel'}
    ]
});