package fr.soprasteria.hashcode.asteam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import fr.soprasteria.hashcode.asteam.model.Ride;
import fr.soprasteria.hashcode.asteam.model.RideStatus;
import fr.soprasteria.hashcode.asteam.model.Vehicule;


/**
 * App.
 *
 */
public class OptimizeFleet 
{
	static String BASENAME = "C:/projects/prj-asip-sante/workspace/workspace_GHC/as-team/";
	static String FILENAME = BASENAME + "src/main/resources/a_example.in";
	static String example = "e_high_bonus";
	static String FILENAME_IN = BASENAME + "src/main/resources/" + example +".in";
	static String FILENAME_OUT = BASENAME + "out/" + example +".out";	
	private int totalRows;
	private int totalColumns;
	private int totalVehicules;
	private int totalRides;
	private int bonus;
	private int totalSteps;

	private ArrayList<Ride> rides;
	
	private ArrayList<Vehicule> vehicules;
	
    public static void main( String[] args )
    {
		OptimizeFleet optimizeFleet = new OptimizeFleet();

		optimizeFleet.read(FILENAME_IN);

		optimizeFleet.optimize();

		optimizeFleet.printResult();
    }

	private void read(String fileName) {
		
		List<String> lines;

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			// line 0
			lines = br.lines().collect(Collectors.toList());

			String[] infosFirstLine = lines.get(0).split(" ");

			totalRows = Integer.parseInt(infosFirstLine[0]);
			totalColumns = Integer.parseInt(infosFirstLine[1]);
			totalVehicules =  Integer.parseInt(infosFirstLine[2]);
			totalRides = Integer.parseInt(infosFirstLine[3]);
			bonus = Integer.parseInt(infosFirstLine[4]);
			totalSteps = Integer.parseInt(infosFirstLine[5]);
			
			System.out.println(String.format("%d rows, %d columns, %d vehicles, %d rides, %d bonus and %d steps",
					totalRows, totalColumns, totalVehicules, totalRides, bonus, totalSteps));
			
			// read rides
			rides = new ArrayList<Ride>();
			for(int i=1; i<=totalRides; i++) {
				final String[] infosRideLine = lines.get(i).split(" ");
				final Ride ride = new Ride();
				
				ride.setIndex(i-1);
				
				ride.setStartA(Integer.parseInt(infosRideLine[0]));
				ride.setStartB(Integer.parseInt(infosRideLine[1]));
				
				ride.setEndX(Integer.parseInt(infosRideLine[2]));
				ride.setEndY(Integer.parseInt(infosRideLine[3]));
				
				ride.setEarlyStartS(Integer.parseInt(infosRideLine[4]));
				ride.setLatestFinishF(Integer.parseInt(infosRideLine[5]));
				ride.setStatut(RideStatus.LIBRE);
				
				System.out.println(ride);
				
				rides.add(ride);
			}


			Collections.sort(rides);

			for (Ride ride : rides)
			{
				System.out.println(ride);
			}

			
			vehicules = new ArrayList<Vehicule>();
			for(int i=0; i<totalVehicules; i++) {
				Vehicule vehicule = new Vehicule();
				vehicule.setIndex(i);
				vehicules.add(vehicule);
			}
						
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	private void optimize() {
		
		for (int i = 0; i < totalSteps - 1; i++)
		{
			for (Vehicule v : vehicules)
			{
				if (!v.isEnCours())
				{
					Collections.sort(rides, new Comparator<Ride>() {
	
						@Override
						public int compare(Ride o1, Ride o2) {
							int score = Math.abs(Math.abs(v.getX() - o1.getStartA()) + Math.abs(v.getY() - o1.getStartB()) - o1.getEarlyStartS());
							int oScore = Math.abs(Math.abs(v.getX() - o2.getStartA()) + Math.abs(v.getY() - o2.getStartB()) - o2.getEarlyStartS());
							return oScore > score ? -1 : oScore == score ? 0 : 1;
						}
						
					}
					);
					for (Ride r : rides)
					{
						if (r.getStatut().equals(RideStatus.LIBRE))
						{
							
							int distanceDepart = Math.abs(Math.abs(v.getX() - r.getStartA()) + Math.abs(v.getY() - r.getStartB()));
							int tempsAttente = r.getEarlyStartS() > i ? 0 : r.getEarlyStartS() - i;
							int dureeFinal = distanceDepart + tempsAttente + r.getDuree();
							if (dureeFinal + i <= totalSteps)
							{
								v.addRide(r);
								r.setStatut(RideStatus.EN_COURS);
								v.setEtapeFinal(dureeFinal);
								break;
							}
						}
					}
					v.setEnCours(true);
					
				}
				else {
					v.setEtapeFinal(v.getEtapeFinal() - 1);
					if (v.getEtapeFinal() == 0)
					{
						v.setEnCours(false);
					}
				}
			}
		}

	}
	
	private void printResult() {
		
		File file = new File(FILENAME_OUT);
		FileOutputStream fileOutputStream;
		BufferedWriter bufferedWriter = null;
		try {
			fileOutputStream = new FileOutputStream(file);

			bufferedWriter = new BufferedWriter(new OutputStreamWriter(
					fileOutputStream));

			for(Vehicule vehicule : vehicules) {
				
				bufferedWriter.write(String.valueOf(vehicule.getRides().size()));
				for(Ride ride : vehicule.getRides()) {
					bufferedWriter.write(" " + ride.getIndex());
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
