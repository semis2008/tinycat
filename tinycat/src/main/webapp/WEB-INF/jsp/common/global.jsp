<%@page import="com.wnJava.vo.UserVO"%>
<%@ page pageEncoding="utf-8" %>
<%
	// 设置basePath参数
	String contextPath = request.getContextPath(); // 工程路径
	int serverPort = request.getServerPort(); // 服务器端口
	StringBuffer urlStr = request.getRequestURL();
	UserVO user = (UserVO)request.getAttribute("loginUser");
	
	//获取当前页面模块
	String fun = "index";
	if(urlStr.indexOf("index")>0) {
		fun = "index";
	}
	if(urlStr.indexOf("blog")>0) {
		fun = "blog";
	}
	if(urlStr.indexOf("lucence")>0) {
		fun = "lucence";
	}
	 
	String title = "Kalor | 快速 尖锐 不凡";
	String description = "或许可以做成最简单的社区网站。";
	String author = "偷懒的熊";
	String keywords = "社区 java lucence 快速开发 bootstrap";
	String version = "?v_1.0.0";
	String staticPath = contextPath + "/static";
	String ICP_BEIAN = "2013 京ICP备13011487号";
	
%>
<!DOCTYPE html>
