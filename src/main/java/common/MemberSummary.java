package common;

import javax.servlet.http.HttpServletRequest;

import dao.MemberDao;
import dto.MemberDto;

public class MemberSummary implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = MemberDao.getDao();
		String id = (String)request.getSession().getAttribute("sessionId");

		MemberDto member_summary = dao.getMemberSummary(id);
		
		request.setAttribute("member_summary", member_summary);
	}

}
