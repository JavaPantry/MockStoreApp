Ext.define('QuotaKPI.controller.company.MaintenanceController', {
    extend: 'Ext.app.Controller',

    stores: ['company.SalesRepresentative'/*,'company.SalesManager'*/],

    models: ['company.SalesRepresentativeModel'
             ,'company.ProductLineModel'
             ,'company.TocModel'
             ,'company.SalesRepresentativeManagerModel'],//TODO - <AP> do models actually required?

    views: ['company.SalesRepresentativeMaintenanceView'
            ,'company.SalesRepresentativesGrid'
    		,'company.SalesRepresentativeDetailForm'
    		,'company.SalesRepresentativeManagersListForm'
    		,'company.ManagerDetailForm'
    		,'company.TOCListForm'
    		,'company.TocDetailForm'
    		],

    refs: [	{ref: 'SalesRepresentativesGrid', selector: 'SalesRepresentativesGrid'}
			,{ref: 'SalesRepresentativeDetailForm', selector: 'SalesRepresentativeDetailForm'}
			,{ref: 'SalesRepresentativeManagersListForm', selector: 'SalesRepresentativeManagersListForm'}
			,{ref: 'ManagerDetailForm', selector: 'ManagerDetailForm'}
			,{ref: 'TOCListForm', selector: 'TOCListForm'}
			,{ref: 'TocDetailForm', selector: 'TocDetailForm'}
			],

    init: function() {
        this.control({
        	'dataview':{openUserForm: this.openUserForm,
        				openManagerListForm: this.openManagerListForm,
        				openTocListForm: this.openTocListForm,
						openManagerDetailForm: this.openManagerDetailForm}
        
            ,'SalesRepresentativesGrid dataview':{itemdblclick: this.editSalesRepDetailOnDblClk}
            ,'SalesRepresentativesGrid button[action=editSalesRepDetail]': {click: this.editSalesRepDetailBtn}
            ,'SalesRepresentativesGrid button[action=editSalesRepDetailAdd]': {click: this.editSalesRepDetailAdd}
            ,'SalesRepresentativesGrid button[action=deleteSalesRepDetail]': {click: this.deleteSalesRepDetail}
            
            ,'SalesRepresentativeDetailForm button[action=userDetailEditCancel]': {click: this.cancelDetailView}
            ,'SalesRepresentativeDetailForm button[action=salesRepDetailSave]': {click: this.salesRepDetailSave}
            
            ,'SalesRepresentativeManagersListForm button[action=userDetailEditCancel]': {click: this.cancelDetailView}
            //,'SalesRepresentativeManagersListForm button[action:actionEdit]': {click: this.openManagerDetailForm}

            // handled within SalesRepresentativeManagersListForm and in here by openManagerDetailForm
            //,'SalesRepresentativeManagersListForm dataview':{itemdblclick: this.onManagerDetailDblClk}
            ,'SalesRepresentativeManagersListForm button[action=actionAdd]': {click: this.newManagerDetailForm}
            ,'SalesRepresentativeManagersListForm button[action=actionDelete]': {click: this.deleteManagerDetailForm}

            ,'ManagerDetailForm button[action=actionSave]': {click: this.saveManagerSalesRepProdLineJoin}            
            ,'ManagerDetailForm button[action=userDetailEditCancel]': {click: this.cancelEditManagerDetail}
            
            ,'TOCListForm button[action=actionAdd]': {click: this.openTocDetailForm}
            ,'TOCListForm button[action=actionDelete]': {click: this.deleteTocDetailForm}
            ,'TOCListForm button[action=userDetailEditCancel]': {click: this.cancelDetailView}
            
            ,'TocDetailForm button[action=actionSave]': {click: this.saveTocDetailForm}
            ,'TocDetailForm button[action=userDetailEditCancel]': {click: this.cancelTocDetailEdit}
            
        });
        ////console.log('MaintenanceController loaded');
    }

    ,openUserForm : function(userId) {
    	//console.log('MaintenanceController receive click over '+userId+' user');
    }

    ,deleteTocDetailForm : function(button) {
    	//console.log('deleteTocDetailForm');
    	var tocListForm	= this.getTOCListForm();
    	var theGrid 	= tocListForm.down('#TOCPanel');
    	//var theStore 	= theGrid.getStore();
    	var selectedRecord	= theGrid.getSelectionModel().getSelection()[0];
		if (selectedRecord == null || selectedRecord == undefined ){
			Ext.MessageBox.show({title: 'ERROR',
		    			msg: "Please select a TOC to delete!",
		    			icon: Ext.MessageBox.ERROR,
		    			buttons: Ext.Msg.OK});
			return;
		}
    	
    	Ext.MessageBox.confirm('Confirm', 'Are you sure to delete this manager?', this.deleteTocDetailConfirm, this);
    }
	,deleteTocDetailConfirm: function(btn) {
		if (btn != "yes") return;
    	var tocListForm		= this.getTOCListForm();
    	var theGrid 	= tocListForm.down('#TOCPanel');
    	var theStore 	= theGrid.getStore();
    	var selectedRecord	= theGrid.getSelectionModel().getSelection()[0];
		//console.log('delete toc records '+selectedRecord);
		theStore.remove(selectedRecord);
		var syncOptions = {	controller:this,
							theStore:theStore, 
							success:this.deleteTocDetailSyncCallback,
							failure:this.deleteTocDetailFailSyncCallback};
		var salesRepresentativeId = tocListForm.salesRepRecord.get('salesRepresentativeId')
		theStore.getProxy().setExtraParam('salesRepresentativeId', salesRepresentativeId);
		//.setExtraParams({'salesRepresentativeId':salesRepresentativeId});
		theStore.sync(syncOptions);
	}

	,deleteTocDetailSyncCallback:function (batch, syncOptions){
		//console.log('delete Toc deleteTocDetailSyncCallback');
		var controller = syncOptions.controller;
		var tocListForm	= controller.getTOCListForm();
		var salesRepresentativeId = tocListForm.salesRepRecord.get('salesRepresentativeId')
		var theGrid = tocListForm.down('#TOCPanel');
		var theStore= theGrid.getStore();
  		theStore.load({params:{'salesRepresentativeId': salesRepresentativeId}});
	}

	,deleteTocDetailFailSyncCallback:function (batch, syncOptions){
		//console.log('delete Toc deleteTocDetailFailSyncCallback');
	    syncOptions.theStore.load();
	}
    //end of deleteTocDetailForm

    ,openTocListForm : function(userId,salesRepRecord) {
    	//console.log('MaintenanceController receive click over '+userId+' toc, selectedRecord = '+salesRepRecord);
		var tocDetailForm	= this.getTOCListForm();
		tocDetailForm.loadFormHeader(salesRepRecord);
		var cardPanel = tocDetailForm.up('panel');
		var cardLayout = cardPanel.getLayout();
		cardLayout.setActiveItem('TOCListForm'); //either idx=3, varRef or itemId

		var theGrid = tocDetailForm.down('#TOCPanel');
		var theStore= theGrid.getStore();
		//theStore.loadData(salesRepRecord.get('tocs')); 
		theStore.load({params:{'salesRepresentativeId': salesRepRecord.get('salesRepresentativeId')}});
    }
    
    ,saveTocDetailForm : function(button) {
    	//console.log('saveTocDetailForm');
    	//var tocDetailForm	= this.getTOCListForm();
    	var tocDetailForm	= this.getTocDetailForm();
		var tocField				= tocDetailForm.down('#tocId');
		var tocId					= tocField.getValue();
		var salesRepresentativeField	= tocDetailForm.down('#salesRepresentativeId');
		var salesRepresentativeId		= salesRepresentativeField.getValue();

		var postObject = new Object();
		postObject.data = new Object();
		//set inline SalesRepTocLink object (see MaintenanceController:linkTocAndSalesRepresentative)
        postObject.data.tocId = tocId;
        postObject.data.salesRepresentativeId = salesRepresentativeId;
         var postJson = Ext.encode(postObject);//json2obj postObject = Ext.decode(postJson);
         ////console.log('postJson : ' + postJson);
             
         var me	= this;
         me.saveTocDetailBtn = button;
         //instead following Ext.Ajax.request try synch theStore
         Ext.Ajax.request({
              method: 'POST',
              url: 'ajax/linkTocAndSalesRepresentative',
              jsonData: postJson,//requestData,
              timeout:120000,
              success: function (response,request){
    	  			me.cancelTocDetailEdit(me.saveTocDetailBtn);
    	  			var tocListForm	= me.getTOCListForm();
    	  			var salesRepresentativeId = tocListForm.salesRepRecord.get('salesRepresentativeId')
    	  			var theGrid = tocListForm.down('#TOCPanel');
    	  			var theStore= theGrid.getStore();
    	  			theStore.load({params:{'salesRepresentativeId': salesRepresentativeId}});
              },
              failure: function (response,request){
                     ////console.log(response.responseText);
                          Ext.MessageBox.show({
                   title: 'AJAX FAILURE:',
                   msg: 'Unable to save for the reason: '+response.responseText,
                   icon: Ext.MessageBox.ERROR,
                   buttons: Ext.Msg.OK
               });
              }
          });

    	
    }
	,openTocDetailForm : function(viewOrButton, salesRepRecord) {

		var cardPanel = viewOrButton.up('SalesRepresentativeMaintenanceView');//'panel'
		var cardLayout = cardPanel.getLayout();
	
		var tocDetailForm = this.getTocDetailForm();
		var tocListForm = viewOrButton.up('TOCListForm');
		tocDetailForm.loadFormHeader(tocListForm.salesRepRecord);
		
		var tocIdFld	= tocDetailForm.down('#tocId');
		var remoteTocStore = Ext.getStore('remoteTocStore');
		//TODO - <AP> change to theStore.getProxy().setExtraParam('salesRepresentativeId', salesRepresentativeId);
		//var params = {	'salesRepId': tocListForm.salesRepRecord.get('salesRepresentativeId')};
		remoteTocStore.getProxy().setExtraParam('salesRepId', tocListForm.salesRepRecord.get('salesRepresentativeId'));
		remoteTocStore.load({params:{}//params:params //now they are passed as ExtraParam and will be passed in typeahead request 
				,callback: function(records, operation, success) {
				if(records.length == 0){
					Ext.MessageBox.show({title: 'ERROR',
		    			msg: "There no TOCs to add!",
		    			icon: Ext.MessageBox.ERROR,
		    			buttons: Ext.Msg.OK});
					return;
				}
				var tocDetailForm = cardLayout.setActiveItem('TocDetailForm');
		    	}
			});
		//tocIdFld.setValue(tocListForm.salesRepRecord.get('tocId'));
	}

	,cancelTocDetailEdit : function(button) {
    	this._cancelDetailView(button, 'TOCListForm');//idx=3
    }
    
    ,openManagerListForm : function(userId,salesRepRecord) {
    	//console.log('MaintenanceController receive click over '+userId+' manager');
		var managerDetailForm	= this.getSalesRepresentativeManagersListForm();
		managerDetailForm.loadFormHeader(salesRepRecord);
		
		var cardPanel = managerDetailForm.up('panel');
		var cardLayout = cardPanel.getLayout();
		cardLayout.setActiveItem('SalesRepresentativeManagersListForm'); //either idx=2, varRef or itemId
		
		var theGrid  = managerDetailForm.down('#ManagerPanel');
		var theStore = theGrid.getStore();//salesRepresentativeManagersListFormStore from SalesRepresentativeManagersListForm
		//load from salesRepresentativeRecord in memory> 
		//theStore.loadData(salesRepresentativeRecord.get('managers'));
		theStore.load({params:{'salesRepresentativeId': salesRepRecord.get('salesRepresentativeId')}});
    }    
    ,newManagerDetailForm:function(button){
		var salesRepRecord = Ext.create('QuotaKPI.model.company.SalesRepresentativeManagerModel'); 
		this._openManagerDetailForm(button, salesRepRecord, true);
    }
    
    //handler for 'openManagerDetailForm' event from SalesRepresentativeManagersListForm
    ,openManagerDetailForm : function(viewOrButton, salesRepRecord) {//, item, index, eventobj, obj
    	this._openManagerDetailForm(viewOrButton, salesRepRecord, false);
    }
    
	,_openManagerDetailForm : function(viewOrButton, salesRepRecord, isNew) {
		
		var cardPanel = viewOrButton.up('SalesRepresentativeMaintenanceView');
		var cardLayout = cardPanel.getLayout();
	
		var managerDetailForm	= this.getManagerDetailForm();
		
		var salesRepresentativeManagersListForm = viewOrButton.up('SalesRepresentativeManagersListForm');
		managerDetailForm.loadFormHeader(salesRepresentativeManagersListForm.salesRepRecord);

		var theGrid = managerDetailForm.down('#ProductLineGrid');//('#ManagerDetailPanel');
		var theStore = theGrid.getStore();

		var params = null;
		var remoteManagerListStore = Ext.getStore('remoteManagerListStore');
		/*QKPI-69:
		 * http://localhost:8080/QuotaKPIWeb/ajax/users?salesRepId=CIG001&topManagerId=T03212
		 * http://localhost:8080/QuotaKPIWeb/ajax/users?salesRepId=CIG001
		 * 
		if(isNew){
			params = {	'salesRepId': salesRepresentativeManagersListForm.salesRepRecord.get('salesRepresentativeId')};
		}else{
			params = {	'salesRepId': salesRepresentativeManagersListForm.salesRepRecord.get('salesRepresentativeId')
						,'topManagerId':salesRepRecord.get('employeeId')};//salesRepresentativeManagersListForm.salesRepRecord.get('managerId')
		}*/
		//TODO - <AP> change to theStore.getProxy().setExtraParam('salesRepresentativeId', salesRepresentativeId);
		if(isNew){
			remoteManagerListStore.getProxy().setExtraParam('salesRepId', salesRepresentativeManagersListForm.salesRepRecord.get('salesRepresentativeId'));
			remoteManagerListStore.getProxy().setExtraParam('topManagerId', '');
		}else{
			remoteManagerListStore.getProxy().setExtraParam('salesRepId', salesRepresentativeManagersListForm.salesRepRecord.get('salesRepresentativeId'));
			remoteManagerListStore.getProxy().setExtraParam('topManagerId', salesRepRecord.get('employeeId'));
		}
		
		remoteManagerListStore.load({params:{}//QKPI-69: params
									,callback: function(records, operation, success) {
								        //console.log(records);
										if(records.length == 0){
											Ext.MessageBox.show({title: 'ERROR',
								    			msg: "There no managers to add!",
								    			icon: Ext.MessageBox.ERROR,
								    			buttons: Ext.Msg.OK});
											return;
										}
										var managerFld				= managerDetailForm.down('#managerId');
										managerFld.setValue(salesRepRecord.get('employeeId'));//('managerId'));
										managerDetailForm = cardLayout.setActiveItem('ManagerDetailForm');//either idx=4, varRef or itemId
										//load from memory store.loadData(selectedRecord.get('managers'));
										theStore.load({params:{	'salesRepresentativeId': salesRepresentativeManagersListForm.salesRepRecord.get('salesRepresentativeId'),
																'managerId':salesRepRecord.get('employeeId')} });
								    	}
									});
		
	}

    
    /*
     * start of deleteManagerDetailForm
     */
    ,deleteManagerDetailForm:function(button){
    	//console.log('SalesRepresentativeManagersListForm deleteManagerDetailForm');
    	var salesRepresentativeManagersListForm		= this.getSalesRepresentativeManagersListForm();//salesRepresentativeMaintenanceView.down('SalesRepresentativeManagersListForm');
    	var salesRepresentativeManagersListGrid 	= salesRepresentativeManagersListForm.down('#ManagerPanel');
    	var salesRepresentativeManagersListStore 	= salesRepresentativeManagersListGrid.getStore();
    	var selectedRecord	= salesRepresentativeManagersListGrid.getSelectionModel().getSelection()[0];
		if (selectedRecord == null || selectedRecord == undefined ){
			Ext.MessageBox.show({title: 'ERROR',
		    			msg: "Please select a Manager to delete!",
		    			icon: Ext.MessageBox.ERROR,
		    			buttons: Ext.Msg.OK});
			return;
		}
    	
    	Ext.MessageBox.confirm('Confirm', 'Are you sure to delete this manager?', this.deleteManagerConfirm, this);
    	
    	var cardPanel = button.up('SalesRepresentativeMaintenanceView');//'panel'
		var cardLayout = cardPanel.getLayout();
		//var managerDetailForm = cardLayout.setActiveItem('ManagerDetailForm'); //either idx=4, varRef or itemId
    }
	,deleteManagerConfirm: function(btn) {
		if (btn != "yes") return;
    	var salesRepresentativeManagersListForm		= this.getSalesRepresentativeManagersListForm();//salesRepresentativeMaintenanceView.down('SalesRepresentativeManagersListForm');
    	var salesRepresentativeManagersListGrid 	= salesRepresentativeManagersListForm.down('#ManagerPanel');
    	var salesRepresentativeManagersListStore 	= salesRepresentativeManagersListGrid.getStore();
    	var selectedRecord	= salesRepresentativeManagersListGrid.getSelectionModel().getSelection()[0];

		//console.log('delete manager records '+selectedRecord);
		salesRepresentativeManagersListStore.remove(selectedRecord);
		var syncOptions = {	theStore:salesRepresentativeManagersListStore, 
							success:this.deleteManagerSyncCallback,
							failure:this.deleteManagerFailSyncCallback};
		salesRepresentativeManagersListStore.sync(syncOptions);
	}

	,deleteManagerSyncCallback:function (batch, syncOptions){
		//console.log('delete manager deleteManagerSyncCallback');
	    syncOptions.theStore.load();
	}

	,deleteManagerFailSyncCallback:function (batch, syncOptions){
		//console.log('delete manager deleteManagerFailSyncCallback');
	    syncOptions.theStore.load();
	}
	//end of deleteManagerDetailForm

	,saveManagerSalesRepProdLineJoin: function(button){
    	var salesRepresentativeMaintenanceView		= button.up('SalesRepresentativeMaintenanceView');
    	var salesRepresentativeManagersListForm		= salesRepresentativeMaintenanceView.down('SalesRepresentativeManagersListForm');
    	var salesRepresentativeManagersListGrid 	= salesRepresentativeManagersListForm.down('#ManagerPanel');
    	var salesRepresentativeManagersListStore 	= salesRepresentativeManagersListGrid.getStore();
    	
    	
		var managerDetailForm			= button.up('ManagerDetailForm');
		//TODO - <AP> if join between manager and Sales rep not exists set flag NEW to true
		var managerField				= managerDetailForm.down('#managerId');
		var managerId					= managerField.getValue();
		var salesRepresentativeField	= managerDetailForm.down('#salesRepresentativeId');
		var salesRepresentativeId		= salesRepresentativeField.getValue();
		
		//var theGrid = managerDetailForm.down('#ManagerPanel'); //if(this.emptyCheck(theGrid) != true){WARNING: Please select at least one access option.}
		var theGrid = managerDetailForm.down('#ProductLineGrid');//('#ManagerDetailPanel');
		//console.log('grid = ' + theGrid + ', salesRepresentativeField = '+ salesRepresentativeField+ ', managerId = '+managerId);
		var theStore= theGrid.getStore();
		
		//hack to fill-out 'managerId' in record if manager is new (wasn't filled by server)
		var size = theStore.getCount();
		for(var i=0; i < size; i++){
			var record = theStore.getAt(i);
			var recordManagerId = record.get('managerId');
			if(record.get('exists') && recordManagerId == ''/*== undefined*/){
				record.set('managerId',managerId);
			}
		}
		//TODO - <AP> if no product line is set and manager is new - no record will be created and user must be notified
		//end of hack 
		var syncOptions = {	theStore:theStore,
							salesRepresentativeManagersListStore:salesRepresentativeManagersListStore,
							salesRepresentativeId:salesRepresentativeId,
							success:this.saveManagerSalesRepProdLineJoinCallback,
							failure:this.saveManagerSalesRepProdLineJoinFailureSyncCallback};
		theStore.sync(syncOptions);
		this.cancelEditManagerDetail(button);
	}
    ,saveManagerSalesRepProdLineJoinCallback:function (batch, syncOptions){
    	syncOptions.salesRepresentativeManagersListStore.load({params:{'salesRepresentativeId': syncOptions.salesRepresentativeId}});
        //syncOptions.theStore.load();
        //syncOptions.wnd.close();
    }
	,saveManagerSalesRepProdLineJoinFailureSyncCallback:function (batch, syncOptions){
		//syncOptions.theStore.load();
		//access to error message inside store's exception
	}

    
    
    ,cancelEditManagerDetail : function(button) {
    	this._cancelDetailView(button, 'SalesRepresentativeManagersListForm');//idx=2
    }
    ,cancelDetailView : function(button) {
    	this._cancelDetailView(button, 'SalesRepresentativesGrid');//idx=0
    }
    
    /*
     * SalesRepresentativeMaintenanceView
     *	[0] - 'SalesRepresentativesGrid'
     *	[1] - 'SalesRepresentativeDetailForm'
     *	[2] - 'SalesRepresentativeManagersListForm'
     *	[3] - 'TOCListForm'
     *	[4] - 'ManagerDetailForm'
     *	[5] - 'TocDetailForm'
     *
     */
    ,_cancelDetailView : function(button, pageItemId) {
    	var gridPanel	= button.up('panel');
		var cardPanel 	= gridPanel.up('panel');
		var cardLayout 	= cardPanel.getLayout();
		cardLayout.setActiveItem(pageItemId); //either idx, varRef or itemId
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
	
	,deleteSalesRepDetail : function(button) {
		//console.log('deleteSalesRepDetail button '+button);
		var theGrid		= this.getSalesRepresentativesGrid();
		var selectedRecord	= theGrid.getSelectionModel().getSelection()[0];
		if (selectedRecord == null || selectedRecord == undefined ){
			Ext.MessageBox.show({title: 'ERROR',
		    			msg: "Please select a User for edit!",
		    			icon: Ext.MessageBox.ERROR,
		    			buttons: Ext.Msg.OK});
			return;
		}
		//get confirm and delete Search Confirm in FAST
		Ext.MessageBox.confirm('Confirm', 'Are you sure to delete this record?', this.deleteSalesRepConfirm, this);
	}
	,deleteSalesRepConfirm: function(btn) {
		if (btn != "yes") return;
		var theGrid		= this.getSalesRepresentativesGrid();
		var theStore	= theGrid.getStore();
		var records		= theGrid.getSelectionModel().getSelection()[0];
		//console.log('records '+records);
		theStore.remove(records);
		var syncOptions = {	theStore:theStore, 
							success:this.deleteSalesRepSyncCallback,
							failure:this.deleteSalesRepFailureSyncCallback};
		theStore.sync(syncOptions);
	}

	,deleteSalesRepSyncCallback:function (batch, syncOptions){
	    syncOptions.theStore.load();
	}

	,deleteSalesRepFailureSyncCallback:function (batch, syncOptions){
		syncOptions.theStore.load();
	}

	
	,editSalesRepDetailAdd : function(button) {
		var gridPanel		= this.getSalesRepresentativesGrid();
		var selectedRecord = Ext.create('QuotaKPI.model.company.SalesRepresentativeModel');
		this._editSalesRepDetail(gridPanel, selectedRecord, true);
	}

	,editSalesRepDetailOnDblClk : function(gridPanel, selectedRecord) {
		var gridPanel2		= this.getSalesRepresentativesGrid();
		this._editSalesRepDetail(gridPanel2, selectedRecord, false);
	}
	
    ,editSalesRepDetailBtn : function(button) {
		var gridPanel		= this.getSalesRepresentativesGrid();
		var selectedRecord	= gridPanel.getSelectionModel().getSelection()[0];
		if (selectedRecord == null || selectedRecord == undefined ){
			Ext.MessageBox.show({title: 'ERROR',
		    			msg: "Please select a User for edit!",
		    			icon: Ext.MessageBox.ERROR,
		    			buttons: Ext.Msg.OK});
			return;
		}
		this._editSalesRepDetail(gridPanel, selectedRecord, false);
    }
    
	,_editSalesRepDetail : function(gridPanel, selectedRecord, isNew) {
		var userDetailForm	= this.getSalesRepresentativeDetailForm();
		userDetailForm.newUserMode = isNew;
		var formRef	= userDetailForm.getForm();
		var salesRepresentativeIdField = formRef.findField('salesRepresentativeId');
		var salesRepresentativeIdDisplay = formRef.findField('_salesRepresentativeId');
		if(!isNew){
			salesRepresentativeIdField.hide();
			salesRepresentativeIdDisplay.show();
			salesRepresentativeIdDisplay.setValue(selectedRecord.get('salesRepresentativeId'));
		}else{
			salesRepresentativeIdField.show();
			salesRepresentativeIdDisplay.hide();
		}
		
		var formRef	= userDetailForm.getForm();
		formRef.loadRecord(selectedRecord);
		
		var cardPanel = gridPanel.up('panel');
		var cardLayout = cardPanel.getLayout();
		cardLayout.setActiveItem('SalesRepresentativeDetailForm'); //either idx=1, varRef or itemId
	}//eof _editSalesRepDetail
	
});
