package com.tinycat.web;
 

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

import com.tinycat.util.RoomType;
import com.tinycat.util.WebUtil;

public class WSMsgInbound extends MessageInbound {

	//当前连接的用户名称
	private final String user;
	private String roomName;
	private RoomType roomType;
	private String typeName;
	
	public WSMsgInbound(String user) {
		this.user = user;
	}

	public String getUser() {
		return this.user;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	//建立连接的触发的事件
	@Override
	protected void onOpen(WsOutbound outbound) {
		//向连接池添加当前的连接对象
		WSMsgInboundPool.addMessageInbound(this);
	}

	@Override
	protected void onClose(int status) {
		// 触发关闭事件，在连接池中移除连接
//		if(WebUtil.getLoginUser().equals("null")) {
//			WSMsgInboundPool.removeMessageInbound(this);
//		}
	}

	@Override
	protected void onBinaryMessage(ByteBuffer message) throws IOException {
		throw new UnsupportedOperationException("Binary message not supported.");
	}

	//客户端发送消息到服务器时触发事件
	@Override
	protected void onTextMessage(CharBuffer message) throws IOException {
		//向所有在线用户发送消息
		WSMsgInboundPool.sendMessageToAll(message.toString());
	}

	@Override
	public int getReadTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}
}
