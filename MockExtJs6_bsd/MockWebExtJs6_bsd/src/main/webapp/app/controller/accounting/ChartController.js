Ext.define('QuotaKPI.controller.accounting.ChartController', {
    extend: 'Ext.app.Controller',
	//extend: 'Ext.app.ViewController',
	
    alias: 'controller.column-stacked-3d',

    stores: ['accounting.KpiChart'],
    
    models: ['accounting.QuotaModel'],

    views: ['accounting.ChartView'],

    refs: [  {ref: 'ChartView',	selector: 'ChartView'}
			],

    init: function() {
        this.control({
        	//'QuotaGrid button[action=quotaDetailSave]'		:	{click: this.quotaDetailSave},
        });
    },

	/*
	 *  http://stackoverflow.com/questions/19253885/extjs-how-correcty-call-a-controller-method-from-another-controller-or-closure
	 *  
	 *  Call: this.getController('accounting.ChartController').onQuotaChartEvent(quotas); 
	 */
    onQuotaChartEvent: function (quotas) {
    	//console.log('onQuotaChartEvent with quotas:'+quotas);
    	
    },
    
    
	onTooltipRender: function (tooltip, record, item) {
        var formatString = '0,000 (millions of USD)',
            fieldIndex = Ext.Array.indexOf(item.series.getYField(), item.field),
            sector = item.series.getTitle()[fieldIndex],
            value = Ext.util.Format.number(record.get(item.field), formatString);

        tooltip.setHtml(sector + ': ' + value);
	},

	onAxisLabelRender: function (axis, label, layoutContext) {
	    return Ext.util.Format.number(layoutContext.renderer(label) / 1000, '0,000');
	},

    onAxisRangeChange: function (axis, range) {
        if (!range) {
            return;
        }
        // expand the range slightly to make sure markers aren't clipped
        if (range[1] > 15000000) {
            range[1] = 18000000;
        }
    }
});






