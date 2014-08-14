var wsUrl = "ws://localhost:8080/tinycat/wsServlet";
var ws;
var worldMsgArea = $("#worldMsgArea");
var powerMsgArea = $("#powerMsgArea");
var selfMsgArea = $("#selfMsgArea");

function startConn() {
          try {
              if ("WebSocket" in window) {
              	ws = new WebSocket(wsUrl);
              }
              else if("MozWebSocket" in window) {
              	ws = new MozWebSocket(wsUrl);
              }
          } catch (ex) {
              Log(ex, "ERROR");
              return;
          }
          ws.onopen = WSonOpen;
          ws.onmessage = WSonMessage;
          ws.onclose = WSonClose;
          ws.onerror = WSonError;
          
          
      	  $("#worldNewTip").hide();
      	  $("#powerNewTip").hide();
      	  $("#selfNewTip").hide();
  };

function hideTip(area) {
	$("#"+area+"NewTip").hide();
}
  
  function WSonOpen() {
      Log("连接已经建立。", "success","world");
  };

  function WSonMessage(event) {
	  var message = JSON.parse(event.data);  
      Log(message.msg,message.type,message.area);            
  };

  function WSonClose() {
  };

  function WSonError() {
      Log("远程连接中断。浏览器版本和级别不支持本应用", "worn", "world");
  };


  function SendDataClicked(optId) {
	  var msg={};
	  msg = {msg:optId,
			  type:"info",
			  area:"world"
	  };
	  
      ws.send(JSON.stringify(msg));
  };


  function Log(Text, MessageType, MsgArea) {
      if (MessageType == "success") Text = "<span style='color: green;'>" + Text + "</span><br/>";
      if (MessageType == "worn") Text = "<span style='color: red;'>" + Text + "</span><br/>";
      if (MessageType == "info") Text = "<span>" + Text + "</span><br/>";
      
      if (MsgArea == "world") {
    	  worldMsgArea.append(Text);
    	  $("#worldNewTip").show();
    	  worldMsgArea.scrollTop = worldMsgArea.scrollHeight;
      }
      if (MsgArea == "power") {
    	  powerMsgArea.append(Text);
    	  $("#powerNewTip").show();
    	  powerMsgArea.scrollTop = powerMsgArea.scrollHeight;
      }
      if (MsgArea == "self") {
    	  selfMsgArea.append(Text);
    	  $("#selfNewTip").show();
    	  selfMsgArea.scrollTop = selfMsgArea.scrollHeight;
      }
  };


  $(document).ready(function () {
      var WebSocketsExist = true;
      try {
          var dummy = new WebSocket(wsUrl);
      } catch (ex) {
          try
          {
          	webSocket = new MozWebSocket(wsUrl);
          }
          catch(ex)
          {
          	WebSocketsExist = false;
          }
      }

      if (WebSocketsExist) {
    	  startConn();
      } else {
          alert("您的浏览器不支持WebSocket。请选择其他的浏览器再尝试连接服务器。", "ERROR");
      }    
  });