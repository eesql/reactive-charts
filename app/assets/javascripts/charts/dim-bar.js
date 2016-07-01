
function dimBar(myChart) {

    var option = {
            backgroundColor: '#161627',
            title: {
                text: '购票量工作日分布',
                subtext: '',
                sublink: 'http://www.changtu.com/',
                left: 'center',
                textStyle: {
                    color: '#eee'
                }
            },
            tooltip : {
                trigger: 'item',
                axisPointer : {
                    type : 'shadow'
                }
            },
            legend: {
                top: '11%',
                textStyle: {
                    color: 'rgba(999, 999, 999, 0.8)'
                },
                data:['PC','iOS','Android','Touch','支付宝','微信','阿里去啊','其他']
            },
            grid: {
                left: '3%',
                right: '4%',
                top: '18%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    axisLabel: {
                        textStyle: {
                            color:'#eee'
                        }
                    },
                    data : ['周一','周二','周三','周四','周五','周六','周日']
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    axisLabel: {
                        textStyle: {
                            color:'#eee'
                        }
                    }
                }
            ],
            series : [
                {
                    name:'PC',
                    type:'bar',
                    stack: '通道',
                    itemStyle: {
                        normal: {
                            color:'rgba(236, 110, 116, 0.9)'
                        },
                        emphasis: {
                            color:'rgba(999, 999, 999, 0.9)'
                        }
                    },
                    data:[530139, 538754, 572592, 621046, 682812, 539526, 541032]
                },
                {
                    name:'iOS',
                    type:'bar',
                    stack: '通道',
                    itemStyle: {
                        normal: {
                            color:'rgba(246, 172, 26, 0.9)'
                        },
                        emphasis: {
                            color:'rgba(999, 999, 999, 0.9)'
                        }
                    },
                    data:[313825, 306683, 320501, 359079, 428905, 372791, 391557]
                },
                {
                    name:'Android',
                    type:'bar',
                    stack: '通道',
                    itemStyle: {
                        normal: {
                            color:'rgba(255, 230, 122, 0.9)'
                        },
                        emphasis: {
                            color:'rgba(999, 999, 999, 0.9)'
                        }
                    },
                    data:[270113, 270528, 282184, 308954, 368445, 316565, 332371]
                },
                {
                    name:'Touch',
                    type:'bar',
                    stack: '通道',
                    itemStyle: {
                        normal: {
                            color:'rgba(204, 225, 152, 0.9)'
                        },
                        emphasis: {
                            color:'rgba(999, 999, 999, 0.9)'
                        }
                    },
                    data:[122085, 117004, 125429, 137626, 162598, 151792, 156351]
                },
                {
                    name:'支付宝',
                    type:'bar',
                    stack: '通道',
                    itemStyle: {
                        normal: {
                            color:'rgba(0, 152, 158, 0.9)'
                        },
                        emphasis: {
                            color:'rgba(999, 999, 999, 0.9)'
                        }
                    },
                    data:[71663, 71768, 78020, 84147, 99125, 81272, 81390]
                },
                {
                    name:'微信',
                    type:'bar',
                    stack: '通道',
                    itemStyle: {
                        normal: {
                            color:'rgba(63, 179, 112, 0.9)'
                        },
                        emphasis: {
                            color:'rgba(999, 999, 999, 0.9)'
                        }
                    },
                    data:[35712, 32940, 35602, 38803, 49623, 45536, 50330]
                },
                {
                    name:'阿里去啊',
                    type:'bar',
                    stack: '通道',
                    itemStyle: {
                        normal: {
                            color:'rgba(216, 34, 13, 0.9)'
                        },
                        emphasis: {
                            color:'rgba(999, 999, 999, 0.9)'
                        }
                    },
                    data:[9719, 8761, 10656, 10318, 13145, 11582, 12424]
                },
                {
                    name:'其他',
                    type:'bar',
                    stack: '通道',
                    itemStyle: {
                        normal: {
                            color:'rgba(135, 151, 201, 0.9)'
                        },
                        emphasis: {
                            color:'rgba(999, 999, 999, 0.9)'
                        }
                    },
                    data:[11695, 10800, 11581, 12786, 15224, 14618, 15542]
                }
            ]
        };

    myChart.setOption(option);
}


function multiLine(myChart) {

    var data2013 = [
        ['一级',168116,217458,26073784.5,164504],
        ['二级',23081,27674,1995141,22555],
        ['三级',1,1,7,1]
    ];

    var data2014 = [
        ['一级',801074,1012242,100097255.9,781849],
        ['二级',210360,255752,18700907,206355],
        ['三级',2711,4596,172001,2495]
    ];

    var data2015 = [
        ['一级',3430117,4138791,365847151.9,3337681],
        ['二级',1002167,1191062,84578963.5,974804],
        ['三级',30133,48135,2007353,28582]
    ];

    var data2016 = [
        ['一级',2603359,3113698,285424454.14,2552643],
        ['二级',924567,1095805,82751349.71,903666],
        ['三级',42850,54614,2895441.7,41653]
    ];
    var schema = [
        {name: 'city_level', index: 0, text: '城市级别'},
        {name: 'order_cnt', index: 1, text: '订单量'},
        {name: 'ticket_cnt', index: 2, text: '订票量'},
        {name: 'ar_money', index: 3, text: '交易金额'},
        {name: 'user_cnt', index: 4, text: '下单用户数'}
    ];

    var lineStyle = {
        normal: {
            width: 5,
            opacity: 0.9
        }
    };

    var option = {
        backgroundColor: '#333',
        title: {
            text: '城市分级多指标展示',
            subtext: 'data from 畅途',
            sublink: 'http://www.changtu.com/',
            left: 'center',
            textStyle: {
                color: '#eee'
            }
        },
        color: ['#d94e5d','#eac736','#50a3ba','#ca8eff'],
        legend: {
            bottom: 30,
            data: ['2013', '2014', '2015','2016'],
            itemGap: 20,
            textStyle: {
                color: '#fff',
                fontSize: 14
            }
        },
        tooltip: {
            padding: 10,
            backgroundColor: '#222',
            borderColor: '#777',
            borderWidth: 1
        },
        // dataZoom: {
        //     show: true,
        //     orient: 'vertical',
        //     parallelAxisIndex: [0]
        // },
        parallelAxis: [
            {dim: 0, name: schema[0].text, type: 'category', data: ['三级','二级','一级']},
            {dim: 1, name: schema[1].text},
            {dim: 2, name: schema[2].text},
            {dim: 3, name: schema[3].text},
            {dim: 4, name: schema[4].text}
        ],
        parallel: {
            left: '8%',
            right: '18%',
            top: '15%',
            bottom: 100,
            parallelAxisDefault: {
                type: 'value',
                nameLocation: 'end',
                nameGap: 20,
                nameTextStyle: {
                    color: '#fff',
                    fontSize: 12
                },
                axisLine: {
                    lineStyle: {
                        color: '#aaa'
                    }
                },
                axisTick: {
                    lineStyle: {
                        color: '#777'
                    }
                },
                splitLine: {
                    show: false
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff'
                    }
                }
            }
        },
        series: [
            {
                name: '2013',
                type: 'parallel',
                lineStyle: lineStyle,
                data: data2013
            },
            {
                name: '2014',
                type: 'parallel',
                lineStyle: lineStyle,
                data: data2014
            },
            {
                name: '2015',
                type: 'parallel',
                lineStyle: lineStyle,
                data: data2015
            },
            {
                name: '2016',
                type: 'parallel',
                lineStyle: lineStyle,
                data: data2016
            }
        ]
    };

    myChart.setOption(option);
}