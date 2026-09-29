function printMailType() {
	memberInfoUpdateForm.t_email_type.value = memberInfoUpdateForm.mails.value;
	if(memberInfoUpdateForm.mails.value != "") {
		memberInfoUpdateForm.t_email_type.readOnly = true;
	} else {
		memberInfoUpdateForm.t_email_type.readOnly = false;
	}
}

function goMemberInfoUpdate() {
	if(checkEmpty(memberInfoUpdateForm.t_nickname, "닉네임을 입력하세요")) return;

	if(checkEmpty(memberInfoUpdateForm.t_email_address, "이메일 주소를 입력하세요")) return;
	if(checkEmpty(memberInfoUpdateForm.t_email_type, "이메일 타입을 입력하세요")) return;
	
	memberInfoUpdateForm.t_action.value = "memberInfoUpdate";
	memberInfoUpdateForm.method = "post";
	memberInfoUpdateForm.action = "Member";
	memberInfoUpdateForm.submit();
}

function goMemberExit() {
	if(confirm("정말로 탈퇴하시겠습니까?")) {
			memberInfoUpdateForm.t_action.value = "memberExit";
			memberInfoUpdateForm.method = "post";
			memberInfoUpdateForm.action = "Member";
			memberInfoUpdateForm.submit();
	}
}