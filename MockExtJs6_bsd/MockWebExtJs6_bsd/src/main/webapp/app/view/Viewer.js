Ext.define('QuotaKPI.view.Viewer', {
    extend: 'Ext.tab.Panel',
    alias: 'widget.rootViewer',
    title: 'QuotaKPI', activeItem: 0, margins: '1 1 1 1', cls: 'preview',
    iconCls: 'icon-chart',
    requires: [
        'Ext.direct.*',
        'Ext.data.*'
	]
/*
var quotaUser = true;    
var budgetUser = false;    
var companyUser = false;    
var reportUser = false;    
var adminUser = false;    
*/

    ,initComponent: function() {
        this.items = [
                		{iconCls: 'tabs', xtype:'QuotaView', title: 'Quota',disabled: !quotaUser 
            			,listeners: {
            	             activate: function(tab){
            	            	
            	            	//TEST start
            	            	//var store1 = Ext.getStore('accounting.Quota');
            	            	//var store2 = Ext.data.StoreManager.lookup('QuotaKPI.store.accounting.Quota');
            	            	//console.log('QuotaKPI.view.Viewer store2='+store2);
            	            	 
            	            	 //TODO - <AP> TypeError: Ext.data.StoreManager.lookup(...) is undefined
            	            	 //var storeLookupId = Ext.data.StoreManager.lookup('QuotaKPI.store.accounting.Quota').getId(); 
            	            	 //var storeLookupTypeId = Ext.data.StoreManager.lookup({type: 'accounting.Quota'}).getId();            	            	 
            	            	 //console.log('QuotaKPI.view.Viewer storeLookupId = '+storeLookupId +'storeLookupTypeId = '+storeLookupTypeId);
            	            	//store2.load();
            	            	//TEST end
            	            	
        		            	 var rootViewer = this.up('rootViewer');
        		            	 rootViewer.setTitle('QuotaKPI: Quotas list'); 
            	            	 var theGrid = tab.down('QuotaGrid');
            	            	 
            	            	//TEST start
            	            	 //theGrid.store = Ext.data.StoreManager.lookup('QuotaKPI.store.accounting.Quota');
            	            	//TEST end
            	            	 var theStore = theGrid.store;
            	            	 theStore.remoteSort = true;
            	            	 theStore.remoteFilter = true;
            	            	 theStore.load();
            	            	 console.log('QuotaKPI.view.Viewer theStore='+theStore);
            	             	}
            			}}
            		,{iconCls: 'tabs', xtype: 'BudgetView',	title: 'Budget',disabled: !budgetUser
            			, listeners: {
            	             activate: function(tab){
        		            	 var rootViewer = this.up('rootViewer');
        		            	 rootViewer.setTitle('QuotaKPI: Budget list'); 

            	            	 var theGrid = tab.down('BudgetGrid');
            	            	 var theStore = theGrid.store;
            	            	 theStore.remoteSort = true;
            	            	 theStore.remoteFilter = true;
            	            	 theStore.load();
            	             	}
            	         	}
            		}

			,	{iconCls: 'tabs', xtype: 'SalesRepresentativeMaintenanceView',title: 'IDS Security',disabled: !companyUser//, id: 'SalesRepresentativeMaintenanceView'
				, listeners: {
		             activate: function(tab){
		            	 var rootViewer = this.up('rootViewer');
		            	 rootViewer.setTitle('QuotaKPI: Sales Representatives list'); 
		            	 //reload store
		            	 var theGrid = tab.down('SalesRepresentativesGrid');
		            	 // there no filters theGrid.filters.clearFilters();
		            	 var theStore = theGrid.store;
    	            	 theStore.remoteSort = true;
    	            	 theStore.remoteFilter = true;
						 //if (theStore.isFiltered())
							//theStore.clearFilter();
		            	 theStore.load();
						}
		         	}
		         }
			,{iconCls: 'icon-organisation', xtype: 'ImportView', title: 'Import entities'}
			,{iconCls: 'icon-organisation', xtype: 'ChartView', title: 'Charts'}
			,{iconCls: 'tabs', xtype:'SiteView', title: 'Bsd Store'//,disabled: !quotaUser 
    			,listeners: {
   	             activate: function(tab){
		             var rootViewer = this.up('rootViewer');
		             rootViewer.setTitle('BSD: Store list'); 
   	            	 var theGrid = tab.down('SiteGrid');
   	            	 var theStore = theGrid.store;
   	            	 theStore.remoteSort = true;
   	            	 theStore.remoteFilter = true;
   	            	 theStore.load();
   	            	 console.log('QuotaKPI.view.Viewer theStore='+theStore);
   	             	}
   			}}
			];
        this.callParent(arguments);
    }

});

