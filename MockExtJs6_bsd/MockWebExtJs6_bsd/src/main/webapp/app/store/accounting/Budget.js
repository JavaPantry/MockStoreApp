Ext.define('QuotaKPI.store.accounting.Budget', {
    extend: 'Ext.data.Store',
    model: 'QuotaKPI.model.accounting.QuotaModel',
    storeId: 'accounting.Budget',
    autoLoad: false,
    pageSize: 20,
    /*
     * due to bug 'autoLoad:false ignored for remote sort and remote Filter' disable remote sorting and filtering and turn it on when pass initial spring security
     */
    remoteSort: false,
    remoteFilter: false,
    
    proxy: {
        type: 'ajax',
        api: {read	: 'ajax/budgets'
            ,update : 'ajax/budgets/update' 
            ,create : 'ajax/budgets/create'
            ,destroy: 'ajax/budgets/delete'},
        reader: {type: 'json',
	            //extJs 6.0.1.x: root: 'data',
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
        	success: function(proxy, response, operation){},
            exception: function(proxy, response, operation){
            	if(response.responseText != undefined && response.responseText.indexOf("<!DOCTYPE html>") > -1){
            		console.log('"<!DOCTYPE html>" detected in ajax response... redirect to login.');
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
    }
});