package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.member.MemberExit;
import command.member.MemberLogin;
import command.member.MemberLogout;
import command.member.MemberMyInfo;
import command.member.MemberMyInfoUpdate;
import command.member.MemberSave;
import common.CommonExecute;
import common.MemberSummary;
import common.NoticeSummary;

/**
 * Servlet implementation class Member
 */
@WebServlet("/Member")
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Member() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("t_action");
		String viewPage = "";
		
		if(action == null || action.equals("")) action = "login";
		
		CommonExecute side_mem = new MemberSummary();
		side_mem.execute(request);
		
		CommonExecute side_noti = new NoticeSummary();
		side_noti.execute(request);
		
		if(action.equals("login")) { // 로그인 페이지
			viewPage = "member/member_login.jsp";
		} else if(action.equals("join")) {
			viewPage = "member/member_join.jsp";
		} else if(action.equals("memberSave")) { // 회원가입
			CommonExecute mem = new MemberSave();
			mem.execute(request);
			viewPage = "common_alert.jsp";
		} else if(action.equals("memberLogin")) { // 로그인 세션 생성
			CommonExecute mem = new MemberLogin();
			mem.execute(request);
			viewPage = "common_alert.jsp";
		} else if(action.equals("memberLogout")) { // 로그아웃 세션 만료
			CommonExecute mem = new MemberLogout();
			mem.execute(request);
			viewPage = "common_alert.jsp";
		} else if(action.equals("myInfo")) { // 내 정보
			String id = (String)request.getSession().getAttribute("sessionId");
			if(id == null) {
				request.setAttribute("t_msg", "로그인 정보가 만료되었습니다. 다시 로그인하세요");
				request.setAttribute("t_url", "Member");
				viewPage = "common_alert.jsp";
			} else {
				CommonExecute mem = new MemberMyInfo();
				mem.execute(request);
				viewPage = "member/member_myinfo.jsp";
			}
		} else if(action.equals("updateForm")) { // 내 정보 수정폼
			String id = (String)request.getSession().getAttribute("sessionId");
			if(id == null) {
				request.setAttribute("t_msg", "로그인 정보가 만료되었습니다. 다시 로그인하세요");
				request.setAttribute("t_url", "Member");
				viewPage = "common_alert.jsp";
			} else {
				CommonExecute mem = new MemberMyInfo();
				mem.execute(request);
				viewPage = "member/member_myinfo_update.jsp";
			}
		} else if(action.equals("memberInfoUpdate")) { // 내 정보 수정
			String id = (String)request.getSession().getAttribute("sessionId");
			if(id == null) {
				request.setAttribute("t_msg", "로그인 정보가 만료되었습니다. 다시 로그인하세요");
				request.setAttribute("t_url", "Member");
				viewPage = "common_alert.jsp";
			} else {
				CommonExecute mem = new MemberMyInfoUpdate();
				mem.execute(request);
				viewPage = "common_alert.jsp";
			}
		} else if(action.equals("memberExit")) { // 회원 탈퇴
			String id = (String)request.getSession().getAttribute("sessionId");
			if(id == null) {
				request.setAttribute("t_msg", "로그인 정보가 만료되었습니다. 다시 로그인하세요");
				request.setAttribute("t_url", "Member");
				viewPage = "common_alert.jsp";
			} else {
				CommonExecute mem = new MemberExit();
				mem.execute(request);
				viewPage = "common_alert.jsp";
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
