Ext.define('QuotaKPI.controller.accounting.AccountingController', {
    extend: 'Ext.app.Controller',

    selectedDealerId : 0,
    
    stores: ['accounting.Quota','accounting.Budget','accounting.KpiChart'],

    models: ['accounting.QuotaModel'],

    views: ['accounting.QuotaView','accounting.QuotaGrid'
			,'accounting.BudgetView','accounting.BudgetGrid'
			,'accounting.QuotaCreateWnd','accounting.ChartView'
    		],

    refs: [  {ref: 'QuotaView',		selector: 'QuotaView'}
			,{ref: 'QuotaGrid',		selector: 'QuotaGrid'}
			,{ref: 'QuotaChart',	selector: 'QuotaChart'}
			,{ref: 'BudgetView',	selector: 'BudgetView'}
			,{ref: 'BudgetGrid',	selector: 'BudgetGrid'}
			,{ref: 'QuotaCreateWnd',	selector: 'QuotaCreateWnd'}],

    init: function() {
        this.control({
        	'QuotaGrid button[action=quotaDetailSave]'		:	{click: this.quotaDetailSave},
        	'QuotaGrid button[action=quotaDetailCreate]'	:	{click: this.quotaDetailCreate},
        	'QuotaGrid button[action=quotaDetailReset]'		:	{click: this.quotaDetailReset},
        	'QuotaGrid button[action=quotaDetailDelete]'	:	{click: this.quotaDetailDelete},
        	'QuotaGrid button[action=quotaChart]'			:	{click: this.quotaChart},
        	
        	'BudgetGrid button[action=budgetDetailSave]'	:	{click: this.budgetDetailSave},
        	'BudgetGrid button[action=budgetDetailCreate]'	:	{click: this.budgetDetailCreate},
        	'BudgetGrid button[action=budgetDetailReset]'	:	{click: this.budgetDetailReset},
        	'BudgetGrid button[action=budgetDetailDelete]'	:	{click: this.budgetDetailDelete},
        	
        	'QuotaCreateWnd button[action=quotaCreateWndSave]': {click: this.quotaCreateWndSave},
        	'QuotaCreateWnd button[action=quotaCreateWndCancel]': {click: this.closeWindow}

        });
    }

	,quotaChart: function(button){
		var theGrid		= button.up('panel');//this.getQuotaGrid();
		var selectedRecords = theGrid.getSelectionModel().getSelection();
		if(selectedRecords != undefined && selectedRecords.length>0){ 
			var quotas = [];
			for (var i=0;i<selectedRecords.length;i++){
				quotas.push(selectedRecords[i]);
				/*for (var j=0;j<12;j++){
					quotas.push(selectedRecords[i]);
				}*/
			}
			
			this.getController('accounting.ChartController').onQuotaChartEvent(quotas);
			
		}else{
			Ext.MessageBox.show({title: 'ERROR',
				msg: 'Select records to chart.',
				icon: Ext.MessageBox.ERROR,
				buttons: Ext.Msg.OK});
		}
	}


//Budget & Budget
	,budgetDetailSave: function(button) {
       	var budgetDetailGrid = this.getBudgetGrid();
       	var theStore = budgetDetailGrid.getStore();
       	var syncOptions = {	theStore:theStore,success:this.updateBudgetCallback};
       	theStore.sync(syncOptions);
	}
    ,updateBudgetCallback:function (batch, syncOptions){
        syncOptions.theStore.load();
    }
//end of Budget
    
	,quotaDetailSave: function(button){
       	var dealerQuotaDetailGrid = this.getQuotaGrid();
       	var theStore = dealerQuotaDetailGrid.getStore();
       	var syncOptions = {	theStore:theStore,success:this.updateQuotaCallback};
       	theStore.sync(syncOptions);
	}
    ,updateQuotaCallback:function (batch, syncOptions){
        syncOptions.theStore.load();
    }
    
    ,budgetDetailCreate: function(button){
		var wnd		= Ext.create('QuotaKPI.view.accounting.QuotaCreateWnd');
		wnd.setTitle('Create Budget Header');
	    var form	= wnd.down('form');
	    var formRef	= form.getForm();
	    var salesRepCombo = formRef.findField('salesRepresentativeId');
	    salesRepCombo.hide();
	    var quotaOrBudgetField = formRef.findField('quotaOrBudget');
	    quotaOrBudgetField.setValue('Budget');
	}

    ,quotaDetailCreate: function(button){
		var wnd		= Ext.create('QuotaKPI.view.accounting.QuotaCreateWnd');
	}
    
    
	,quotaDetailReset: function(button){
    	var theGrid		= button.up('panel');//this.getQuotaGrid();
		var selectedRecords = theGrid.getSelectionModel().getSelection();
    	if(selectedRecords != undefined && selectedRecords.length>0){ 
    		Ext.MessageBox.confirm('Confirm', 'Are you sure to reset all selected quotas?', this.quotaDetailResetConfirm, this);
    	}else{
			Ext.MessageBox.show({title: 'ERROR',
				msg: 'Select records to reset.',
				icon: Ext.MessageBox.ERROR,
				buttons: Ext.Msg.OK});
    	}
	}
	
    ,quotaDetailResetConfirm: function(btn) {
    	if (btn != "yes") return;
    	var theGrid		= this.getQuotaGrid();
		var selectedRecords = theGrid.getSelectionModel().getSelection();
		var theStore	= theGrid.getStore();
		for (var i=0;i<selectedRecords.length;i++){
    		//records[i].set('deleted',true);
			for (var j=0;j<12;j++){
				selectedRecords[i].set('value'+j,0);
			}
    	    //theStore.remove(selectedRecords[i]);
		}
    }
	
	,budgetDetailReset: function(button){
    	var theGrid		= button.up('panel');//this.getBudgetGrid();
		var selectedRecords = theGrid.getSelectionModel().getSelection();
    	if(selectedRecords != undefined && selectedRecords.length>0){ 
    		Ext.MessageBox.confirm('Confirm', 'Are you sure to reset all selected quotas?', this.budgetDetailResetConfirm, this);
    	}else{
			Ext.MessageBox.show({title: 'ERROR',
				msg: 'Select records to reset.',
				icon: Ext.MessageBox.ERROR,
				buttons: Ext.Msg.OK});
    	}
	}
	
    ,budgetDetailResetConfirm: function(btn) {
    	if (btn != "yes") return;
    	var theGrid		= this.getBudgetGrid();
		var selectedRecords = theGrid.getSelectionModel().getSelection();
		var theStore	= theGrid.getStore();
		for (var i=0;i<selectedRecords.length;i++){
    		//records[i].set('deleted',true);
			for (var j=0;j<12;j++){
				selectedRecords[i].set('value'+j,0);
			}
    	    //theStore.remove(selectedRecords[i]);
		}
    }
    
    ,quotaDetailDelete: function(button){
    	var theGrid		= button.up('panel');//this.getQuotaGrid();
		var selectedRecords = theGrid.getSelectionModel().getSelection();
    	if(selectedRecords != undefined && selectedRecords.length>0){ 
    		Ext.MessageBox.confirm('Confirm', 'Are you sure to delete all selected quotas?', this.quotaDetailDeleteConfirm, this);
    	}else{
			Ext.MessageBox.show({title: 'ERROR',
				msg: 'Select records to delete.',
				icon: Ext.MessageBox.ERROR,
				buttons: Ext.Msg.OK});
    	}
   	}
    	
    ,quotaDetailDeleteConfirm: function(btn) {
    	if (btn != "yes") return;
    	var theGrid		= this.getQuotaGrid();
		var selectedRecords = theGrid.getSelectionModel().getSelection();
		var theStore	= theGrid.getStore();
		for (var i=0;i<selectedRecords.length;i++){
    	    theStore.remove(selectedRecords[i]);
		}
       	var syncOptions = {	theStore:theStore,success:this.updateQuotaCallback};
       	theStore.sync(syncOptions);
    }

    ,budgetDetailDelete: function(button){
    	var theGrid		= button.up('panel');//this.getQuotaGrid();
		var selectedRecords = theGrid.getSelectionModel().getSelection();
    	if(selectedRecords != undefined && selectedRecords.length>0){ 
    		Ext.MessageBox.confirm('Confirm', 'Are you sure to delete all selected budgets?', this.budgetDetailDeleteConfirm, this);
    	}else{
			Ext.MessageBox.show({title: 'ERROR',
				msg: 'Select records to delete.',
				icon: Ext.MessageBox.ERROR,
				buttons: Ext.Msg.OK});
    	}
   	}
    	
    ,budgetDetailDeleteConfirm: function(btn) {
    	if (btn != "yes") return;
    	var theGrid		= this.getBudgetGrid();
		var selectedRecords = theGrid.getSelectionModel().getSelection();
		var theStore	= theGrid.getStore();
		for (var i=0;i<selectedRecords.length;i++){
    	    theStore.remove(selectedRecords[i]);
		}
       	var syncOptions = {	theStore:theStore,success:this.updateQuotaCallback};
       	theStore.sync(syncOptions);
    }

    /*
     * 	var response = Ext.Ajax.request({
   	        method: 'POST',
   	        async:false,
   	        url: 'ajax/checkSalesRepId?salesRepId=' + value,
   	        failure: function (response,request){
				Ext.MessageBox.show({title: 'AJAX FAILURE:',msg: 'Request failed!',
									icon: Ext.MessageBox.ERROR, buttons: Ext.Msg.OK});
   	        }
   	    });
		//we are here because request is async
		var jsonResponse = Ext.JSON.decode(response.responseText,false);
		if (jsonResponse.success == false){
			field.markInvalid(jsonResponse.message);
			//saveBtn.setDisabled(true);
			return false;
		}
		
		
	,salesRepDetailSave: function(button) {
		var me			= this;
		var theForm		= this.getSalesRepresentativeDetailForm();
		var formRef		= theForm.getForm();
		
		var salesRepresentativeIdValue		= formRef.findField('salesRepresentativeId').getValue();
		var salesRepresentativeNameValue	= formRef.findField('salesRepresentativeName').getValue();
		var userIdValue						= formRef.findField('userId').getValue();
		var allAccessValue					= formRef.findField('allAccess').getValue();
		
		//console.log(''+salesRepresentativeIdValue+', '+ salesRepresentativeNameValue+', '+  userIdValue+', '+ allAccessValue);
		
		if(!formRef.isValid()){
			Ext.MessageBox.show({title: 'ERROR',
				msg: 'Please fill correctly all required fields.',
				icon: Ext.MessageBox.ERROR,
				buttons: Ext.Msg.OK});
			return;
		}
		
		formRef.submit({
			url: 'ajax/updateSalesRep'
			,submitEmptyText: false
			,timeout:2000
			//if need to set form to submit as json (http://alvinalexander.com/source-code/software-dev/sencha-extjs-extajaxrequest-json-post-and-get-form-panel-examples)
			//,headers: { 'Content-Type': 'application/json' },
            //,params : Ext.JSON.encode(formPanel.getValues()),
			,failure: function(form, action){
						//TODO - <AP> "NetworkError: 404 Not Found - http://localhost:8080/MockStubWeb/ajax/updateSalesRep" probably expect xml or HTML Ok response  
						Ext.MessageBox.show({title: 'ERROR',
									msg: 'userDetailForm submit fail!',
									icon: Ext.MessageBox.ERROR,
									buttons: Ext.Msg.OK});
						//console.log('failure callback');
				    	var theGrid	= me.getSalesRepresentativesGrid();
						var cardPanel 	= theGrid.up('panel');
						var cardLayout 	= cardPanel.getLayout();
						theGrid = cardLayout.setActiveItem(0); //either idx, varRef or itemId
						var store	= theGrid.getStore();
						store.load();
						}
			,success: function(form, action){
						//console.log('success callback');
				    	var theGrid	= me.getSalesRepresentativesGrid();
						var cardPanel 	= theGrid.up('panel');
						var cardLayout 	= cardPanel.getLayout();
						theGrid = cardLayout.setActiveItem(0); //either idx, varRef or itemId
						var store	= theGrid.getStore();
						store.load();
						}
			//,waitMsg: 'Saving Sales Representative...' instead animated gif shows nice progress bar in popup window
			});
	}//eof salesRepDetailSave

		
     */
    ,quotaCreateWndSave: function(button){
		var wnd		= button.up('window');//.QuotaCreateWnd');
		var form	= wnd.down('form');
		var formRef	= form.getForm();
	    var quotaOrBudgetField = formRef.findField('quotaOrBudget');
	    var windowType = quotaOrBudgetField.getValue();

		var values	= form.getValues();
		var record	= Ext.create('QuotaKPI.model.accounting.QuotaModel');
		record.set(values);
		
		var theGrid = null; 
		if(windowType === 'Budget'){
			theGrid = this.getBudgetGrid();
		}else{
			theGrid = this.getQuotaGrid();
		}
		
		// Validate before add to store
			var response = Ext.Ajax.request({
	   	        method: 'POST',
	   	        async:false,
	   	        url: 'ajax/validateQuota',
	   	        jsonData:record,//instead form:form,
	   	        params:{type:windowType},
	   	        failure: function (response,request){
					Ext.MessageBox.show({title: 'AJAX FAILURE:',msg: 'Request failed!',
										icon: Ext.MessageBox.ERROR, buttons: Ext.Msg.OK});
	   	        }
	   	    });
			//we are here because request is async
			var jsonResponse = Ext.JSON.decode(response.responseText,false);
			if (jsonResponse.success == false){
				//field.markInvalid(jsonResponse.message);
				//saveBtn.setDisabled(true);
				Ext.MessageBox.show({title: 'Quota Validation:',msg: 'Validation failed. Same Quota for same category, year and type already exists.',
					icon: Ext.MessageBox.ERROR, buttons: Ext.Msg.OK});
				return false;
			}/*else{
				Ext.MessageBox.show({title: 'Quota Validation:',msg: 'Quota pass validation.',
					icon: Ext.MessageBox.OK, buttons: Ext.Msg.OK});
				return false;
			}*/
		// end of Validation
       	var theStore = theGrid.getStore();
       	theStore.add(record);
       	
       	var syncOptions = {	theStore:theStore,
       						wnd:wnd,
							success:this.updateQuotaCallback
							};
       	theStore.sync(syncOptions);
       	wnd.close();
	}

    ,closeWindow:  function(button) {
	    var wnd    = button.up('window');
	    wnd.close();
	}
    
    //TODO - <AP> TBR
	,quotaDetailReturn: function(button) {
		var dealerQuotaView = button.up('DealerQuotaView');
		var cardLayout = dealerQuotaView.getLayout();
		cardLayout.setActiveItem(0);
	}
    //TODO - <AP> TBR
	,budgetDetailReturn: function(button) {
		var budgetView = button.up('BudgetView');
		var cardLayout = budgetView.getLayout();
		cardLayout.setActiveItem(0);
	}
    
});






