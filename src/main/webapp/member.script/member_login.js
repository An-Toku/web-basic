function goLogin() {
	if(checkEmpty(loginForm.t_id, "아이디를 입력하세요")) return;
	if(checkEmpty(loginForm.t_password, "비밀번호를 입력하세요")) return;
	
	loginForm.t_action.value = "memberLogin";
	loginForm.method = "post";
	loginForm.action = "Member";
	loginForm.submit();
}

function enterCheck() {
	if(event.keyCode == 13) {
		goLogin();
	}
}