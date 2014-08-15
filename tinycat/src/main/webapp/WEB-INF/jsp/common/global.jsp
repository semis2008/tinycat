<%@page import="java.util.Date"%>
<%@page import="com.tinycat.dto.UserDTO"%>
<%@ page pageEncoding="utf-8" %>
<%
	// 设置basePath参数
	String contextPath = request.getContextPath(); // 工程路径
	int serverPort = request.getServerPort(); // 服务器端口
	StringBuffer urlStr = request.getRequestURL();
	UserDTO user = (UserDTO)request.getAttribute("loginUser");
	
	Date now = new Date();
	//获取当前页面模块
	String fun = "index";
	if(urlStr.indexOf("index")>0) {
		fun = "index";
	}
	if(urlStr.indexOf("teach")>0) {
		fun = "teach";
	}
	 
	String title = "帝都风云录 | 多人在线文字类页游";
	String description = "在虚拟城市--帝都中，创造自己的风云录";
	String author = "伪冥";
	String keywords = "文字类 页游 虚拟角色  人工智能 交互";
	String version = "?v_1.0.0";
	String staticPath = contextPath + "/static";
	String headPath = staticPath + "/img/head";
	
	String ICP_BEIAN = "2013 京ICP备13011487号";
	
%>
<!DOCTYPE html>
