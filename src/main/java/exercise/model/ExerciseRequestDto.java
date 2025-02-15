package exercise.model;

import java.sql.Timestamp;

public class ExerciseRequestDto {
	private int index;
	private int categoryIndex;
	private String categoryName;
	private int userCode;
	private String userId;
	private String userName;
	private String name;
	private String content;
	private Timestamp createDate;
	private Timestamp modDate;

	public ExerciseRequestDto() { }

	public ExerciseRequestDto(int userCode) {
		this.userCode = userCode;
	}

	public ExerciseRequestDto(int index, int userCode) {
		this.index = index;
		this.userCode = userCode;
	}
	
	public ExerciseRequestDto(int categoryIndex, int userCode, String name, String content) {
		this.categoryIndex = categoryIndex;
		this.userCode = userCode;
		this.name = name;
		this.content = content;
	}

	public ExerciseRequestDto(int index, int categoryIndex, int userCode, String name, String content) {
		this.index = index;
		this.categoryIndex = categoryIndex;
		this.userCode = userCode;
		this.name = name;
		this.content = content;
	}

	public ExerciseRequestDto(int index, int categoryIndex, int userCode, String name, String content, Timestamp createDate, Timestamp modDate) {
		this.index = index;
		this.categoryIndex = categoryIndex;
		this.userCode = userCode;
		this.name = name;
		this.content = content;
		this.createDate = createDate;
		this.modDate = modDate;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCategoryIndex() {
		return categoryIndex;
	}

	public void setCategoryIndex(int categoryIndex) {
		this.categoryIndex = categoryIndex;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
}