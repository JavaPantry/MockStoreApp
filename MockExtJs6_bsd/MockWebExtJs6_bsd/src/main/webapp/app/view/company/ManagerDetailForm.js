var productLineStore = new Ext.data.JsonStore({
							storeId: 'ProductLineStore'
							,model: 'QuotaKPI.model.company.ProductLineModel'
							,autoLoad: false
							,proxy:	{type:'ajax',
									api: {
										read : 'ajax/productLines',
										update : 'ajax/updateProductLines'
									}
									,reader: {
							            type: 'json',
							            //extJs 6.0.1.x: root: 'data',
							            rootProperty: 'data',

							            successProperty: 'success',
							            totalProperty : 'total'
							        }
							        ,writer: {
							            type: 'json',
							            allowSingle: false,
							            //extJs 6.0.1.x: root: 'data',
							            rootProperty: 'data',

							            writeAllFields: true,
							            encode: false
							        }
							        ,listeners: {
							            exception: function(proxy, response, operation){
							            	if(response.responseText != undefined && response.responseText.indexOf("<!DOCTYPE html>") > -1){
							            		console.log('"<!DOCTYPE html>" detected in ajax response... redirect to login.')
							            		window.location.assign('login');
							            		return;
							            	}
							            	var resp = Ext.JSON.decode(response.responseText,true);
							            	//console.log('ManagerDetailForm response = '+response+', responseText = '+response.responseText)
							                Ext.MessageBox.show({
							                    title: 'Remote Access Failed',
							                    msg: 'ManagerDetailForm: Error on server side',//resp.msg,
							                    icon: Ext.MessageBox.ERROR,
							                    buttons: Ext.Msg.OK
							                });
							            }
							        }
							}
					    });

var remoteManagerListStore = new Ext.data.JsonStore({
	storeId: 'remoteManagerListStore',
	proxy: {
	type: 'ajax',
	api: {		read : 'ajax/users' //need to pass salesRepId to exclude already taken employees 
	},
	reader: {	type: 'json',
	            //extJs 6.0.1.x: root: 'data',
	            rootProperty: 'data',

				successProperty: 'success'
			}
	},
	fields: ['employeeId','name']
	});

Ext.define('QuotaKPI.view.company.ManagerDetailForm' ,{
		extend	: 'QuotaKPI.view.company.BaseDetailView',
		alias	: 'widget.ManagerDetailForm',
		itemId	: 'ManagerDetailForm',
		initComponent: function() {
			this.removePreviousSiblingContentIfAnyHack('TOCPanel');
			this.items.push(this.getContent());
			this.callParent(arguments);
			this.down('#itemDelete').hide();
			this.down('#itemAdd').hide();
			this.down('#itemEdit').hide();
		},
		
		getContent: function(){
			return	{xtype: 'container',
				anchor: '100% 80%',
				itemId:'ManagerDetailPanel',
				items: [{xtype: 'combo', labelWidth:defaultLabelWidth,labelCls:'fast-label',
							queryMode: 'remote',//if remote combo will be loaded twice on first time click over arrow btn
		                	forceSelection: true,
		                	fieldLabel: 'Manager Name',
		                	name: 'employeeId',
		                	itemId:'managerId',
		                	displayField: 'name',
		                	valueField: 'employeeId',
		                	store: remoteManagerListStore,
	                    	editable: true,
				            typeAhead: true,
				            minChars:1,
				            listConfig: {
				                loadingText: 'Searching...',
				                emptyText: 'No matching found.',
				                getInnerTpl: function() {return '{name} : <i><font color="blue">{employeeId}</font></i>';}
					            //getInnerTpl: function() {return '<span style="font-weight:bold">{name}</span> : <i><font color="blue">{userId}</font></i>';}
				            }

		                	//,listeners : {change :function (combo, newValue, oldValue){}}
						}
						,{xtype:'grid',
						    columnLines: true,
						    store: productLineStore,
						    itemId:'ProductLineGrid',
							//title:'ManagerDetailForm inserted in successor',
						    columns: [	{header: 'salesRepresentativeId',hidden:true,dataIndex: 'salesRepresentativeId'}
						    			,{header: 'managerId',hidden:true,dataIndex: 'managerId'}
						              	,{header: 'Product Line Code',flex:1,dataIndex: 'code'}
						    			,{header: 'Product Line Name',flex:6,dataIndex: 'description'}
										,{header: 'Selected',flex:1, xtype:'checkcolumn',dataIndex: 'exists'}
							]//columns
						}//grid
						]
					};
			}
		});

