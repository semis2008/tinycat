package com.tinycat.web;
 

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WSMsgInboundPool {

	//保存连接的MAP容器
	private static final Map<String,WSMsgInbound > loginConnections = new HashMap<String,WSMsgInbound>();
 		
	//向连接池中添加连接
	public static void addMessageInbound(WSMsgInbound inbound){
		//添加连接
		if(inbound.getUser()==null) {
		}else {
			System.out.println("user : " + inbound.getUser() + " join..");
			loginConnections.put(inbound.getUser(), inbound);
		}
	}
	
	//获取所有的在线用户
	public static Set<String> getOnlineUser(){
		return loginConnections.keySet();
	}
	
	public static void removeMessageInbound(WSMsgInbound inbound){
		//移除连接
		System.out.println("user : " + inbound.getUser() + " exit..");
		if(inbound.getUser()==null) {
		}else {
			loginConnections.remove(inbound.getUser());
		}
	}
	
	/**
	 * 
	  * 向指定用户发送消息
	  *
	  * @autor: wn  2014-8-27 下午4:21:45
	  * @param user
	  * @param message
	 */
	public static void sendMessageToUser(String user,String message){
		try {
			//向特定的用户发送数据
			System.out.println("send message to user : " + user + " ,message content : " + message);
			WSMsgInbound inbound = loginConnections.get(user);
			if(inbound != null){
				inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	  * 向所有用户（包括游客）发送消息
	  *
	  * @autor: wn  2014-8-27 下午4:21:15
	  * @param message
	 */
	public static void sendMessageToAll(String message){
		try {
			Set<String> keySet = loginConnections.keySet();
			for (String key : keySet) {
				WSMsgInbound inbound = loginConnections.get(key);
				if(inbound != null){
					System.out.println("send message to user : " + key + " ,message content : " + message);
					inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
