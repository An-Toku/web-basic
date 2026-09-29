<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common_header.jsp" %>
<link href="css/pages/notice.css" rel="stylesheet">
<script src="notice.script/notice_list.js"></script>
<form name="noticeView">
	<input type="hidden" name="t_action">
	<input type="hidden" name="t_no">
</form>
	<div class="body">
		<%@ include file="../common_menu.jsp" %>
		
		<div class="right">
			<div class="list">
				<div class="formTitle">
					<h1>공지사항</h1>
				</div>
				<div class="formSearchArea">
					<p>총게시글 ${totalCount}건</p>
					<form name="noticeList">
						<input type="hidden" name="t_action" value="list">
						<input type="hidden" name="t_nowPage">
						<div class="searchInputs">
							<select name="t_select">
								<option value="title" <c:if test="${select eq 'title'}">selected</c:if>>제목</option>
								<option value="content" <c:if test="${select eq 'content'}">selected</c:if>>내용</option>
							</select>
							<input type="text" name="t_search" value="${search}">
							<input type="button" value="검색" class="button" onclick="goSearch()">
						</div>
					</form>
				</div>
				<table>
					<colgroup>
						<col width="9%">
						<col width="45%">
						<col width="9%">
						<col width="13%">
						<col width="15%">
						<col width="9%">
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>첨부</th>
							<th>게시자</th>
							<th>게시일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${dtos}" var="dto" varStatus="loop">
							<tr>
								<td>${order - loop.index}</td>
								<td><a href="javascript:goNoticeView('${dto.getNo()}')">${dto.getTitle()}</a></td>
								<th>
									<c:if test="${not empty dto.getAttach()}">
										<img src="img/clip.png">
									</c:if>
								</th>
								<td>${dto.getReg_name()}</td>
								<td>${dto.getReg_date()}</td>
								<td>${dto.getHit()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div class="paging">
					${pageDisplay}
					<c:if test="${sessionLevel eq 'top'}">
						<a href="javascript:goPage('Notice', 'write')" class="write">글쓰기</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="../common_footer.jsp" %>
