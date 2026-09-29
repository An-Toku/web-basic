function goUpdateForm(no) {
	work.t_action.value = "updateForm";
	work.t_no.value = no;
	work.method = "post";
	work.action = "Notice";
	work.submit();
}

function goDelete(no) {
	if(confirm("정말로 삭제하시겠습니까?")) {
		work.t_action.value = "delete";
		work.t_no.value = no;
		work.method = "post";
		work.action = "Notice";
		work.submit();
	}
}

function goNoticeView(no) {
	view.t_action.value = "view";
	view.t_no.value = no;
	view.method = "post";
	view.action = "Notice";
	view.submit();
}