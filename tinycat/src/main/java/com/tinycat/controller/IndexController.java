package com.tinycat.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tinycat.dto.Room;
import com.tinycat.dto.RoomList;
import com.tinycat.dto.UserDTO;
import com.tinycat.pojo.User;
import com.tinycat.service.UserService;
import com.tinycat.util.RoomType;
import com.tinycat.util.TalkUtil;
import com.tinycat.util.WebUtil;

/**
 * 系统响应处理类
 * 
 * @author Kalor
 * @time 2012-12-14
 */
@Controller
public class IndexController {
	@Resource
	UserService userService;
	 
	/**
	 * 显示主页
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/index")
	private ModelAndView showIndex(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		setLoginUserDTO(req);
		
		//获取房间列表
		RoomList newsRoomList = getRoomList(RoomType.NEWS);
		RoomList gameRoomList = getRoomList(RoomType.GAME);
		RoomList tvRoomList = getRoomList(RoomType.TV);
		RoomList lifeRoomList = getRoomList(RoomType.LIFE);
		 
		req.setAttribute("newsRoomList", newsRoomList);
		req.setAttribute("gameRoomList", gameRoomList);
		req.setAttribute("tvRoomList", tvRoomList);
		req.setAttribute("lifeRoomList", lifeRoomList);
		
		return new ModelAndView("index");
	}

	private RoomList getRoomList(RoomType type) {
		List<Room> roomList = TalkUtil.getRoomList(type);
		RoomList resRoomList = new RoomList();
		if(roomList.size()>RoomList.SHOW_NUM) {
			resRoomList.setHasMore(true);
			resRoomList.setRooms(roomList.subList(0, RoomList.SHOW_NUM));
		}else {
			resRoomList.setHasMore(false);
			resRoomList.setRooms(roomList);

		}
		return resRoomList;
	}
	
	/**
	 * 未授权错误页
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/unauthorized")
	private ModelAndView showUnauthorized(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		setLoginUserDTO(req);
		return new ModelAndView("unauthorized");
	}

	private void setLoginUserDTO(HttpServletRequest req) {
		UserDTO user = WebUtil.getLoginUser(userService);
		req.setAttribute("loginUser", user);
	}
}
