package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import common.CommonUtil;
import common.DBConnection;
import dto.MemberDto;

public class MemberDao {
	Connection con = null;
	LogPreparedStatement ps = null;
	ResultSet rs = null;
	
	private MemberDao() {};
	private static MemberDao dao = new MemberDao();
	
	public static MemberDao getDao() {
		return dao;
	}

	public Connection getCon() {
		return con;
	}

	public LogPreparedStatement getPs() {
		return ps;
	}

	public ResultSet getRs() {
		return rs;
	}
	
	public int checkId(String id) {
		int count = 0;
		String sql = "select count(*) as count from my_안덕_member \n"
				+ "where id = ?";
		
		try {
			con = DBConnection.getConnection();
			ps = new LogPreparedStatement(con, sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count");
			}
		} catch(SQLException e) {
			System.out.println("checkId() 에러: " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return count;
	}
	
    public String encryptSHA256(String value) throws NoSuchAlgorithmException{
		String encryptData ="";
		
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		sha.update(value.getBytes());
		
		byte[] digest = sha.digest();
		for (int i=0; i<digest.length; i++) {
		   encryptData += Integer.toHexString(digest[i] &0xFF).toUpperCase();
		}
		
		return encryptData;
    }

	public int memberSave(MemberDto dto) {
		int result = 0;
		String sql = "insert into my_안덕_member \n"
				+ "(id, nickname, password, email_address, email_type, "
				+ "reg_date) \n"
				+ "values \n"
				+ "(?, ?, ?, ?, ?, ?)";
		
		try {
			con = DBConnection.getConnection();
			ps = new LogPreparedStatement(con, sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getNickname());
			ps.setString(3, dto.getPassword());
			ps.setString(4, dto.getEmail_address());
			ps.setString(5, dto.getEmail_type());
			ps.setTimestamp(6, CommonUtil.getCurrentDateNTime());
			
			result = ps.executeUpdate();
		} catch(SQLException e) {
			System.out.println("memberSave() 에러: " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return result;
	}

	public Map<String, String> getLoginName(String id, String password) {
		Map<String, String> memberInfo = new HashMap<String, String>();
		String sql = "select nickname, to_char(exit_date) as exit_date \n"
				+ "from my_안덕_member \n"
				+ "where id = ? \n"
				+ "and password = ?";
		
		try {
			con = DBConnection.getConnection();
			ps = new LogPreparedStatement(con, sql);
			ps.setString(1, id);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String nickname = rs.getString("nickname");
				String exit_date = rs.getString("exit_date");
				memberInfo.put("nickname", nickname);
				memberInfo.put("exit_date", exit_date);
			}
			
		} catch(SQLException e) {
			System.out.println("getLoginName() 에러: " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		
		return memberInfo;
	}

	public MemberDto getMemberInfo(String id) {
		MemberDto dto = null;
		String sql = "select nickname, email_address, email_type, "
				+ "to_char(reg_date, 'yyyy.MM.dd hh24:mi:ss') as reg_date, "
				+ "to_char(update_date, 'yyyy.MM.dd hh24:mi:ss') as update_date "
				+ "from my_안덕_member "
				+ "where id = ?";

		try {
			con = DBConnection.getConnection();
			ps = new LogPreparedStatement(con, sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			if(rs.next()) {
				String nickname = rs.getString("nickname");
				String email_address = rs.getString("email_address");
				String email_type = rs.getString("email_type");
				String reg_date = rs.getString("reg_date");
				String update_date = rs.getString("update_date");

				dto = new MemberDto(id, nickname, email_address, email_type, reg_date, update_date, "");
			}
		} catch(SQLException e) {
			System.out.println("getMemberInfo() 에러: " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}

		return dto;
	}

	public int memberMyInfoUpdate(MemberDto dto) {
		int result = 0;
		String sql = "update my_안덕_member \n"
				+ "set nickname = ?, "
				+ "email_address = ?, "
				+ "email_type = ?, "
				+ "update_date = ? \n"
				+ "where id = ?";
		
		try {
			con = DBConnection.getConnection();
			ps = new LogPreparedStatement(con, sql);
			ps.setString(1, dto.getNickname());
			ps.setString(2, dto.getEmail_address());
			ps.setString(3, dto.getEmail_type());
			ps.setTimestamp(4, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
			ps.setString(5, dto.getId());
			result = ps.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("memberMyInfoUpdate() 에러: " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}

	public int memberDelete(String memberId) {
		int result = 0;
		String sql = "update my_안덕_member \n"
				+ "set exit_date = ? \n"
				+ "where id = ?";
		
		try {
			con = DBConnection.getConnection();
			ps = new LogPreparedStatement(con, sql);
			ps.setTimestamp(1, Timestamp.valueOf(java.time.LocalDateTime.now()));
			ps.setString(2, memberId);
			result = ps.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("memberDelete() 에러: " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return result;
	}

	public MemberDto getMemberSummary(String id) {
		MemberDto dto = null;
		String sql = "select nickname, to_char(reg_date, 'yyyy.MM.dd') as reg_date \n"
				+ "from my_안덕_member \n"
				+ "where id = ?";
		
		try {
			con = DBConnection.getConnection();
			ps = new LogPreparedStatement(con, sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String nickname = rs.getString("nickname");
				String reg_date = rs.getString("reg_date");
				
				dto = new MemberDto(id, nickname, reg_date);
			}
		} catch(SQLException e) {
			System.out.println("getMemberSummary() 에러: " + sql);
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, ps, rs);
		}
		return dto;
	}
}
