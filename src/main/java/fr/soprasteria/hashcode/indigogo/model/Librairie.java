package fr.soprasteria.hashcode.indigogo.model;

import java.util.List;

public class Librairie {

	private List<Livre> listeLivres;
	
	private List<String> listeIndexLivres;
	
	private boolean ouvert;
	
	private int nbJoursOuverture;
	
	private int capaciteTraitement;
	
	private int nbLivres;

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
	
	public int getCapaciteTraitement() {
		return capaciteTraitement;
	}

	public void setCapaciteTraitement(int capaciteTraitement) {
		this.capaciteTraitement = capaciteTraitement;
	}

	public int getNbLivres() {
		return nbLivres;
	}

	public void setNbLivres(int nbLivres) {
		this.nbLivres = nbLivres;
	}

	public List<String> getListeIndexLivres() {
		return listeIndexLivres;
	}

	public void setListeIndexLivres(List<String> listeIndexLivres) {
		this.listeIndexLivres = listeIndexLivres;
	}

	@Override
	public String toString() {
		return "Librairie [listeLivres=" + listeLivres + ", listeIndexLivres="
				+ listeIndexLivres + ", ouvert=" + ouvert
				+ ", nbJoursOuverture=" + nbJoursOuverture
				+ ", capaciteTraitement=" + capaciteTraitement + ", nbLivres="
				+ nbLivres + "]";
	}
	
}
