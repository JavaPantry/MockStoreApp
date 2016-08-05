var remoteTocStore = new Ext.data.JsonStore({
	autoLoad: false,
	storeId: 'remoteTocStore',
	proxy: {type: 'ajax',
			api: {read : 'ajax/lookuptoc'},//'ajax/tocs'
			reader: {type: 'json',
		            //extJs 6.0.1.x: root: 'data',
		            rootProperty: 'data',
					successProperty: 'success'}	},
			fields: ['tocId','tocName'] 
			});

Ext.define('QuotaKPI.view.company.TocDetailForm' ,{
		extend	:	'QuotaKPI.view.company.BaseDetailView',
		alias	:	'widget.TocDetailForm',
		itemId	:	'TocDetailForm',
		initComponent: function() {
			this.removePreviousSiblingContentIfAnyHack('ManagerDetailPanel');
			this.items.push(this.getContent());
			this.callParent(arguments);
			this.down('#itemDelete').hide();
			this.down('#itemAdd').hide();
			this.down('#itemEdit').hide();
		},
		
		getContent: function(){
			return	{xtype: 'container',
				anchor: '100% 80%',
				items: [{xtype: 'combo', labelWidth:defaultLabelWidth, labelCls:'fast-label',
							queryMode: 'remote', //'local', 
		                	forceSelection: true,
		                	fieldLabel: 'TOC Name',
		                	name: 'tocId',
		                	itemId:'tocId',
		                	displayField: 'tocName',
		                	valueField: 'tocId',
		                	store: remoteTocStore,
	                    	editable: true,
				            typeAhead: true,
				            minChars:1,
				            listConfig: {
				                loadingText: 'Searching...',
				                emptyText: 'No matching found.',
				                getInnerTpl: function() {return '{tocName} : <i><font color="blue">{tocId}</font></i>';}
				            }		                	
						}]
					};
			}
		});

