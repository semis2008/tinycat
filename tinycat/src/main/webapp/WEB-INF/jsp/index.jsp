<%@ page pageEncoding="utf-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%><!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]--><!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]--><!-- BEGIN HEAD --><%@ include file="common/global.jsp"%><head><meta charset="utf-8" /><meta content="width=device-width, initial-scale=1.0" name="viewport" /><%@ include file="common/head-content.jsp"%><link href="<%=staticPath%>/css/index.css" rel="stylesheet"	type="text/css" /><link href="<%=staticPath%>/css/chat.css" rel="stylesheet"	type="text/css" /></head><!-- END HEAD --><!-- BEGIN BODY --><body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">	<input type="hidden" value="<%=contextPath%>" id="contextPath">	<input type="hidden" value="<%=headPath%>" id="headPath">	<shiro:guest>		<input type="hidden" value="-1" id="loginUserId">	</shiro:guest>	<shiro:user>		<input type="hidden" value="${loginUser.id }" id="loginUserId">		<input type="hidden" value="${loginUser.name }" id="loginUserName">		<input type="hidden" value="${loginUser.photo }" id="loginUserPhoto">	</shiro:user>	<!-- Navigation -->	<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">		<div class="container">			<div class="navbar-header">				<button type="button" class="navbar-toggle" data-toggle="collapse"					data-target=".navbar-main-collapse">					<i class="fa fa-bars"></i>				</button>				<a class="navbar-brand page-scroll menu" href="#page-top"> <i					class="fa fa-play-circle"></i> <span class="light">开始</span>					WanTalk				</a>			</div>			<!-- Collect the nav links, forms, and other content for toggling -->			<div				class="collapse navbar-collapse navbar-right navbar-main-collapse">				<ul class="nav navbar-nav">					<!-- Hidden li included to remove active class from about link when scrolled up past about section -->					<li class="hidden"><a href="#page-top"></a></li>					<li><a class="page-scroll menu" href="#rooms">房间</a></li>					<li><a class="page-scroll menu" href="#talk">聊天</a></li>					<shiro:guest>						<li><a class="page-scroll menu" href="#login">登陆/注册</a></li>					</shiro:guest>					<shiro:user>						<li><a class="page-scroll menu" href="#login"><img								alt="head" class="head_photo_20 img-circle"								src="<%=headPath %>/${loginUser.photo }.jpg"> <span								id="head-user-name">${loginUser.name }</span> </a></li>					</shiro:user>				</ul>			</div>			<!-- /.navbar-collapse -->		</div>		<!-- /.container -->	</nav>	<!-- Intro Header -->	<header class="intro">		<div class="intro-body">			<div class="container">				<div class="row">					<div class="col-md-8 col-md-offset-2">						<h1 class="brand-heading">							WANT<em>alk</em>						</h1>						<p class="intro-text">免费，开放的在线聊天平台。立刻选择喜欢的房间，看看大家都在讨论什么.</p>						<a href="#rooms" class="btn btn-circle page-scroll"> <i							class="fa fa-angle-double-down animated"></i>						</a>					</div>				</div>			</div>		</div>	</header>	<!-- About Section -->	<section id="rooms" class="container content-section ">		<div class="row">			<div class="col-lg-12">				<div class="list-group col-lg-3">					<a href="javascript:void(0);" class="list-group-item title">新闻</a>					<div id="news_room_list"> 						<c:forEach items="${newsRoomList.rooms }" var="room">							<a href="#talk" class="list-group-item">${room.name }<label class="pull-right"><i class="fa fa-user"></i> ${room.userCount }</label></a>						</c:forEach>					</div>					<a class="tip addRoom list-group-item btn btn-circle pull-left"						data-toggle="tooltip" data-placement="top" title="新建一个房间"> <i						class="fa fa-plus"></i>					</a>					 <a id="news_change_rooms_down" page-num="1"						class="tip <c:if test="${newsRoomList.hasMore==false }">hidden</c:if> changeRooms list-group-item btn btn-circle pull-right"						data-toggle="tooltip" data-placement="top" title="下一批房间"> <i						class="fa fa-angle-double-down"></i>					</a>					 <a id="news_change_rooms_up" page-num="1"						class="tip changeRooms list-group-item btn btn-circle hidden pull-right"						data-toggle="tooltip" data-placement="top" title="上一批房间"> <i						class="fa fa-angle-double-up"></i>					</a>				</div>				<div class="list-group col-lg-3">					<a href="javascript:void(0);" class="list-group-item title">游戏</a>					<div id="game_room_list">						<c:forEach items="${gameRoomList.rooms }" var="room">							<a href="#talk" class="list-group-item">${room.name }<label class="pull-right"><i class="fa fa-user"></i> ${room.userCount }</label></a>						</c:forEach>					</div>					<a class="tip addRoom list-group-item btn btn-circle pull-left"						data-toggle="tooltip" data-placement="top" title="新建一个房间"> <i						class="fa fa-plus"></i>					</a>					 <a id="game_change_rooms_down" page-num="1"						class="tip <c:if test="${gameRoomList.hasMore==false }">hidden</c:if> changeRooms list-group-item btn btn-circle pull-right"						data-toggle="tooltip" data-placement="top" title="下一批房间"> <i						class="fa fa-angle-double-down"></i>					</a>					 <a id="game_change_rooms_up" page-num="1"						class="tip changeRooms list-group-item btn btn-circle hidden pull-right"						data-toggle="tooltip" data-placement="top" title="上一批房间"> <i						class="fa fa-angle-double-up"></i>					</a>				</div>				<div class="list-group col-lg-3">					<a href="javascript:void(0);" class="list-group-item title">影视剧</a>					<div id="tv_room_list">						<c:forEach items="${tvRoomList.rooms }" var="room">							<a href="#talk" class="list-group-item">${room.name }<label class="pull-right"><i class="fa fa-user"></i> ${room.userCount }</label></a>						</c:forEach>					</div>					<a class="tip addRoom list-group-item btn btn-circle pull-left"						data-toggle="tooltip" data-placement="top" title="新建一个房间"> <i						class="fa fa-plus"></i>					</a>					 <a id="tv_change_rooms_down" page-num="1"						class="tip <c:if test="${tvRoomList.hasMore==false }">hidden</c:if> changeRooms list-group-item btn btn-circle pull-right"						data-toggle="tooltip" data-placement="top" title="下一批房间"> <i						class="fa fa-angle-double-down"></i>					</a>					 <a id="tv_change_rooms_up" page-num="1"						class="tip changeRooms list-group-item btn btn-circle hidden pull-right"						data-toggle="tooltip" data-placement="top" title="上一批房间"> <i						class="fa fa-angle-double-up"></i>					</a>				</div>				<div class="list-group col-lg-3">					<a href="javascript:void(0);" class="list-group-item title">生活</a>					<div  id="life_room_list">						<c:forEach items="${lifeRoomList.rooms }" var="room">							<a href="#talk" class="list-group-item">${room.name }<label class="pull-right"><i class="fa fa-user"></i> ${room.userCount }</label></a>						</c:forEach>					</div>					<a class="tip addRoom list-group-item btn btn-circle pull-left"						data-toggle="tooltip" data-placement="top" title="新建一个房间"> <i						class="fa fa-plus"></i>					</a>					 <a id="life_change_rooms_down" page-num="1"						class="tip <c:if test="${lifeRoomList.hasMore==false }">hidden</c:if> changeRooms list-group-item btn btn-circle pull-right"						data-toggle="tooltip" data-placement="top" title="下一批房间"> <i						class="fa fa-angle-double-down"></i>					</a>					 <a id="life_change_rooms_up" page-num="1"						class="tip changeRooms list-group-item btn btn-circle pull-right"						data-toggle="tooltip" data-placement="top" title="上一批房间"> <i						class="fa fa-angle-double-up"></i>					</a>				</div>			</div>			<div class="col-lg-8 col-lg-offset-2" id="new-room-div">				<div class="new-room">					<div class="form-horizontal">						<div class="form-group">							<label class="col-sm-2 control-label">类别</label>							<div class="col-sm-10">								<div class="styled-select">									<select id="add-room-type">										<option value="news">新闻</option>										<option value="game">游戏</option>										<option value="tv">影视剧</option>										<option value="life">生活</option>									</select>								</div>							</div>						</div>						<div class="form-group">							<label for="roomName" class="col-sm-2 control-label">房间名称</label>							<div class="col-sm-10">								<input type="text" class="form-control" id="add-room-name"									placeholder="输入房间名称">							</div>						</div>						<div class="form-group">							<label for="roomPassword" class="col-sm-2 control-label">设置密码</label>							<div class="col-sm-10">								<input type="text" class="form-control" id="add-room-password"									placeholder="房间密码，不输入则无密码">							</div>						</div>						<div class="form-group">							<div class="col-sm-offset-2 col-sm-10">								<button id="add-room-btn" class="btn btn-success">创建</button>								<button type="reset" class="btn btn-default">重置</button>							</div>						</div>					</div>				</div>			</div>		</div>	</section>	<!-- talk Section -->	<section id="talk" class="content-section">		<div class="mid-section">			<div class="container">				<%@ include file="chat.jsp"%>			</div>		</div>	</section>	<!-- Contact Section -->	<section id="login" class="content-section text-center">		<div class="container">			<div class="row" id="login-info-row">				<div class="col-lg-12">					<shiro:guest>						<p id="free-title">开始和世界各地的朋友们讨论你所关注的话题！</p>						<p id="free-description">							免费注册 <span class="line"> | </span> 创建自己的房间并邀请好友加入 <span								class="line"> | </span> <span class="no-exp"> 简约轻便 </span>						</p>						<a id="show-regist-btn" href="javascript:void(0)"							class="btn btn-default btn-large"> <span>马上注册!</span>						</a> | <a id="show-login-btn" href="javascript:void(0)"							class="btn btn-success btn-large"> <span>登陆</span>						</a>					</shiro:guest>					<shiro:user>						<ul class="list-unstyled">							<li><p>									<img alt="head" class="head_photo_100"										src="<%=headPath %>/${loginUser.photo }.jpg">								</p></li>							<li><p class="InfoUserName text-center">									<i class="fa fa-user fa-border"></i> ${loginUser.name}								</p></li>							<li><p>									注册时间：									<fmt:formatDate value="${loginUser.reg_time }" type="date"										dateStyle="long" />								</p></li>							<li><div id="change_photo" class="tip btn btn-default"									data-toggle="tooltip" data-placement="top"									title="从头像库里随机选择一个，更换为自己的头像">更换头像（随机）</div> |								<div id="change_name" class="tip btn btn-primary"									data-toggle="tooltip" data-placement="top" title="编辑自己的昵称">									编辑昵称 <i class="fa fa-caret-up" id="hide-edit-name"></i><i										id="show-edit-name" class="fa fa-caret-down"></i>								</div> |								<div onclick="location='<%=contextPath%>/logout'"									data-toggle="tooltip" data-placement="top" title="退出WanTalk"									class="tip btn btn-danger">退出</div></li>							<li>								<div class="edit-name col-lg-4 col-lg-offset-4">									<span class="outer-sp"> <span class="inner-sp"> <input											type="text" id="new-name-text" placeholder="输入新昵称" />											<button id="new-name-btn" class="btn btn-success btn-block"												type="button">确认</button>									</span>									</span>								</div>							</li>						</ul>					</shiro:user>				</div>			</div>			<div class="row" id="login-row">				<div class="col-lg-12">					<div class="loginpanel">						<i id="loading_login" class="hidden fa fa-spinner fa-spin bigicon"></i>						<h2>							<span class="fa fa-quote-left "></span> 登录 <span								class="fa fa-quote-right "></span> <a								id="show-info-btn-from-login" href="javascript:void(0)"								class="pull-right"><i class="fa fa-mail-reply"></i></a>						</h2>						<div id="login-error-tip" class="hidden alert alert-danger">							<i class="fa fa-exclamation-triangle"></i> <span></span>						</div>						<div>							<input id="login_email" type="email" placeholder="登录邮箱"								onblur="check_login();"> <input id="login_password"								type="password" placeholder="输入密码" onblur="check_login();">							<div class="buttonwrapper">								<button id="loginbtn" class="btn btn-default loginbutton">									<span class="fa fa-check"></span>								</button>								<span id="lockbtn_login" class="fa fa-lock lockbutton redborder"></span>							</div>						</div>					</div>				</div>			</div>			<div class="row" id="regist-row">				<div class="col-lg-12">					<div class="loginpanel">						<i id="loading_regist"							class="hidden fa fa-spinner fa-spin bigicon"></i>						<h2>							<span class="fa fa-quote-left "></span> 注册 <span								class="fa fa-quote-right "></span> <a								id="show-info-btn-from-regist" href="javascript:void(0)"								class="pull-right"><i class="fa fa-mail-reply"></i></a>						</h2>						<div id="regist-error-tip" class="hidden alert alert-danger">							<i class="fa fa-exclamation-triangle"></i> <span></span>						</div>						<div>							<input id="regist_email" type="email" placeholder="注册邮箱"								onkeypress="check_regist();"> <input								id="regist_username" type="text" placeholder="昵称"								onkeypress="check_regist();"> <input								id="regist_password" type="password" placeholder="输入密码"								onkeypress="check_regist();">							<div class="buttonwrapper">								<button id="registbtn" class="btn btn-default loginbutton">									<span class="fa fa-check"></span>								</button>								<span id="lockbtn_regist"									class="fa fa-lock lockbutton redborder"></span>							</div>						</div>					</div>				</div>			</div>		</div>	</section>	<!-- Footer -->	<footer>		<div class="container text-center">			<ul class="list-inline">				<li>&copy; <%=ICP_BEIAN%>.				</li>				<li>/</li>				<li>author:<%=author%></li>				<li>/</li>				<li>email: <a href="javascript:void(0)">semis2008@126.com</a></li>			</ul>		</div>	</footer>	<!-- jQuery Version 1.11.0 -->	<script src="<%=staticPath%>/js/lib/jquery-1.11.1.min.js"></script>	<!-- jQuery Version 1.11.0 -->	<script src="<%=staticPath%>/js/lib/jquery.easing.min.js"></script>	<!-- Bootstrap Core JavaScript -->	<script src="<%=staticPath%>/js/bootstrap.min.js"></script>	<!-- Custom Theme JavaScript -->	<script src="<%=staticPath%>/js/common.js"></script>	<!-- Custom Theme JavaScript -->	<script src="<%=staticPath%>/js/socket.js"></script>	<script src="<%=staticPath%>/js/index.js"></script></body></html>