package shop.vo;

public class BoardVO {
	private	int boardnum;
	private	String member_id;
	private int p_id;
	private	String title;
	private	String content;
	private	int hits;
	private	String credate;
	
	
	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public int getBoardnum() {
		return boardnum;
	}
	
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public int getHits() {
		return hits;
	}
	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getCredate() {
		return credate;
	}

	public void setCredate(String credate) {
		this.credate = credate;
	}

	
	
	
	
	
}
