package fr.soprasteria.hashcode.indigo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.soprasteria.hashcode.indigo.model.Photo;
import fr.soprasteria.hashcode.indigo.model.Tag;

public class Reader {

	static String BASENAME = "C:/projects/prj-asip-sante/workspace/workspace_GHC/ghc2019/";
	static String FILENAME = BASENAME + "src/main/resources/2019/a_example.txt";
	static String example = "a_example";
	static String FILENAME_IN = BASENAME + "src/main/resources/2019/" + example +".txt";
	static String FILENAME_OUT = BASENAME + "2019/out/" + example +".out";	
	private int totalRows;
	
	public static void main(String[] args) {
		
		Reader r = new Reader();
		
		r.read(FILENAME_IN);

	}

	private void read(String fileName) {
		
		List<String> lines;

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			// line 0
			lines = br.lines().collect(Collectors.toList());

			String[] infosFirstLine = lines.get(0).split(" ");
			totalRows = Integer.parseInt(infosFirstLine[0]);

			System.out.println("Nombre d'images : " + Integer.parseInt(infosFirstLine[0]));
			
//			System.out.println(String.format("%d rows, %d columns, %d vehicles, %d rides, %d bonus and %d steps",
//					totalRows, totalColumns, totalVehicules, totalRides, bonus, totalSteps));
			
			// read lines
			List<Photo> listPhotos = new ArrayList<Photo>();
			List<Tag> listTag = new ArrayList<Tag>();
			for(int i=1; i<=totalRows; i++) {
				final String[] infosRideLine = lines.get(i).split(" ");
				System.out.println("*******ID " + i + "**********");
				Photo photo = new Photo();
				photo.setId(i);
				System.out.println("Position=" + infosRideLine[0]);
				photo.setType(infosRideLine[0]);
				final int nbTag = Integer.parseInt(infosRideLine[1]);
				System.out.println("Nombre de tag=" + nbTag);
				for (int j=1; j<=nbTag; j++) {
					Tag t = new Tag(infosRideLine[j+1]);
					System.out.println("Tag=" + infosRideLine[j+1]);
					photo.getTags().add(t);
				}
				completerListScoreTag(photo, listTag);
				listPhotos.add(photo);
				System.out.println(photo);
				
			}
			
			System.out.println(listTag);
			
			for (Photo p : listPhotos) {
				for (Tag tag : p.getTags()) {
					for (Tag tagGlobal : listTag) {
						if (tag.getNom().equals(tagGlobal.getNom())) {
							p.setScore(p.getScore() + tagGlobal.getOccurence());
						}
					}
				}
			}
			
			System.out.println(listPhotos);
						
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	private void completerListScoreTag(final Photo p, final List<Tag> listTag) {
		for (Tag tagPhoto : p.getTags()) {
			boolean present = false;
			for (Tag t : listTag) {
				if (t.getNom().equals(tagPhoto.getNom())) {
					t.setOccurence(t.getOccurence() + 1);
					present = true;
					break;
				}
			}
			if (!present) {
				tagPhoto.setOccurence(1);
				listTag.add(tagPhoto);
			}
		}
	}
}
