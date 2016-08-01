function radarChart(myChart) {

    var data2016 = [
        {
            value: [231144, 1022652, 520803, 563281, 394869, 0, 0],
            name: '2016'
        }
    ];

    var data2015 = [
        {
            value: [76466, 457827, 238032, 298023, 244594, 379532, 1038954],
            name: '2015'
        }
    ];

    var data2014 = [
        {
            value: [5831, 149127, 50504, 62473, 48960, 59032, 253057],
            name: '2014'
        }
    ];

    var data2013 = [
        {
            value: [1136, 29104, 9882, 12551, 9217, 17554, 67510],
            name: '2013'
        }
    ];

    var lineStyle = {
        normal: {
            width: 3,
            opacity: 0.5
        },
        emphasis: {
            width: 4,
            opacity: 0.8
        }
    };

    var option = {
        backgroundColor: '#161627',
        title: {
            text: '',
            subtext: '',
            sublink: 'http://www.changtu.com/',
            left: 'center',
            textStyle: {
                color: '#eee'
            }
        },
        tooltip : {
            trigger: 'item'
        },
        legend: {
            bottom: 5,
            data: ['2016', '2015', '2014', '2013'],
            itemGap: 20,
            textStyle: {
                color: '#fff',
                fontSize: 14
            }
        },
        radar: {
            indicator: [
                {name: '元旦',
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: 'rgba(238, 197, 102, 0.7)'
                    }
                },
                max: 1100000},
                {name: '春节', max: 1100000},
                {name: '清明', max: 1100000},
                {name: '五一', max: 1100000},
                {name: '端午', max: 1100000},
                {name: '中秋', max: 1100000},
                {name: '国庆', max: 1100000}
            ],
            shape: 'circle',
            splitNumber: 5,
            name: {
                textStyle: {
                    color: 'rgb(238, 197, 102)'
                }
            },
            splitLine: {
                lineStyle: {
                    color: [
                        'rgba(238, 197, 102, 0.3)', 'rgba(238, 197, 102, 0.3)',
                        'rgba(238, 197, 102, 0.4)', 'rgba(238, 197, 102, 0.6)',
                        'rgba(238, 197, 102, 0.8)', 'rgba(238, 197, 102, 1)'
                    ].reverse()
                }
            },
            splitArea: {
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(238, 197, 102, 0.5)'
                }
            }
        },
        series: [
            {
                name: '2016',
                type: 'radar',
                lineStyle: lineStyle,
                data: data2016,
                symbol: 'none',
                itemStyle: {
                    normal: {
                        color: '#F9713C'
                    }
                },
                areaStyle: {
                    normal: {
                        opacity: 0.1
                    }
                }
            },
            {
                name: '2015',
                type: 'radar',
                lineStyle: lineStyle,
                data: data2015,
                symbol: 'none',
                itemStyle: {
                    normal: {
                        color: '#00e3e3'
                    }
                },
                areaStyle: {
                    normal: {
                        opacity: 0.05
                    }
                }
            },
            {
                name: '2014',
                type: 'radar',
                lineStyle: lineStyle,
                data: data2014,
                symbol: 'none',
                itemStyle: {
                    normal: {
                        color: '#6a6aff'
                    }
                },
                areaStyle: {
                    normal: {
                        opacity: 0.05
                    }
                }
            },
            {
                name: '2013',
                type: 'radar',
                lineStyle: lineStyle,
                data: data2013,
                symbol: 'none',
                itemStyle: {
                    normal: {
                        color: '#f9f900'
                    }
                },
                areaStyle: {
                    normal: {
                        opacity: 0.05
                    }
                }
            }
        ]
    };

    myChart.setOption(option);
}