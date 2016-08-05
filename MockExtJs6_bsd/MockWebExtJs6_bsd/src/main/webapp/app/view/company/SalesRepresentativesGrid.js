Ext.define('QuotaKPI.view.company.SalesRepresentativesGrid' ,{
    extend: 'Ext.grid.Panel',
    xtype: 'SalesRepresentativesGrid',
    itemId	: 'SalesRepresentativesGrid',//required for back button
    columnLines: true,
    store: 'company.SalesRepresentative',
	plugins: ['gridfilters'],
    /*render button in cell solution: http://www.techmix.net/2010/11/25/add-button-to-extjs-gridpanel-cell-using-renderer/ 
    http://stackoverflow.com/questions/9534077/extjs-4-grid-disable-rowselection-for-specific-column/*/
    viewConfig: {
        listeners: {
            beforecellmousedown: function(view, cell, cellIdx, record, row, rowIdx, eOpts){
                if(cellIdx === 2){
                	console.log('click over user '+record.get('userId'));
    				this.fireEvent('openUserForm', record.get('userId'));
                }else if(cellIdx === 4){
                    console.log('click over manager'+record.get('managerId'));
    				this.fireEvent('openManagerListForm', record.get('managerId'), record);
                }
                else if(cellIdx === 5){
                    console.log('click over TOC'+record.get('tocName'));
    				this.fireEvent('openTocListForm', record.get('tocId'), record);
                }
            }
        }
    },
    
    columns: [	{header: "Sales Representative Id",flex:1,dataIndex: 'salesRepresentativeId'
			    	,filter: {type: 'string', dataIndex: 'salesRepresentativeId'}
			    }
				,{header: "Sales Representative Name",flex:2,dataIndex: 'salesRepresentativeName'
					,filter: {type: 'string', dataIndex: 'salesRepresentativeName'}
				}
				,{header: "UserId", hidden:true, flex:1,dataIndex: 'userId'}
				,{header: "User Name: Id", flex:2,dataIndex: 'userName',
							renderer: function(value, meta, record, rowIndex) {
								return Ext.String.format('{0}: {1}',record.get('userName'),record.get('userId'));
							}
							,filter: {type: 'string', dataIndex: 'userName'}
				}
				,{header: "Manager", flex:1,dataIndex: 'managerId',
							renderer: function(value, meta, record, rowIndex) {
								return Ext.String.format('<a href="#">{0}</a>',record.get('managerName'));
							}
				}
				,{header: "TOC: Id", flex:1,dataIndex: 'tocName',
					renderer: function(value, meta, record, rowIndex) {
						var tocName = record.get('tocName');
						if(tocName === undefined || tocName === '')
							return 'N/A';
						else
							return Ext.String.format('<a href="#">{0}:{1}</a>',record.get('tocName'),record.get('tocId'));
					}
				}
				,{header: "All Access",flex:1,dataIndex: 'allAccess'
					,renderer: function(value){
            	        if (value == true) {
            	            return 'Yes';
            	        }else {
            	            return 'No';
            	        }
            	 }}
				],
	
initComponent: function() {
		this.dockedItems = [
			{xtype: 'toolbar',
            dock:'top',
            items: [{text: 'New Representative',iconCls: 'icon-user-add',itemId: 'companySalesRepresentativeAdd',action: 'editSalesRepDetailAdd'}
					,{text: 'Edit Representative',iconCls: 'icon-modify',itemId: 'companySalesRepresentativeEdit',minWidth: 80,action: 'editSalesRepDetail'}
					,{text: 'Delete Representative',iconCls: 'icon-modify',itemId: 'companySalesRepresentativeDelete',minWidth: 80,action: 'deleteSalesRepDetail'}]
			}
			,{xtype: 'pagingtoolbar',
		            dock:'bottom',
		            store: 'company.SalesRepresentative',
		            //extJs 6.0.1.x:store: store: 'company.SalesRepresentative',
					store: Ext.getStore('company.SalesRepresentative'),
		            displayInfo: true,
		            displayMsg: 'Displaying SalesRepresentative {0} - {1} of {2}',
		            emptyMsg: "No SalesRepresentative to display"
		      }];
		this.callParent(arguments);
	}
});


