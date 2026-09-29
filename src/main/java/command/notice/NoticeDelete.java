package command.notice;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import common.CommonUtil;
import dao.NoticeDao;

public class NoticeDelete implements CommonExecute {

	@Override
	public void execute(HttpServletRequest request) {
		NoticeDao dao = NoticeDao.getDao();
		String no = request.getParameter("t_no");
		String deleteAttach = request.getParameter("t_ori_attach");

		int result = dao.noticeDelete(no);
		
		if(deleteAttach == null) deleteAttach = "";
		if(result == 1 && !deleteAttach.equals("")) {
			File file = new File(CommonUtil.getNoticeDir(request), deleteAttach);
			boolean tf = file.delete();
			
			if(!tf) {
				System.out.println("공지사항 삭제 중 첨부파일 삭제에 실패하였습니다");
			}
		}
		
		String msg = result == 1 ? "삭제되었습니다" : "삭제 실패하였습니다";
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Notice");

	}

}
