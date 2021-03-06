/*
 * Request with sorting and filtering cause problem:
 * post: https://www.sencha.com/forum/showthread.php?307156-ExtJs-6-problem-with-grid-filter-type-list
 * bug: https://www.sencha.com/forum/showthread.php?307161-ExtJs-6-problem-with-grid-filter-type-list
 * filter [{"operator":"in","value":[1],"property":"amountType"}]
 * limit	20
 * page	1
 * start	0
 * summary	false
 */

Ext.define('QuotaKPI.view.siteManagement.ProductGridInStore', {
	extend: 'Ext.grid.Panel'
	,xtype: 'ProductGridInStore'
	,itemId	: 'ProductGridInStore'
    ,columnLines: true
    ,multiSelect: true
    ,stripeRows: true
    ,store: 'siteManagement.ProductInStore'
    ,viewConfig: {
        plugins: {
            ptype: 'gridviewdragdrop',
//            dragGroup: group1,
//            dropGroup: group2
        },
        listeners: {
            drop: function(node, data, dropRec, dropPosition) {
                var dropOn = dropRec ? ' ' + dropPosition + ' ' + dropRec.get('name') : ' on empty view';
                //Ext.example.msg('Drag from right to left', 'Dropped ' + data.records[0].get('name') + dropOn);
            }
        }
    }
	,plugins: [Ext.create('Ext.grid.plugin.CellEditing', {
	   	clicksToEdit: 1
	   	,pluginId : 'productPriceInStoreEditor' // to find editor by grid.getPlugin('rowEditingMonthEditGridExt').editor.form.findField('name')
	   	//,listeners : {'beforeedit' : function(editor, e, eOpts) {}}
	})
	//,'gridfilters'
	] // plugins

	,columns: [ {header		: 'sku', flex:1,					dataIndex:'sku'}
			    ,{header	: 'Product Name', flex:2,			dataIndex:'EProductName'}
			    ,{header	: 'Product Description', flex:4,	dataIndex:'EProductDescription'}
			    
				,{header	: 'Scheduled Price', flex:4,	dataIndex:'priceScheduled',editor:{xtype:'numberfield',minValue: 0}}
				,{header	: 'Scheduled Date', flex:4,	dataIndex:'priceSchedule', xtype:'datecolumn', format:'m-d-Y', renderer : Ext.util.Format.dateRenderer('m-d-Y')}
			    ,{header	: 'Price in Store', flex:1,			dataIndex:'price',editor:{xtype:'numberfield',minValue: 0}}
	]

	,dockedItems : [
					/*{xtype: 'toolbar',dock: 'top',
					    items:	[
								{iconCls: 'icon-add',		itemId: 'productCreate', text: 'Create Product', action: 'productCreate'}
								]}*/
	               /*,{xtype: 'pagingtoolbar', 
	            	   	dock:'bottom',
	            	   	store: 'siteManagement.Product',
						displayInfo: true,
						displayMsg: 'Displaying Quota Details {0} - {1} of {2}',
						emptyMsg: "No Quota to display"
					}*/
	               ]
    	  ,initComponent: function() {
    		this.callParent(arguments);
    	}    	
   });