package command.member;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExecute;
import dao.MemberDao;

public class MemberLogin implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = MemberDao.getDao();
		String id = request.getParameter("t_id");
		String password = request.getParameter("t_password");

		try {
			password = dao.encryptSHA256(password);
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		Map<String, String> memberInfo = dao.getLoginName(id, password); 
		
		String nickname = memberInfo.get("nickname");
		String exit_date = memberInfo.get("exit_date");
		
		String msg = "";
		String url = "";
		if(!nickname.equals("")) {
			if(exit_date == null) {
			msg = nickname + "님 환영합니다";
			url = "Index";
			
			HttpSession session = request.getSession();
			session.setAttribute("sessionId", id);
			session.setAttribute("sessionName", nickname);
			if(id.equals("manager")) { // 관리자 등록
				session.setAttribute("sessionLevel", "top");
			}
			session.setMaxInactiveInterval(60 * 60 * 4);
			} else {
				msg = "탈퇴된 계정이니 고객센터에 문의해주세요";
				url = "Member";
			}
		} else {
			msg = "ID나 비밀번호가 정확하지 않습니다";
			url = "Member";
		}
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
	}

}
