package fr.soprasteria.hashcode.indigo.model;

import java.util.ArrayList;
import java.util.List;

public class Photo {

	private int id;
	private String type;
	private List<Tag> tags = new ArrayList<Tag>();
	private int score = 0;
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Photo() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "Photo [id=" + id + ", type=" + type + ", tags=" + tags
				+ ", score=" + score + "]";
	}
}
