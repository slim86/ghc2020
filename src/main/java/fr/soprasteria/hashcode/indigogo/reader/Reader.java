package fr.soprasteria.hashcode.indigogo.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.soprasteria.hashcode.indigogo.model.Context;
import fr.soprasteria.hashcode.indigogo.model.Librairie;
import fr.soprasteria.hashcode.indigogo.model.Livre;

public final class Reader {

	private static final String IN_EXTENTION = ".txt";
	
	public static Context read(final String fileName) {
		
		Context ctxt = new Context();
		
		final String fileNameWithExtension = fileName + IN_EXTENTION;

		System.out.println("Lecture du fichier " + fileNameWithExtension);
		List<String> lines;

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileNameWithExtension))) {

			// line 0
			lines = br.lines().collect(Collectors.toList());
			System.out.println("Nombre de lignes : "
					+ lines.size());

			String[] infosFirstLine = lines.get(0).split(" ");
			
			ctxt.setNbLivres(Integer.parseInt(infosFirstLine[0]));
			ctxt.setNbLibrairies(Integer.parseInt(infosFirstLine[1]));
			ctxt.setNbJours(Integer.parseInt(infosFirstLine[2]));
			System.out.println(ctxt);
			
			// line 1
			Map<String,Livre> listeLivres = new HashMap<String,Livre>();
			String[] scoreLivres = lines.get(1).split(" ");
			int idxLivre = 0;
			Map<String, Integer> mapScore = new HashMap<String, Integer>();
			for (String scoreLivre : scoreLivres) {
				final Livre livre = new Livre();
				livre.setId(Integer.toString(idxLivre));
				livre.setScore(Integer.parseInt(scoreLivre));
				listeLivres.put(livre.getId(),livre);
				mapScore.put(Integer.toString(idxLivre), Integer.parseInt(scoreLivre));
				idxLivre++;
			}
			
			final List<Librairie> listeLibrairie = new ArrayList<Librairie>();
			int index = 0;
			for (int idxLibrairie = 0; idxLibrairie < ((ctxt.getNbLibrairies() *2)); idxLibrairie++) {
				final String[] infosLibrairie = lines.get(idxLibrairie + 2).split(" ");
				final Librairie librairie = new Librairie();
				librairie.setIdx(Integer.toString(index));
				librairie.setNbLivres(Integer.parseInt(infosLibrairie[0]));
				librairie.setNbJoursOuverture(Integer.parseInt(infosLibrairie[1]));
				librairie.setCapaciteTraitement(Integer.parseInt(infosLibrairie[2]));
				
				final String[] infosLibrairieIdxLivres = lines.get(idxLibrairie + 3).split(" ");
				librairie.setListeIndexLivres(Arrays.asList(infosLibrairieIdxLivres));
				librairie.setListeLivres(new ArrayList<Livre>());
				for(String idLivre : librairie.getListeIndexLivres()) {
					librairie.getListeLivres().add(listeLivres.get(idLivre));
				}
				listeLibrairie.add(librairie);
				idxLibrairie++;
				index++;
			}
			
			System.out.println("Liste des librairies :");
			listeLibrairie.forEach(item->System.out.println(item));
//			Collections.reverse(listeLibrairie);
			ctxt.setLibrairies(listeLibrairie);
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}

		return ctxt;
	}
}
