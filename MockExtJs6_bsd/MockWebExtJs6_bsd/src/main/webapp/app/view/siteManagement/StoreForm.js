var defaultLabelWidth = 150;
Ext.define('QuotaKPI.view.siteManagement.StoreForm' ,{
		extend	: 'Ext.Panel',
		alias	: 'widget.StoreForm',
		itemId	: 'StoreForm',
		layout:'anchor',
		items:[

				{xtype: 'form'
						,url: 'bsd/updatestore'
						,padding: '5 5 5 5'
						,anchor: '100% 100%'
						,border: false 
						,style: 'background-color: #faa;'
						,defaults: {labelWidth:200, 
									anchor: '100% 100%',
									labelPad:2, 
									labelAlign: 'left',
									allowBlank: false,
									combineErrors: true,
									msgTarget: 'side'
									}
						,items: [
									{xtype: 'fieldcontainer',
//									combineErrors: false,
//									minHeight : 90,
									anchor: '100% 100%',
									style: 'background-color: #aaa;',
									layout:'vbox',
									defaults: {xtype: 'fieldcontainer',layout:'hbox',margin:'1',labelPad:2}
									,items: [   
											{	defaults: {xtype: 'textfield', labelWidth:defaultLabelWidth,labelCls:'fast-label'},
												items: [{fieldLabel:'Store Name',itemId:'StoreName', name:'storeName' }
														/*,{fieldLabel:'Sales Representative Name',itemId:'salesRepresentativeName'}*/]},
											{	defaults: {xtype: 'textfield', labelWidth:defaultLabelWidth,labelCls:'fast-label'},
												items: [{fieldLabel:'Store Description',name:'storeDescription'}
														/*,{fieldLabel:'User Name',name:'lastName'}*/]}
											]//eof fieldcontainer items
									}//eof fieldcontainer
								]//eof form items
				}//eof form
			]//eof items of BsdUserForm				
				
			,dockedItems: [{
			    xtype: 'toolbar',
			    dock: 'top',
			    layout: {pack: 'left'},
			    defaults: {minWidth: 80},
			    items: [
						{text: 'Save',itemId:'bsdUserSave',iconCls: 'icon-save',action:'actionSave'}
						,'->'
						,{text: 'Return',itemId:'bsdUserCancel',iconCls: 'icon-return',action:'editCancel'}
						]
			}]//eof dockedItems

			,initComponent: function() {
				this.callParent(arguments);
			}

		});
