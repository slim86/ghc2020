package fr.soprasteria.hashcode.indigogo.model;

public class Livre {

	private String id;
	
	private int score;

	public Livre() {
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Livre [id=" + id + ", score=" + score + "]";
	}
	
	
}
