package com.tinycat.web;
 

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

public class WSMsgInbound extends MessageInbound {

	//当前连接的用户名称
	private final String user;

	public WSMsgInbound(String user) {
		this.user = user;
	}

	public String getUser() {
		return this.user;
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
		WSMsgInboundPool.removeMessageInbound(this);
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
