package fr.soprasteria.hashcode.yt.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Endpoint {
	
	private int latenceDataCenter;
	
	private List<CacheEndPoint> caches;

	public int getLatenceDataCenter() {
		return latenceDataCenter;
	}

	public void setLatenceDataCenter(int latenceDataCenter) {
		this.latenceDataCenter = latenceDataCenter;
	}

	public List<CacheEndPoint> getCaches() {
		return caches;
	}

	public void setCaches(List<CacheEndPoint> caches) {
		this.caches = caches;
	}

	public void addCache(CacheEndPoint cache) {
		if(caches == null){
			caches = new ArrayList<>();
		}
		caches.add(cache);
	}

	public Set<String> getListeCacheEndPoint() {
	    Set<String> newSet = new HashSet<String>();
        for (CacheEndPoint cep : this.getCaches()) {
            newSet.add(cep.getCache().getNumCache());
        }
        return newSet;
    }
	
	@Override
	public String toString() {
		return "Endpoint [latenceDataCenter=" + latenceDataCenter + ", caches="
				+ caches + "]";
	}
	
}
