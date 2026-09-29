package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExecute;
import dao.MemberDao;

public class MemberExit implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = MemberDao.getDao();
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute("sessionId");
		
		int result = dao.memberDelete(memberId);
		String msg = "";
		String url = "";
		if(result == 1) {
			msg = "회원탈퇴 되었습니다";
			url = "Index";
			session.invalidate();
		} else {
			msg = "회원탈퇴에 실패하였습니다";
			url = "Member";
			request.setAttribute("t_action", "myInfo");
			request.setAttribute("t_id", memberId);
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
	}

}
