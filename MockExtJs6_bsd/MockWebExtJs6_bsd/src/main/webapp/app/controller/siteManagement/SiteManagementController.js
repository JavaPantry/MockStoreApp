Ext.define('QuotaKPI.controller.siteManagement.SiteManagementController', {
    extend: 'Ext.app.Controller',

    stores: ['siteManagement.Store'
             ,'siteManagement.Product'
             ,'siteManagement.ProductAvailableForStore'
             ,'siteManagement.ProductInStore'
             ,'siteManagement.BsdUser'
             ],
    models: [],
    views: ['siteManagement.SiteView'
            
            ,'siteManagement.StoreView'
            ,'siteManagement.StoreGrid'
            ,'siteManagement.StoreForm'
            ,'siteManagement.StoreMangeProductsForm'
                        
            ,'siteManagement.ProductView'
            ,'siteManagement.ProductGrid'
            ,'siteManagement.ProductGridInStore'
            ,'siteManagement.ProductGridAvailableForStore'
            ,'siteManagement.ProductForm'
            
            ,'siteManagement.BsdUserView'
            ,'siteManagement.BsdUserGrid'
            ,'siteManagement.BsdUserForm'
            ],
    refs: [  {ref: 'SiteView',		selector: 'SiteView'}
			
			,{ref: 'StoreView',	selector: 'StoreView'}
			,{ref: 'StoreGrid',	selector: 'StoreGrid'}
			,{ref: 'StoreForm',	selector: 'StoreForm'}
			,{ref: 'StoreMangeProductsForm',	selector: 'StoreMangeProductsForm'}
			
			,{ref: 'ProductView',	selector: 'ProductView'}
			,{ref: 'ProductGrid',	selector: 'ProductGrid'}
			,{ref: 'ProductGridInStore',	selector: 'ProductGridInStore'}
			,{ref: 'ProductGridAvailableForStore',	selector: 'ProductGridAvailableForStore'}
			,{ref: 'ProductForm',	selector: 'ProductForm'}
			
			,{ref: 'BsdUserGrid',	selector: 'BsdUserGrid'}
			,{ref: 'BsdUserForm',	selector: 'BsdUserForm'}
			,{ref: 'BsdUserView',	selector: 'BsdUserView'}
			],

    init: function() {
        this.control({
        	'StoreGrid button[action=storeCreate]'		:	{click: this.storeCreate}
        	,'StoreGrid button[action=storeManage]'		:	{click: this.storeManageProducts}
        	,'StoreGrid dataview':{itemdblclick: this.editStoreOnDblClk}    
        	,'StoreForm button[action=editCancel]'		:	{click: this.storeEditCancel}
        	,'StoreForm button[action=actionSave]'		:	{click: this.commonFormActionSave}

        	,'StoreMangeProductsForm button[action=editCancel]'		:	{click: this.storeEditCancel}
        	,'StoreMangeProductsForm button[action=actionSave]'		:	{click: this.storeMangeActionSave}
        	
        	,'BsdUserGrid button[action=bsdUserCreate]'	:	{click: this.bsdUserCreate}
        	,'BsdUserForm button[action=editCancel]'	:	{click: this.bsdUserDetailEditCancel}
        	,'BsdUserForm button[action=actionSave]'	:	{click: this.commonFormActionSave}
        	
        	,'ProductGrid button[action=productCreate]'	:	{click: this.productCreate}
        	,'ProductForm button[action=editCancel]'	:	{click: this.productEditCancel}
        	,'ProductForm button[action=actionSave]'	:	{click: this.commonFormActionSave}

        });
    }
	
    ,storeManageProducts:function(button){
    	var theGrid		= this.getStoreGrid();
    	//var selectedRecord = Ext.create('QuotaKPI.model.company.SalesRepresentativeModel');
    	//this._editSalesRepDetail(gridPanel, selectedRecord, true);
		
    	//var cardPanel = gridPanel.up('panel');
		//var cardLayout = cardPanel.getLayout();
		//cardLayout.setActiveItem('StoreMangeProductsForm'); //either idx=1, varRef or itemId
    	var selectedRecord	= theGrid.getSelectionModel().getSelection()[0];
		if (selectedRecord == null || selectedRecord == undefined ){
			Ext.MessageBox.show({title: 'ERROR',
		    			msg: "Please select a Store to load!",
		    			icon: Ext.MessageBox.ERROR,
		    			buttons: Ext.Msg.OK});
			return;
		}
    	
    	this._editStore(theGrid, selectedRecord);
    }
    
    ,editStoreOnDblClk: function(gridPanel, selectedRecord){
    	//gridPanel point to something else, cause exception in cardLayout.setActiveItem('StoreMangeProductsForm');
    	var theGrid		= this.getStoreGrid();

    	this._editStore(theGrid, selectedRecord);
    }
    
    ,_editStore: function(gridPanel, selectedRecord){
		var cardPanel = gridPanel.up('panel');
		var cardLayout = cardPanel.getLayout();
		cardLayout.setActiveItem('StoreMangeProductsForm'); //either idx=1, varRef or itemId
		
		// reload productInStore grid with store Id
		var productInStoreStore =  Ext.getStore('siteManagement.ProductInStore')
		var storeId = selectedRecord.get('id');
		productInStoreStore.appStoreId = storeId;
		productInStoreStore.load({params:{'storeId': storeId}});
		
		var productAvailableForStoreStore =  Ext.getStore('siteManagement.ProductAvailableForStore');
		productAvailableForStoreStore.load({params:{'storeId': storeId}});
    }

    /*
     * need to save assigned product list. 
     * Products may be:
     * - assigned (create new ProductPriceInStore)
     * - edited (update existing ProductPriceInStore)
     * - deleted (ProductPriceInStore server need to detect record absense in submitted json)
     * 
     * Form should be validated before submission at least for zero price for assigned products
     * http://www.mysamplecode.com/2012/02/extjs-grid-validation-error-qtip.html
     */
    ,storeMangeActionSave:function(button){
    	var theGrid = this.getProductGridInStore();
    	var theStore = theGrid.getStore();
    	var view = theGrid.getView();
    	var error = false;
    	var columnLength = theGrid.columns.length;
    	
    	theStore.each(function(record,idx){
    		// destroy cell editor > var errors = record.validate();
    		//also console warning that's deprecated: [W] QuotaKPI.model.siteManagement.ProductPriceInStore: validations has been deprecated. Please use validators instead.
    		//'validate' may be about 'validate form fields' and 'not grid cells' 
			for (var i = 0; i < columnLength; i++) {
				var cell = view.getCellByPosition({row: idx, column: i});
				cell.removeCls("x-form-invalid-field-default");
				cell.set({'data-errorqtip': ''});
				fieldName = theGrid.columns[i].dataIndex;
				if (fieldName === 'price') {
					//Do your validation here
					var price = record.get('price');
					
					if(price == undefined || price <= 0) {
					cell.addCls("x-form-invalid-field-default");
					cell.set({'data-errorqtip': 'Price should be defined and greater than zero'});
					error = true;
					}
				}
			}
    	});//eof each(...
    	
    	if(error){
    		Ext.MessageBox.show({title: 'Validation failed.', msg: 'Price should be defined and greater than zero',
				icon: Ext.MessageBox.ERROR,buttons: Ext.Msg.OK});
    		return;
    	}
    	
    	var syncOptions = {	theStore:theStore,success:this.updateQuotaCallback};
    	//add request par-r theStore.appStoreId
    	theStore.getProxy().setExtraParams({
		    'storeId':theStore.appStoreId
		    });
    	
       	theStore.sync(syncOptions);

    }
    ,updateQuotaCallback:function (batch, syncOptions){
    	var appStoreId = syncOptions.theStore.appStoreId; 
        syncOptions.theStore.load({params:{'storeId': appStoreId}});
    }
    
    /*
     * common save function for new/update store/product/user forms
     * 
     * - common because form submit to url property taken from form
     */
	,commonFormActionSave:function(button){
		
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






