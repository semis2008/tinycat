<%@ page pageEncoding="utf-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]--><!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]--><!-- BEGIN HEAD --><%@ include file="common/global.jsp"%><head><meta charset="utf-8" /><meta content="width=device-width, initial-scale=1.0" name="viewport" /><%@ include file="common/head-content.jsp"%><link href="<%=staticPath%>/css/index.css" rel="stylesheet"	type="text/css" /></head><!-- END HEAD --><!-- BEGIN BODY --><body>	<%@ include file="common/header.jsp"%>	<input type="hidden" value="<%=contextPath%>" id="contextPath">	<!-- BEGIN CONTAINER -->	<div id="content">		<header class="no-radius image hero-unit" id="banner-head">			<div class="container text-center logo">				<h1>					<img alt="Kalor" src="<%=staticPath%>/img/logo.png">				</h1>			</div>		</header>		<div id="main-wrapper" class="container">			<div class="row">				<div class="span3">					<!-- 活跃用户 -->					<section id="activeUserSec">						<h3 class="major">							<span><i class="icon-user"></i> 活跃の喵</span>						</h3>						<ul class="unstyled inline">							<c:forEach items="${activeUsers }" var="user">								<li class="padding-mini"><a href="javascript:void(0)"									title="${user.name }"> <img										src="<%=staticPath %>/${user.photo}" class="img-polaroid"										width="32px" height="32px" alt=""> <span										class="activeUserName text-center">${user.name} </span>								</a></li>							</c:forEach>						</ul>						<a class="btn btn-primary btn-large" href="javascript:void(0)">注册后你也可以显示在这里						</a>					</section>					<!-- /活跃用户 -->				</div>				<div class="span9">					<section id="loginSec">						<h3 class="major">							<span><i class="icon-plus-sign"></i> 注册</span>						</h3>						<div>							<form class="form-horizontal">								<div class="control-group">									<label class="control-label" for="inputEmail">注册邮箱</label>									<div class="controls">										<input type="email" required class="input-xlarge"											id="inputEmail" placeholder="test@example.com"> <em											class="help-inline">您登陆时所需的凭证</em>									</div>								</div>								<div class="control-group">									<label class="control-label" for="inputName">昵称</label>									<div class="controls">										<input type="text" required class="input-xlarge"											id="inputName" placeholder="昵称"> <em											class="help-inline">站内的昵称，起个好名字吧~</em>									</div>								</div>								<div class="control-group">									<label class="control-label" for="inputPassword">密码</label>									<div class="controls">										<input type="text" required class="input-xlarge"											id="inputPassword" placeholder="您的密码"> <em											class="help-inline">密码要记牢哦~</em>									</div>								</div>								<div class="control-group">									<label class="control-label" for="inputPasswordCon">确认密码</label>									<div class="controls">										<input type="password" required class="input-xlarge"											id="inputPasswordCon" placeholder="再输入一次"> <em											class="help-inline">重复之前输入的密码</em>									</div>								</div>								<div class="control-group">									<label class="control-label" for="inputRandCode">验证码</label>									<div class="controls">										<input type="text" required 											id="inputRandCode" placeholder="验证码"> <img											src="<%=contextPath%>/RandImg"											onclick="this.src='<%=contextPath%>/RandImg?d='+Math.random();" />										<em class="help-inline">请输入左图中字母</em>									</div>								</div>								<div class="control-group">									<div class="controls">										<button type="reset" class="btn btn-large ">											<i class="icon-remove-sign"></i> 重置										</button>										<button type="button" class="btn btn-large btn-success" onclick="userRegist()">											<i class="icon-plus-sign"></i> 注册										</button>									</div>								</div>							</form>						</div>					</section>				</div>			</div>		</div>	</div>	<!-- 底部 -->	<%@ include file="common/bottom.jsp"%></body></html>