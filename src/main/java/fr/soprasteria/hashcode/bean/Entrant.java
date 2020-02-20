package fr.soprasteria.hashcode.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entrant {
	
	private Params params;
	 
	private Map<String, Video> videos;
	 
	private List<Endpoint> endPoints;
	
	private Map<String, Cache> caches;

	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public Map<String, Video> getVideos() {
		if(videos == null){
			videos = new HashMap<String, Video>();
		}
			
		return videos;
	}

	public void setVideos(Map<String, Video> videos) {
		this.videos = videos;
	}

	public List<Endpoint> getEndPoints() {
		return endPoints;
	}

	public void setEndPoints(List<Endpoint> endPoints) {
		this.endPoints = endPoints;
	}

	public Map<String, Cache> getCaches() {
		if(caches == null){
			caches = new HashMap<>();
		}
		return caches;
	}

	public void setCaches(Map<String, Cache> caches) {
		this.caches = caches;
	}
	
	
	 
}
