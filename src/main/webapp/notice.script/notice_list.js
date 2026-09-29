function goSearch() {
	noticeList.t_nowPage.value = "1";
	noticeList.method = "post";
	noticeList.action = "Notice";
	noticeList.submit();
}

function goListPage(pageNum) {
	noticeList.t_nowPage.value = pageNum;
	noticeList.method = "post";
	noticeList.action = "Notice";
	noticeList.submit();
}

function goNoticeView(no) {
	noticeView.t_action.value = "view";
	noticeView.t_no.value = no;
	noticeView.method = "post";
	noticeView.action = "Notice";
	noticeView.submit();
}