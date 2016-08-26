/*
 * Request with sorting and filtering cause problem:
 * post: https://www.sencha.com/forum/showthread.php?307156-ExtJs-6-problem-with-grid-filter-type-list
 * bug: https://www.sencha.com/forum/showthread.php?307161-ExtJs-6-problem-with-grid-filter-type-list
 * filter [{"operator":"in","value":[1],"property":"amountType"}]
 * limit	20
 * page	1
 * start	0
 * summary	false
 * 
 */
Ext.define('QuotaKPI.view.siteManagement.StoreGrid', {
	extend: 'Ext.grid.Panel'
	,xtype: 'StoreGrid'
	,itemId	: 'StoreGrid'
    ,columnLines: true
    ,store: 'siteManagement.Site'

	,columns: [ //{header     : 'Id',					dataIndex: 'id'},
			    {header     : 'storeName',			dataIndex: 'storeName'},
			    {header     : 'clientName',			dataIndex: 'clientName'},
			    {header     : 'storeDescription',	dataIndex: 'storeDescription'}
	]

	,dockedItems : [
	                {xtype: 'toolbar',dock: 'top',
					    items:	[
								{iconCls: 'icon-add',		itemId: 'storeCreate', text: 'Create Store', action: 'storeCreate'}
								,{iconCls: 'icon-add',		itemId: 'storeManage', text: 'Manage Products in Store', action: 'storeManage'}
						]
					}
	               /*,{xtype: 'pagingtoolbar', 
	            	   	dock:'bottom',
	            	   	store: 'accounting.Quota',
						displayInfo: true,
						displayMsg: 'Displaying Quota Details {0} - {1} of {2}',
						emptyMsg: "No Quota to display"
					}*/
	               ]
    	  ,initComponent: function() {
    		this.callParent(arguments);
    	}    	
   });