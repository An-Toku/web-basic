package command.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import common.CommonUtil;
import dao.NoticeDao;
import dto.NoticeDto;

public class NoticeList implements CommonExecute {
	@Override
	public void execute(HttpServletRequest request) {
		NoticeDao dao = NoticeDao.getDao();
		String select = request.getParameter("t_select");
		String search = request.getParameter("t_search");

		if(select == null || select.equals("")) select = "title";
		if(search == null) search = "";

		int totalCount = dao.getTotalCount(select, search);
		int listSetupCount = 5;
		int pageNumberCount = 5;
		String nowPage = request.getParameter("t_nowPage");
		int currentPage = 1;

		if(nowPage != null && !nowPage.equals("")) currentPage = Integer.parseInt(nowPage);

		int totalPage = totalCount / listSetupCount;
		if(totalCount % listSetupCount != 0) totalPage++;

		int start = (currentPage - 1) * listSetupCount + 1;
		int end = currentPage * listSetupCount;
		int order = totalCount - (start - 1);

		List<NoticeDto> dtos = dao.getNoticeList(select, search, start, end);
		String pageDisplay = CommonUtil.getPageSetting(currentPage, totalPage, pageNumberCount);

		request.setAttribute("dtos", dtos);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("select", select);
		request.setAttribute("search", search);
		request.setAttribute("pageDisplay", pageDisplay);
		request.setAttribute("order", order);
	}
}
