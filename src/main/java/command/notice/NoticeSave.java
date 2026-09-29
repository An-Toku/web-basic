package command.notice;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.CommonExecute;
import common.CommonUtil;
import dao.NoticeDao;
import dto.NoticeDto;

public class NoticeSave implements CommonExecute {
	@Override
	public void execute(HttpServletRequest request) {
		NoticeDao dao = NoticeDao.getDao();
		String attachDir = CommonUtil.getNoticeDir(request);
		int maxSize = 1024 * 1024 * 10;
		
		MultipartRequest mpr = null;
		try {
			mpr = new MultipartRequest(request, attachDir, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch(IOException e) {
			e.printStackTrace();
			request.setAttribute("t_msg", "등록에 실패했습니다");
			request.setAttribute("t_url", "Notice");
			return;
		}

		String no = dao.getNoticeNo();
		String title = CommonUtil.getDoubleQuot(CommonUtil.getSingleQuot(mpr.getParameter("t_title")));
		String content = CommonUtil.getDoubleQuot(CommonUtil.getSingleQuot(mpr.getParameter("t_content")));
		String attach = mpr.getFilesystemName("t_attach");
		if(attach == null) attach = "";

		HttpSession session = request.getSession();
		String reg_id = (String)session.getAttribute("sessionId");
		String reg_date = mpr.getParameter("t_reg_date");

		NoticeDto dto = new NoticeDto(no, title, content, attach, reg_id, reg_date);
		int result = dao.noticeSave(dto);

		request.setAttribute("t_msg", result == 1 ? "등록되었습니다." : "등록에 실패했습니다");
		request.setAttribute("t_url", "Notice");
	}
}
