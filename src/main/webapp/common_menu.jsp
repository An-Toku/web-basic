<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<div class="left">
			<c:if test="${not empty sessionId}">
			<div class="top">
				<div class="profileBar">
					<img src="img/prof.png" class="profileImg">
					<div class="smallInfo">
						<p class="name">${sessionName}님</p>
						<span class="memberBadge">
						<c:choose>
							<c:when test="${sessionLevel eq 'top'}">매니저</c:when>
							<c:otherwise>회원</c:otherwise>
						</c:choose>
						</span>
					</div>
					<p class="profileGreeting">오늘도 즐거운 공부 되세요.</p>
					<div class="content">
						<strong>가입일</strong>
						<p>${member_summary.getReg_date()}</p>
					</div>
					<div class="content">
						<strong>최근 작성 포스트</strong>
						<a href="">일본에 다녀옴...</a>
					</div>
					<div class="content">	
						<strong>최근 작성 질문</strong>
						<a href="">일본어로 거위...</a>
					</div>
				</div>
			</div>
			</c:if>
			
			<div class="bottom">
				<div class="noticeBox">
					<div class="titleBox">
						<a href="javascript:goPage('Notice', 'list')"><p class="title">공지사항</p></a>
					</div>
					<form name="noticeSummaryView">
						<input type="hidden" name="t_action">
						<input type="hidden" name="t_no">
						<div class="content">
							<c:forEach var="dto" items="${noticeDtos}"> 
								<a href="javascript:goNoticeView('${dto.getNo()}')"><span>${dto.getTitle()}</span><small>${dto.getReg_date()}</small></a>
							</c:forEach>
						</div>
					</form>
				</div>
			</div>
		</div>
