Ext.define('QuotaKPI.view.siteManagement.SiteView' ,{
	extend: 'Ext.tab.Panel',
    alias : 'widget.SiteView',
    items:[	
		{iconCls: 'icon-organisation', title: 'Bsd Site Admin', xtype:'StoreView',listeners: {
   	             activate: function(tab){
 	            	
		            var rootViewer = this.up('rootViewer');
		            rootViewer.setTitle('Stores list'); 
 	            	var theGrid = tab.down('StoreGrid');
 	            	var theStore = theGrid.store;//theStore.remoteSort = true;//theStore.remoteFilter = true;
 	            	theStore.load();
 	             	}
 			}}
		,{iconCls: 'icon-organisation', title: 'Products', xtype:'ProductView',listeners: {
   	             activate: function(tab){
		            var rootViewer = this.up('rootViewer');
		            rootViewer.setTitle('Products list'); 
 	            	var theGrid = tab.down('ProductGrid');
 	            	var theStore = theGrid.store;
 	            	theStore.load();
 	             	}
 			}}
		,{iconCls: 'icon-organisation', title: 'Users', xtype:'BsdUserView',listeners: {
	             activate: function(tab){
			            var rootViewer = this.up('rootViewer');
			            rootViewer.setTitle('Users list'); 
	 	            	var theGrid = tab.down('BsdUserGrid');
	 	            	var theStore = theGrid.store;
	 	            	theStore.load();
	 	             	}
	 			}}
		,{iconCls: 'icon-organisation', title: 'Bsd Store Admin', xtype:'panel'}
    ],
    
    /*can't see in scope with this or self    
    refresh: function(tab, title){
    	var rootViewer = this.up('rootViewer');
        rootViewer.setTitle(title); 
    	var theGrid = tab;//.down('ProductGrid');
    	var theStore = theGrid.store;
    	theStore.load();
    }*/

});