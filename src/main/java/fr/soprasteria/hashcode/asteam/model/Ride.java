package fr.soprasteria.hashcode.asteam.model;

public class Ride implements Comparable<Ride>{

	private int index;

	private int startA;
	private int startB;
	
	private int endX;
	private int endY;
	
	private int earlyStartS;
	private int latestFinishF;
	
	private RideStatus statut;
	
	private int duree;
	
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
	 * @return the startA
	 */
	public final int getStartA() {
		return startA;
	}
	/**
	 * @param startA the startA to set
	 */
	public final void setStartA(int startA) {
		this.startA = startA;
	}
	/**
	 * @return the startB
	 */
	public final int getStartB() {
		return startB;
	}
	/**
	 * @param startB the startB to set
	 */
	public final void setStartB(int startB) {
		this.startB = startB;
	}
	/**
	 * @return the endX
	 */
	public final int getEndX() {
		return endX;
	}
	/**
	 * @param endX the endX to set
	 */
	public final void setEndX(int endX) {
		this.endX = endX;
	}
	/**
	 * @return the endY
	 */
	public final int getEndY() {
		return endY;
	}
	/**
	 * @param endY the endY to set
	 */
	public final void setEndY(int endY) {
		this.endY = endY;
	}
	/**
	 * @return the earlyStartS
	 */
	public final int getEarlyStartS() {
		return earlyStartS;
	}
	/**
	 * @param earlyStartS the earlyStartS to set
	 */
	public final void setEarlyStartS(int earlyStartS) {
		this.earlyStartS = earlyStartS;
	}
	/**
	 * @return the latestFinishF
	 */
	public final int getLatestFinishF() {
		return latestFinishF;
	}
	/**
	 * @param latestFinishF the latestFinishF to set
	 */
	public final void setLatestFinishF(int latestFinishF) {
		this.latestFinishF = latestFinishF;
	}

	public RideStatus getStatut() {
		return statut;
	}
	public void setStatut(RideStatus statut) {
		this.statut = statut;
	}
	@Override
	public String toString() {
		return String.format("ride from [%d, %d] to [%d, %d], earliest start %d, latest finish %d",
				startA, startB, endX, endY, earlyStartS, latestFinishF);
	}

	public int compareTo(Ride o)
	{
		// distance vehicule-point début - time début
		int score = Math.abs(Math.abs(0 - startA) + Math.abs(0 - startB) - earlyStartS);
		int oScore = Math.abs(Math.abs(0 - o.startA) + Math.abs(0 - o.startB) - o.earlyStartS);
		return oScore > score ? -1 : oScore == score ? 0 : 1;
	}
	
	public int getDuree()
	{
		if (duree == 0)
		{
			duree = Math.abs(startA - endX) + Math.abs(startB - endY);
		}
		return duree;
	}
}
