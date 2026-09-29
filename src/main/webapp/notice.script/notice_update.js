function changeFile(obj) {
	if(obj.files.length > 0) {
		noticeUpdateform.t_attach_name.value = obj.files[0].name;
		noticeUpdateform.t_delete_attach.value = "no";
	}
}

function deleteFile() {
	noticeUpdateform.t_attach.value = "";
	noticeUpdateform.t_attach_name.value = "";
	noticeUpdateform.t_delete_attach.value = "yes";
}

function goUpdate() {
	if(checkEmpty(noticeUpdateform.t_title, "제목을 입력하세요")) return;
	if(checkEmpty(noticeUpdateform.t_content, "내용을 입력하세요")) return;
	
	const maxSize = 1024 * 1024 * 10;
	if(noticeUpdateform.t_attach.value != "") {
		if(noticeUpdateform.t_attach.files[0].size > maxSize) {
			alert("첨부파일은 10mb 이내로 등록해주세요");
			return;
		}
	}
	
	noticeUpdateform.method = "post";
	noticeUpdateform.action = "Notice?t_action=update";
	noticeUpdateform.submit();
}