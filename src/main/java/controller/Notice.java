package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.notice.NoticeDelete;
import command.notice.NoticeList;
import command.notice.NoticeSave;
import command.notice.NoticeUpdate;
import command.notice.NoticeView;
import common.CommonExecute;
import common.CommonUtil;
import common.MemberSummary;
import common.NoticeSummary;

/**
 * Servlet implementation class Notice
 */
@WebServlet("/Notice")
public class Notice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Notice() {
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
		
		if(action == null || action.equals("")) action = "list";
		
		CommonExecute side_mem = new MemberSummary();
		side_mem.execute(request);
		
		CommonExecute side_noti = new NoticeSummary();
		side_noti.execute(request);
		
		if(action.equals("list")) { // 공지 리스트
			CommonExecute noti = new NoticeList();
			noti.execute(request);
			viewPage = "notice/notice_list.jsp";
		} else if(action.equals("view")) {
			CommonExecute noti = new NoticeView();
			noti.execute(request);
			viewPage = "notice/notice_view.jsp";
		} else if(action.equals("write")) { // 공지 작성
			request.setAttribute("today", CommonUtil.getTodayTime());
			viewPage = "notice/notice_write.jsp";
		} else if(action.equals("save")) { // 공지 저장
			CommonExecute noti = new NoticeSave();
			noti.execute(request);
			viewPage = "common_alert.jsp";
		} else if(action.equals("updateForm")) { // 공지 수정 폼
			CommonExecute noti = new NoticeView();
			noti.execute(request);
			request.setAttribute("today", CommonUtil.getTodayTime());
			viewPage = "notice/notice_update.jsp";
		} else if(action.equals("update")) { // 공지 수정
			CommonExecute noti = new NoticeUpdate();
			noti.execute(request);
			viewPage = "common_alert.jsp";
		} else if(action.equals("delete")) { // 공지 삭제
			CommonExecute noti = new NoticeDelete();
			noti.execute(request);
			viewPage = "common_alert.jsp";
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
