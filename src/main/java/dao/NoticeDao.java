package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.CommonUtil;
import common.DBConnection;
import dto.NoticeDto;

public class NoticeDao {
	Connection con = null;
	LogPreparedStatement ps = null;
	ResultSet rs = null;

	private NoticeDao() {}
	private static NoticeDao dao = new NoticeDao();

	public static NoticeDao getDao() {
		return dao;
	}

	public String getNoticeNo() {
		String no = "";
		String sql = "select nvl(max(no), 'N000') as no from my_안덕_notice";

		try {
			con = DBConnection.getConnection();
			ps = new LogPreparedStatement(con, sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				no = rs.getString("no");
				int num = Integer.parseInt(no.substring(1)) + 1;
				no = new DecimalFormat("N000").format(num);
			}
		} catch(SQLException e) {
			System.out.println("getNoticeNo() 에러: " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return no;
	}

	public int noticeSave(NoticeDto dto) {
		int result = 0;
		String sql = "insert into my_안덕_notice \n"
				+ "(no, title, content, attach, reg_id, reg_date) \n"
				+ "values \n"
				+ "(?, ?, ?, ?, ?, ?)";
		
		try {
			con = DBConnection.getConnection();
			ps = new LogPreparedStatement(con, sql);
			ps.setString(1, dto.getNo());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			ps.setString(4, dto.getAttach());
			ps.setString(5, dto.getReg_id());
			ps.setTimestamp(6, java.sql.Timestamp.valueOf(dto.getReg_date()));
			result = ps.executeUpdate();
		} catch(SQLException e) {
			System.out.println("noticeSave() 에러: " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}

	public List<NoticeDto> getNoticeList(String select, String search, int start, int end) {
		List<NoticeDto> dtos = new ArrayList<>();
		String sql = "select * \n"
				+ 	"from(\n"
				+	    "select rownum as rnum, tbl.*\n"
				+	    "from(\n"
				+	        "select n.no, n.title, n.attach, m.nickname as reg_name, \n"
				+	        "to_char(n.reg_date, 'yyyy.MM.dd') as reg_date, n.hit\n"
				+	        "from my_안덕_notice n, my_안덕_member m\n"
				+	        "where n.reg_id = m.id\n"
				+	        "and n." + select + " like ?\n"
				+	        "order by n.no desc\n"
				+	    ") tbl"
				+	")\n"
				+	"where rnum >= ? and rnum <= ?";

		try {
			con = DBConnection.getConnection();
			ps = new LogPreparedStatement(con, sql);
			ps.setString(1, "%" + search + "%");
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			while(rs.next()) {
				String no = rs.getString("no");
				String title = rs.getString("title");
				String attach = rs.getString("attach");
				String reg_name = rs.getString("reg_name");
				String reg_date = rs.getString("reg_date");
				int hit = rs.getInt("hit");
				
				NoticeDto dto = new NoticeDto(no, title, attach, reg_name, reg_date, hit);
				dtos.add(dto);
			}
		} catch(SQLException e) {
			System.out.println("getNoticeList() 에러: " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dtos;
	}

	public int getTotalCount(String select, String search) {
		int count = 0;
		String sql = "select count(*) as count \n"
				+ "from my_안덕_notice n, my_안덕_member m \n"
				+ "where n.reg_id = m.id \n"
				+ "and n." + select + " like ?";

		try {
			con = DBConnection.getConnection();
			ps = new LogPreparedStatement(con, sql);
			ps.setString(1, "%" + search + "%");
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");
			}
		} catch(SQLException e) {
			System.out.println("getTotalCount() 에러: " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return count;
	}

	public Map<String, NoticeDto> getNoticeView(String no) {
		Map<String, NoticeDto> dtos = new HashMap<>();
		NoticeDto dto = null;
		NoticeDto prevDto = null;
		NoticeDto nextDto = null;
		String sql = "select * \n"
				+ "from ( \n"
				+ 	"select n.no, n.title, n.content, n.attach, n.reg_id, "
				+ 	"m.nickname as reg_name, to_char(n.reg_date, 'yyyy.MM.dd hh24:mi:ss') as reg_date, "
				+ 	"n.update_id, m2.nickname as update_name, "
				+ 	"to_char(n.update_date, 'yyyy.MM.dd hh24:mi:ss') as update_date, n.hit, "
				+ 	"lag(n.no) over (order by n.no) as prev_no, "
				+ 	"lag(n.title) over (order by n.no) as prev_title, "
				+ 	"lead(n.no) over (order by n.no) as next_no, "
				+ 	"lead(n.title) over (order by n.no) as next_title \n"
				+ 	"from my_안덕_notice n \n"
				+ 	"join my_안덕_member m \n"
				+ 	"on n.reg_id = m.id \n"
				+ 	"left join my_안덕_member m2 \n"
				+ 	"on n.update_id = m2.id \n"
				+ ") notice \n"
				+ "where notice.no = ?";
		
		try {
			con = DBConnection.getConnection();
			ps = new LogPreparedStatement(con, sql);
			ps.setString(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String attach = rs.getString("attach");
				String reg_id = rs.getString("reg_id");
				String reg_name = rs.getString("reg_name");
				String reg_date = rs.getString("reg_date");
				String update_id = rs.getString("update_id");
				String update_name = rs.getString("update_name");
				String update_date = rs.getString("update_date");
				int hit = rs.getInt("hit");
				
				dto = new NoticeDto(no, title, content, attach, reg_id, reg_name, reg_date, update_id, update_name, update_date, hit);
				
				String prev_no = rs.getString("prev_no");
				String prev_title = rs.getString("prev_title");
				if(prev_title != null) {
					prev_title = CommonUtil.recoverQuot(prev_title);
					if(prev_title.length() >= 21) {
						prev_title = prev_title.substring(0, 20) + "...";
					}
				}
				
				prevDto = new NoticeDto(prev_no, prev_title);
				
				String next_no = rs.getString("next_no");
				String next_title = rs.getString("next_title");
				
				if(next_title != null) {
					next_title = CommonUtil.recoverQuot(next_title);
					if(next_title.length() >= 21) {
						next_title = next_title.substring(0, 20) + "...";
					}
				}
				
				nextDto = new NoticeDto(next_no, next_title);
				
				dtos.put("dto", dto);
				dtos.put("prevDto", prevDto);
				dtos.put("nextDto", nextDto);
			}
		} catch(SQLException e) {
			System.out.println("getNoticeView() 에러: " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}

	public int hitIncrease(String no) {
		int result = 0;
		String sql = "update my_안덕_notice \n"
				+ "set hit = hit + 1 \n"
				+ "where no = ?";
		
		try {
			con = DBConnection.getConnection();
			ps = new LogPreparedStatement(con, sql);
			ps.setString(1, no);
			result = ps.executeUpdate();
		} catch(SQLException e) {
			System.out.println("hitIncrease() 에러: " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}

	public int noticeUpdate(NoticeDto dto) {
		int result = 0;
		String sql = "update my_안덕_notice \n"
				+ "set title = ?, "
				+ "content = ?, "
				+ "attach = ?, "
				+ "update_id = ?, "
				+ "update_date = ? \n"
				+ "where no = ?";
		
		try {
			con = DBConnection.getConnection();
			ps = new LogPreparedStatement(con, sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			ps.setString(3, dto.getAttach());
			ps.setString(4, dto.getUpdate_id());
			ps.setTimestamp(5, java.sql.Timestamp.valueOf(dto.getUpdate_date()));
			ps.setString(6, dto.getNo());
			result = ps.executeUpdate();
		} catch(SQLException e) {
			System.out.println("noticeUpdate() 에러: " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}

	public int noticeDelete(String no) {
		int result = 0;
		String sql = "delete from my_안덕_notice \n"
				+ "where no = ?";
		
		try {
			con = DBConnection.getConnection();
			ps = new LogPreparedStatement(con, sql);
			ps.setString(1, no);
			result = ps.executeUpdate();
		} catch(SQLException e) {
			System.out.println("noticeDelete() 에러: " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	
	public ArrayList<NoticeDto> getNoticeSummary(int notice_count) {
		ArrayList<NoticeDto> dtos = new ArrayList<NoticeDto>();
		String sql = "select no, title, reg_date \n"
				+ "from ( \n"
				+ 	"select no, title, to_char(reg_date, 'MM.dd') as reg_date \n"
				+ 	"from my_안덕_notice \n"
				+ 	"order by no desc \n"
				+ ") \n"
				+ "where rownum <= ?";
		
		try {
			con = DBConnection.getConnection();
			ps = new LogPreparedStatement(con, sql);
			ps.setInt(1, notice_count);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String no = rs.getString("no");
				String title = rs.getString("title");
				
				if(title != null) {
					title = CommonUtil.recoverQuot(title);
				}
				
				if(title.length() >= 21) {
					title = title.substring(0, 20) + "...";
				}
				
				String reg_date = rs.getString("reg_date");
				
				dtos.add(new NoticeDto(no, title, reg_date));
			}
		} catch(SQLException e) {
			System.out.println("getNoticeSummary() 에러: " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return dtos;
	}
}
