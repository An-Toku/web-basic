package common;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.NoticeDao;
import dto.NoticeDto;

public class NoticeSummary implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		NoticeDao dao = NoticeDao.getDao();
		int notice_count = 5;
		ArrayList<NoticeDto> noticeDtos = dao.getNoticeSummary(notice_count);
		
		request.setAttribute("noticeDtos", noticeDtos);
	}

}
