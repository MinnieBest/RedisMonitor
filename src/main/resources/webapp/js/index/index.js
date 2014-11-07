$(document).ready(function() {
		 Highcharts.setOptions({
	            global: {
	                useUTC: false
	            }
	        });
	    
	        var chart;
	        $('#container').highcharts({
	            chart: {
	                type: 'spline',
	                animation: Highcharts.svg, // don't animate in old IE
	                marginRight: 0,
	                events: {
	                    load: function() {
	                        // set up the updating of the chart each second
	                        var series = this.series[0];
	                        setInterval(function() {
	                        	$.getJSON('/chartMemery.htm' , function(data){
	        						var x = data.hashMap.create_time , // current time
	        						y = data.hashMap.used_memory/1024 ;
	        						series.addPoint([x, y], true, true );
	        						});
	                        }, 2000);
	                    }
	                }
	            },
	            title: {
	                text: 'memery'
	            },
	            xAxis: {
	                type: 'datetime',
	                tickPixelInterval: 150
	            },
	            yAxis: {
	                title: {
	                    text: 'kb'
	                },
	                plotLines: [{
	                    value: 0,
	                    width: 1,
	                    color: '#808080'
	                }]
	            },
	            tooltip: {
	                formatter: function() {
	                        return '<b>'+ this.series.name +'</b><br/>'+
	                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
	                        Highcharts.numberFormat(this.y, 2);
	                }
	            },
	            legend: {
	                enabled: false
	            },
	            exporting: {
	                enabled: false
	            },
	            series: [{
	                name: 'memery',
	                data: (function() {
	                    var data = [],
	                        time = (new Date()).getTime(),
	                        i;
	    
	                    for (i = -19; i <= 0; i++) {
	                        data.push({
	                            x: time + i * 1000,
	                            y: Math.random() * (1000 - 800) + 800
	                        });
	                    }
	                    return data;
	                })()
	            }]
	        });
	        $('#keysChart').highcharts({
	        	chart: {
	        		type: 'spline',
	        		animation: Highcharts.svg, // don't animate in old IE
	        		marginRight: 10,
	        		events: {
	        			load: function() {
	        				// set up the updating of the chart each second
	        				var series = this.series[0];
	        				setInterval(function() {
	        					$.getJSON('/chartKeys.htm' , function(data){
	        						var x = data.hashMap.create_time , // current time
	        						y = data.hashMap.dbSize ;
	        						series.addPoint([x, y], true, true );
	        						});
	        				}, 2000);
	        			}
	        		}
	        	},
	        	title: {
	        		text: 'keys'
	        	},
	        	xAxis: {
	        		type: 'datetime',
	        		tickPixelInterval: 150
	        	},
	        	yAxis: {
	        		title: {
	        			text: ''
	        		},
	        		plotLines: [{
	        			value: 0,
	        			width: 1,
	        			color: '#808080'
	        		}]
	        	},
	        	tooltip: {
	        		formatter: function() {
	        			return '<b>'+ this.series.name +'</b><br/>'+
	        			Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
	        			Highcharts.numberFormat(this.y, 2);
	        		}
	        	},
	        	legend: {
	        		enabled: false
	        	},
	        	exporting: {
	        		enabled: false
	        	},
	        	series: [{
	        		name: 'keys',
	        		data: (function() {
	        			var data = [],
	        			time = (new Date()).getTime(),
	        			i;
	        			
	        			for (i = -19; i <= 0; i++) {
	        				data.push({
	        					x: time + i * 1000,
	        					y: 0
	        				});
	        			}
	        			return data;
	        		})()
	        	}]
	        });
	});