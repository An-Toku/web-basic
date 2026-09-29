<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common_header.jsp" %>
<link href="css/pages/member.css" rel="stylesheet">

<script type="text/javascript" src="common.script/jquery-1.8.1.min.js"></script>
<script src="common.script/common.js"></script>
<script src="member.script/member_join.js"></script>
	
	<div class="body">
		<%@ include file="../common_menu.jsp" %>
		
		<div class="right">
			<div class="memberJoin">
				<div class="formTitle">
					<h1>회원가입</h1>
				</div>
				<form name="joinForm">
				<input type="hidden" name="t_action">
				<table>
					<tr>
						<th>ID</th>
						<td>
							<div class="joinFieldLine idFieldLine">
								<input type="text" name="t_id" class="idInput" maxlength="15" onkeydown="setEmpty()">
								<a href="javascript:idCheck()" class="idCheckButton">아이디 검사</a>
								<input type="text" name="t_idCheck" readonly class="invisibleTextBox idCheckResult">
							</div>
							<small>영어, 숫자 조합 15자 이내</small>
							
						</td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td>
							<input type="text" name="t_nickname" maxlength="10">
							<small>10자 이내</small>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="t_password"></td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td><input type="password" name="t_passwordConfirm"></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<div class="joinFieldLine emailJoinField">
								<input type="text" name="t_emailAddress">
								<span class="emailSeparator">@</span>
								<input type="text" name="t_emailType">
								<select name="mails" onchange="printMailType()">
									<option value="" selected>직접 입력</option>
									<option value="gmail.com">gmail.com</option>
									<option value="naver.com">naver.com</option>
									<option value="daum.net">daum.net</option>
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<th colspan="2"><input type="button" value="가입" class="button" onclick="goJoin()"></th>
					</tr>
				</table>
				</form>
			</div>
		</div>
	</div>
	
	
<%@ include file="../common_footer.jsp" %>
