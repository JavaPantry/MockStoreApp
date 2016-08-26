var amountTypeStore = Ext.create('Ext.data.Store', {
        fields: [{type: 'number', name: 'amountType'},{type: 'string', name: 'amountTypeName'}],                              
        data: [{'amountType':0,'amountTypeName':'Dollars'},{'amountType':1,'amountTypeName':'Units'}]
    });
var salesRepCodesStore= new Ext.data.JsonStore({
	autoLoad: true,
	proxy: {type: 'ajax',
			api: {read : 'ajax/salesRepCodes'},
			reader: {type: 'json',
		            //extJs 6.0.1.x: root: 'data',
		            rootProperty: 'data',
					successProperty: 'success'}
	},
	fields: ['code','name']
	});

var categoryCodesStore= new Ext.data.JsonStore({
	autoLoad: true,
	proxy: {type: 'ajax',
			api: {read : 'ajax/categoryRepCodes'},
			reader: {type: 'json',
		            //extJs 6.0.1.x: root: 'data',
		            rootProperty: 'data',
					successProperty: 'success'}
	},
	fields: ['code','name']
	});
	
/*see fast c:\sts_workspaceMMPOM.3.4.0_FaST_Ph2_Maven2\fast-web\WebContent\app\view\company\DealerGroupDetailCreateWnd.js*/
Ext.define('QuotaKPI.view.accounting.QuotaCreateWnd', {
    extend: 'Ext.window.Window',
    alias : 'widget.QuotaCreateWnd',
    title : 'Create Quota Header',
    layout: 'fit',
    autoShow: true,
    modal: true,
    width: 600, height: 200,
    iconCls: 'icon-user',
    listeners: {destroy:function(win){}},
    initComponent: function() {
        this.callParent(arguments);
    },
    
    /*
		match to QuotaModel:
         {name: 'salesRepresentativeId', type: 'string'},
         {name: 'categoryId', type: 'string'},
         {name: 'amountType', type: 'number'},
         {name: 'year', type: 'number'},
	*/
	items:[	{xtype: 'form',padding: '5 5 0 5',border: false, style: 'background-color: #faa;',
			defaults: {labelWidth:200, anchor: '100%',labelPad:2/*labelAlign: 'left',allowBlank: false,combineErrors: true,msgTarget: 'side', */},
			items: [{fieldLabel: 'Budget or Quota',xtype: 'displayfield',name : 'quotaOrBudget',hidden:true, value: 'Quota',allowBlank: false, labelCls:'fast-label'}
					//{fieldLabel: 'Sales Representative Code',xtype: 'textfield',name : 'salesRepCode',value: '',allowBlank: false, labelCls:'fast-label'}
					//,{fieldLabel: 'Sales Representative Name',xtype: 'textfield',name : 'salesRepName',value: '', labelCls:'fast-label'}
					,{fieldLabel: 'Sales Representative', xtype: 'combo', labelCls:'fast-label',
						queryMode: 'remote', forceSelection: true,
						name: 'salesRepresentativeId', displayField: 'name', valueField: 'code',
		                editable: false,
		                store: salesRepCodesStore,
		                listeners: {change: function(combo, newValue, oldValue){
		                    			var form = combo.up('form');
		                    			}//on change
		                    		}//listeners
		                ,listConfig: {loadingText: 'Searching...',
						              emptyText: 'No matching found.',
							          getInnerTpl: function() {
						                    return '<span style="font-weight:bold">{code}</span> - <i><font color="blue">{name}</font></i>';
						              }
						            }
					}//combo salesRep
					
					//,{fieldLabel: 'Category Code',xtype: 'textfield',name : 'categoryCode',value: '',allowBlank: false, labelCls:'fast-label'}
					//,{fieldLabel: 'Category Name',xtype: 'textfield',name : 'categoryName',value: '', labelCls:'fast-label'}
					,{fieldLabel: 'Category', xtype: 'combo', labelCls:'fast-label',
						queryMode: 'remote', forceSelection: true,
						name: 'categoryId', displayField: 'name',valueField: 'code',
		                editable: false,
		                store: categoryCodesStore,
		                listeners: {change: function(combo, newValue, oldValue){
		                    			var form = combo.up('form');
		                    			}//on change
		                    		}//listeners
		                ,listConfig: {loadingText: 'Searching...',
						              emptyText: 'No matching found.',
							          getInnerTpl: function() {
						                    return '<span style="font-weight:bold">{code}</span> - <i><font color="blue">{name}</font></i>';
						              }
						            }
					}//combo category

					//,{fieldLabel: 'Amount Type',xtype: 'textfield',name : 'amountType',value: '', labelCls:'fast-label'}
					,{xtype: 'combo',
                        	mode: 'local',
                        	queryMode: 'local',                   	
                        	triggerAction: 'all',
                        	forceSelection: true,
                        	editable: true,
                        	fieldLabel: 'Amount Type',
                        	name: 'amountType',
                        	displayField: 'amountTypeName',
                        	valueField: 'amountType',
                        	store: amountTypeStore, labelCls:'fast-label'
			            }
					,{fieldLabel: 'Year',xtype:'numberfield',name : 'year',value: '2014', labelCls:'fast-label'}
					]
			}],
    dockedItems:[{xtype: 'toolbar',dock:'top',
    			 items: [{text: 'Save', iconCls: 'icon-save',itemId: 'quotaCreateWndSave', action: 'quotaCreateWndSave'}
						,'->',{text: 'Cancel', iconCls: 'icon-cancel',itemId: 'quotaCreateWndCancel',action: 'quotaCreateWndCancel'}
						/*,'->',{iconCls: 'icon-search',itemId: 'dealerGroupDetailCreateWndLookup',text: 'Customer Lookup',action: 'dealerGroupDetailCreateWndLookup'}*/
						]
				 }]
});
