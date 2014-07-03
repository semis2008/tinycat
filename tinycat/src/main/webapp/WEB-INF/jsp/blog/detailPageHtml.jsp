<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<section id="diaryDetail_${diary.id }" class="diarySec">
	<h3 class="major">
		<span><i class="icon-reorder"></i> 博文详情</span>
	</h3>

	<div>
		<img width="60px" height="60px" alt="" class="img-polaroid left"
			src="<%=staticPath %>${diary.author_photo }" />
		<blockquote>
			<h3 class="muted">
				<c:choose>
					<c:when test="${fn:length(diary.title) > 20}">
						<c:out value="${fn:substring(diary.title, 0, 20)}......"
							escapeXml="true" />
					</c:when>
					<c:otherwise>
						<c:out value="${diary.title}" escapeXml="true" />
					</c:otherwise>
				</c:choose>

				<em title="回复/阅读数">[${diary.reply_num}/${diary.read_num}]</em>
			</h3>
			<small> <c:forTokens items="${diary.tags }" delims="_"
					var="tag">
					<label class="label"><i class="icon-tag"></i> ${tag }</label>
				</c:forTokens> <em><a class="text-info" title="查看他发布的所有博文" href="#">${diary.author_name
						}</a> 发布于 <fmt:formatDate value="${diary.publish_time }" type="date"
						dateStyle="long" /></em>
			</small>
		</blockquote>
	</div>
	<hr />
	<div class="padding-small">
		<p>${diary.content }</p>
		<p>-----</p>
	</div>

	<div id="comments" class="padding-mini margin-middle">
		<div class="alert">
			BLOG “<a href="#">${diary.title }</a>”
			<c:if test="${diary.reply_num eq 0 }">
			还没有人回复哦，来做第一个<a href="#reply-div">回复</a>的吧~  
		</c:if>
			<c:if test="${diary.reply_num > 0}">
			有${diary.reply_num }条回复
		</c:if>
		</div>
	</div>

	<c:forEach items="${replyList }" var="reply">
		<div id="comment${reply.id }" class="well media margin-mini">
			<a href="#" class="pull-left"> <img
				class="img-polaroid media-object" style="width: 50px; height: 50px;"
				src="<%=staticPath %>${reply.user_photo }"
				data-src="holder.js/64x64" />
			</a>

			<div class="media-body">
				<h4 class="media-heading">
					<fmt:formatDate value="${reply.reply_time }" type="date"
						dateStyle="long" />
					<a href="#" class="name">${reply.user_name }</a>

					<c:if test="${reply.parent_id > 0 }">
						<small>回复 <a href="#">${reply.parent_name }</a>
						</small>
					</c:if>

					<div class="btn-toolbar pull-right">
						<button class="reply-btn btn btn-mini btn-info"
							onclick="showParentDiv(${reply.id})">
							<icon class="icon-pencil icon-white"></icon>
							回复
						</button>
					</div>
				</h4>
				<div>${reply.reply }</div>
			</div>
		</div>
	</c:forEach>
	<hr />
	<div id="reply-div" class="padding-mini">
		<div class="span1" id="quote-img" style="display: none">
			<p>
				<img src="<%=staticPath %>/img/chat/big-quote.png"
					style="width: 40px;" />
			</p>
		</div>
		<div>
			<div class="well media margin-mini" id="quote" style="display: none"></div>
		</div>
		<div class="well media">
			<a href="#" class="pull-left"> <img
				class="media-object img-polaroid" style="width: 50px; height: 50px;"
				src="<%=staticPath %>/img/head/default/defaultGuest.jpg" data-src="holder.js/50x50" />
			</a>
			<div class="media-body">
				<h4 class="media-heading">
					<textarea rows="3" id="user_reply" class="span8"
						placeholder="发表回复..." required></textarea>
				</h4>
			</div>
			<div class="span4 offset2">
				<button class="btn btn-block btn-success" type="submit"
					onclick="commitReplyUser()">回复</button>
			</div>
		</div>
	</div>

	<!-- 隐藏域，提供日志id信息 -->
	<input id="diaryId" value="${diary.id}" type="hidden" />
	<!-- 隐藏域，提供评论id信息 -->
	<input id="parentId" value="0" type="hidden" />
</section>