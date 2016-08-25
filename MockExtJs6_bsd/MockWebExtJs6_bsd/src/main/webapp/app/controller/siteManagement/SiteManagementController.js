Ext.define('QuotaKPI.controller.siteManagement.SiteManagementController', {
    extend: 'Ext.app.Controller',

    stores: ['siteManagement.Site'
             ,'siteManagement.Product'
             ,'siteManagement.BsdUser'
             ],
    models: ['siteManagement.SiteModel'],
    views: ['siteManagement.SiteView'
            ,'siteManagement.SiteGrid'
            
            ,'siteManagement.ProductView'
            ,'siteManagement.ProductGrid'
            ,'siteManagement.ProductForm'
            
            ,'siteManagement.BsdUserView'
            ,'siteManagement.BsdUserGrid'
            ,'siteManagement.BsdUserForm'
            ],
    refs: [  {ref: 'SiteView',		selector: 'SiteView'}
			,{ref: 'SiteGrid',		selector: 'SiteGrid'}
			
			,{ref: 'ProductView',	selector: 'ProductView'}
			,{ref: 'ProductGrid',	selector: 'ProductGrid'}
			,{ref: 'ProductForm',	selector: 'ProductForm'}
			
			,{ref: 'BsdUserGrid',	selector: 'BsdUserGrid'}
			,{ref: 'BsdUserForm',	selector: 'BsdUserForm'}
			,{ref: 'BsdUserView',	selector: 'BsdUserView'}
			],

    init: function() {
        this.control({
        	'BsdUserGrid button[action=bsdUserCreate]'		:	{click: this.bsdUserCreate}
        	,'BsdUserForm button[action=bsdUserDetailEditCancel]'	:	{click: this.bsdUserDetailEditCancel}

        	,'ProductGrid button[action=productCreate]'		:	{click: this.productCreate}
        	,'ProductForm button[action=productEditCancel]'	:	{click: this.productEditCancel}

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
	
	productCreate: function(button) {
    	var gridPanel		= this.getProductGrid();
    	//var selectedRecord = Ext.create('QuotaKPI.model.company.SalesRepresentativeModel');
    	//this._editSalesRepDetail(gridPanel, selectedRecord, true);
		var cardPanel = gridPanel.up('panel');
		var cardLayout = cardPanel.getLayout();
		cardLayout.setActiveItem('ProductForm'); //either idx=1, varRef or itemId
	},
	
	productEditCancel: function(button) {
		this._cancelDetailView(button, 'ProductGrid');
	},

	_cancelDetailView : function(button, pageItemId) {
    	var formPanel	= button.up('panel');
		var cardPanel 	= formPanel.up('panel');
		var cardLayout 	= cardPanel.getLayout();
		cardLayout.setActiveItem(pageItemId); //either idx, varRef or itemId
    }
});






