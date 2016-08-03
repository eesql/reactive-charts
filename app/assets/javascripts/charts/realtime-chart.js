

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

}