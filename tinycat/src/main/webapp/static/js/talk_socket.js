var path = $("#contextPath").val();
var roomWsUrl = "ws://localhost:8080/tinycat/roomServlet";
var roomWs;

function startConn() {
	try {
		if ("WebSocket" in window) {
			roomWs = new WebSocket(roomWsUrl);
		} else if ("MozWebSocket" in window) {
			roomWs = new MozWebSocket(roomWsUrl);
		}
	} catch (ex) {
		Log(ex, "ERROR");
		return;
	}
	roomWs.onopen = WSonOpen;
	roomWs.onmessage = WSonMessage;
	roomWs.onclose = WSonClose;
	roomWs.onerror = WSonError;

};

function WSonOpen() {
};

function WSonMessage(event) {
	// 接收到消息之后，动态更新房间列表
	var message = JSON.parse(event.data);

	Log(message.msg, message.type, message.area);
};

function WSonClose() {
};

function WSonError() {
};

$(function() {
	var WebSocketsExist = true;
	try {
		var dummy = new WebSocket(roomWsUrl);
	} catch (ex) {
		try {
			webSocket = new MozWebSocket(roomWsUrl);
		} catch (ex) {
			WebSocketsExist = false;
		}
	}

	if (WebSocketsExist) {
		startConn();
	} else {
		alert("您的浏览器不支持WebSocket。请选择其他的浏览器再尝试连接服务器。", "ERROR");
	}
});
