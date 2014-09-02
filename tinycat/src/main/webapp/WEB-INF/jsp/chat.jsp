<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<div class="row panel">
	<div class="panel-heading">
		${room.typeName } <i class="fa fa-angle-double-right"></i> ${room.name }
	</div>
	<div class="panel-body">
		<div class="col-lg-9">
			<div class="chat_container" id="chat_window">
				<ul id="chat_content" class="list-unstyled">
					<li>
						<div class="media">
							<a href="javascript:void(0)" class="pull-left"> <img
								class="media-object img-polaroid"
								style="width: 32px; height: 32px;"
								src="<%=headPath%>/tinycat.jpg">
							</a>
							<div class="media-body">
								<blockquote>
									<span>这个电视剧真的不怎么样.视剧真的不怎么样.视剧真的不怎么样.视剧真的不怎么样.视剧真的不怎么样.视剧真的不怎么样.视剧真的不怎么样....</span>
								</blockquote>
							</div>
						</div>
					</li>
					<li>
						<div class="media">
							<a href="javascript:void(0)" class="pull-right"> <img
								class="media-object img-polaroid"
								style="width: 32px; height: 32px;"
								src="<%=headPath%>/tinycat.jpg">
							</a>
							<div class="media-body">
								<blockquote class="blockquote-reverse">
									<span>是啊是啊，浪费我时间</span>
								</blockquote>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="col-lg-3">
			<div class="list-group online-users">
				<a href="javascript:void(0)" class="ou-title"> <i
					class="fa fa-users"></i> 本房间活跃用户
				</a>
				<div class="ou-users">
					<c:forEach items="${roomUsers }" var="user">
					<a href="javascript:void(0)" class="list-group-item"><img
						alt="head" class="head_photo_20 img-rounded"
						src="<%=headPath %>/${user.photo }.jpg"> ${user.name }</a>	
					</c:forEach>
				</div>
			</div>

		</div>
		<div class="col-lg-9 input-bar">
			<div class="input-group">
				<input type="text" class="form-control"> <span
					class="input-group-btn">
					<button class="btn btn-success" type="button">发送</button>
				</span>
			</div>
		</div>
	</div>
</div>
