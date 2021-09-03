package model;

public class GuestBookDTO {
	private int guestbookNo; // guestbook_no
	private String title;
	private String content;

	public GuestBookDTO() {
		super();
	}

	// insert용도 -- guestbookNo는 DB의 sequence를 이용할 것이니까
	public GuestBookDTO(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}


	public GuestBookDTO(int guestbookNo, String title, String content) {
		super();
		this.guestbookNo = guestbookNo;
		this.title = title;
		this.content = content;
	}

	public int getGuestbookNo() {
		return guestbookNo;
	}

	public void setGuestbookNo(int guestbookNo) {
		this.guestbookNo = guestbookNo;
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

	@Override
	public String toString() {
		return "GuestbookDTO [guestbookNo=" + guestbookNo + ", title=" + title + ", content=" + content + "]";
	}
}
