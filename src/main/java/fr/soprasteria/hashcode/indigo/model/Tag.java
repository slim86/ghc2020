package fr.soprasteria.hashcode.indigo.model;

public class Tag {

	private String nom;
	private int occurence;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getOccurence() {
		return occurence;
	}
	public void setOccurence(int occurence) {
		this.occurence = occurence;
	}
	public Tag(String nom) {
		super();
		this.nom = nom;
	}
	@Override
	public String toString() {
		return "Tag [nom=" + nom + ", occurence=" + occurence + "]";
	}
	
}
