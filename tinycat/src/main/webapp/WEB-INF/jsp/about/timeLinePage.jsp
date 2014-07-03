<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- BEGIN PAGE CONTENT-->
<div class="row-fluid">
	<div class="span12">
		<ul class="timeline">
			<li class="timeline-yellow">
				<div class="timeline-time">
					<span class="date">5/22/14</span> <span class="time">11:37</span>
				</div>
				<div class="timeline-icon">
					<i class="icon-trophy"></i>
				</div>
				<div class="timeline-body">
					<h2>再次对wnjava进行重构</h2>
					<div class="timeline-content">
						<img class="timeline-img pull-left"
							src="<%=staticPath%>/image/2.jpg" alt="">
						在wnjava上线运行了1年之后，域名和空间到期，我并没有续费。我的想法是用一段时间再次重构一下项目本身，这次重构主要有下面几方面的考虑：
						1.原来的项目代码很难管理，重构后将使用maven进行管理
						2.重构将使用更多的便利性框架技术，如SpringMVC和MetroNIC 3.重构将继续删减系统功能，保留的博客功能也需要重构
						4.重构将在代码级别完善现有代码，为以后的扩展做准备
					</div>
				</div>
			</li>
			<li class="timeline-blue">
				<div class="timeline-time">
					<span class="date">19/11/13</span> <span class="time">12:04</span>
				</div>
				<div class="timeline-icon">
					<i class="icon-facetime-video"></i>
				</div>
				<div class="timeline-body">
					<h2>wnjava--恋爱时间线 模块开发完毕。</h2>
					<div class="timeline-content">
						<img class="timeline-img pull-left"
							src="<%=staticPath%>/image/1.jpg" alt="">
						恋爱时间线是独立于wnjava的一个小模块。主要是记录我和我女朋友的相识的点点滴滴，同时提醒我许多东西。做的不是很完整，不过已经可以用了
					</div>
				</div>
			</li>
			<li class="timeline-green">
				<div class="timeline-time">
					<span class="date">28/8/13</span> <span class="time">15:36</span>
				</div>
				<div class="timeline-icon">
					<i class="icon-comments"></i>
				</div>
				<div class="timeline-body">
					<h2>重构编辑和删除日志</h2>
					<div class="timeline-content">
						<img class="timeline-img pull-left"
							src="<%=staticPath%>/image/3.jpg" alt=""> 对原来的逻辑进行了重构
					</div>
				</div>
			</li>
			<li class="timeline-purple">
				<div class="timeline-time">
					<span class="date">10/8/13</span> <span class="time">13:15</span>
				</div>
				<div class="timeline-icon">
					<i class="icon-music"></i>
				</div>
				<div class="timeline-body">
					<h2>使用Pjax</h2>
					<div class="timeline-content">
						使用Pjax进行数据的提交以及页面的跳转。Pjax是Ajax和PushState两个技术的结合，可以实现在不刷新全局页面的情况下，异步刷新页面，同时保留用户的浏览历史。
						Pjax虽然集成进来了，但是存在一些bug.</div>
				</div>
			</li>
			<li class="timeline-red">
				<div class="timeline-time">
					<span class="date">7/8/13</span> <span class="time">11:30</span>
				</div>
				<div class="timeline-icon">
					<i class="icon-rss"></i>
				</div>
				<div class="timeline-body">
					<h2>整理代码</h2>
					<div class="timeline-content">
						<img class="timeline-img pull-left"
							src="<%=staticPath%>/image/5.jpg" alt="">
						对wnjava项目代码进行清理，便于接下来的开发。删除了一些资源，同时增加了许多插件。
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>
<!-- END PAGE CONTENT-->