<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common_header.jsp" %>
<link href="css/pages/member.css" rel="stylesheet">

<script src="common.script/common.js"></script>
<script src="member.script/member_login.js"></script>	

	<div class="body">
		<%@ include file="../common_menu.jsp" %>
		
		<div class="right">
			<div class="memberLogin">
				<div class="formTitle">
					<h1>로그인</h1>
				</div>
				<form name="loginForm">
				<input type="hidden" name="t_action">
				<table>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="t_id" tabindex="1"></td>
						<th rowspan="2" id="loginButtonArea"><input type="button" value="로그인" tabindex="3" onclick="goLogin()"></th>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="t_password" tabindex="2" onkeypress="enterCheck()"></td>
					</tr>
				</table>
				<div class="find">
					<a href="   ">아이디 찾기</a>
					<a href="   ">비밀번호 찾기</a>
				</div>
				</form>
			</div>
		</div>
	</div>
	
	<%@ include file="../common_footer.jsp" %>
