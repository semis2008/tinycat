<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<article>
	<!-- 推荐日志 -->
	<section id="topDiarySec" class="diarySec">
		<%@include file="topDiaryHtml.jsp"%>
	</section>
	<!-- /推荐日志 -->
	<!-- 热门日志 -->
	<section id="hotDiarySec">
		<h3 class="major">
			<span><i class="icon-lightbulb"></i> 热门博文</span>
		</h3>
		<div>
			<div class="span4">
				<ul class="unstyled">
					<c:forEach items="${leftHotDiary}" var="diary">
						<li><a href="<%=contextPath %>/blog/${diary.id }"><i
								class="icon-lightbulb"></i> <c:choose>
									<c:when test="${fn:length(diary.title) > 20}">
										<c:out value="${fn:substring(diary.title, 0, 20)}..."
											escapeXml="true" />
									</c:when>
									<c:otherwise>
										<c:out value="${diary.title}" escapeXml="true" />
									</c:otherwise>
								</c:choose> </a> <em>[${diary.reply_num }/${diary.read_num }]</em></li>
					</c:forEach>
				</ul>
			</div>
			<div class="span4">
				<ul class="unstyled">
					<c:forEach items="${rightHotDiary}" var="diary">
						<li><a href="<%=contextPath %>/blog/${diary.id }"><i class="icon-lightbulb"></i> <c:choose>
									<c:when test="${fn:length(diary.title) > 20}">
										<c:out value="${fn:substring(diary.title, 0, 20)}......"
											escapeXml="true" />
									</c:when>
									<c:otherwise>
										<c:out value="${diary.title}" escapeXml="true" />
									</c:otherwise>
								</c:choose> </a> <em>[${diary.reply_num }/${diary.read_num }]</em></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<a class="button" href="# ">查看全部 <i class=" icon-hand-right"></i>
		</a>
	</section>
	<!-- /热门日志 -->
	<!-- 关于 -->
	<section id="aboutSec">
		<h3 class="major">
			<span><i class="icon-info-sign"></i> 关于</span>
		</h3>
		<div class="row">
			<div class="span3">
				<img style="width: 80px; height: 80px"
					src="<%=staticPath%>/img/wn_head01.jpg"
					class="img-rounded margin-bottom-middle" />
				<h5>我是王宁，本站的作者，毕业两年,程序员。</h5>
				<ul>
					<li>喜欢创造的快乐。</li>
					<li>喜欢<span class="label label-info">小懒虫</span>。
					</li>
					<li>游泳篮球LOL,And<span class="label label-info">大菠萝</span></li>
					<li>喜欢各种萌物，奋斗中...</li>
				</ul>
			</div>

			<div class="span5">
				<img style="width: 200px; height: 80px"
					src="<%=staticPath%>/img/logo.png"
					class="img-rounded margin-bottom-middle" />
				<p>
				<h5>现在大家看到的wnJava的设计样式，算是第四版了，相比前面的版本，第四版主要进行了以下几处的改进：</h5>
				<ul class="">
					<li>在原有风格基础上，调整并细化了部分页面的<span class="label label-info">显示效果</span></li>
					<li>使用<span class="label label-info">shiro</span>框架构建网站权限控制</li>
					<li>使用<span class="label label-info">maven</span>管理代码</li>
					<li>使用了许多新技术，例如全文检索的<span class="label label-info">lucence</span></li>
				</ul>
				</p>
			</div>
		</div>
		<div class="row">
			<div class="span7 alert alert-info row margin-top-small">
				<i class="icon-github"></i> GitHub源码地址 ：
				https://github.com/semis2008/kalor
			</div>
		</div>
	</section>
	<!-- /关于WnJava -->
	<!-- 发布留言 -->
	<section id="leaveMsgSec">
		<h3 class="major">
			<span><i class="icon-envelope"></i> 给我留言</span>
		</h3>
		<div class="row">
			<div class="span4">
				<input type="text" placeholder="Name" id="leave-msg-name"
					class="text">
			</div>
			<div class="span4">
				<input type="email" placeholder="Email" id="leave-msg-email"
					class="text">
			</div>
		</div>
		<div class="row">
			<div class="span8">
				<textarea rows="5" placeholder="Message" id="leave-msg-content"></textarea>
			</div>
		</div>
		<div class="row">
			<div class="span8">
				<ul class="unstyled inline">
					<li><a class="button">留言</a></li>
					<li><a class="button button-alt">清空</a></li>
				</ul>
			</div>
		</div>
	</section>
	<!-- /发布留言 -->
</article>
