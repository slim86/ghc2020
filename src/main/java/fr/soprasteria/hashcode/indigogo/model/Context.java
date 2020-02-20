package fr.soprasteria.hashcode.indigogo.model;

public class Context {

	@Override
	public String toString() {
		return "Context [nbJours=" + nbJours + ", nbLivres=" + nbLivres
				+ ", nbLibrairies=" + nbLibrairies + "]";
	}

	private int nbJours;
	
	private int nbLivres;
	
	private int nbLibrairies;

	public Context() {
		
	}
	
	public int getNbJours() {
		return nbJours;
	}

	public void setNbJours(int nbJours) {
		this.nbJours = nbJours;
	}

	public int getNbLivres() {
		return nbLivres;
	}

	public void setNbLivres(int nbLivres) {
		this.nbLivres = nbLivres;
	}

	public int getNbLibrairies() {
		return nbLibrairies;
	}

	public void setNbLibrairies(int nbLibrairies) {
		this.nbLibrairies = nbLibrairies;
	}
	
	
}
