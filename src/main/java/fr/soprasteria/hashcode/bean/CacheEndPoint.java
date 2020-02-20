package fr.soprasteria.hashcode.bean;

public class CacheEndPoint {

	private Cache cache;
	
	private int latence;


	public int getLatence() {
		return latence;
	}

	public void setLatence(int latence) {
		this.latence = latence;
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}
	
	@Override
	public String toString() {
		return "CacheEndPoint [cache=" + cache + ", latence=" + latence + "]";
	}
	
}
