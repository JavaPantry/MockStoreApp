Ext.define('QuotaKPI.view.accounting.ChartView' ,{
    extend: 'Ext.Panel',
    alias : 'widget.ChartView',
    xtype: 'column-stacked-3d',
    //controller: 'column-stacked-3d', //see that we've changed controller class to Ext.app.ViewController
    layout: 'anchor',

    requires: [
               'Ext.chart.CartesianChart',
               'Ext.chart.axis.Numeric',
               'Ext.chart.axis.Time',
               'Ext.draw.modifier.Highlight',
               'Ext.chart.interactions.ItemHighlight',
               'Ext.chart.theme.*'
           ],

    
    items:[{
			    xtype: 'cartesian',
			    reference: 'chart',
			    store: {type: 'kpi-chart'},
			    theme: 'Muted',
			    insetPadding: '40 20 10 20',
			    width: '100%',
			    height: 500,
			    interactions: ['itemhighlight'],
			    series: {
			        type: 'bar3d',
			        xField: 'country',
			        yField: ['agr', 'ind', 'ser'],
			        title: ['Agriculture', 'Industry', 'Services'],
			        style: {
			            maxBarWidth: 80
			        },
			        highlight: true,
			        tooltip: {
			            trackMouse: true,
			            //renderer: 'onTooltipRender'
			        }
			    },
			    legend: {
			        docked: 'bottom'
			    },
			    axes: [{
			        type: 'numeric3d',
			        position: 'left',
			        grid: {
			            odd: {
			                fillStyle: 'rgba(255, 255, 255, 0.06)'
			            },
			            even: {
			                fillStyle: 'rgba(0, 0, 0, 0.03)'
			            }
			        },
			        title: 'Quota',
			        //renderer: 'onAxisLabelRender',
			        listeners: {
			            //rangechange: 'onAxisRangeChange'
			        }
			    }, {
			        type: 'category3d',
			        position: 'bottom',
			        grid: true
			    }],
			    sprites: {
			        type: 'text',
			        text: 'Selected Quotas',
			        fontSize: 22,
			        width: 100,
			        height: 30,
			        x: 40,
			        y: 20
			    }
			}
    ]
});