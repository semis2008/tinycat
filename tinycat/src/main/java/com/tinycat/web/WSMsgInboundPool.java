package com.tinycat.web;
 

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.tinycat.dto.RoomMsg;
import com.tinycat.dto.UserDTO;
import com.tinycat.dto.UserMsg;
import com.tinycat.pojo.User;
import com.tinycat.util.RoomType;

public class WSMsgInboundPool {

	//保存连接的MAP容器
	private static final Map<String,WSMsgInbound > loginConnections = new HashMap<String,WSMsgInbound>();
 		
	//向连接池中添加连接
	public static void addMessageInbound(WSMsgInbound inbound){
		//添加连接
		if(inbound.getUser()==null) {
		}else {
			System.out.println("user : " + inbound.getUser() + " join..");
			if(loginConnections.get(inbound.getUser())!=null) {
				WSMsgInbound old = loginConnections.get(inbound.getUser());
				inbound.setRoomName(old.getRoomName());
				inbound.setRoomType(old.getRoomType());
			}
			loginConnections.put(inbound.getUser(), inbound);
		}
	}
	
	//获取所有的在线用户
	public static Set<String> getOnlineUser(){
		return loginConnections.keySet();
	}
	
	public static void removeMessageInbound(WSMsgInbound inbound){
//		//移除连接
//		System.out.println("user : " + inbound.getUser() + " exit..");
//		if(inbound.getUser()==null) {
//		}else {
//			loginConnections.remove(inbound.getUser());
//		}
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
	
	public static void userJoinRoom(UserDTO user,String roomName,RoomType roomType) {
		WSMsgInbound inbound = loginConnections.get(user.getEmail());
		inbound.setRoomName(roomName);
		inbound.setRoomType(roomType);
		loginConnections.put(user.getEmail(), inbound);
		
		//向所有登陆用户发送通知
		UserMsg userMsg = new UserMsg();
		userMsg.setAction("user_join");
		userMsg.setUser(user);
		Gson gson = new Gson();
		WSMsgInboundPool.sendMessageWithInRoom(gson.toJson(userMsg), roomName, roomType);
	}
	
	public static List<String> getUsersByRoom(String roomName,RoomType roomType) {
		List<String> users = new ArrayList<String>();
		Set<String> keySet = loginConnections.keySet();
		for (String key : keySet) {
			WSMsgInbound inbound = loginConnections.get(key);
			if(inbound != null){
				if(inbound.getRoomType()==roomType&&inbound.getRoomName().equals(roomName)) {
					users.add(inbound.getUser());
				}
			}
		}
		return users;
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
			System.out.println("send message to All ser ,message content : " + message);
			Set<String> keySet = loginConnections.keySet();
			for (String key : keySet) {
				WSMsgInbound inbound = loginConnections.get(key);
				if(inbound != null){
					inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	  * 向指定房间推送消息
	  *
	  * @autor: wn  2014-8-27 下午4:21:15
	  * @param message
	 */
	public static void sendMessageWithInRoom(String message,String roomName,RoomType type){
		try {
			Set<String> keySet = loginConnections.keySet();
			System.out.println("send message to Room:" + roomName + ",message content : " + message);
			for (String key : keySet) {
				WSMsgInbound inbound = loginConnections.get(key);
				if(inbound != null&&inbound.getRoomName().equals(roomName)&&inbound.getRoomType()==type){
					inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
