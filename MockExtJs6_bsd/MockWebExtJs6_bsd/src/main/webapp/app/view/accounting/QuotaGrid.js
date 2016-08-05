/*
 * Request with sorting and filtering cause problem:
 * post: https://www.sencha.com/forum/showthread.php?307156-ExtJs-6-problem-with-grid-filter-type-list
 * bug: https://www.sencha.com/forum/showthread.php?307161-ExtJs-6-problem-with-grid-filter-type-list
 * filter [{"operator":"in","value":[1],"property":"amountType"}]
 * limit	20
 * page	1
 * start	0
 * summary	false
 * 
 */
Ext.define('QuotaKPI.view.accounting.QuotaGrid', {
	extend: 'Ext.grid.Panel'
	,xtype: 'QuotaGrid'
	
   	,selModel: Ext.create('Ext.selection.CheckboxModel')
    ,columnLines: true
    ,store: 'accounting.Quota'
    ,features: [{ftype: 'summary',
			    	showSummaryRow:false,
			    	remoteRoot:'summary',
			    	id: 'totalQuotaSummFeature'
			    	//dock:'bottom'
			     }]
	
	,plugins: [Ext.create('Ext.grid.plugin.CellEditing', {
		        	   	clicksToEdit: 1
		        	   	,pluginId : 'rowEditingBudgetEditGridExt' // to find editor by grid.getPlugin('rowEditingMonthEditGridExt').editor.form.findField('name')
		        	   	//,listeners : {'beforeedit' : function(editor, e, eOpts) {}}
		           }),
		        'gridfilters'
		       ] // plugins


	,columns: [  {header     : 'Id',locked: true, hidden: true,dataIndex: 'id'}
				,{header     : 'SalesRep Id',locked: true, sortable: true,dataIndex: 'salesRepresentative.salesRepresentativeId',width:70
					,filter: {type: 'string', dataIndex: 'salesRepresentative.salesRepresentativeId'}
				}
				,{header     : 'SalesRep Name',locked: true, sortable: true,dataIndex: 'salesRepresentative.salesRepresentativeName',width:150
					,filter: {type: 'string', dataIndex: 'salesRepresentative.salesRepresentativeName'}
				}
				,{header     : 'Cat.Id',locked: true, sortable: true,dataIndex: 'category.categoryId',width:50
					,filter: {type: 'string',dataIndex: 'category.categoryId'}
				}
				,{header     : 'Category Name',locked: true, sortable: true,dataIndex: 'category.categoryName',width:150
					,filter: {type: 'string',dataIndex: 'category.categoryName'}
				}
				,{header     : 'Amount Type',locked: true, sortable: true,dataIndex: 'amountType',width:70
					,renderer: function(value){
            	        if (value == 1) {
            	            return 'Unit';
            	        }else if (value == 0) {
            	            return 'Dollar';
            	        }
            	        return ('invalid value ='+value);
	            	}
					,filter: {
	                     type: 'list'
	                     ,options: [[0, 'Dollar'], [1, 'Unit']] //[{amountType: 0, name:'Dollar'}, {amountType: 1, name:'Unit'}]
						 ,dataIndex: 'amountType'
						 //,single:true
	                 }
				}
				,{header     : 'Year',locked: true, sortable: true,dataIndex: 'year',width:50
					, summaryRenderer: function(value, summaryData, dataIndex) {
			            return '<b><i>Total:</i></b>'; 
			        }
					,filter: {type: 'numeric',dataIndex: 'year'}
				}
				
				,{header     : 'Jan',flex:1,align:'right',renderer: Ext.util.Format.numberRenderer('0,000'),dataIndex:'value1',editor:{xtype:'numberfield',minValue: 0}	,summaryType : 'remote'}
				,{header     : 'Feb',flex:1,align:'right',renderer: Ext.util.Format.numberRenderer('0,000'),dataIndex: 'value2',editor : {xtype : 'numberfield',minValue: 0},summaryType : 'remote'}//doesn't work: ,summaryRenderer: Ext.util.Format.numberRenderer('0,000')}
				,{header     : 'Mar',flex:1,align:'right',renderer: Ext.util.Format.numberRenderer('0,000'),dataIndex: 'value3',editor : {xtype : 'numberfield',minValue: 0},summaryType : 'remote'}
				,{header     : 'April',flex:1,align:'right',renderer: Ext.util.Format.numberRenderer('0,000'),dataIndex: 'value4',editor : {xtype : 'numberfield',minValue: 0},summaryType : 'remote'}
				,{header     : 'May',flex:1,align:'right',renderer: Ext.util.Format.numberRenderer('0,000'),dataIndex: 'value5',editor : {xtype : 'numberfield',minValue: 0},summaryType : 'remote'}
				,{header     : 'June',flex:1,align:'right',renderer: Ext.util.Format.numberRenderer('0,000'),dataIndex: 'value6',editor : {xtype : 'numberfield',minValue: 0},summaryType : 'remote'}
				,{header     : 'July',flex:1,align:'right',renderer: Ext.util.Format.numberRenderer('0,000'),dataIndex: 'value7',editor : {xtype : 'numberfield',minValue: 0},summaryType : 'remote'}
				,{header     : 'Aug',flex:1,align:'right',renderer: Ext.util.Format.numberRenderer('0,000'),dataIndex: 'value8',editor : {xtype : 'numberfield',minValue: 0},summaryType : 'remote'}
				,{header     : 'Sep',flex:1,align:'right',renderer: Ext.util.Format.numberRenderer('0,000'),dataIndex: 'value9',editor : {xtype : 'numberfield',minValue: 0},summaryType : 'remote'}
				,{header     : 'Oct',flex:1,align:'right',renderer: Ext.util.Format.numberRenderer('0,000'),dataIndex: 'value10',editor : {xtype : 'numberfield',minValue: 0},summaryType : 'remote'}
				,{header     : 'Nov',flex:1,align:'right',renderer: Ext.util.Format.numberRenderer('0,000'),dataIndex: 'value11',editor : {xtype : 'numberfield',minValue: 0},summaryType : 'remote'}
				,{header     : 'Dec',flex:1,align:'right',renderer: Ext.util.Format.numberRenderer('0,000'),dataIndex: 'value12',editor : {xtype : 'numberfield',minValue: 0},summaryType : 'remote'}
				,{header     : 'Year Total',flex:1,align:'right',renderer: Ext.util.Format.numberRenderer('0,000'),dataIndex: 'total'
					,renderer: function(value, meta, record) {
						var total=0;
						for(var i=1;i<13;i++){
							var fieldName = 'value'+i;
							var value = record.get(fieldName);
							total += value;
						}
						return Ext.util.Format.number(total,'0,000'); 
					}
					,summaryType : 'remote'
				}
	]

	,dockedItems : [{xtype: 'toolbar',dock: 'top',
							    items: [{iconCls: 'icon-save',		itemId: 'quotaDetailSave',text: 'Save',action: 'quotaDetailSave'}
										,{iconCls: 'icon-add',		itemId: 'quotaDetailCreate',text: 'Create Quota',action: 'quotaDetailCreate'}
										,{iconCls: 'icon-reset',	itemId: 'quotaDetailReset',text: 'Reset Quota',action: 'quotaDetailReset'}
										,{iconCls: 'icon-delete',	itemId: 'quotaDetailDelete',text: 'Delete Quota',action: 'quotaDetailDelete'}
										//TODO - <AP> Meta 'grid.feature.RemoteSummary'
										,{iconCls: 'icon-add',	itemId: 'quotaDetailSummup',text: 'Get Total Quota',
											enableToggle: true,
											toggleHandler:function(item, pressed){
												//var output = Ext.String.format('Button "{0}" was toggled to {1}.', item.text, pressed);
												//console.log(output);
												var theGrid = item.up('panel')
												var lockedTotalSummFeature = theGrid.lockedGrid.getView().getFeature('totalQuotaSummFeature');
												lockedTotalSummFeature.toggleSummaryRow(pressed);
												var normalTotalSummFeature = theGrid.normalGrid.getView().getFeature('totalQuotaSummFeature');
												normalTotalSummFeature.toggleSummaryRow(pressed);
												theGrid.lockedGrid.getView().refresh();
												theGrid.normalGrid.getView().refresh();
												theGrid.getStore().proxy.extraParams = {summary:pressed};
												theGrid.getStore().load();
										    },action: 'quotaSummup'}
										,{iconCls: 'icon-chart',	itemId: 'quotaChart',text: 'Quota Chart',action: 'quotaChart'}
										
										//,'->',{iconCls: 'icon-return',itemId: 'quotaDetailReturn',text: 'Cancel',action: 'quotaDetailReturn'}
					]}
	               ,{xtype: 'pagingtoolbar', 
	            	   	dock:'bottom',
	            	   	store: 'accounting.Quota',
						displayInfo: true,
						displayMsg: 'Displaying Quota Details {0} - {1} of {2}',
						emptyMsg: "No Quota to display"
					}
	               ]
    	  ,initComponent: function() {
    		this.callParent(arguments);
    	}    	
   });