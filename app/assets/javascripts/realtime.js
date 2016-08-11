
/** 更新当日累积支付完成票数指标,以及pv等所有kpi指标 **/
function updateDailyKPI(id1,id2,id3) {

    var source=new EventSource("/sse/update-kpi");
    source.onmessage=function(event)
      {
        var d = JSON.parse(event.data);
        document.getElementById(id1).innerHTML=d[0][1];
        document.getElementById(id2).innerHTML=d[1][1];
        document.getElementById(id3).innerHTML=d[1][2];
      };
 };


/** 处理分钟渠道提交票数指标数据,转换为Echarts展现需要的格式 **/
function getMinChannelOrders(data) {
    /**var data = {
                  title:"堆叠区域图",
                  legend:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎'],
                  xaxis:['周一','周二','周三','周四','周五','周六','周日'],
                  data:[
                    [120, 132, 101, 134, 90, 230, 210],
                    [220, 182, 191, 234, 290, 330, 310],
                    [150, 232, 201, 154, 190, 330, 410],
                    [320, 332, 301, 334, 390, 330, 320],
                    [820, 932, 901, 934, 1290, 1330, 1320]
                  ]
                };**/


    var cdata = {
        title:"",
        legend:['PC','iOS','Touch','Android','WeChat','AliTrip'],
        xaxis:['00','01','02','03','04','05','06','07','08','09','10','11','12',
               '13','14','15','16','17','18','19','20','21','22','23'],
        data:[ [],[],[],[],[],[] ]
    };

    // 初始化data数组
    for (var i=0; i<24; i++) {
        cdata.data[0].push(0);
        cdata.data[1].push(0);
        cdata.data[2].push(0);
        cdata.data[3].push(0);
        cdata.data[4].push(0);
        cdata.data[5].push(0);
    }


    for (var i=0; i<data.length; i++) {
        var d = data[i];
        var ch = d[0].split(".")[1];

        for (var id=0; id < cdata.legend.length; id++) {
            // 匹配通道
            if ( ch == cdata.legend[id] ) {
                var x = d[0].split(".")[0].substr(11,2);
                if ( x.substr(0,1) == "0" ) { var xx = x.substr(1,1);} else { var xx = x;}
                cdata.data[id][parseInt(xx)] += parseInt(d[1]);
            }

        }
    };

    return cdata;
 };


/** 处理实时pv指标数据格式 **/
function getRealPVData(data,series) {
    var jdata = {
        seriesName:series,
        date:[],
        data:[]
    };

    for (var i=0; i < data.length; i++) {
        jdata.data.push(data[i][1]);

        jdata.date.push(data[i][0].substr(15));
    };

    return jdata;
}