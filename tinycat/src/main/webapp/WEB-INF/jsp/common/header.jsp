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
			<div class="brand">&nbsp;&nbsp;&nbsp;&nbsp;Kalor&nbsp;&nbsp;&nbsp;&nbsp;</div>
			<div class="nav-collapse collapse navbar-responsive-collapse">
				<ul class="nav">
					<li <%if (fun.equals("index")) {%> class="active" <%}%>><a
						href="<%=contextPath%>/index"><i class="icon-home icon-white"></i>
							首页</a></li>
					<li <%if (fun.equals("blog")) {%> class="active" <%}%>><a
						href="<%=contextPath%>/blog"><i class="icon-edit icon-white"></i>
							博文</a></li>
					<li <%if (fun.equals("lucence")) {%> class="active" <%}%>><a
						href="<%=contextPath%>/lucence"><i
							class="icon-search icon-white"></i> lucence</a></li>
					<li><a href="<%=contextPath%>/index#leaveMsgSec"><i
							class="icon-envelope icon-white"></i> 留言</a></li>
					<li><a href="<%=contextPath%>/index#aboutSec"><i
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
						data-toggle="dropdown">${loginUser.name }<b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#"><i class="icon-home icon-black"></i>
									个人主页</a></li>
							<li><a href="javascript:void(0)"><i
									class="icon-edit icon-black"></i> 日志：${loginUser.blogNum }篇</a></li>
							<li class="divider"></li>
							<li><a href="#" data-type="pjax"><i
									class="icon-pencil icon-black"></i> 写日志</a></li>
							<li class="divider"></li>
							<li><a href="#" onclick="userQuit();"><i
									class="icon-off icon-black"></i> 注销</a></li>
						</ul></li>
						</shiro:user>
				</ul>
			</div>
		</div>
	</div>
</div>

<div class="fixed goToTopDiv hide pointer padding-small">
	<i onclick="goToTop()" class="icon-chevron-up icon-white icon-2x"></i>
</div>
