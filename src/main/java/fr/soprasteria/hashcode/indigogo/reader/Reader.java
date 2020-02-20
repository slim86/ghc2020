package fr.soprasteria.hashcode.indigogo.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public final class Reader {

	public static void read(String fileName) {

		System.out.println("Lecture du fichier " + fileName);
		List<String> lines;

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			// line 0
			lines = br.lines().collect(Collectors.toList());

			String[] infosFirstLine = lines.get(0).split(" ");

			System.out.println("Nombre de lignes : "
					+ infosFirstLine.length);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
