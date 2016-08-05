Ext.apply(Ext.form.field.VTypes, {
	salesRepIdCheck: function(value, field) {
		field.clearInvalid();
		var form = field.up('#SalesRepresentativeDetailForm');
		if(form.newUserMode != true)
			return true;
		//var saveBtn = form.down('#saveBtn');
		//saveBtn.setDisabled(false);
		
		var response = Ext.Ajax.request({
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
		return true;
    },
    salesRepIdCheckText: 'Sales Representative Id already exists in QuotaKPI database.'

});

var remoteEmployeeList= new Ext.data.JsonStore({
	autoLoad: true,
	proxy: {
	type: 'ajax',
	api: {
		read : 'ajax/lookupusers' 
	},
	reader: {
		type: 'json',
        //extJs 6.0.1.x: root: 'data',
        rootProperty: 'data',

		successProperty: 'success'
	}
	},
	fields: [
	         {name: 'userId',mapping:'employeeId'},
	         {name: 'name'}
	         ]
	});




Ext.define('QuotaKPI.view.company.SalesRepresentativeDetailForm' ,{
		extend	: 'Ext.form.Panel',
		alias	: 'widget.SalesRepresentativeDetailForm',
		timeout : 5,
		newUserMode: false,
		itemId	: 'SalesRepresentativeDetailForm',
		autoHeight: true,
		bodyPadding: 10,
		layout: 'form',
		
        items   : [{xtype: 'fieldcontainer',
					fieldLabel: '',
					combineErrors: true,
					layout: 'vbox',
					defaults: {anchor: '100%'},			
					items: [
					        
					        {fieldLabel: 'Sales Rep ID',xtype: 'displayfield',name : '_salesRepresentativeId', labelCls:'fast-label'}
							,{fieldLabel: 'Sales Rep ID', xtype: 'textfield',name: 'salesRepresentativeId'
								, vtype:'salesRepIdCheck',allowBlank: false	,msgTarget: 'side', style : {textTransform: "uppercase"}
								,listeners:	{
									change: function(field, newValue, oldValue){
												field.setValue(newValue.toUpperCase());
											}
									}
							}
							,{xtype: 'textfield', name: 'salesRepresentativeName', fieldLabel: 'Sales Rep Name', allowBlank: false, msgTarget: 'side'}
							,{xtype: 'combo',
		                    	queryMode: 'remote',
		                    	forceSelection: true,
		                    	fieldLabel: 'User Name',
		                    	name: 'userId',
		                    	displayField: 'name',
		                    	valueField: 'userId',//'employeeId',
		                    	store: remoteEmployeeList,
		                    	allowBlank: false,
		                    	msgTarget: 'side',
		                    	editable: true,
					            typeAhead: true,
					            minChars:1,
					            listConfig: {
					                loadingText: 'Searching...',
					                emptyText: 'No matching found.',
					                getInnerTpl: function() {return '{name} : <i><font color="blue">{userId}</font></i>';}
						            //getInnerTpl: function() {return '<span style="font-weight:bold">{name}</span> : <i><font color="blue">{userId}</font></i>';}
					            }
							}
							/*,{// <AP> !!!!this combo is working but not required!!!!
		                    	xtype: 'combo',
		                    	queryMode: 'remote',
		                    	forceSelection: true,
		                    	fieldLabel: 'Manager Name',
		                    	name: 'managerId',
		                    	displayField: 'name',
		                    	valueField: 'employeeId',
		                    	editable: false,
		                    	store: remoteEmployeeList,
		                    	listeners : {
		                    		 //TODO - <AP> add user existence ajax call here 
		                    		change: function(combo, newValue, oldValue){
		                    			var form = combo.up('form');
		                    			if (!form.newUserMode)//activate existence check only in newUser mode 
		                    				return;
		                    		}//on change
		                    		}//listeners
							}//*/
							,{  xtype: 'checkboxgroup',   //TODO - <AP> change group to {xtype: 'checkbox',boxLabel: 'All access', name: 'allAccess'}
					            items: [
										{boxLabel: 'All access', name: 'allAccess'}
					            	]
								}
					    ]
			        	}
    		],
        // inline buttons
        dockedItems: [{
            xtype: 'toolbar',
            dock: 'top',
            layout: {pack: 'left'},
            items: [
		            {
		                minWidth: 80,
		                text: 'Save',
		                iconCls: 'icon-save',
		                itemId:"saveBtn",
		                action:'salesRepDetailSave'
		            },'->',
		            {
		                minWidth: 80,
		                text: 'Return',
		                iconCls: 'icon-return',
		                action:'userDetailEditCancel'//TODO - <AP> rename to salesRepDetailEditCancel
		            }]
        } ]
    });//end of UserDetail

/* TODO - <AP> should I move it to app.js?
 * Ext.onReady(function() {
    Ext.QuickTips.init();
});*/
