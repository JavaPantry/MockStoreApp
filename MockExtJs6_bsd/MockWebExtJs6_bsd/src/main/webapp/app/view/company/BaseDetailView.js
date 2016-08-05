var defaultLabelWidth = 150;

Ext.define('QuotaKPI.view.company.BaseDetailView' ,{
    extend: 'Ext.Panel',
    layout: 'anchor',
    items:[
		{xtype: 'fieldcontainer',
		combineErrors: false,
		minHeight : 90,
		anchor: '100% 20%',
		layout:'vbox',
		defaults: {xtype: 'fieldcontainer',layout:'hbox',margin:'1',labelPad:2}
		,items: [   
				{	defaults: {xtype: 'displayfield', labelWidth:defaultLabelWidth,labelCls:'fast-label'},
					items: [{fieldLabel:'Sales Representative Code',itemId:'salesRepresentativeId'}
							,{fieldLabel:'Sales Representative Name',itemId:'salesRepresentativeName'}]},
				{	defaults: {xtype: 'displayfield', labelWidth:defaultLabelWidth,labelCls:'fast-label'},
					items: [{fieldLabel:'User Id',itemId:'userId'}
							,{fieldLabel:'User Name',itemId:'userName'}]},
				{	defaults: {xtype: 'displayfield', labelWidth:defaultLabelWidth,labelCls:'fast-label'},
					items: [{fieldLabel:'All Access',itemId:'allAccess'}]}
				]
		}
    	//in successor use: this.items.push(getContent());//return {xtype:'panel',anchor: '100% 80%'}
    ]
	,salesRepRecord:null
	,loadFormHeader: function(salesRepRecord){
		this.salesRepRecord = salesRepRecord;
		var salesRepresentativeIdFld	= this.down('#salesRepresentativeId');
		var salesRepresentativeNameFld	= this.down('#salesRepresentativeName');
		var userIdFld					= this.down('#userId');
		var userNameFld					= this.down('#userName');
		var allAccessFld				= this.down('#allAccess');

		salesRepresentativeIdFld.setValue(salesRepRecord.get('salesRepresentativeId'));
		salesRepresentativeNameFld.setValue(salesRepRecord.get('salesRepresentativeName'));
		userIdFld.setValue(salesRepRecord.get('userId'));
		userNameFld.setValue(salesRepRecord.get('userName'));
		allAccessFld.setValue(salesRepRecord.get('allAccess'));
	}

	,dockedItems: [{
	    xtype: 'toolbar',
	    dock: 'top',
	    layout: {pack: 'left'},
	    defaults: {minWidth: 80},
	    items: [
				{text: 'Edit',itemId:'itemEdit',iconCls: 'icon-save',action:'actionEdit'}
				,{text: 'Save',itemId:'itemSave',iconCls: 'icon-save',action:'actionSave'}
				,{text: 'Add',itemId:'itemAdd',iconCls: 'icon-save',action:'actionAdd'}
				,{text: 'Delete',itemId:'itemDelete',iconCls: 'icon-save',action:'actionDelete'}
				,'->'
				,{text: 'Return',itemId:'itemCancel',iconCls: 'icon-return',action:'userDetailEditCancel'}
				]
	}]
	/* !!!hack!!!
	 * remove previous sibling's content if any
	 */
	,removePreviousSiblingContentIfAnyHack: function(conterpartItemId){
		for (var i=0; i<this.items.length; i++){
			if(this.items[i].itemId != undefined && this.items[i].itemId === conterpartItemId){
				////console.log('HACK_ALERT:removePreviousSiblingContentIfAnyHack removes '+this.items[i].itemId);
				this.items.splice(i, 1);
			}
		}
	}
});