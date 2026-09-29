function checkEmpty(obj,msg){
	if(obj.value == ""){
		alert(msg);
		obj.focus();
		return true;
	} else {
		return false;
	}
}

function goPage(url, action) {
	forHref.t_url.value = url;
	forHref.t_action.value = action;
	forHref.method = "post";
	forHref.action = forHref.t_url.value;
	forHref.submit();
}

function goNoticeView(no) {
	noticeSummaryView.t_action.value = "view";
	noticeSummaryView.t_no.value = no;
	noticeSummaryView.method = "post";
	noticeSummaryView.action = "Notice";
	noticeSummaryView.submit();
}
