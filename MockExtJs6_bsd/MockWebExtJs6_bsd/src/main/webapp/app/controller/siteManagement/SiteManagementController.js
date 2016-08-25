Ext.define('QuotaKPI.controller.siteManagement.SiteManagementController', {
    extend: 'Ext.app.Controller',

    stores: ['siteManagement.Store'
             ,'siteManagement.Product'
             ,'siteManagement.BsdUser'
             ],
    models: [],
    views: ['siteManagement.SiteView'
            
            ,'siteManagement.StoreView'
            ,'siteManagement.StoreGrid'
            ,'siteManagement.StoreForm'
            
            ,'siteManagement.ProductView'
            ,'siteManagement.ProductGrid'
            ,'siteManagement.ProductForm'
            
            ,'siteManagement.BsdUserView'
            ,'siteManagement.BsdUserGrid'
            ,'siteManagement.BsdUserForm'
            ],
    refs: [  {ref: 'SiteView',		selector: 'SiteView'}
			
			,{ref: 'StoreView',	selector: 'StoreView'}
			,{ref: 'StoreGrid',	selector: 'StoreGrid'}
			,{ref: 'StoreForm',	selector: 'StoreForm'}
			
			,{ref: 'ProductView',	selector: 'ProductView'}
			,{ref: 'ProductGrid',	selector: 'ProductGrid'}
			,{ref: 'ProductForm',	selector: 'ProductForm'}
			
			,{ref: 'BsdUserGrid',	selector: 'BsdUserGrid'}
			,{ref: 'BsdUserForm',	selector: 'BsdUserForm'}
			,{ref: 'BsdUserView',	selector: 'BsdUserView'}
			],

    init: function() {
        this.control({
        	'StoreGrid button[action=storeCreate]'		:	{click: this.storeCreate}
        	,'StoreForm button[action=storeEditCancel]'	:	{click: this.storeEditCancel}

        	,'BsdUserGrid button[action=bsdUserCreate]'		:	{click: this.bsdUserCreate}
        	,'BsdUserForm button[action=bsdUserDetailEditCancel]'	:	{click: this.bsdUserDetailEditCancel}

        	,'ProductGrid button[action=productCreate]'		:	{click: this.productCreate}
        	,'ProductForm button[action=productEditCancel]'	:	{click: this.productEditCancel}

        });
    },
	
    
    storeCreate: function(button) {
    	var gridPanel		= this.getStoreGrid();
    	//var selectedRecord = Ext.create('QuotaKPI.model.company.SalesRepresentativeModel');
    	//this._editSalesRepDetail(gridPanel, selectedRecord, true);
		var cardPanel = gridPanel.up('panel');
		var cardLayout = cardPanel.getLayout();
		cardLayout.setActiveItem('StoreForm'); //either idx=1, varRef or itemId
	},
	
	storeEditCancel: function(button) {
		this._cancelDetailView(button, 'StoreGrid');
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






