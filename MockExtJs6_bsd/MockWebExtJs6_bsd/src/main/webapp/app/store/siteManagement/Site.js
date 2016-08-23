Ext.define('QuotaKPI.store.siteManagement.Site', {
    extend: 'Ext.data.Store',
    model: 'QuotaKPI.model.siteManagement.SiteModel',
    storeId: 'siteManagement.Site',
    autoLoad: false,
    pageSize: 20,
    /*
     * due to bug 'autoLoad:false ignored for remote sort and remote Filter' disable remote sorting and filtering and turn it on when pass initial spring security
     */
    remoteSort: false,
    remoteFilter: false,

    proxy: {
        type: 'ajax',
        api: {read	: 'ajax/quotas'
        	,update : 'ajax/quotas/update'
            ,create : 'ajax/quotas/create'
            ,destroy: 'ajax/quotas/delete'},
        reader: {type: 'json',
	            rootProperty: 'data',
	            successProperty: 'success',
	            messageProperty: 'message',
	            totalProperty: 'total'},
        writer: {type: 'json',
	            allowSingle: false,
	            rootProperty: 'data',
	            writeAllFields: true,
	            encode: false},
        listeners: {
        	success: function(proxy, response, operation){
                /*var resp = Ext.JSON.decode(response.responseText,true);
                var message = '';
                if (resp != null){
                    message = resp.message;
                }
				Ext.MessageBox.show({title: 'Success Quota request.', msg: message,
					icon: Ext.MessageBox.ERROR,buttons: Ext.Msg.OK});*/
        	},
            exception: function(proxy, response, operation){
            	
            	if(response.status === 403){
            		console.log('response.status === 403')
            	}
            	if(response.statusText === 'Forbidden'){
            		console.log('response.statusText === Forbidden')
            	}

            	if(response.responseText != undefined && response.responseText.indexOf("<!DOCTYPE html>") > -1){
            		console.log('"<!DOCTYPE html>" detected in ajax response... redirect to login.')
            		window.location.assign('login');
            		return;
            	}
            	
                var resp = Ext.JSON.decode(response.responseText,true);
                var message = '';
                if (resp != null){
                    message = resp.message;
                }
				Ext.MessageBox.show({title: 'REMOTE EXCEPTION.', msg: message,
									icon: Ext.MessageBox.ERROR,buttons: Ext.Msg.OK});
            }
        }
    }//eof proxy
	
});