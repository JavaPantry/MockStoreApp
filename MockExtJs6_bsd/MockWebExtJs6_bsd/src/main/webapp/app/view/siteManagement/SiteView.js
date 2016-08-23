Ext.define('QuotaKPI.view.siteManagement.SiteView' ,{
	extend: 'Ext.tab.Panel',
    alias : 'widget.SiteView',
    layout: 'anchor',

    items:[	
		{xtype:'SiteGrid',anchor: '100% 100%'}
    ]
});