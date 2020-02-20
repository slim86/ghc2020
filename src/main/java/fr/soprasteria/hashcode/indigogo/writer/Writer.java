package fr.soprasteria.hashcode.indigogo.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public final class Writer {

	private static final String OUT_EXTENSION = ".out";

	public static void write(final String filename) {

		final String filenameWithExtension = filename + OUT_EXTENSION;
		File file = new File(filenameWithExtension);
		FileOutputStream fileOutputStream;
		BufferedWriter bufferedWriter = null;
		try {
			fileOutputStream = new FileOutputStream(file);

			bufferedWriter = new BufferedWriter(new OutputStreamWriter(
					fileOutputStream));

			// for(Vehicule vehicule : vehicules) {
			//
			// bufferedWriter.write(String.valueOf(vehicule.getRides().size()));
			// for(Ride ride : vehicule.getRides()) {
			// bufferedWriter.write(" " + ride.getIndex());
			// }
			//
			// bufferedWriter.newLine();
			// }

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
