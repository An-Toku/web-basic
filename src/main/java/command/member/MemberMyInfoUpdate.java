package command.member;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.MemberDao;
import dto.MemberDto;

public class MemberMyInfoUpdate implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = MemberDao.getDao();
		String memberId = (String)request.getSession().getAttribute("sessionId");
		String nickname = request.getParameter("t_nickname");
		String email_address = request.getParameter("t_email_address");
		String email_type = request.getParameter("t_email_type");
		
		MemberDto dto = new MemberDto(memberId, nickname, email_address, email_type);
		int result = dao.memberMyInfoUpdate(dto);
		
		String t_msg = result == 1 ? "회원정보가 수정되었습니다." : "회원정보 수정에 실패했습니다";
		request.setAttribute("t_msg", t_msg);
		request.setAttribute("t_url", "Member");
		request.setAttribute("t_action", "myInfo");
		request.setAttribute("t_id", memberId);
	}

}
