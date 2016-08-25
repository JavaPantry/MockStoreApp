var defaultLabelWidth = 150;
Ext.define('QuotaKPI.view.siteManagement.ProductForm' ,{
		extend	: 'Ext.Panel',
		alias	: 'widget.ProductForm',
		itemId	: 'ProductForm',
		layout:'vbox',
		items:[
				{xtype: 'fieldcontainer',
				combineErrors: false,
				minHeight : 90,
				anchor: '100% 20%',
				layout:'vbox',
				defaults: {xtype: 'fieldcontainer',layout:'hbox',margin:'1',labelPad:2}
				,items: [   
						{	defaults: {xtype: 'displayfield', labelWidth:defaultLabelWidth,labelCls:'fast-label'},
							items: [{fieldLabel:'User Id',itemId:'userId'}
									/*,{fieldLabel:'Sales Representative Name',itemId:'salesRepresentativeName'}*/]},
						{	defaults: {xtype: 'displayfield', labelWidth:defaultLabelWidth,labelCls:'fast-label'},
							items: [{fieldLabel:'First Name',itemId:'firstName'}
									,{fieldLabel:'User Name',itemId:'lastName'}]}
						]
				}
		    	//in successor use: this.items.push(getContent());//return {xtype:'panel',anchor: '100% 80%'}
		    ]

			,dockedItems: [{
			    xtype: 'toolbar',
			    dock: 'top',
			    layout: {pack: 'left'},
			    defaults: {minWidth: 80},
			    items: [
						,{text: 'Save',itemId:'productSave',iconCls: 'icon-save',action:'actionSave'}
						,'->'
						,{text: 'Return',itemId:'productCancel',iconCls: 'icon-return',action:'productEditCancel'}
						]
			}]
			,initComponent: function() {
				this.callParent(arguments);
			}

		});
