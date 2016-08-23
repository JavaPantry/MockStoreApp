Ext.define('QuotaKPI.controller.siteManagement.SiteManagementController', {
    extend: 'Ext.app.Controller',

    stores: ['siteManagement.Site'],
    models: ['siteManagement.SiteModel'],
    views: ['siteManagement.SiteView','siteManagement.SiteGrid'],
    refs: [  {ref: 'SiteView',		selector: 'SiteView'}
			,{ref: 'SiteGrid',		selector: 'SiteGrid'}],

    init: function() {
        this.control({
//        	'QuotaGrid button[action=quotaDetailSave]'		:	{click: this.quotaDetailSave},
//        	'QuotaGrid button[action=quotaDetailCreate]'	:	{click: this.quotaDetailCreate}
        });
    }

    
});






