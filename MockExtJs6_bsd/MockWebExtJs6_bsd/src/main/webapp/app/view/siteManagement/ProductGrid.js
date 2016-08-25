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

Ext.define('QuotaKPI.view.siteManagement.ProductGrid', {
	extend: 'Ext.grid.Panel'
	,xtype: 'ProductGrid'
	,itemId	: 'ProductGrid'
    ,columnLines: true
    ,store: 'siteManagement.Product'
	,columns: [ {header     : 'sku',					dataIndex: 'sku'}
			    ,{header     : 'Product Name',			dataIndex: 'EProductName'}
			    ,{header     : 'Product Description',			dataIndex: 'EProductDescription'}
	]

	,dockedItems : [
					{xtype: 'toolbar',dock: 'top',
					    items:	[
								{iconCls: 'icon-add',		itemId: 'productCreate', text: 'Create Product', action: 'productCreate'}
								]}
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