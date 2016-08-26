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
        	,'StoreForm button[action=editCancel]'		:	{click: this.storeEditCancel}
        	,'StoreForm button[action=actionSave]'		:	{click: this.bsdUserActionSave}

        	,'BsdUserGrid button[action=bsdUserCreate]'	:	{click: this.bsdUserCreate}
        	,'BsdUserForm button[action=editCancel]'	:	{click: this.bsdUserDetailEditCancel}
        	,'BsdUserForm button[action=actionSave]'	:	{click: this.bsdUserActionSave}
        	
        	,'ProductGrid button[action=productCreate]'	:	{click: this.productCreate}
        	,'ProductForm button[action=editCancel]'	:	{click: this.productEditCancel}
        	,'ProductForm button[action=actionSave]'	:	{click: this.bsdUserActionSave}

        });
    },
	
    
	bsdUserActionSave:function(button){
		
		var formPanel	= button.up('panel');//this.getBsdUserForm();
		var form		= formPanel.down('form');
		var formRef		= form.getForm();
		
		formRef.submit({
//		    clientValidation: true,
//		    url: 'updateConsignment.php',
//		    params: {
//		        newStatus: 'delivered'
//		    },
		    success: function(form, action) {
		       Ext.Msg.alert('Success', action.result.msg);
		    },
		    failure: function(form, action) {
		        switch (action.failureType) {
		            case Ext.form.action.Action.CLIENT_INVALID:
		                Ext.Msg.alert('Failure', 'Form fields may not be submitted with invalid values');
		                break;
		            case Ext.form.action.Action.CONNECT_FAILURE:
		                Ext.Msg.alert('Failure', 'Ajax communication failed');
		                break;
		            case Ext.form.action.Action.SERVER_INVALID:
		               Ext.Msg.alert('Failure', action.result.msg);
		       }
		    }
		});
	},
	
    _formSave: function(button){
		var formPanel	= button.up('window');//.QuotaCreateWnd');
		var form		= wnd.down('form');
		var formRef		= form.getForm();

		var values	= form.getValues();
		var record	= Ext.create('QuotaKPI.model.accounting.QuotaModel');
		record.set(values);
		
		var theGrid = null; 
		theGrid = this.getBudgetGrid();
       	var theStore = theGrid.getStore();
       	theStore.add(record);
       	
       	var syncOptions = {	theStore:theStore,
       						wnd:wnd,
							success:this.updateQuotaCallback
							};
       	theStore.sync(syncOptions);
	},

    storeCreate: function(button) {
    	var gridPanel		= this.getStoreGrid();
    	//var selectedRecord = Ext.create('QuotaKPI.model.company.SalesRepresentativeModel');
    	//this._editSalesRepDetail(gridPanel, selectedRecord, true);
		var cardPanel = gridPanel.up('panel');
		var cardLayout = cardPanel.getLayout();
		cardLayout.setActiveItem('StoreForm'); //either idx=1, varRef or itemId
	},
	
    bsdUserCreate: function(button) {
    	var gridPanel		= this.getBsdUserGrid();
    	//var selectedRecord = Ext.create('QuotaKPI.model.company.SalesRepresentativeModel');
    	//this._editSalesRepDetail(gridPanel, selectedRecord, true);
		var cardPanel = gridPanel.up('panel');
		var cardLayout = cardPanel.getLayout();
		cardLayout.setActiveItem('BsdUserForm'); //either idx=1, varRef or itemId
	},
	
	productCreate: function(button) {
    	var gridPanel		= this.getProductGrid();
    	//var selectedRecord = Ext.create('QuotaKPI.model.company.SalesRepresentativeModel');
    	//this._editSalesRepDetail(gridPanel, selectedRecord, true);
		var cardPanel = gridPanel.up('panel');
		var cardLayout = cardPanel.getLayout();
		cardLayout.setActiveItem('ProductForm'); //either idx=1, varRef or itemId
	},
	
	storeEditCancel: function(button) {
		this._cancelDetailView(button, 'StoreGrid');
	},

	bsdUserDetailEditCancel: function(button) {
		this._cancelDetailView(button, 'BsdUserGrid');
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






