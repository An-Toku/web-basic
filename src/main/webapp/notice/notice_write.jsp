<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common_header.jsp" %>
<link href="css/pages/notice.css" rel="stylesheet">
<script src="common.script/common.js"></script>
<script src="notice.script/notice_write.js"></script>

	<div class="body">
		<%@ include file="../common_menu.jsp" %>
		
		<div class="right">
			<div class="noticePage noticeForm">
				<div class="formTitle">
					<h1>공지사항 작성</h1>
				</div>

				<form name="noticeWriteform" enctype="multipart/form-data">
					<div class="formRow">
						<label for="title">제목</label>
						<input type="text" id="title" name="t_title" class="titleInput">
					</div>

					<div class="formRow contentRow">
						<label for="content">내용</label>
						<textarea id="content" name="t_content"></textarea>
					</div>

					<div class="formRow fileRow">
						<label for="attach">첨부파일</label>
						<input type="file" id="attach" name="t_attach">
					</div>

					<div class="formRow">
						<label>작성자</label>
						<div class="fileArea">${sessionName}</div>
					</div>

					<div class="formRow">
						<label>작성일</label>
						<div class="fileArea">${today}</div>
						<input type="hidden" name="t_reg_date" value="${today}">
					</div>

					<div class="noticeButtons">
						<input type="button" value="등록" class="button" onclick="goSave()">
						<a href="Notice" class="button">목록</a>
					</div>
				</form>
			</div>
		</div>
	</div>

<%@ include file="../common_footer.jsp" %>
