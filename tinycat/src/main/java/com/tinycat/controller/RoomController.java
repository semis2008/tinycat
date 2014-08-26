package com.tinycat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.hibernate.loader.custom.ResultRowProcessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tinycat.dto.Room;
import com.tinycat.dto.RoomList;
import com.tinycat.dto.UserDTO;
import com.tinycat.pojo.User;
import com.tinycat.service.UserService;
import com.tinycat.util.DateUtil;
import com.tinycat.util.JsonUtil;
import com.tinycat.util.ParamUtils;
import com.tinycat.util.RoomType;
import com.tinycat.util.StringUtil;
import com.tinycat.util.TalkUtil;
import com.tinycat.util.WebUtil;

/**
 * 用户相关处理类
 * 
 * @author Kalor
 * @time 2012-12-14
 */
@Controller
public class RoomController {
	@Resource
	UserService userService;

	@RequestMapping(value = "/addRoom")
	private void add(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Long userId = ParamUtils.getLongParameter(req, "userId", 0l);
		String roomType = ParamUtils.getParameter(req, "roomType");
		String roomName = ParamUtils.getParameter(req, "roomName");
		String password = ParamUtils.getParameter(req, "password");
		if (userId == 0l || "".equals(roomType) || "".equals(roomName)) {
			JsonUtil.outputDTOToJSON("参数错误", false, resp);
			return;
		}
		Room room = buildRoom(userId, roomType, roomName, password);
		TalkUtil.addRoom(room.getType(), room);
		JsonUtil.outputDTOToJSON(room, true, resp);
	}
	
	@RequestMapping(value = "/changeRooms")
	private void changeRooms(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String type = ParamUtils.getParameter(req, "type");
		String action = ParamUtils.getParameter(req, "action");
		int page = ParamUtils.getIntParameter(req, "page", 1);
		
		if ("".equals(type) || "".equals(action)) {
			JsonUtil.outputDTOToJSON("参数错误", false, resp);
			return;
		}
		RoomType roomType = null;
		if ("news".equals(type)) {
			roomType = RoomType.NEWS;
		} else if ("game".equals(type)) {
			roomType = RoomType.GAME;
		} else if ("tv".equals(type)) {
			roomType = RoomType.TV;
		} else if ("life".equals(type)) {
			roomType = RoomType.LIFE;
		}
		List<Room> resustRooms = new ArrayList<Room>();
		List<Room> rooms = TalkUtil.getRoomList(roomType);
		int start=0,end=rooms.size();
		if(action.equals("up")) {
			start = (page-2)*RoomList.SHOW_NUM;
			if((page-1)*RoomList.SHOW_NUM>rooms.size()) 
				end = rooms.size();
			else
				end = (page-1)*RoomList.SHOW_NUM;
		}else if(action.equals("down")) {
			start = page*RoomList.SHOW_NUM;
			if((page+1)*RoomList.SHOW_NUM>rooms.size()) 
				end = rooms.size();
			else
				end = (page+1)*RoomList.SHOW_NUM;
			
		}
		resustRooms = rooms.subList(start, end);
		RoomList resRoomList = new RoomList();
		if(rooms.size()>end) {
			resRoomList.setHasMore(true);
			resRoomList.setRooms(resustRooms.subList(0, RoomList.SHOW_NUM));
		}else {
			resRoomList.setHasMore(false);
			resRoomList.setRooms(resustRooms);
		}
		if(action.equals("up"))
			resRoomList.setPage(page-1);
		else
			resRoomList.setPage(page+1);
		
		JsonUtil.outputDTOToJSON(resRoomList, true, resp);
	}

	private Room buildRoom(Long userId, String roomType, String roomName, String password) {
		User user = userService.getUserById(userId);
		UserDTO dto = UserDTO.init(user);
		Room room = new Room();
		room.setCreateTime(new Date());
		room.setCreateUser(dto);
		room.setName(roomName);
		if ("".equals(password)) {
			room.setHasPassword(false);
		} else {
			room.setHasPassword(true);
			room.setPassword(password);
		}
		if ("news".equals(roomType)) {
			room.setType(RoomType.NEWS);
		} else if ("game".equals(roomType)) {
			room.setType(RoomType.GAME);
		} else if ("tv".equals(roomType)) {
			room.setType(RoomType.TV);
		} else if ("life".equals(roomType)) {
			room.setType(RoomType.LIFE);
		}
		room.setUsers(Arrays.asList(dto));
		return room;
	}
}
