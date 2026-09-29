package dto;

public class NoticeDto {
	private String no, title, content, attach, reg_id, reg_name, reg_date, update_id, update_name, update_date;
	private int hit;
	
	
	// index 공지사항
	public NoticeDto(String no, String title, String reg_date) {
		this.no = no;
		this.title = title;
		this.reg_date = reg_date;
	}

	// notice update
	public NoticeDto(String no, String title, String content, String attach, String reg_id, String reg_name,
			String reg_date, String update_id, String update_name, String update_date, int hit) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.reg_id = reg_id;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
		this.update_id = update_id;
		this.update_name = update_name;
		this.update_date = update_date;
		this.hit = hit;
	}
	
	// notice list
	public NoticeDto(String no, String title, String attach, String reg_name, String reg_date, int hit) {
		this.no = no;
		this.title = title;
		this.attach = attach;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
		this.hit = hit;
	}

	// notice save
	public NoticeDto(String no, String title, String content, String attach, String reg_id, String reg_date) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
	}

	// notice view
	public NoticeDto(String no, String title) {
		this.no = no;
		this.title = title;
	}

	public String getNo() {
		return no;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getAttach() {
		return attach;
	}
	public String getReg_id() {
		return reg_id;
	}
	public String getReg_name() {
		return reg_name;
	}
	public String getReg_date() {
		return reg_date;
	}
	public String getUpdate_id() {
		return update_id;
	}
	public String getUpdate_name() {
		return update_name;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public int getHit() {
		return hit;
	}
}
