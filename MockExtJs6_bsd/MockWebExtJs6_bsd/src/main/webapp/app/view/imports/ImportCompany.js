/* 
 * load extjs4 grid data on form submission
 * http://stackoverflow.com/questions/20841650/how-to-load-extjs-4-grid-data-on-form-submission 
 */

Ext.define('companyColumnModel', {
    extend: 'Ext.data.Model',
    //idProperty:'code',
    fields: [
    	     {name: 'code', type: 'string'},
    	     {name: 'name', type: 'string'},
    	     {name: 'hasErrors', type: 'boolean'},
    	     {name: 'messages', type: 'string'},
    ]
});

// create the data store
var importCompanyStore = Ext.create('Ext.data.Store', {
    model: 'companyColumnModel',
    fields: [
       {name: 'code'},
       {name: 'name'},
       {name: 'hasErrors'}
       
    ],
    proxy: {
        type: 'ajax',
        reader: {
        	successProperty: 'success',
            type: 'json',
            rootProperty: 'data'
        }
    },
    autoLoad: false
});


Ext.define('QuotaKPI.view.imports.ImportCompany', {
	extend: 'Ext.grid.Panel', 
	alias: 'widget.ImportCompany'
    ,columnLines: true
    ,viewConfig : {
		getRowClass : function(record) {
			var hasErrors = record.get('hasErrors');
			if(hasErrors == true) {
				return 'importErrorRow';// see app.css
			};
		}
	}

    ,store: importCompanyStore
    	
	,columns: [	//https://blogs.walkingtree.in/2013/11/09/what-can-i-do-with-a-gridpanel-in-extjs/
				{ header: 'Error', xtype: 'actioncolumn', dataIndex: 'hasErrors'
					,renderer: function(value, metaData, record, row, col, store, gridView){
						if (value == true ) {
							metaData.tdAttr = 'data-qtip="' + 'Click to see Errors"';
						} 
						return value;
					}
	         	    , align: 'center'//,tooltip: 'Add child'
		            ,width: 50//MainAppViewConfig.actionColumnWidth
	                ,icon: 'resources/mps/images/delete.png'
	                ,getClass : function( v, meta, record ) {
	                	var hasErrors = record.get('hasErrors');
	        			if ( hasErrors== false ) {
	        			return 'x-hide-display';
	        			}
        			}
		            ,handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
		            	console.log('handle error indicator');  
		            	var hasErrors = record.get('hasErrors');
		            	if(hasErrors){
		            		//Ext.Msg.alert('Record has errors:',record.get('messages'));
		            		Ext.MessageBox.show({title: 'Record has errors:',
								msg: record.get('messages'),
								icon: Ext.MessageBox.ERROR,
								buttons: Ext.Msg.OK});

		            	}
		            }
		        }
	           	,{header: 'Code', flex:2, sortable: true,dataIndex: 'code'}
				,{header: 'Name', flex:6, sortable: true,dataIndex: 'name'}
				,{header: 'Errors', flex:1, dataIndex: 'hasErrors'
					,renderer: function(value, metaData, record, row, col, store, gridView){
	         	        if (value == true) {
	         	        	//metaData.tdAttr = 'data-qtip="' + '<div>Errors!</div><div>Errors!</div><div>Errors!</div><div>Errors!</div><div>Errors!</div><div>Errors!</div><div>Errors!</div><div>Errors!</div>"';
	         	        	metaData.tdAttr = 'data-qtip="' + '<TABLE><TR><TD> Error 1</TD><TD>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</TD></TR><TR><TD> Error 2</TD><TD>It is a long established fact that a reader will be distracted by the readable content of a page </TD></TR><TR><TD> Error 3</TD><TD>Contrary to popular belief, Lorem Ipsum is not simply random text. </TD></TR><TR><TD> Error 4</TD><TD>It has roots in a piece of classical Latin literature from 45 BC</TD></TR><TR><TD> Error 5</TD><TD>de Finibus Bonorum et Malorum</TD></TR></TABLE>"';
	         	            return '<img src="resources/mps/images/delete.png">';
	         	        }else {
	         	        	
	         	        	return '<img src="resources/mps/images/approve.png">';
	         	        }
	         	        return '';
					}
				}
				
				
				]

	,dockedItems : [{xtype: 'toolbar',dock: 'top',
							    items: [{iconCls: 'icon-add',		itemId: 'downloadCompanyExcel',text: 'Download Excel Template',action: 'downloadCompanyExcelTemplate'},
							            //{iconCls: 'icon-add',		itemId: 'uploadCompanyExcel',text: 'Upload Company Import Excel',action: 'uploadCompanyExcel'},
							            {xtype:'form',padding: '0 0 0 0',frame: true
											  ,items : [{xtype:'filefield',name:'fileUpload'
														,itemId: 'forecastUploadExelActuals'
														,buttonOnly: true
														,buttonText:'Upload Company Import Excel'
													  ,listeners:{
														  'change': function(fb, v){
															  var theForm = fb.up('form').getForm();
															  if(theForm.isValid()){
																  	theForm.submit({
																  		headers : {'Content-Type':'multipart/form-data; charset=UTF-8; application/json'}, //application/json;text/x-json
																        //method : 'POST',
												                        url: 'companyUpload',
												                        waitMsg: 'Uploading your file...',
												                        success: function(form, action){
												                        	//var formContainer = form.owner;
																			//var theForm = formContainer.up('UploadReviewActualsGrid');
																			//var theStore= theForm.getStore(); 
												                        	var jsonResponse = Ext.JSON.decode(action.response.responseText);
												                        	importCompanyStore.loadData(jsonResponse.data );
												                        	var entities = jsonResponse.data;
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
																  	               Ext.Msg.alert('Failure', 'Ext.form.action.Action.SERVER_INVALID');//action.result.msg
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