Ext.define('QuotaKPI.controller.ImportController', {
    extend: 'Ext.app.Controller'
    ,stores: []
    ,models: []
    ,views: ['ImportView'
             ,'imports.ImportCompany'
             ,'imports.ImportBillTo'
             ,'imports.ImportShipTo'
             ,'imports.ImportMachine'
             ,'imports.ImportDealer'
             ,'imports.ImportInvoice'
             ]
    ,refs:	[
          	 {ref: 'ImportCompany',	selector: 'ImportCompany'},
          	 {ref: 'ImportBillTo',	selector: 'ImportBillTo'},
          	 {ref: 'ImportShipTo',	selector: 'ImportShipTo'},
          	 {ref: 'ImportMachine',	selector: 'ImportMachine'},
          	 {ref: 'ImportDealer',	selector: 'ImportDealer'},
          	 {ref: 'ImportInvoice',	selector: 'ImportInvoice'}
    		]

    ,init: function() {
    	var me = this;
        this.control({
        	'ImportCompany button[action=downloadCompanyExcelTemplate]':	{click: this.getSpreadsheetTemplate},
        	'ImportCompany button[action=uploadCompanyExcel]':	{click: this.getSpreadsheetTemplate},
        	'ImportBillTo button[action=downloadBillToExcelTemplate]':	{click: this.getSpreadsheetTemplate},
        	'ImportBillTo button[action=uploadBillToExcel]':	{click: this.getSpreadsheetTemplate},
        	'ImportShipTo button[action=downloadShipToExcelTemplate]':	{click: this.getSpreadsheetTemplate},
        	'ImportShipTo button[action=uploadShipToExcel]':	{click: this.getSpreadsheetTemplate},
        	'ImportMachine button[action=downloadMachineExcelTemplate]':	{click: this.getSpreadsheetTemplate},
        	'ImportMachine button[action=uploadMachineExcel]':	{click: this.getSpreadsheetTemplate},
        	'ImportDealer button[action=downloadDealerExcelTemplate]':	{click: this.getSpreadsheetTemplate},
        	'ImportDealer button[action=uploadDealerExcel]':	{click: this.getSpreadsheetTemplate},
        	'ImportInvoice button[action=downloadInvoiceExcelTemplate]':	{click: this.getSpreadsheetTemplate},
        	'ImportInvoice button[action=uploadInvoiceExcel]':	{click: this.getSpreadsheetTemplate},
        });
    }
    

	,getSpreadsheetTemplate: function(button){
	    var hiddenIFrameID = 'hiddenDownloader',
	    iframe = document.getElementById(hiddenIFrameID);
	    if (iframe === null) {
	        iframe = document.createElement('iframe');
	        iframe.id = hiddenIFrameID;
	        iframe.style.display = 'none';
	        document.body.appendChild(iframe);
	    }
	    iframe.src = 'resources/importTemplates/MPS NBT Customer Company Info Excel Template.xlsx';
	}

});






