Ext.define('QuotaKPI.view.company.SalesRepresentativeMaintenanceView' ,{
    extend: 'Ext.Panel',
    alias : 'widget.SalesRepresentativeMaintenanceView',
    iconCls: 'icon-grid',
    columnLines: true,
    layout:'card',
    items:[	{xtype:'SalesRepresentativesGrid'
    		, listeners: {activate: function(tab){
            	 var rootViewer = this.up('rootViewer');
            	 rootViewer.setTitle('QuotaKPI: Company Hierarchy.');
            	 //reload store
            	 var theGrid = tab;//.down('SalesRepresentativesGrid');
            	 // there no filters theGrid.filters.clearFilters();
            	 var theStore = theGrid.store;
				 if (theStore.isFiltered())
					theStore.clearFilter();
            	 theStore.load();
             }}}
    		,{xtype:'SalesRepresentativeDetailForm'
    		, listeners: {activate: function(tab){
            	 var rootViewer = this.up('rootViewer');
            	 rootViewer.setTitle('QuotaKPI: Sales Representative - Details.'); 
             }}}
			,{xtype:'SalesRepresentativeManagersListForm'
    		, listeners: {activate: function(tab){
            	 var rootViewer = this.up('rootViewer');
            	 rootViewer.setTitle('QuotaKPI: Manager - Details.');
            	/* //reload store
            	 var theGrid = tab.down('SalesRepresentativeManagersListForm');
            	 // there no filters theGrid.filters.clearFilters();
            	 var theStore = theGrid.store;
				 if (theStore.isFiltered())
					theStore.clearFilter();
            	 theStore.load();*/
             }}}
    		,{xtype:'TOCListForm'
        		, listeners: {activate: function(tab){
                	 var rootViewer = this.up('rootViewer');
                	 rootViewer.setTitle('QuotaKPI: TOC - Details.'); 
                	 //reload store
                	 /*var theGrid = tab.down('TOCListForm');
                	 // there no filters theGrid.filters.clearFilters();
                	 var theStore = theGrid.store;
    				 if (theStore.isFiltered())
    					theStore.clearFilter();
                	 theStore.load();*/
                 }}}
    		,{xtype:'ManagerDetailForm'
        		, listeners: {activate: function(tab){
                	 var rootViewer = this.up('rootViewer');
                	 rootViewer.setTitle('QuotaKPI: Manager - Details.'); 
                 }}}
    		,{xtype:'TocDetailForm'
        		, listeners: {activate: function(tab){
                	 var rootViewer = this.up('rootViewer');
                	 rootViewer.setTitle('QuotaKPI: TOC - Details.'); 
                 }}}
			]
});
