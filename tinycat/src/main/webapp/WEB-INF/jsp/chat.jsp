<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->

<div class="panel chat-panel">
	<header class="panel_header">
		<h3 class="">Tiny Cat</h3>
	</header>
	<div class="panel_body_container">
		<div class="chat_container">
			<ul id="chat_content" class="unstyled">
				<li>
					<div class="media">
						<a href="#" class="pull-left"> <img
							class="media-object img-polaroid"
							style="width: 32px; height: 32px;" src="<%=headPath%>/4.jpg">
						</a>
						<div class="media-body">
							<blockquote>
								<p class="">我是 tinyCat,快来和我说说话啊~</p>
								<small>TinyCat <em><fmt:formatDate value="<%=now %>"
													type="time" dateStyle="short" /></em></small>
							</blockquote>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<footer class="chat_toolbar_footer">
		<div class="chat_toolbar">
			<textarea id="sendMsgText" class="input input_white chat_textarea"></textarea>
			<shiro:guest>
			<button class="btn btn-success" id="sendBtn" onclick="sendMsg(0)">
				<span>发送</span>
			</button>
			</shiro:guest>
			<shiro:user>
			<button class="btn btn-success" id="sendBtn" onclick="sendMsg(1)" disabled="">
				<span>发送</span>
			</button>
			</shiro:user>			
		</div>
	</footer>
</div>