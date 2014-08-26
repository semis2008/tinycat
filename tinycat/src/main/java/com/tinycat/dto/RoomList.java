package com.tinycat.dto;

import java.util.List;

public class RoomList {
	public static int SHOW_NUM = 8; 
	
	private List<Room> rooms;
	private boolean hasMore;
	private int page;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public List<Room> getRooms() {
		return rooms;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	public boolean isHasMore() {
		return hasMore;
	}
	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}
	
}
