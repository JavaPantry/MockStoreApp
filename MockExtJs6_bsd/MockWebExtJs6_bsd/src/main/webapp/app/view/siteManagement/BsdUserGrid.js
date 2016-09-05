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

Ext.define('QuotaKPI.view.siteManagement.BsdUserGrid', {
	extend: 'Ext.grid.Panel'
	,xtype: 'BsdUserGrid'
	,itemId	: 'BsdUserGrid'
    ,columnLines: true
    ,store: 'siteManagement.BsdUser'
	,columns: [ {header     : 'id',					dataIndex: 'id'}//TODO - <AP> remove id BsdUser grid
			    ,{header     : 'userId',			dataIndex: 'userId'}
			    ,{header     : 'firstName',			dataIndex: 'firstName'}
			    ,{header     : 'lastName',			dataIndex: 'lastName'}
			    ,{header     : 'email',				dataIndex: 'email'}
			    ]

	,dockedItems : [{xtype: 'toolbar',dock: 'top',
							    items:	[
										{iconCls: 'icon-add',		itemId: 'bsdUserCreate',text: 'Create User',action: 'bsdUserCreate'}
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