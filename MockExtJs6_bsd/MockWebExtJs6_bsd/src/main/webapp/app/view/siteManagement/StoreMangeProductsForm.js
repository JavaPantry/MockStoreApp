var defaultLabelWidth = 150;
Ext.define('QuotaKPI.view.siteManagement.StoreMangeProductsForm' ,{
		extend	: 'Ext.Panel',
		alias	: 'widget.StoreMangeProductsForm',
		itemId	: 'StoreMangeProductsForm',
		layout:'anchor',
		items:[
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
