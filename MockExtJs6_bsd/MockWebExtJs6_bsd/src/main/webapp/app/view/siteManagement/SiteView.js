Ext.define('QuotaKPI.view.siteManagement.SiteView' ,{
	extend: 'Ext.tab.Panel',
    alias : 'widget.SiteView',
    items:[	
		{iconCls: 'icon-organisation', title: 'Bsd Site Admin', xtype:'SiteGrid',listeners: {
   	             activate: function(tab){
 	            	
		             var rootViewer = this.up('rootViewer');
		             rootViewer.setTitle('Store list'); 
 	            	 var theGrid = tab;//.down('SiteGrid');
 	            	 var theStore = theGrid.store;
 	            	 //theStore.remoteSort = true;
 	            	 //theStore.remoteFilter = true;
 	            	 theStore.load();
 	            	console.log(''+theStore);
 	             	}
 			}}
		,{iconCls: 'icon-organisation', title: 'Products', xtype:'ProductGrid',listeners: {
   	             activate: function(tab){
 	            	
		             var rootViewer = this.up('rootViewer');
		             rootViewer.setTitle('Products list'); 
 	            	 var theGrid = tab;//.down('ProductGrid');
 	            	 var theStore = theGrid.store;
 	            	 theStore.load();
 	            	 console.log(''+theStore);
 	             	}
 			}}
		,{iconCls: 'icon-organisation', title: 'Bsd Store Admin', xtype:'panel'}
		,{iconCls: 'icon-organisation', title: 'Users', xtype:'panel'}
    ]
});