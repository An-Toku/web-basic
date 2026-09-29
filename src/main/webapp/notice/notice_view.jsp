<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common_header.jsp" %>
<link href="css/pages/notice.css" rel="stylesheet">
<script src="notice.script/notice_view.js"></script>

<form name="work">
	<input type="hidden" name="t_action">
	<input type="hidden" name="t_no">
	<input type="hidden" name="t_ori_attach" value="${dto.getAttach()}">
</form>	

<form name="view">
	<input type="hidden" name="t_action">
	<input type="hidden" name="t_no">
</form>
	<div class="body">
		<%@ include file="../common_menu.jsp" %>
		
		<div class="right">
			<div class="noticePage noticeView">
				<div class="formTitle">
					<h1>공지사항</h1>
				</div>

				<div class="noticeDetail">
					<div class="detailTitle">
						<h2>${dto.getTitle()}</h2>
					</div>

					<div class="detailInfo">
						<div class="infoItem">
							<span class="label">게시자</span>
							<span>${dto.getReg_name()}</span>
						</div>
						<div class="infoItem">
							<span class="label">등록일</span>
							<span>${dto.getReg_date()}</span>
						</div>
						<div class="infoItem">
							<span class="label">조회수</span>
							<span>${dto.getHit()}</span>
						</div>
					</div>
					
					<c:if test="${not empty dto.getUpdate_name()}">
						<div class="detailInfo">
							<div class="infoItem">
    							<span class="label">수정자</span>
    							<span>${dto.getUpdate_name()}</span>
							</div>
							<div class="infoItem">
    							<span class="label">수정일</span>
							<span>${dto.getUpdate_date()}</span>
							</div>
					</div>
					</c:if>

					<div class="detailContent">${dto.getContent()}</div>

					<div class="detailFile">
						<span class="label">첨부파일</span>
						<a href="FileDownServlet?t_fileDir=notice&t_fileName=${dto.getAttach()}">${dto.getAttach()}</a>
					</div>
				</div>

				<div class="noticeNavigation">
					<c:if test="${not empty nextDto.getNo()}">
						<div class="navigationRow">
							<button type="button" class="navigationButton" onclick="javascript:goNoticeView('${nextDto.getNo()}')">다음 글</button>
							<span class="navigationTitle" onclick="javascript:goNoticeView('${nextDto.getNo()}')">${nextDto.getTitle()}</span>
						</div>
					</c:if>
					<c:if test="${not empty prevDto.getNo()}">
						<div class="navigationRow">
							<button type="button" class="navigationButton" onclick="javascript:goNoticeView('${prevDto.getNo()}')">이전 글</button>
							<span class="navigationTitle" onclick="javascript:goNoticeView('${prevDto.getNo()}')">${prevDto.getTitle()}</span>
						</div>
					</c:if>
				</div>

				<div class="noticeButtons">
					<c:if test="${sessionLevel eq 'top'}">
						<a href="javascript:goUpdateForm('${dto.getNo()}')" class="button">수정</a>
						<a href="javascript:goDelete('${dto.getNo()}')" class="button">삭제</a>
					</c:if>
					<a href="Notice" class="button">목록</a>
				</div>
			</div>
		</div>
	</div>

<%@ include file="../common_footer.jsp" %>
