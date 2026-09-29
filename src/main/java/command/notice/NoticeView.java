package command.notice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.NoticeDao;
import dto.NoticeDto;

public class NoticeView implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		NoticeDao dao = NoticeDao.getDao();
		String no = request.getParameter("t_no");
		String action = request.getParameter("t_action");
		
		if(action.equals("view")) {
			int result = dao.hitIncrease(no);
			if(result != 1) System.out.println("게시물 조회수 증가 오류");
		}
		
		Map<String, NoticeDto> dtos = dao.getNoticeView(no);
		
		NoticeDto dto = dtos.get("dto");
		NoticeDto prevDto = dtos.get("prevDto");
		NoticeDto nextDto = dtos.get("nextDto");
		
		request.setAttribute("dto", dto);
		request.setAttribute("prevDto", prevDto);
		request.setAttribute("nextDto", nextDto);
	}

}
