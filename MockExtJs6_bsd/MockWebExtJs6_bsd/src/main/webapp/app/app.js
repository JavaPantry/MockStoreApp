Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*',
    'Ext.form.*',
    'Ext.QuickTips.*',
    'Ext.grid.filters.Filters',
    'Ext.toolbar.Toolbar',
    'Ext.toolbar.Paging'
]);

//extJs 6.0.1.x:Ext.require(['feature.filters','Ext.ux.grid.FiltersFeature']);
//Ext.require(['Ext.grid.filters.Filters']);

Ext.define('Ext.grid.feature.Summary', {// referred as 'Ext.grid.feature.RemoteSummary'
    override: 'Ext.grid.feature.Summary',
    //alias: 'remotesummary',
    // adds 'remote' summary type
    getSummary: function (store, type, field, group) {
        if (type === 'remote') {
            return this.getRemoteSummaryRecord(store).get(field);
        } else {
            return this.callParent(arguments);
        }
    },
    
    // helper for our custom summary type, mainly copied from:
    // http://docs.sencha.com/extjs/4.2.3/source/AbstractSummary.html#Ext-grid-feature-AbstractSummary-method-generateSummaryData
    getRemoteSummaryRecord: function(store) {
        if (!this.remoteSummaryRecord) {
            var reader = store.proxy.reader,
                remoteRoot = this.remoteRoot,
                root;
            
            if (remoteRoot && reader.rawData) {
                root = reader.root;
                reader.root = remoteRoot;
                reader.buildExtractors(true);
                
                this.remoteSummaryRecord = reader.read(reader.rawData).records[0];
                
                if (!reader.convertRecordData) {
                    reader.buildExtractors();
                }
                
                reader.root = root;
                reader.buildExtractors(true);
            }
        }
        if (!this.remoteSummaryRecord) {
            this.remoteSummaryRecord = store.model.create({});
        }
        return this.remoteSummaryRecord;
    }
    
    // ensure our remoteSummaryRecord stay fresh
    ,onStoreUpdate: function() {
        delete this.remoteSummaryRecord;
        return this.callParent(arguments);
    }
});

Ext.application({
    name: 'QuotaKPI',
	//extJs 6.0.1.x:renderTo: Ext.getBody(),
    views: [
            //Define all views not here, but in controller>'QuotaKPI.view.accounting.BudgetView',
            //Define all views not here, but in controller>'QuotaKPI.view.accounting.QuotaView'
            //Define all views not here, but in controller> ,'QuotaKPI.view.accounting.ChartView'
        ],

        models: [
            'QuotaKPI.model.accounting.QuotaModel'
        ],

        stores: [
            'QuotaKPI.store.accounting.Quota',
            'QuotaKPI.store.accounting.Budget'
        ],

    // Define all the controllers that should initialize at boot up of your application
    controllers: [
        'ImportController'
        ,'accounting.ChartController'
        ,'accounting.AccountingController'
        ,'company.MaintenanceController'
        ,'siteManagement.SiteManagementController'
    ],

    init: function(app){
    	//debugger;
		//As a bad alternative to spring security Here you may set control flags isProductAccessible = isProductAccessible=='true'?false:true; 
		////console.log('QuotaKPI app loaded controllers = '+controllers);    	
    },
    
    autoCreateViewport: true
});
