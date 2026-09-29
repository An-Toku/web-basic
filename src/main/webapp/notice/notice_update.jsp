<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common_header.jsp" %>
<link href="css/pages/notice.css" rel="stylesheet">
<script src="common.script/common.js"></script>
<script src="notice.script/notice_update.js"></script>

	<div class="body">
		<%@ include file="../common_menu.jsp" %>
	
		<div class="right">
			<div class="noticePage noticeForm">
				<div class="formTitle">
					<h1>공지사항 수정</h1>
				</div>

				<form name="noticeUpdateform" enctype="multipart/form-data">
					<input type="hidden" name="t_no" value="${dto.getNo()}">

					<div class="formRow">
						<label for="title">제목</label>
						<input type="text" id="title" name="t_title" class="titleInput" value="${dto.getTitle()}">
					</div>

					<div class="formRow contentRow">
						<label for="content">내용</label>
						<textarea id="content" name="t_content">${dto.getContent()}</textarea>
					</div>

					<div class="formRow fileRow">
						<label>첨부파일</label>
						<div class="fileArea">
							<label for="attach" class="fileButton">파일 선택</label>
							<input type="file" id="attach" name="t_attach" onchange="changeFile(this)">
							<input type="text" id="attachName" name="t_attach_name" value="${dto.getAttach()}" readonly>
							<input type="hidden" name="t_ori_attach" value="${dto.getAttach()}">
							<input type="hidden" name="t_delete_attach" value="no">
							<input type="button" value="삭제" class="fileDeleteButton" onclick="deleteFile()">
						</div>
					</div>

					<div class="formRow">
						<label>등록자</label>
						<div class="fileArea">${dto.getReg_name()}</div>
					</div>

					<div class="formRow">
						<label>등록일</label>
						<div class="fileArea">${dto.getReg_date()}</div>
					</div>

					<div class="formRow">
						<label>수정자</label>
						<div class="fileArea">${sessionName}</div>
						<input type="hidden" name="t_update_id" value="${sessionId}">
					</div>

					<div class="formRow">
						<label>수정일</label>
						<div class="fileArea">${today}</div>
						<input type="hidden" name="t_update_date" value="${today}">
					</div>

					<div class="noticeButtons">
						<input type="button" value="수정" class="button" onclick="goUpdate()">
						<a href="Notice" class="button">목록</a>
					</div>
				</form>
			</div>
		</div>
	</div>

<%@ include file="../common_footer.jsp" %>