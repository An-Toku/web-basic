<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common_header.jsp" %>
<link href="css/pages/member.css" rel="stylesheet">
<script src="member.script/member_myinfo_update.js"></script>
	<div class="body">
		<%@ include file="../common_menu.jsp" %>

		<div class="right">
			<div class="memberInfo">
				<div class="formTitle">
					<h1>회원정보 수정</h1>
				</div>
				<form name="memberInfoUpdateForm">
					<input type="hidden" name="t_action">
					<div class="memberInfoList">
						<div class="infoRow">
							<strong>아이디</strong>
							<span>${sessionId}</span>
						</div>
						<div class="infoRow">
							<strong>닉네임</strong>
							<div class="editField">
								<input type="text" name="t_nickname" class="nicknameInput" maxlength="10" value="${dto.getNickname()}">
							</div>
						</div>
						<div class="infoRow">
							<strong>이메일</strong>
							<div class="editField emailField">
								<input type="text" name="t_email_address" value="${dto.getEmail_address()}">
								<span class="emailSeparator">@</span>
								<input type="text" name="t_email_type" value="${dto.getEmail_type()}">
								<select name="mails" onchange="printMailType()">
									<option value="" selected>직접 입력</option>
									<option value="gmail.com">gmail.com</option>
									<option value="naver.com">naver.com</option>
									<option value="daum.net">daum.net</option>
								</select>
							</div>
						</div>
						<div class="infoRow">
							<strong>회원가입일</strong>
							<span>${dto.getReg_date()}</span>
						</div>
						<c:if test="${not empty dto.getUpdate_date()}">
							<div class="infoRow">
								<strong>회원정보 수정일</strong>
								<span>${dto.getUpdate_date()}</span>
							</div>
						</c:if>
					</div>
					<div class="memberInfoButtons">
						<button type="button" class="button" onclick="goMemberInfoUpdate()">수정 완료</button>
						<button type="button" class="button memberExitButton" onclick="goMemberExit()">회원 탈퇴</button>
					</div>
				</form>
			</div>
		</div>
	</div>

<%@ include file="../common_footer.jsp" %>
