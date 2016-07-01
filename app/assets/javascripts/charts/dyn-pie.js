
function dynPie(myChart) {

    var dataMap = {};
        function dataFormatter(obj) {
            var temp;
            for (var year = 2013; year <= 2016; year++) {
                var cnt = 0;
                temp = obj[year];
                cnt = temp[0];
                obj[year] = {
                    name : '访问来源',
                    value : temp[0]
                 }
                obj[year] = cnt;
            }
            return obj;
        }

        dataMap.a_pc = dataFormatter({
            2016:[1511002],
            2015:[2193516],
            2014:[723942],
            2013:[197654]
        });

        dataMap.aligo = dataFormatter({
            2016:[89526],
            2015:[0],
            2014:[0],
            2013:[0]
        });

        dataMap.a_page = dataFormatter({
            2016:[600382],
            2015:[652958],
            2014:[126320],
            2013:[8711]
        });

        dataMap.a_client = dataFormatter({
            2016:[1964686],
            2015:[2371062],
            2014:[353046],
            2013:[19308]
        });

        dataMap.tel = dataFormatter({
            2016:[0],
            2015:[0],
            2014:[0],
            2013:[1693]
        });

        dataMap.b_pc = dataFormatter({
            2016:[1213158],
            2015:[1952087],
            2014:[694367],
            2013:[189605]
        });

        dataMap.b_alipay = dataFormatter({
            2016:[297764],
            2015:[241203],
            2014:[29491],
            2013:[8049]
        });

        dataMap.c_ios = dataFormatter({
            2016:[907845],
            2015:[1378935],
            2014:[227894],
            2013:[12446]
        });

        dataMap.c_android = dataFormatter({
            2016:[1054663],
            2015:[989904],
            2014:[125148],
            2013:[6858]
        });

        dataMap.d_3gt = dataFormatter({
            2016:[376516],
            2015:[539439],
            2014:[84352],
            2013:[639]
        });

        dataMap.d_3gn = dataFormatter({
            2016:[0],
            2015:[35331],
            2014:[41950],
            2013:[8072]
        });

        dataMap.d_wechat = dataFormatter({
            2016:[211056],
            2015:[77972],
            2014:[0],
            2013:[0]
        });

        var option = {
            baseOption: {
                backgroundColor: '#161627',
                timeline: {
                    // y: 0,
                    axisType: 'category',
                    // realtime: false,
                    // loop: false,
                    autoPlay: true,
                    // currentIndex: 2,
                    playInterval: 1000,
                    // controlStyle: {
                    //     position: 'left'
                    // },
                    data: [
                        '2013','2014','2015','2016'
                    ],
                    label: {
                        formatter : function(s) {
                            return (new Date(s)).getFullYear();
                        }
                    }
                },
                title: {
                    subtext: '',
                    sublink: 'http://www.changtu.com/',
                    left: 'center',
                    textStyle: {
                        color: '#eee'
                    }
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {d}%"
                },

                color: ['rgba(236, 110, 116, 0.9)','rgba(88, 76, 157, 0.9)',
                        'rgba(82, 176, 197, 0.9)','rgba(110, 164, 88, 0.9)',
                        'rgba(204, 224, 141, 0.9)','rgba(234, 97, 137, 0.9)',
                        'rgba(249, 193, 100, 0.9)','rgba(254, 235, 200, 0.9)',
                        'rgba(243, 168, 187, 0.9)','rgba(230, 28, 100, 0.9)',
                        'rgba(196, 123, 177, 0.9)','rgba(188, 200, 205, 0.9)',
                        'rgba(255, 228, 96, 0.9)','rgba(136, 0, 68, 0.9)',
                        'rgba(158, 79, 30, 0.9)','rgba(226, 69, 54, 0.9)',
                        'rgba(0, 158, 178, 0.9)','rgba(149, 130, 149, 0.9)'],
                calculable : true,
                grid: {
                    top: 80,
                    bottom: 100
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        selectedMode: 'single',
                        radius: [0, '30%'],
                        center: ['50%', '55%'],
                        label: {
                            normal: {
                                position: 'inner',
                                formatter: "{b}"
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                    },
                    {
                        name:'访问来源',
                        type:'pie',
                        radius: ['40%', '57%'],
                        center: ['50%', '55%'],
                        label: {
                            normal: {
                                formatter: "{b}"
                            }
                        }
                    }
                ]
            },
            options: [
                {
                    title: {text: '2013订单通道来源占比'},

                    series: [
                        {data: [
                            {name: 'PC端', value: dataMap.a_pc['2013']},
                            {name: '客户端', value: dataMap.a_client['2013']},
                            {name: '移动网页端', value: dataMap.a_page['2013']},
                            {name: '电话售票', value: dataMap.tel['2013']}
                            ]
                        },
                        {data: [
                            {name: 'PC版', value: dataMap.b_pc['2013']},
                            {name: '支付宝', value: dataMap.b_alipay['2013']},
                            {name: 'iOS', value: dataMap.c_ios['2013']},
                            {name: 'Android', value: dataMap.c_android['2013']},
                            {name: '3G触屏版', value: dataMap.d_3gt['2013']},
                            {name: '3G普通版', value: dataMap.d_3gn['2013']},
                            {name: '电话售票', value: dataMap.tel['2013']}
                        ]}
                    ]
                },
                {
                    title : {text: '2014订单通道来源占比'},
                    series : [
                        {data: [
                            {name: 'PC端', value: dataMap.a_pc['2014']},
                            {name: '客户端', value: dataMap.a_client['2014']},
                            {name: '移动网页端', value: dataMap.a_page['2014']}
                        ]},
                        {data: [
                            {name: 'PC版', value: dataMap.b_pc['2014']},
                            {name: '支付宝', value: dataMap.b_alipay['2014']},
                            {name: 'iOS', value: dataMap.c_ios['2014']},
                            {name: 'Android', value: dataMap.c_android['2014']},
                            {name: '3G触屏版', value: dataMap.d_3gt['2014']},
                            {name: '3G普通版', value: dataMap.d_3gn['2014']}
                        ]}
                    ]
                },
                {
                    title : {text: '2015订单通道来源占比'},
                    series : [
                        {data: [
                            {name: 'PC端', value: dataMap.a_pc['2015']},
                            {name: '客户端', value: dataMap.a_client['2015']},
                            {name: '移动网页端', value: dataMap.a_page['2015']},

                        ]},
                        {data: [
                            {name: 'PC版', value: dataMap.b_pc['2015']},
                            {name: '支付宝', value: dataMap.b_alipay['2015']},
                            {name: 'iOS', value: dataMap.c_ios['2015']},
                            {name: 'Android', value: dataMap.c_android['2015']},
                            {name: '3G触屏版', value: dataMap.d_3gt['2015']},
                            {name: '3G普通版', value: dataMap.d_3gn['2015']},
                            {name: '微信', value: dataMap.d_wechat['2015']}
                        ]}
                    ]
                },
                {
                    title : {text: '2016订单通道来源占比'},
                    series : [
                        {data: [
                            {name: 'PC端', value: dataMap.a_pc['2016']},
                            {name: '客户端', value: dataMap.a_client['2016']},
                            {name: '移动网页端', value: dataMap.a_page['2016']},
                            {name: 'API-alitrip', value: dataMap.aligo['2016']}
                        ]},
                        {data: [
                            {name: 'PC版', value: dataMap.b_pc['2016']},
                            {name: '支付宝', value: dataMap.b_alipay['2016']},
                            {name: 'iOS', value: dataMap.c_ios['2016']},
                            {name: 'Android', value: dataMap.c_android['2016']},
                            {name: '3G触屏版', value: dataMap.d_3gt['2016']},
                            {name: '3G普通版', value: dataMap.d_3gn['2016']},
                            {name: '微信', value: dataMap.d_wechat['2016']},
                            {name: 'API-alitrip', value: dataMap.aligo['2016']}
                        ]}
                    ]
                }
            ]
        };

        myChart.setOption(option);
}