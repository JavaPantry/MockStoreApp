Ext.define('QuotaKPI.controller.siteManagement.SiteManagementController', {
    extend: 'Ext.app.Controller',

    stores: ['siteManagement.Site'
             ,'siteManagement.Product'
             ,'siteManagement.BsdUser'
             ],
    models: ['siteManagement.SiteModel'],
    views: ['siteManagement.SiteView'
            ,'siteManagement.SiteGrid'
            ,'siteManagement.ProductGrid'
            ,'siteManagement.BsdUserView'
            ,'siteManagement.BsdUserGrid'
            ,'siteManagement.BsdUserForm'
            ],
    refs: [  {ref: 'SiteView',		selector: 'SiteView'}
			,{ref: 'SiteGrid',		selector: 'SiteGrid'}
			,{ref: 'ProductGrid',	selector: 'ProductGrid'}
			,{ref: 'BsdUserGrid',	selector: 'BsdUserGrid'}
			,{ref: 'BsdUserForm',	selector: 'BsdUserForm'}
			,{ref: 'BsdUserView',	selector: 'BsdUserView'}
			],

    init: function() {
        this.control({
        	'BsdUserGrid button[action=bsdUserCreate]'		:	{click: this.bsdUserCreate}
//        	,'QuotaGrid button[action=bsdUserDetailEditCancel]'	:	{click: this.bsdUserDetailEditCancel}
        });
    },
	
    bsdUserCreate: function() {
		
	},
	
	bsdUserDetailEditCancel: function() {
		
	}
});






