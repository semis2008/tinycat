var path = $("#contextPath").val();
var headPath = $("#headPath").val();
var roomWsUrl = "ws://localhost:8080/tinycat/WS";
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
	var msg = JSON.parse(event.data);
	if (msg.action == "room_add") {
		addRoomAction(msg.roomName, msg.roomType);
	} else if (msg.action == "user_join") {
		joinUserAction(msg.user);
	}
};

function WSonClose() {
};

function WSonError() {
};

$(document).ready(function() {
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

function joinUserAction(user) {
	var userHtml = " <a href='javascript:void(0)' class='list-group-item'><img alt='head' " +
		"class='head_photo_20 img-rounded'	src='" + headPath + "/" + user.photo + ".jpg'>" + user.name + "</a>";
	var users = $(".ou-users a");
	if (users.size() > 9) {
		$(".ou-users a:last").remove();
	}
	$(".ou-users").prepend(userHtml);
}

function addRoomAction(name, type) {
	var list, size, changeRoomDown;
	var roomHtml = "<a href='#talk' class='list-group-item'>" + name + "</a>";
	if (type == "NEWS") {
		list = $("#news_room_list");
		size = $("#news_room_list a").size();
		changeRoomDown = $("#news_change_rooms_down");
	}
	if (type == "GAME") {
		list = $("#game_room_list");
		size = $("#game_room_list a").size();
		changeRoomDown = $("#game_change_rooms_down");
	}
	if (type == "TV") {
		list = $("#tv_room_list");
		size = $("#tv_room_list a").size();
		changeRoomDown = $("#tv_change_rooms_down");
	}
	if (type == "LIFE") {
		list = $("#life_room_list");
		size = $("#life_room_list a").size();
		changeRoomDown = $("#life_change_rooms_down");
	}
	if (size < 8) {
		list.append(roomHtml);
	} else if (changeRoomDown.hasClass("hidden")) {
		changeRoomDown.removeClass("hidden");
	}

}
