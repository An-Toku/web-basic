<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common_header.jsp" %>
<link href="css/pages/member.css" rel="stylesheet">

	<div class="body">
		<%@ include file="../common_menu.jsp" %>

		<div class="right">
			<div class="memberInfo">
				<div class="formTitle">
					<h1>회원정보</h1>
				</div>
				<div class="memberInfoList">
					<div class="infoRow">
						<strong>아이디</strong>
						<span>${sessionId}</span>
					</div>
					<div class="infoRow">
						<strong>닉네임</strong>
						<span>${dto.getNickname()}</span>
					</div>
					<div class="infoRow">
						<strong>이메일</strong>
						<span>${dto.getEmail_address()}@${dto.getEmail_type()}</span>
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
					<button type="button" class="button" onclick="goPage('Member', 'updateForm')">내 정보 수정</button>
				</div>
			</div>
		</div>
	</div>

<%@ include file="../common_footer.jsp" %>
