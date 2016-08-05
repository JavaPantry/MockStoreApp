Ext.define('QuotaKPI.view.imports.ImportMachine', {
	extend: 'Ext.grid.Panel', 
	alias: 'widget.ImportMachine'
    ,columnLines: true
    //,store: 'dealer.Dealer'
    	
	,columns: [	{header: 'Code'/*,editor : {xtype : 'textfield'}*/, flex:1, sortable: true,dataIndex: 'code'}
				,{header: 'Name'/*,editor : {xtype : 'textfield'}*/, flex:2, sortable: true,dataIndex: 'name'}
				,{/*header: '...',*/ xtype: 'actioncolumn', align: 'center'
		            ,width: 50//MainAppViewConfig.actionColumnWidth
		            ,items: [{
	                icon: 'resources/mps/images/modify.png'
	                , tooltip: 'Edit this entity'
		            ,handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
		                this.fireEvent('editDealerEntity', record);
		                event.stopPropagation();
		            }
		            ,isDisabled: function(view, rowIdx, colIdx, item, record) {
		                return false;//record.get('clazz') == 'machineViewId';
		            }
		            }]
		        }]
	,dockedItems : [{xtype: 'toolbar',dock: 'top',
							    items: [{iconCls: 'icon-add',		itemId: 'downloadMachineExcel',text: 'Download Machine Excel Template',action: 'downloadMachineExcelTemplate'},
							            //{iconCls: 'icon-add',		itemId: 'uploadMachineExcel',text: 'Upload Machine Import Excel',action: 'uploadMachineExcel'},
							            {xtype:'form',padding: '0 0 0 0',frame: true
											  ,items : [{xtype:'filefield',name:'fileUpload'
														,itemId: 'forecastUploadExelActuals'
														,buttonOnly: true
														,buttonText:'Upload Machine Import Excel'
													  ,listeners:{
														  'change': function(fb, v){
															  var theForm = fb.up('form').getForm();
															  if(theForm.isValid()){
																  	theForm.submit({
																  		headers : {'Content-Type':'multipart/form-data; charset=UTF-8'},
																        method : 'POST',
												                        url: 'json/SalesMonthFctSpreadsheetController?m=uploadWeekActuals',
												                        waitMsg: 'Uploading your file...',
												                        success: function(form, action){
												                        	var formContainer = form.owner;
																			var theForm = formContainer.up('UploadReviewActualsGrid');
																			var theStore= theForm.getStore(); 
																			theStore.load();
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
												                }
														  }
													  }
											         }//end btn
											 ]}//end form
										]
					} 
					/*,{xtype: 'pagingtoolbar', dock:'bottom',
						store: 'company.Company',
						displayInfo: true,
						displayMsg: 'Displaying Quota Details {0} - {1} of {2}',
						emptyMsg: "No Dealer to display"}*/
	               ]
    	,initComponent: function() {
    		this.callParent(arguments);
    	}    	
   });