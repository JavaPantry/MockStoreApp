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
        	,'BsdUserGrid button[action=bsdUserDetailEditCancel]'	:	{click: this.bsdUserDetailEditCancel}
        });
    },
	
    bsdUserCreate: function(button) {
    	var gridPanel		= this.getBsdUserGrid();
    	//var selectedRecord = Ext.create('QuotaKPI.model.company.SalesRepresentativeModel');
    	//this._editSalesRepDetail(gridPanel, selectedRecord, true);
		var cardPanel = gridPanel.up('panel');
		var cardLayout = cardPanel.getLayout();
		cardLayout.setActiveItem('BsdUserForm'); //either idx=1, varRef or itemId
	},
	
	bsdUserDetailEditCancel: function(button) {
		this._cancelDetailView(button, 'BsdUserGrid');
	},
	_cancelDetailView : function(button, pageItemId) {
    	//var gridPanel	= button.up('panel');
		var cardPanel 	= button.up('panel');//gridPanel.up('panel');
		var cardLayout 	= cardPanel.getLayout();
		cardLayout.setActiveItem(pageItemId); //either idx, varRef or itemId
    }
});






