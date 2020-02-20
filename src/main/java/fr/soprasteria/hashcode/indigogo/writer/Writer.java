package fr.soprasteria.hashcode.indigogo.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import fr.soprasteria.hashcode.asteam.model.Ride;
import fr.soprasteria.hashcode.indigogo.model.Context;
import fr.soprasteria.hashcode.indigogo.model.Librairie;

public final class Writer {

	private static final String OUT_EXTENSION = ".out";

	public static void write(final String filename, final Context context) {

		final String filenameWithExtension = filename + OUT_EXTENSION;
		File file = new File(filenameWithExtension);
		FileOutputStream fileOutputStream;
		BufferedWriter bufferedWriter = null;
		
		try {
			fileOutputStream = new FileOutputStream(file);

			bufferedWriter = new BufferedWriter(new OutputStreamWriter(
					fileOutputStream));

			bufferedWriter.write(String.valueOf(context.getLibrairies().size()));
			bufferedWriter.newLine();

			for (Librairie librarie : context.getLibrairies()) {
				bufferedWriter
						.write(String.valueOf(librarie.getIdx() + " " + librarie.getListeIndexLivres().size()));
				bufferedWriter.newLine();
				for (String indexLivre : librarie.getListeIndexLivres()) {
					bufferedWriter.write(indexLivre + " ");
				}
				bufferedWriter.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
