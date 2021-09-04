package model;

public class GuestBookDTO {
	private String guestBookNo;
	private String title;
	private String content;
	
	public GuestBookDTO() {
		super();
	}

	public GuestBookDTO(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public GuestBookDTO(String guestBookNo, String title, String content) {
		super();
		this.guestBookNo = guestBookNo;
		this.title = title;
		this.content = content;
	}

	public String getGuestBookNo() {
		return guestBookNo;
	}

	public void setGuestBookNo(String guestBookNo) {
		this.guestBookNo = guestBookNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String toString() {
		return "GuestBookDTO [guestBookNo=" + guestBookNo + ", title=" + title + ", content=" + content + "]";
	}
}
