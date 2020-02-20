package fr.soprasteria.hashcode.indigogo.model;

import java.util.List;

public class Librairie {

	private List<Livre> listeLivres;
	
	private boolean ouvert;
	
	private int nbJoursOuverture;

	public Librairie() {
		
	}
	
	public List<Livre> getListeLivres() {
		return listeLivres;
	}

	public void setListeLivres(List<Livre> listeLivres) {
		this.listeLivres = listeLivres;
	}

	public boolean isOuvert() {
		return ouvert;
	}

	public void setOuvert(boolean ouvert) {
		this.ouvert = ouvert;
	}

	public int getNbJoursOuverture() {
		return nbJoursOuverture;
	}

	public void setNbJoursOuverture(int nbJoursOuverture) {
		this.nbJoursOuverture = nbJoursOuverture;
	}
	
	
}
