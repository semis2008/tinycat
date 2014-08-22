package com.tinycat.dto;

import com.tinycat.util.RoomType;

public class RoomMsg {
	private String roomName;
	private String action;
	private RoomType roomType;
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	 
	 
}
