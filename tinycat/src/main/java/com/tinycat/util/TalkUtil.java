package com.tinycat.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.tinycat.dto.Room;
import com.tinycat.dto.RoomMsg;
import com.tinycat.web.WSMsgInboundPool;

public class TalkUtil {
	private static Map<String,Room> NEWS_ROOM = new HashMap<String, Room>();
	private static Map<String,Room> GAME_ROOM = new HashMap<String, Room>();
	private static Map<String,Room> TV_ROOM = new HashMap<String, Room>();
	private static Map<String,Room> LIFE_ROOM = new HashMap<String, Room>();
	
	/**
	 * 
	  * 将房间添加到房间Map中
	  *
	  * @autor: wn  2014-8-22 上午10:57:19
	  * @param type
	  * @param room
	  * @return
	 */
	public static ReturnType addRoom(RoomType type,Room room) {
		ReturnType returnType = ReturnType.SUCCESS;
		if(type.equals(RoomType.NEWS)) {
			returnType =  addRoomWithType(NEWS_ROOM,room);
		}else if(type.equals(RoomType.GAME)) {
			returnType =  addRoomWithType(GAME_ROOM,room);
		}else if(type.equals(RoomType.TV)) {
			returnType =  addRoomWithType(TV_ROOM,room);
		}else if(type.equals(RoomType.LIFE)) {
			returnType =  addRoomWithType(LIFE_ROOM,room);
		}
		if(ReturnType.SUCCESS==returnType) {
			//向所有用户推送房间变化信息
			RoomMsg msg = new RoomMsg();
			msg.setAction("add");
			msg.setRoomName(room.getName());
			msg.setRoomType(type);
			Gson gson = new Gson();
			WSMsgInboundPool.sendMessageToAll(gson.toJson(msg));
		}
		return ReturnType.SUCCESS;
	}

	/**
	 * 
	  * 将房间从Map中移除
	  *
	  * @autor: wn  2014-8-22 上午10:57:19
	  * @param type
	  * @param room
	  * @return
	 */
	public static ReturnType removeRoom(RoomType type,Room room) {
		ReturnType returnType = ReturnType.SUCCESS;
		if(type.equals(RoomType.NEWS)) {
			returnType =  removeRoomWithType(NEWS_ROOM,room);
		}else if(type.equals(RoomType.GAME)) {
			returnType =  removeRoomWithType(GAME_ROOM,room);
		}else if(type.equals(RoomType.TV)) {
			returnType =  removeRoomWithType(TV_ROOM,room);
		}else if(type.equals(RoomType.LIFE)) {
			returnType =  removeRoomWithType(LIFE_ROOM,room);
		}
		if(ReturnType.SUCCESS==returnType) {
			//向所有登陆用户推送房间变化信息
			RoomMsg msg = new RoomMsg();
			msg.setAction("remove");
			msg.setRoomName(room.getName());
			msg.setRoomType(type);
			Gson gson = new Gson();
			WSMsgInboundPool.sendMessageToAll(gson.toJson(msg));
		}
		return ReturnType.SUCCESS;
	}


	private static ReturnType addRoomWithType(Map<String,Room> rooms,Room room) {
		if(rooms.containsKey(room.getName())) {
			return ReturnType.ROOM_NAME_EXIST;
		}
		rooms.put(room.getName(), room);
		return ReturnType.SUCCESS;
	}
	
	private static ReturnType removeRoomWithType(Map<String,Room> rooms,Room room) {
		if(!rooms.containsKey(room.getName())) {
			return ReturnType.ROOM_NOT_EXIST;
		}
		rooms.remove(room.getName());
		return ReturnType.SUCCESS;
	}
	
	/**
	 * 
	  * 获取指定类型房间列表
	  *
	  * @autor: wn  2014-8-22 上午11:14:31
	  * @param type
	  * @return
	 */
	public static List<Room> getRoomList(RoomType type) {
		if(type.equals(RoomType.NEWS)) {
			return getRoomListFromMap(NEWS_ROOM);
		}else if(type.equals(RoomType.GAME)) {
			return getRoomListFromMap(GAME_ROOM);
		}else if(type.equals(RoomType.TV)) {
			return getRoomListFromMap(TV_ROOM);
		}else if(type.equals(RoomType.LIFE)) {
			return getRoomListFromMap(LIFE_ROOM);
		}
		return null;
	}
	
	private static List<Room> getRoomListFromMap(Map<String,Room> rooms) {
		Object[] roomArr = rooms.values().toArray();
		List<Room> roomList = new ArrayList<Room>();
		for(Object room:roomArr) {
			roomList.add((Room) room);
		}
		return roomList;
		
		
	}
	
	
}
