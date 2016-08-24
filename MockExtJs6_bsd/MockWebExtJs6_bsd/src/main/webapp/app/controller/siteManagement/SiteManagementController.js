Ext.define('QuotaKPI.controller.siteManagement.SiteManagementController', {
    extend: 'Ext.app.Controller',

    stores: ['siteManagement.Site'
             ,'siteManagement.Product'
             ],
    models: ['siteManagement.SiteModel'],
    views: ['siteManagement.SiteView'
            ,'siteManagement.SiteGrid'
            ,'siteManagement.ProductGrid'
            ],
    refs: [  {ref: 'SiteView',		selector: 'SiteView'}
			,{ref: 'SiteGrid',		selector: 'SiteGrid'}
			//,{ref: 'ProductGrid',		selector: 'ProductGrid'}
			],

    init: function() {
        this.control({
//        	'QuotaGrid button[action=quotaDetailSave]'		:	{click: this.quotaDetailSave},
//        	'QuotaGrid button[action=quotaDetailCreate]'	:	{click: this.quotaDetailCreate}
        });
    }
});






