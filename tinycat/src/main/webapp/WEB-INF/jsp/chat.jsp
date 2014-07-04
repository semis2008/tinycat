<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
							class="media-object img-circle"
							style="width: 44px; height: 44px;" src="<%=headPath%>/4.jpg">
						</a>
						<div class="media-body">
							<blockquote>
								<p class="">我是 tinyCat,快来和我说说话啊~</p>
								<small>TinyCat <em><fmt:formatDate value="<%=now %>"
													type="time" dateStyle="short" /></em></small>
							</blockquote>
						</div>
					</div>
				</li><li>
					<div class="media">
						<a href="#" class="pull-right"> <img
							class="media-object img-circle"
							style="width: 44px; height: 44px;" src="<%=headPath%>/0.jpg">
						</a>
						<div class="media-body">
							<blockquote class="pull-right">
								<p class="">你是谁？</p>
								<small>张三 <em>15:54</em></small>
							</blockquote>
						</div>
					</div>
				</li>
				<li></li>
			</ul>
		</div>
	</div>
	<footer class="chat_toolbar_footer">
		<div class="chat_toolbar">
			<textarea class="input input_white chat_textarea"></textarea>
			<button class="btn btn-success">
				<span>发送</span>
			</button>
		</div>
	</footer>
</div>