package fr.soprasteria.hashcode.bean;

public class Params {

	private int nbVideo;
	
	private int nbEndPoint;
	
	private int nbRequest;
	
	private int nbCache;
	
	private int tailleCache;

	public int getNbVideo() {
		return nbVideo;
	}

	public void setNbVideo(int nbVideo) {
		this.nbVideo = nbVideo;
	}

	public int getNbEndPoint() {
		return nbEndPoint;
	}

	public void setNbEndPoint(int nbEndPoint) {
		this.nbEndPoint = nbEndPoint;
	}

	public int getNbRequest() {
		return nbRequest;
	}

	public void setNbRequest(int nbRequest) {
		this.nbRequest = nbRequest;
	}

	public int getNbCache() {
		return nbCache;
	}

	public void setNbCache(int nbCache) {
		this.nbCache = nbCache;
	}

	public int getTailleCache() {
		return tailleCache;
	}

	public void setTailleCache(int tailleCache) {
		this.tailleCache = tailleCache;
	}

	@Override
	public String toString() {
		return "Params [nbVideo=" + nbVideo + ", nbEndPoint=" + nbEndPoint
				+ ", nbRequest=" + nbRequest + ", nbCache=" + nbCache
				+ ", tailleCache=" + tailleCache + "]";
	}

	
}
