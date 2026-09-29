package dto;

public class MemberDto {
	private String id, nickname, password, email_address, email_type, reg_date, update_date, exit_date;

	
	// 사이드 메뉴 프로필
	public MemberDto(String id, String nickname, String reg_date) {
		this.id = id;
		this.nickname = nickname;
		this.reg_date = reg_date;
	}
	
	// 회원 정보 수정
	public MemberDto(String id, String nickname, String email_address, String email_type) {
		this.id = id;
		this.nickname = nickname;
		this.email_address = email_address;
		this.email_type = email_type;
	}

	public MemberDto(String id, String nickname, String password, String email_address, String email_type, String reg_date,
			String update_date, String exit_date) {
		this.id = id;
		this.nickname = nickname;
		this.password = password;
		this.email_address = email_address;
		this.email_type = email_type;
		this.reg_date = reg_date;
		this.update_date = update_date;
		this.exit_date = exit_date;
	}

	public MemberDto(String id, String nickname, String email_address, String email_type, String reg_date,
			String update_date, String exit_date) {
		this.id = id;
		this.nickname = nickname;
		this.email_address = email_address;
		this.email_type = email_type;
		this.reg_date = reg_date;
		this.update_date = update_date;
		this.exit_date = exit_date;
	}
	
	// 회원가입용
	public MemberDto(String id, String nickname, String password, String email_address, String email_type, String reg_date) {
		this.id = id;
		this.nickname = nickname;
		this.password = password;
		this.email_address = email_address;
		this.email_type = email_type;
		this.reg_date = reg_date;
	}

	public String getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail_address() {
		return email_address;
	}

	public String getEmail_type() {
		return email_type;
	}

	public String getReg_date() {
		return reg_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public String getExit_date() {
		return exit_date;
	}
}
