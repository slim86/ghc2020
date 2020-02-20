package fr.soprasteria.hashcode.indigogo.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

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
			StringBuffer firstLine = new StringBuffer();
			firstLine.append("Premiere ligne : ");
			for (String s : infosFirstLine) {
				firstLine.append(s);
				firstLine.append(" ");
			}
			System.out.println(firstLine);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
