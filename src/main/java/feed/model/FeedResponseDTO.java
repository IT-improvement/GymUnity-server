package feed.model;

import java.sql.Timestamp;

public class FeedResponseDTO {
	private String title;
	private String content;
	private int feedIndex;
	private int userCode;
	private Timestamp createDate;
	private Timestamp modDate;
	private String comment;
	private int favoriteCount;
	
	public FeedResponseDTO() {
		
	}
	
	public FeedResponseDTO(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	

	public FeedResponseDTO(String title, String content, int userCode, Timestamp createDate) {
		this.title = title;
		this.content = content;
		this.userCode = userCode;
		this.createDate = createDate;
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

	public int getFeedIndex() {
		return feedIndex;
	}

	public void setFeedIndex(int feedIndex) {
		this.feedIndex = feedIndex;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}

	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public int getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
}
