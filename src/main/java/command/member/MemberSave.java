package command.member;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import common.CommonUtil;
import dao.MemberDao;
import dto.MemberDto;

public class MemberSave implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = MemberDao.getDao();
		
		String id = request.getParameter("t_id");
		String nickname = request.getParameter("t_nickname");
		String password = request.getParameter("t_password");
		
		try {
			password = dao.encryptSHA256(password);
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("비밀번호 해시 실패");
		}
		
		String email_address = request.getParameter("t_emailAddress");
		String email_type = request.getParameter("t_emailType");
		String reg_date = CommonUtil.getTodayTime();
		
		MemberDto dto = new MemberDto(id, nickname, password, email_address, email_type, reg_date);

		int result = dao.memberSave(dto);
		String msg = result == 1 ? nickname + "님 회원가입 되었습니다." : "회원가입에 실패하였습니다.";
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Member");
	}

}
