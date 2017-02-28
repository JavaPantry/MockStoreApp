Ext.define('QuotaKPI.store.company.SalesRepresentative', {
    extend: 'Ext.data.Store',
    model: 'QuotaKPI.model.company.SalesRepresentativeModel',
    storeId:'company.SalesRepresentative',
    autoLoad: false,
    pageSize: 35,
    /*
     * due to bug 'autoLoad:false ignored for remote sort and remote Filter' disable remote sorting and filtering and turn it on when pass initial spring security
     */
    remoteSort: false,
    remoteFilter: false,

    //FaST legacy? don't really need it: constantUrl:'ajax/salesReps',
    proxy: {
        type: 'ajax',
        api: {
        	read : 'ajax/salesReps',
            create : null,//'json/companySalesRepresentativeAction?m=create',
            update: null,//'json/companySalesRepresentativeAction?m=update',
            destroy: 'ajax/deleteSalesReps'//'json/companySalesRepresentativeAction?m=delete'
        },
        reader: {
            type: 'json',
            rootProperty: 'data',
            successProperty: 'success',
            totalProperty : 'total'
        },
        writer: {
            type: 'json',
            writeAllFields: true,
            allowSingle: false,
            encode: false,
            rootProperty: 'data'
        },
        listeners: {
        	//TODO - <AP> somehow despite record delete successful response is error 
            exception: function(proxy, response, operation){
            	if(response.responseText != undefined && response.responseText.indexOf("<!DOCTYPE html>") > -1){
            		console.log('"<!DOCTYPE html>" detected in ajax response... redirect to login.')
            		window.location.assign('login');
            		return;
            	}
                Ext.MessageBox.show({
                    title: 'Remote Access Failed',
                    msg: operation.getError(),
                    icon: Ext.MessageBox.ERROR,
                    buttons: Ext.Msg.OK
                });
            }
        }
    }
});