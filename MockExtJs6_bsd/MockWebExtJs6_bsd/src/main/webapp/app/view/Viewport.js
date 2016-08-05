Ext.define('QuotaKPI.view.Viewport', {
    extend: 'Ext.container.Viewport',

    requires: [
        'QuotaKPI.view.WelcomeView',
        'QuotaKPI.view.AppViewport'
    ],
    layout: 'card',

    items:	[
    		{xtype: 'WelcomeView'}
    		,{xtype: 'AppViewport'}
    		]
});
