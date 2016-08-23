Ext.define('QuotaKPI.view.AppViewport', {
    extend: 'Ext.Panel',
	alias: 'widget.AppViewport',
    requires: [
        'QuotaKPI.view.Viewer',
        'Ext.layout.container.Border'
    ],
    // no need renderTo: Ext.getBody(),
    layout: 'border',

    items: [

	 { region: 'north', border: false, margins: '5 5 0 5', height: 28, bodyStyle: 'background-color: #DFE8F6;',
			items: {
				xtype: 'panel',border: false, bodyStyle: 'font-family: tahoma,arial,helvetica,sans-serif; font-size: 10px; color: #7F7F7F;',
				html: '<TABLE ALIGN="left" BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH="100%" HEIGHT=120 >'+
				'<TR ALIGN="left" VALIGN="top">'+
				'<TD><img style="float: middle; padding: 0px 0px 0px 0px;" src="resources/fast/images/SpringLogo.PNG"/></TD>'+
					'<TD> <span style="float: middle; padding: 4px 3px 0px 0px; font-family: Helvetica,Arial,Verdana,sans-serif; font-weight: bold; font-size: 12px; color: #2F577F;">'+welcomeUserName+'</span> </TD>'+
					'<TD> <h1><span style="float: right; padding: 4px 3px 0px 0px; font-family: Helvetica,Arial,Verdana,sans-serif; font-weight: bold; font-size: 21px; color:#2F577F;">App Admin mode'+
					'<span style="padding: 4px 3px 0px 0px; font-family: Helvetica,Arial,Verdana,sans-serif; font-weight: bold; font-size: 10px; color:#2F577F;">'+FaST_Version_Number+'</span>'
					+'</h1></TD><TD><a href="logout" role="button">Log-out</a></TD>'+
					
				'</TR>'+ 
				/*'<TR><span style="float: middle; padding: 4px 3px 0px 0px;"><a class="btn btn-danger" href="logout" role="button">Log-out</a></span></TR>'+*/
				
				'</TABLE>'
					}
	},
    {
        region: 'center',
        xtype: 'viewer'
    }
    ]
});
