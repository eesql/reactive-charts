

function realArea(myChart,data) {

    var convertSeries = function(data) {
        var series = [];
        for (var i=0; i<data.data.length; i++) {
        series.push( {
            name: data.legend[i],
            type: 'line',
            stack: '总量',
            areaStyle: {normal: {}},
            data: data.data[i]
        });
        }
        return series;
    };


    var option = {
            title: {
                text: data.title
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:data.legend
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : data.xaxis
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : convertSeries(data)
        };

    myChart.setOption(option);



    setTimeout(function() {
        window.onresize = function () {
            myChart.resize();

        }
     },200);

}


function realLine(myChart,data) {


    var option = {
        tooltip: {
            trigger: 'axis',
            position: function (pt) {
                return [pt[0], '10%'];
            }
        },
        title: {
            left: 'center',
            text: '',
        },
        legend: {
            top: 'bottom',
            data:['意向']
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: data.date
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%']
        },
        dataZoom: [{
            type: 'inside',
            start: 80,
            end: 100
        }, {
            start: 0,
            end: 10,
            handleSize: '80%',
            handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
            }
        }],
        series: [
            {
                name: data.seriesName,
                type:'line',
                smooth:true,
                symbol: 'none',
                sampling: 'average',
                itemStyle: {
                    normal: {
                        color: 'rgb(255, 70, 131)'
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgb(255, 158, 68)'
                        }, {
                            offset: 1,
                            color: 'rgb(255, 70, 131)'
                        }])
                    }
                },
                data: data.data
            }
        ]
    };

    myChart.setOption(option);
    setTimeout(function() {
        window.onresize = function () {
            myChart.resize();

        }
     },200);

}