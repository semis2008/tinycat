<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<h3 class="major">
	<span><i class="icon-star-empty"></i> 推荐博文</span>
</h3>
<div>
	<img width="60px" height="60px" alt="" class="img-polaroid left"
		src="<%=staticPath %>${topDiary.author_photo }" />
	<blockquote>
		<h3 class="muted">
			<c:choose>
				<c:when test="${fn:length(topDiary.title) > 20}">
					<c:out value="${fn:substring(topDiary.title, 0, 20)}......"
						escapeXml="true" />
				</c:when>
				<c:otherwise>
					<c:out value="${topDiary.title}" escapeXml="true" />
				</c:otherwise>
			</c:choose>
			<em title="回复/阅读数">[11/22]</em>
		</h3>
		<small> <label class="label"><i class="icon-tag"></i>
				123</label> <em><a class="text-info" title="查看他发布的所有博文" href="#">${topDiary.author_name}</a>
				发布于 <fmt:formatDate value="${topDiary.publish_time }" type="date" dateStyle="long"/></em>
		</small>
	</blockquote>
</div>
<div>
	<p>
		<c:choose>
			<c:when test="${fn:length(topDiary.content) > 200}">
				<c:out value="${fn:substring(topDiary.content, 0, 200)}......"
					escapeXml="true" />
			</c:when>
			<c:otherwise>
				<c:out value="${topDiary.content}" escapeXml="true" />
			</c:otherwise>
		</c:choose>
	</p>
</div>

<a class="button" data-type="pjax" href="#">阅读全文 <i
	class="icon-chevron-down"></i>
</a>
<a href="javascript:void(0)" onclick="changeTopDiary(${topDiary.id }"
	onmouseover="$('.changeTopDiary i').addClass('icon-spin');"
	onmouseout="$('.changeTopDiary i').removeClass('icon-spin');"
	title="换一篇" class="changeTopDiary"><i class="icon-refresh icon-2x"></i>
</a>