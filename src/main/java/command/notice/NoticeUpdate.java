package command.notice;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.CommonExecute;
import common.CommonUtil;
import dao.NoticeDao;
import dto.NoticeDto;

public class NoticeUpdate implements CommonExecute {

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
			request.setAttribute("t_msg", "수정에 실패했습니다");
			request.setAttribute("t_url", "Notice");
        }

        String no = mpr.getParameter("t_no");
        String title = CommonUtil.getDoubleQuot(CommonUtil.getSingleQuot(mpr.getParameter("t_title")));
        String content = CommonUtil.getDoubleQuot(CommonUtil.getSingleQuot(mpr.getParameter("t_content")));
        
        String newAttach = mpr.getFilesystemName("t_attach");
        String oriAttach = mpr.getParameter("t_ori_attach");
        String deleteAttach = mpr.getParameter("t_delete_attach");
        
        String update_id = mpr.getParameter("t_update_id");
        String update_date = mpr.getParameter("t_update_date");
        
        String attach = oriAttach;
        
        if(deleteAttach.equals("yes")) { // 삭제 요청 있으면
            if(oriAttach != null && !oriAttach.equals("")) { // 원본 있으면
                File file = new File(attachDir, oriAttach);

                if(file.exists()) {
                    boolean tf = file.delete();

                    if(!tf) {
                        System.out.println("원본 파일 삭제 오류: " + attachDir + "/" + oriAttach);
                    }
                }
            }

            attach = "";
        } else if(newAttach != null && !newAttach.equals("")) { // 새 파일 있음
            if(oriAttach != null && !oriAttach.equals("")) { // 기존 파일 삭제
                File file = new File(attachDir, oriAttach);
                if(file.exists()) {
                    boolean tf = file.delete();
                    if(!tf) {
                        System.out.println(
                            "원본 파일 삭제 오류: "
                            + attachDir + "/" + oriAttach
                        );
                    }
                }
            }
            attach = newAttach;
        }

        if(attach == null) {
            attach = "";
        }
        
        NoticeDto dto = new NoticeDto(no, title, content, attach, "", "", "", update_id, "", update_date, 0);

        
        int result = dao.noticeUpdate(dto);
        request.setAttribute("t_msg", result == 1 ? "수정되었습니다." : "수정에 실패했습니다");
		request.setAttribute("t_url", "Notice");
		request.setAttribute("t_no", dto.getNo());
		request.setAttribute("t_action", "view");
	}

}
