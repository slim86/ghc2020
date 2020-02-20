package fr.soprasteria.hashcode.bean;

import java.util.HashMap;
import java.util.Map;

public class Cache implements Comparable<Cache> {

	private String numCache;
	
	private Map<String, ScoreVideo> scoreParVideo;
	
	private int enCommun;

	public int getMaxScore() {
		int score = 0;
		for (ScoreVideo sv : this.scoreParVideo.values()) {
			if (sv.getScore() > score)
				score = sv.getScore();
		}
		return score;
	}
	
	public String getNumCache() {
		return numCache;
	}

	public void setNumCache(String numCache) {
		this.numCache = numCache;
	}

	public Map<String, ScoreVideo> getScoreParVideo() {
		if(scoreParVideo == null){
		scoreParVideo = new HashMap<String, ScoreVideo>();
		}
		return scoreParVideo;
	}

	public void setScoreParVideo(Map<String, ScoreVideo> scoreParVideo) {
		this.scoreParVideo = scoreParVideo;
	}

	public int getEnCommun() {
		return enCommun;
	}

	public void setEnCommun(int enCommun) {
		this.enCommun = enCommun;
	}

	
	@Override
	public String toString() {
		return "Cache [numCache=" + numCache + ", scoreParVideo="
				+ scoreParVideo + "]";
	}

	@Override
	public int compareTo(Cache o) {
		if (this.getMaxScore() == o.getMaxScore()) {
			return 0;
		}
		if (this.getMaxScore() > o.getMaxScore()) {
			return 1;
		} else {
			return -1;
		}
	}
	
	
}
