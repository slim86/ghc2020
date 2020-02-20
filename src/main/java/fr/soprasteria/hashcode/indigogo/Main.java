package fr.soprasteria.hashcode.indigogo;

import java.util.ArrayList;
import java.util.List;

import fr.soprasteria.hashcode.indigogo.model.Context;
import fr.soprasteria.hashcode.indigogo.model.Librairie;
import fr.soprasteria.hashcode.indigogo.model.Livre;
import fr.soprasteria.hashcode.indigogo.model.Utils;
import fr.soprasteria.hashcode.indigogo.reader.Reader;
import fr.soprasteria.hashcode.indigogo.writer.Writer;

public class Main {

	static String BASENAME = "C:/Users/jbuvry/Desktop/hashcode2020/travail/ghc2020/src/main/resources/2020/";
	static String BASENAME_OUT = "C:/Users/jbuvry/Desktop/hashcode2020/travail/ghc2020/src/main/resources/2020/out/";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test");
		
		Reader.read(BASENAME + args[0]);
		
		
		
		
		Context context =Reader.read(BASENAME + args[0]);
		Context contextSortie =new Context();
		
		int nbJourInit = context.getNbJours();
		
		List<Librairie> lOuverte = new ArrayList<Librairie>();
		List<Librairie> lFerme= context.getLibrairies();
		List<Librairie> lTemoin= context.getLibrairies();
		List<Livre> livreScan = new ArrayList<Livre>();
		
		int lockouverture=0;
		Librairie libLock=null;
		
		for (int i=nbJourInit-1;i>0;i--) {
			
			//on calcule la lib a ouvrir
			if(lockouverture==0 && !lFerme.isEmpty()) {
				
				if(libLock!=null) {
					lOuverte.add(libLock);
				}
				
				libLock = Utils.calculerOuverture(lFerme,i);
			
				if(libLock!=null) {
					lFerme.remove(libLock);
					lockouverture = libLock.getNbJoursOuverture();
				}
				
				
			}
			if(lockouverture>0) {
				lockouverture--;
			}
			
			
			
			ajouterLivre(livreScan,lOuverte,i);
					
			
		}
		
		creerContexteSortie(contextSortie,livreScan,lOuverte);
		
		
		Writer.write(BASENAME_OUT + args[0],contextSortie);
		
		
	}

	private static void creerContexteSortie(Context contextSortie, List<Livre> livreScan, List<Librairie> lOuverte) {
		contextSortie.setLibrairies(new ArrayList<Librairie>());
		for(Librairie lib : lOuverte) {
			Librairie libSortie = new Librairie();
			libSortie.setListeLivres(new ArrayList<Livre>());
			libSortie.setIdx(lib.getIdx());
			AjouterLivreScan(libSortie,livreScan);
			contextSortie.getLibrairies().add(libSortie);
			
		}
		
	}

	private static void AjouterLivreScan(Librairie libSortie, List<Livre> livreScan) {
		for(Livre livre : livreScan) {
			if(livre.getIdLibrairie().equals(libSortie.getIdx())){
				libSortie.getListeLivres().add(livre);
			}
		}
		
	}

	private static void ajouterLivre(List<Livre> livreScan, List<Librairie> lOuverte, int i) {
		for (Librairie lib : lOuverte) {
				ajouterLivresJournee(livreScan,lib,lOuverte,i);
			
			
		}
		
	}

	private static void ajouterLivresJournee(List<Livre> livreScan,Librairie lib, List<Librairie> lOuverte, int i) {
		
		int capacite = lib.getCapaciteTraitement();
		for (int k=0; k<capacite;k++) {
			if(k<lib.getListeLivres().size()) {
				Livre livre = lib.getListeLivres().get(0);
				livre.setJourTraite(i);
				livre.setIdLibrairie(lib.getIdx());
				removeLivre(lOuverte,livre);
				
				livreScan.add(livre);
			}
		}
		
	}

	private static void removeLivre(List<Librairie> lOuverte, Livre livre) {
		for (Librairie lib : lOuverte) {
			if(lib.getListeLivres()!=null || !lib.getListeLivres().isEmpty()) {
			lib.getListeLivres().remove(livre);
			}
		}
		
	}



}
