Ext.define('QuotaKPI.view.WelcomeView', {
    extend: 'Ext.Panel',
    alias: 'widget.WelcomeView',
    layout:'border',
    
    requires: [
	],

    margins: '1 1 1 1',

    initComponent: function() {
		////console.log('typeof isProductAccessible ' + typeof isProductAccessible);

        this.items =[
        			{region:'north',
					xtype: 'panel',border: false, bodyStyle: 'font-family: tahoma,arial,helvetica,sans-serif; font-size: 10px; color: #7F7F7F;',
					html: '<TABLE ALIGN="left" BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH="100%">'+
					'<TR ALIGN="middle" VALIGN="middle">'+
					'<TD><img style="float: middle; padding: 50px 50px 50px 50px;" src="resources/fast/images/SpringLogo.PNG"/></TD></TR>'+
					'<TR ALIGN="middle" VALIGN="middle">'+
						'<TD> <span style="float: middle; padding: 50px 50px 50px 50px; font-family: Helvetica,Arial,Verdana,sans-serif; font-weight: bold; font-size: 25px; color: #2F577F;">'+welcomeUserName+'</span> </TD></TR>'+
					'<TR ALIGN="middle" VALIGN="middle">'+
						'<TD> <h1><span style="float: midle; padding: 50px 50px 50px 50px; font-family: Helvetica,Arial,Verdana,sans-serif; font-weight: bold; font-size: 35px; color:#2F577F;">Quota KPI.'+
						'<span style="padding: 4px 3px 0px 0px; font-family: Helvetica,Arial,Verdana,sans-serif; font-weight: bold; font-size: 10px; color:#2F577F;">'+FaST_Version_Number+'</span>'
						+'</span></h1></TD></TR>'+
					'</TABLE></br>'
					},
					/*
						var quotaUser = true;    
						var budgetUser = false;    
						var companyUser = false;    
						var reportUser = false;    
						var adminUser = false;    
					 */
					{region:'center',
					xtype: 'panel',
					border: false,
					items:	[
							{xtype: 'panel',
							layout: 'vbox',
							width:'50%',
							align: 'center',
							border: false,
							style: {marginLeft: '35%',marginTop: '20px'},
							defaults: {iconCls: 'tabs',xtype: 'button',width:300, scale: 'large',style: {marginLeft: '10px', marginTop: '20px'},'text-anchor': 'middle'},
							items:	[{
										id:'QuotaBtn', text : 'Quota',
										disabled: !quotaUser,
										handler: function() {
											var rootCardHolder	= this.up('viewport');
											var rootViewer	= rootCardHolder.down('rootViewer');
											rootViewer.setActiveTab(0);
											rootCardHolder.getLayout().setActiveItem(1);
											
											}
										},
										{
										id:'BudgetBtn', text : 'Budget',
										disabled: !budgetUser,
										handler: function() {
											var rootCardHolder	= this.up('viewport');
											var rootViewer	= rootCardHolder.down('rootViewer');
											rootViewer.setActiveTab(1);
											rootCardHolder.getLayout().setActiveItem(1);
											
											}
										},
										{
										id:'UsersBtn', text : 'IDS Security',
										disabled: !companyUser,
										handler: function() {
											var rootCardHolder	= this.up('viewport');
											var rootViewer	= rootCardHolder.down('rootViewer');
											rootViewer.setActiveTab(2);
											rootCardHolder.getLayout().setActiveItem(1);
											
											}
										},
										{
										id:'ReportBtn', text : 'Report',
										disabled: !reportUser,
										handler: function() {
											window.open('http://mis-birtccipr:8080/birt/run?__report=/CWLJC390.rptdesign','Report');
											}
										}
								]
							}
						]
					}
					];
        this.callParent(arguments);
    }
/*  ,listeners:{
		afterrender: function(  ){
			////console.log("From WelcomePage::afterrender  : isProd = "+isProductAccessible+", isSale = "+isSaleAccessible+", isDealer = "+isDealerAccessible+", isCompany = "+isCompanyAccessible);
			//debugger;
			var btn1 = Ext.getCmp('ProductsBtn');
			btn1.enable(isProductAccessible);
			var btn2 = Ext.getCmp('DealerBtn');
			btn2.enable(isDealerAccessible);
			var btn3 = Ext.getCmp('SalesBtn');
			btn3.enable(isSaleAccessible);
			var btn4 = Ext.getCmp('CompanyBtn');
			btn4.enable(isCompanyAccessible);
		}
	}*/
});