package fr.soprasteria.hashcode.indigogo.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import fr.soprasteria.hashcode.indigogo.model.Context;
import fr.soprasteria.hashcode.indigogo.model.Librairie;
import fr.soprasteria.hashcode.indigogo.model.Livre;

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
						.write(String.valueOf(librarie.getIdx() + " " + librarie.getListeLivres().size()));
				bufferedWriter.newLine();
				for (Livre livre : librarie.getListeLivres()) {
					bufferedWriter.write(livre.getId() + " ");
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
