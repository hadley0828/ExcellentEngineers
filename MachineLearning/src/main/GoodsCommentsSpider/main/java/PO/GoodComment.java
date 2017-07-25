package PO;

public class GoodComment {
	
	private String name;
	private int id;
	private String category;
	private String comment;
	private int score;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public GoodComment(String name, int id, String category, String comment, int score) {
		super();
		this.name = name;
		this.id = id;
		this.category = category;
		this.comment = comment;
		this.score = score;
	
	}
	@Override
	public String toString() {
		return "GoodComment [name=" + name + ", id=" + id + ", category=" + category + ", comment=" + comment
				+ ", score=" + score + "]";
	}
	
	
}
