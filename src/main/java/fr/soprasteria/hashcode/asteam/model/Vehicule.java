package fr.soprasteria.hashcode.asteam.model;

import java.util.ArrayList;
import java.util.List;

public class Vehicule {

	int index;
	
	List<Ride> rides = new ArrayList<Ride>();
	
	boolean enCours = false;
	
	int etapeFinal = 0;
	
	int x = 0;
	
	int y = 0;

	/**
	 * @return the index
	 */
	public final int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public final void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the rides
	 */
	public final List<Ride> getRides() {
		return rides;
	}

	/**
	 * @param rides the rides to set
	 */
	public final void setRides(List<Ride> rides) {
		this.rides = rides;
	}

	public final void addRide(Ride ride) {
		this.rides.add(ride);
	}

	public boolean isEnCours() {
		return enCours;
	}

	public void setEnCours(boolean enCours) {
		this.enCours = enCours;
	}

	public int getEtapeFinal() {
		return etapeFinal;
	}

	public void setEtapeFinal(int etapeFinal) {
		this.etapeFinal = etapeFinal;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
