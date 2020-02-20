package fr.soprasteria.hashcode.bean;

public class ScoreVideo implements Comparable<ScoreVideo>{

	private String idVideo;
	
	private int score;

	public String getIdVideo() {
		return idVideo;
	}

	public void setIdVideo(String idVideo) {
		this.idVideo = idVideo;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int compareTo(ScoreVideo o) {
			if (this.score == o.getScore()) {
				return 0;
			}
			if (this.score > o.getScore()) {
				return 1;
			} else {
				return -1;
			}
		}

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ScoreVideo [idVideo=" + this.idVideo + ", score=" + this.score + "]";
    }
	
	
}
