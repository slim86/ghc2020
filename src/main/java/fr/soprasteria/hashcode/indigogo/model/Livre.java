package fr.soprasteria.hashcode.indigogo.model;

public class Livre {

	private String id;
	
	private int score;
	
	private String idLivre;
	
	private int jourTraite;

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

	public String getIdLivre() {
		return idLivre;
	}

	public void setIdLivre(String idLivre) {
		this.idLivre = idLivre;
	}

	public int getJourTraite() {
		return jourTraite;
	}

	public void setJourTraite(int jourTraite) {
		this.jourTraite = jourTraite;
	}

	@Override
	public String toString() {
		return "Livre [id=" + id + ", score=" + score + "]";
	}
	
	
}
