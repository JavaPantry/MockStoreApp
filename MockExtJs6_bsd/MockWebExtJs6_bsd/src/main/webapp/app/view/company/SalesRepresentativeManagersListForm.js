var salesRepresentativeManagersListFormStore = new Ext.data.JsonStore({
		storeId: 'salesRepresentativeManagersListFormStore'
		,autoLoad: false
		//TBR: - ,model: 'QuotaKPI.model.company.ProductLineModel'
		,model: 'QuotaKPI.model.company.SalesRepresentativeManagerModel'
		,proxy:	{type:'ajax',
				api: {
					read	: 'ajax/salesRepManagers',
					//update: 'ajax/updateSalesRepManagers',// covered by ManagerDetailForm: create/delete by ajax/updateProductLines'
					destroy	: 'ajax/deleteSalesRepManagers'
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
		                Ext.MessageBox.show({
		                    title: 'Remote Access Failed',
		                    msg: resp.message,
		                    icon: Ext.MessageBox.ERROR,
		                    buttons: Ext.Msg.OK
		                });
		            }
		        }
		}
    });


Ext.define('QuotaKPI.view.company.SalesRepresentativeManagersListForm' ,{
		extend	: 'QuotaKPI.view.company.BaseDetailView',
		alias	: 'widget.SalesRepresentativeManagersListForm',
		itemId	: 'SalesRepresentativeManagersListForm',
		initComponent: function() {
			this.removePreviousSiblingContentIfAnyHack('TOCPanel');
			this.items.push(this.getContent());
			this.callParent(arguments);
			this.down('#itemSave').hide();
			this.down('#itemEdit').hide();
			//this.down('#itemAdd').hide();
			//this.down('#itemDelete').hide();
		},
		getContent: function(){
			return	{	xtype:'grid',
						itemId:'ManagerPanel',
					    columnLines: true,
					    store: salesRepresentativeManagersListFormStore,
					    //Ext.create('Ext.data.Store', {fields: ['employeeId','name','productLinesContentStr'],data: []}),
						anchor: '100% 80%',
					    columns:[	{header: "Sales Representative Id", hidden:true, flex:1,dataIndex: 'salesRepresentativeId'}//.managerId
					    			,{header: "Manager Id", hidden:true, flex:1,dataIndex: 'employeeId'}//.managerId
									,{header: "Manager Name",flex:5,dataIndex: 'name'}
									,{header: "Product Lines",flex:5,dataIndex: 'productLinesContentStr'}
								],
						viewConfig: {
							/*
							 * how to add dblclick listener
							 * http://stackoverflow.com/questions/8010949/extjs-how-to-register-events-in-a-dynamically-created-grid-panel-in-mvc 
							 */
					        listeners: {
					        	'itemdblclick': function(view, selectedRecord, item, index, eventobj, obj) {
					        		this.fireEvent('openManagerDetailForm', view, selectedRecord, item, index, eventobj, obj);
				                 }
					        }
						}
					};
		}
    });

