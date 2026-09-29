function goSave() {
	if(checkEmpty(noticeWriteform.t_title, "제목을 입력하세요")) return;
	if(checkEmpty(noticeWriteform.t_content, "제목을 입력하세요")) return;
	
	const maxSize = 1024 * 1024 * 10;
	if(noticeWriteform.t_attach.value != "") {
		if(noticeWriteform.t_attach.files[0].size > maxSize) {
			alert("첨부파일은 10mb 이내로 등록해주세요");
			return;
		}
	}
	noticeWriteform.method = "post";
	noticeWriteform.action = "Notice?t_action=save";
	noticeWriteform.submit();
}