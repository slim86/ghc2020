package fr.soprasteria.hashcode.indigogo.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fr.soprasteria.hashcode.indigogo.model.Context;
import fr.soprasteria.hashcode.indigogo.model.Librairie;
import fr.soprasteria.hashcode.indigogo.model.Livre;

public final class Reader {

	private static final String IN_EXTENTION = ".in";
	
	public static void read(final String fileName) {
		
		final String fileNameWithExtension = fileName + IN_EXTENTION;

		System.out.println("Lecture du fichier " + fileNameWithExtension);
		List<String> lines;

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileNameWithExtension))) {

			// line 0
			lines = br.lines().collect(Collectors.toList());
			System.out.println("Nombre de lignes : "
					+ lines.size());

			String[] infosFirstLine = lines.get(0).split(" ");
			Context ctxt = new Context();
			ctxt.setNbLivres(Integer.parseInt(infosFirstLine[0]));
			ctxt.setNbLibrairies(Integer.parseInt(infosFirstLine[1]));
			ctxt.setNbJours(Integer.parseInt(infosFirstLine[2]));
			System.out.println(ctxt);
			
			// line 1
			List<Livre> listeLivres = new ArrayList<Livre>();
			String[] scoreLivres = lines.get(1).split(" ");
			int idxLivre = 0;
			for (String scoreLivre : scoreLivres) {
				final Livre livre = new Livre();
				livre.setId(Integer.toString(idxLivre));
				livre.setScore(Integer.parseInt(scoreLivre));
				listeLivres.add(livre);
			}
			
			final List<Librairie> listeLibrairie = new ArrayList<Librairie>();
			for (int idxLibrairie = 0; idxLibrairie < lines.size() - 3; idxLibrairie++) {
				final String[] infosLibrairie = lines.get(idxLibrairie + 2).split(" ");
				final Librairie librairie = new Librairie();
				librairie.setNbLivres(Integer.parseInt(infosLibrairie[0]));
				librairie.setNbJoursOuverture(Integer.parseInt(infosLibrairie[1]));
				librairie.setCapaciteTraitement(Integer.parseInt(infosLibrairie[2]));
				
				final String[] infosLibrairieIdxLivres = lines.get(idxLibrairie + 3).split(" ");
				librairie.setListeIndexLivres(Arrays.asList(infosLibrairieIdxLivres));
				listeLibrairie.add(librairie);
			}
			
			System.out.println("Liste des librairies :");
			listeLibrairie.forEach(item->System.out.println(item));
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
