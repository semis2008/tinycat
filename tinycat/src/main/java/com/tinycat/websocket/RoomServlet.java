package com.tinycat.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

import com.google.gson.Gson;
import com.tinycat.dto.RoomMsg;

public class RoomServlet extends WebSocketServlet {
	private static final long serialVersionUID = -4853540828121130946L;
	private static ArrayList mmiList = new ArrayList();

	@Override
	protected StreamInbound createWebSocketInbound(String str, HttpServletRequest request) {
		return new MyMessageInbound();
	}

	private class MyMessageInbound extends MessageInbound {
		WsOutbound myoutbound;

		@Override
		public void onOpen(WsOutbound outbound) {
			try {
				System.out.println("Open Client.");
				this.myoutbound = outbound;
				mmiList.add(this);
				outbound.writeTextMessage(CharBuffer.wrap("Hello!"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onClose(int status) {
			System.out.println("Close Client.");
			mmiList.remove(this);
		}

		@Override
		public void onTextMessage(CharBuffer cb) throws IOException {
			System.out.println("Accept Message : " + cb);
			//
			// for (Object mmibs : mmiList) {
			// MyMessageInbound mmib = (MyMessageInbound) mmibs;
			//
			// Gson gson = new Gson();
			// Msg msg = new Msg();
			// msg.setArea("world");
			// msg.setMsg("123");
			// msg.setType("success");
			//
			// CharBuffer buffer = CharBuffer.wrap(gson.toJson(msg));
			// mmib.myoutbound.writeTextMessage(buffer);
			// mmib.myoutbound.flush();
			// }
		}

		@Override
		public void onBinaryMessage(ByteBuffer bb) throws IOException {
		}
	}

	// 向所有的用户发送消息
	public static void sendMessage(RoomMsg message) {
		try {
			for (Object mmibs : mmiList) {
				MyMessageInbound mmib = (MyMessageInbound) mmibs;
				Gson gson = new Gson();
				CharBuffer buffer = CharBuffer.wrap(gson.toJson(message));
				mmib.myoutbound.writeTextMessage(buffer);
				mmib.myoutbound.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}