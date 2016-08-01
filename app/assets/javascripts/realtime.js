
function updateDailyOrders(docId) {
    document.getElementById(docId).innerHTML=1;
    var source=new EventSource("/sse/update-order");


    source.onmessage=function(event)
      {
        var d = JSON.parse(event.data);
        document.getElementById(docId).innerHTML=d;
        document.getElementById(docId).innerHTML=d[0][1];

      };
  };