<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a data-target=".nav-collapse" data-toggle="collapse"
				class="btn btn-navbar"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a>
			<div class="brand">
				&nbsp;&nbsp;&nbsp;&nbsp;帝都<span class="text-info">风云录</span>&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
			<div class="nav-collapse collapse navbar-responsive-collapse">
				<ul class="nav">
					<li <%if (fun.equals("index")) {%> class="active" <%}%>><a
						href="<%=contextPath%>/index"><i class="icon-home icon-white"></i>
							主页</a></li>
					<li><a href="<%=contextPath%>/about"><i
							class="icon-info-sign icon-white"></i> 关于</a></li>
				</ul>
				<ul class="nav pull-right">
					<shiro:guest>
						<li><a href="<%=contextPath%>/regist"><i
								class="icon-plus-sign icon-white"></i> 注册</a></li>

						<li><a href="<%=contextPath%>/login"><i
								class="icon-ok-sign icon-white"></i> 登陆</a></li>
					</shiro:guest>
					<shiro:user>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><img alt="head"
								class="head_photo_20 img-circle"
								src="<%=headPath %>/${loginUser.photo }.jpg">
								${loginUser.name }<b class="caret"></b> </a>
							<ul class="dropdown-menu">
								<li><a href="javascript:void(0)"><i
										class=" icon-flag icon-black"></i> 加入时间：<fmt:formatDate
											value="${loginUser.reg_time }" type="date" dateStyle="long" /></a></li>
								<li class="divider"></li>
								<li><a href="<%=contextPath%>/logout"><i
										class="icon-off icon-black"></i> 注销</a></li>
							</ul></li>
					</shiro:user>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 隐藏域，公用 -->
<input type="hidden" value="<%=contextPath%>" id="contextPath">
<input type="hidden" value="<%=headPath%>" id="headPath">
<shiro:guest>
	<input type="hidden" value="-1" id="loginUserId">
</shiro:guest>
<shiro:user>
	<input type="hidden" value="${loginUser.id }" id="loginUserId">
	<input type="hidden" value="${loginUser.name }" id="loginUserName">
	<input type="hidden" value="${loginUser.photo }" id="loginUserPhoto">
</shiro:user>


<div class="fixed goToTopDiv hide pointer padding-small">
	<i onclick="goToTop()" class="icon-chevron-up icon-white icon-2x"></i>
</div>
