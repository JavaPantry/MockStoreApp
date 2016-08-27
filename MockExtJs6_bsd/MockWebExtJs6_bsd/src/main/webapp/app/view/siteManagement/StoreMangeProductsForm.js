var defaultLabelWidth = 150;
Ext.define('QuotaKPI.view.siteManagement.StoreMangeProductsForm' ,{
		extend	: 'Ext.Panel'
		,alias	: 'widget.StoreMangeProductsForm'
		,itemId	: 'StoreMangeProductsForm'
		,layout: 'border'
		,items:[{xtype:'panel'
					,title:'Client and administration form'
					,region: 'north'
					,split: true
					,height:'20%'
					,html:'<h4>Store Client and admin form</h4>'
					,collapsible: true
					,collapsed: true
				}
				,{xtype:'ProductGridInStore'
					,region: 'west'
					,width: '45%'
					,margins: '10 25 55 95'
				}
				,{xtype:'toolbar'
					,region: 'center'
					,width: '10%'
					,margins: '10 10 10 10'
					,layout: {
				        type: 'vbox',
				        pack: 'center',
				    }
					,items:[
					        {xtype:'button',text: '<',width: '100%'}//,iconCls: 'icon-save'
					        ,{xtype:'button',text: '<<',width: '100%'}
					        ,{xtype:'button',text: '>>',width: '100%'}
					        ,{xtype:'button',text: '>',width: '100%'}
					        ]
				}
				,{xtype:'ProductGridAvailableForStore'
					,region:'east'
					,width: '45%'
				}
		      ]//eof items of BsdUserForm				
				
			,dockedItems: [{
			    xtype: 'toolbar',
			    dock: 'top',
			    layout: {pack: 'left'},
			    defaults: {minWidth: 80},
			    items: [
						{text: 'Save',itemId:'storeMangeProductsFormSave',iconCls: 'icon-save',action:'actionSave'}
						,'->'
						,{text: 'Return',itemId:'storeMangeProductsFormCancel',iconCls: 'icon-return',action:'editCancel'}
						]
			}]//eof dockedItems

			,initComponent: function() {
				this.callParent(arguments);
			}

		});
