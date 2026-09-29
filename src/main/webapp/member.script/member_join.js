function printMailType() {
	joinForm.t_emailType.value = joinForm.mails.value;
	if(joinForm.mails.value != "") {
		joinForm.t_emailType.readOnly = true;
	} else {
		joinForm.t_emailType.readOnly = false;
	}
}

function idCheck() {
	const id = joinForm.t_id.value;
	
	const regex = /^[A-Za-z0-9]+$/;
	let valid = "valid";    
	if(!regex.test(id)) {
		valid = "invalid";
	}
	
	$.ajax({
		type :"POST",
		url : "MemberCheckId",
		data: "t_id=" + id + "&t_valid=" + valid,
		async: false,
		dataType : "text",
		error : function(){
			alert('회원가입 아이디 중복 확인 통신 실패');
		},
		success : function(data){
			let result = $.trim(data);
			joinForm.t_idCheck.value = result
		}
	});	
}

function setEmpty() {
		joinForm.t_idCheck.value = "";
	}

function goJoin() {
	if(checkEmpty(joinForm.t_id, "아이디를 입력하세요")) return;
	if(checkEmpty(joinForm.t_idCheck, "아이디 검사하세요")) return;
	
	if(checkEmpty(joinForm.t_nickname, "닉네임을 입력하세요")) return;
	if(checkEmpty(joinForm.t_password, "비밀번호를 입력하세요")) return;
	if(checkEmpty(joinForm.t_passwordConfirm, "비밀번호 확인을 입력하세요")) return;
	if(joinForm.t_password.value != joinForm.t_passwordConfirm.value) {
		alert("비밀번호 확인을 비밀번호와 같게 해주세요");
		joinForm.t_passwordConfirm.focus();
		return;
	}
	if(checkEmpty(joinForm.t_emailAddress, "이메일 주소를 입력하세요")) return;
	if(checkEmpty(joinForm.t_emailType, "이메일 타입을 입력하세요")) return;
	
	joinForm.t_action.value = "memberSave";
	joinForm.method = "post";
	joinForm.action = "Member";
	joinForm.submit();
}