var tocListFormStore = new Ext.data.JsonStore({
		storeId: 'tocListFormStore'
		,autoLoad: false
		,model: 'QuotaKPI.model.company.TocModel'
		,proxy:	{type:'ajax',
				api: {
					read	: 'ajax/salesRepTocs',
					//update: 'ajax/updateSalesRepManagers',
					destroy	: 'ajax/deleteSalesRepTocs'
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
Ext.define('QuotaKPI.view.company.TOCListForm' ,{
		extend	: 'QuotaKPI.view.company.BaseDetailView',
		alias	: 'widget.TOCListForm',
		itemId	:	'TOCListForm',
		initComponent: function() {
			this.removePreviousSiblingContentIfAnyHack('ManagerPanel');
			this.items.push(this.getContent());
			this.callParent(arguments);
			this.down('#itemSave').hide();
			this.down('#itemEdit').hide();
		},
		
		getContent: function(){
			return	{	xtype:'grid',
						//title:'TOC inserted in successor', 
						itemId:'TOCPanel',
					    columnLines: true,
					    //memory: store: Ext.create('Ext.data.Store', {fields: ['tocId','tocName'],data: []}),
					    store: tocListFormStore,
					    selModel: Ext.create('Ext.selection.CheckboxModel'),
						anchor: '100% 80%',
					    columns: [	{header: "TOC Id",flex:1,dataIndex: 'tocId'}
									,{header: "TOC Name",flex:5,dataIndex: 'tocName'}
						]
					};
		}
    });//end of UserDetail

