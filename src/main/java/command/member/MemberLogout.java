package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExecute;

public class MemberLogout implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String nickname = (String)session.getAttribute("sessionName");
		
		String msg = nickname + "님 로그아웃되었습니다";
		if(nickname == null) msg = "로그아웃되었습니다";
		
		session.invalidate();
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Index");
	}

}
