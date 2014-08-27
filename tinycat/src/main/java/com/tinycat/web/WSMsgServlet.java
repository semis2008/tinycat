package com.tinycat.web;


import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import com.tinycat.util.WebUtil;

//如果要接收浏览器的ws://协议的请求就必须实现WebSocketServlet这个类
public class WSMsgServlet extends WebSocketServlet {

	private static final long serialVersionUID = 1L;
	
	public static int ONLINE_USER_COUNT	= 1;
	
	public static int GUEST_USER_INDEX	= 1;
	
	public String getUser(){
		return WebUtil.getLoginUser();
	}

	//跟平常Servlet不同的是，需要实现createWebSocketInbound，在这里初始化自定义的WebSocket连接对象
    protected StreamInbound createWebSocketInbound(String subProtocol,HttpServletRequest request) {
        return new WSMsgInbound(this.getUser());
    }

}
