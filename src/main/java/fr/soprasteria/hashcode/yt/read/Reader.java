package fr.soprasteria.hashcode.yt.read;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.soprasteria.hashcode.yt.bean.Cache;
import fr.soprasteria.hashcode.yt.bean.CacheEndPoint;
import fr.soprasteria.hashcode.yt.bean.Endpoint;
import fr.soprasteria.hashcode.yt.bean.Entrant;
import fr.soprasteria.hashcode.yt.bean.Params;
import fr.soprasteria.hashcode.yt.bean.ScoreVideo;
import fr.soprasteria.hashcode.yt.bean.Video;

public class Reader {

    public static Map<String, Set<String>> cachesEnCommun = new HashMap<String, Set<String>>();
    
    public static int totalRequest = 0;
    
	public Entrant Read(String pathFile) throws Exception{
		
		Entrant entrant = new Entrant();
		
		BufferedReader br = new BufferedReader(new FileReader(pathFile));
	    
		entrant.setParams(readFirstLine(br.readLine()));
		entrant.setVideos(readVideo(br.readLine()));
	    List<Endpoint> endPoints = new ArrayList<Endpoint>();
	    
	    for (int i = 0; i < entrant.getParams().getNbEndPoint(); i++) {
	    	endPoints.add(readEndPoint(entrant, br));
		}
	    
	    entrant.setEndPoints(endPoints);
	    
	    while (true) {
	    	String video = br.readLine();
	    	if(video == null){
	    		break;
	    	}
	    	readVideoEndPoint(entrant, video);
	    	
		}
	    
	    System.out.println(entrant.getParams());
//	    System.out.println(entrant.getVideos());
	    System.out.println(endPoints.get(0));
	    
	    return entrant;
		
	}
	
	private void readVideoEndPoint(Entrant entrant, String ligneVideo){
		String[] infoVideo = ligneVideo.split(" ");
		String videoNum = infoVideo[0];
		int endPoint = Integer.parseInt(infoVideo[1]);
		int nbRequest = Integer.parseInt(infoVideo[2]);
		totalRequest = totalRequest + nbRequest;
		
//		Video video = entrant.getVideos().get(videoNum);
		Endpoint endP = entrant.getEndPoints().get(endPoint);
		
		if(endP.getCaches() != null){
			for (CacheEndPoint cacheEndPoint : endP.getCaches()) {
				
				Integer newScore = nbRequest * (endP.getLatenceDataCenter() - cacheEndPoint.getLatence());
				ScoreVideo scoreVideo = cacheEndPoint.getCache().getScoreParVideo().get(videoNum);
				if(scoreVideo == null){
				    scoreVideo = new ScoreVideo();
				    scoreVideo.setIdVideo(videoNum);
				    scoreVideo.setScore(newScore);
				} else {
				    scoreVideo.setScore(scoreVideo.getScore() + newScore);
				}
				cacheEndPoint.getCache().getScoreParVideo().put(videoNum, scoreVideo);
			}
		}
		
		
	}
	
	private Params readFirstLine(String firstLine){
		Params result = new Params();
		String[] parametre = firstLine.split(" ");
		
		result.setNbVideo(Integer.parseInt(parametre[0]));
		result.setNbEndPoint(Integer.parseInt(parametre[1]));
		result.setNbRequest(Integer.parseInt(parametre[2]));
		result.setNbCache(Integer.parseInt(parametre[3]));
		result.setTailleCache(Integer.parseInt(parametre[4]));
		
		return result;
	}
	
	private Map<String, Video> readVideo(String videoLine){
		String[] parametre = videoLine.split(" ");
		Map<String, Video> resultat = new HashMap<String, Video>();
		
		
		for (int i = 0; i < parametre.length; i++) {
			Video video = new Video();
			video.setId(i+"");
			video.setTaille(Integer.parseInt(parametre[i]));
			
			resultat.put(video.getId(),video);
		}
		
		return resultat;
	}
	
	private Endpoint readEndPoint(Entrant entrant, BufferedReader br) throws Exception{
		Endpoint endPoint = new Endpoint();
		
		String firstEndPointLine = br.readLine();
		String[] info = firstEndPointLine.split(" ");
		
		endPoint.setLatenceDataCenter(Integer.parseInt(info[0]));
		for (int i = 0; i < Integer.parseInt(info[1]); i++) {
			endPoint.addCache(readCacheInfo(entrant,br.readLine()));
		}
		
		if (endPoint.getCaches() != null) {
    		for (CacheEndPoint cacheEndPoint: endPoint.getCaches()) {
    		    if (cachesEnCommun.containsKey(cacheEndPoint.getCache().getNumCache())) {
    		        cachesEnCommun.get(cacheEndPoint.getCache().getNumCache()).addAll(endPoint.getListeCacheEndPoint());
    		        cachesEnCommun.get(cacheEndPoint.getCache().getNumCache()).remove(cacheEndPoint.getCache().getNumCache());
    		    } else {
    		        cachesEnCommun.put(cacheEndPoint.getCache().getNumCache(), endPoint.getListeCacheEndPoint());
    		        cachesEnCommun.get(cacheEndPoint.getCache().getNumCache()).remove(cacheEndPoint.getCache().getNumCache());
    		    }
    		}
		}
		return endPoint;
	}
	
	private CacheEndPoint readCacheInfo(Entrant entrant, String line){
		String[] info = line.split(" ");
		CacheEndPoint cacheEndPoint = new CacheEndPoint();
		String numCache = info[0];
		Cache cache = entrant.getCaches().get(numCache);
		if(cache == null){
			cache = new Cache();
			cache.setNumCache(numCache);
			entrant.getCaches().put(numCache, cache);
			cache.setEnCommun(1);
		} else {
		    cache.setEnCommun(cache.getEnCommun()+1);
		}
		
		cacheEndPoint.setCache(cache);
		cacheEndPoint.setLatence(Integer.parseInt(info[1]));
		return cacheEndPoint;
	}
	
	
	public static void main(String[] args) throws Exception {
		Reader reader = new Reader();
//		Entrant entrant = reader.Read("D:/Profiles/slim/Documents/Perso/kittens.in");
//		Entrant entrant = reader.Read("D:/Profiles/slim/Documents/Perso/test.in");
//		Entrant entrant = reader.Read("D:/Profiles/slim/Documents/Perso/videos_worth_spreading.in");
//		Entrant entrant = reader.Read("D:/Profiles/slim/Documents/Perso/trending_today.in");
		Entrant entrant = reader.Read("D:/Profiles/slim/Documents/Perso/me_at_the_zoo.in");
		
		List<Cache> cacheSet = new ArrayList(entrant.getCaches().values());
		Collections.sort(cacheSet, Collections.reverseOrder());
		int tailleOccupee = entrant.getParams().getTailleCache();
		final List<String> cacheTraitee = new ArrayList<String>();
		for (Cache cache : cacheSet) {
			List<ScoreVideo> listScoreVideo = new ArrayList(cache.getScoreParVideo().values());
			Collections.sort(listScoreVideo, Collections.reverseOrder());
			System.out.println("For cache "+ cache.getNumCache() +"======> " + listScoreVideo);
			for (ScoreVideo sv : listScoreVideo) {
			    tailleOccupee = tailleOccupee - entrant.getVideos().get(sv.getIdVideo()).getTaille();
//			    System.out.println("Cache " + cache.getNumCache() + " - taille occupée " + tailleOccupee);
    			if (tailleOccupee < 0) {
    			    cache.getScoreParVideo().remove(sv.getIdVideo());
    			} else {
    			    System.out.println("Cache en commun du cache " + cache.getNumCache() + " : " + cachesEnCommun.get(cache.getNumCache()));
                    if (cachesEnCommun.get(cache.getNumCache()) != null) {
                        for (String numCacheAssociee : cachesEnCommun.get(cache.getNumCache())) {
                            System.out.println("Nombre d'endpoint en commun du cache " + numCacheAssociee + " : " + entrant.getCaches().get(numCacheAssociee).getEnCommun());
                            if (entrant.getCaches().get(numCacheAssociee).getEnCommun() == 1 ) {
                                System.out.println("Video " + sv.getIdVideo() + " supprimée du cache " + numCacheAssociee);
                                entrant.getCaches().get(numCacheAssociee).getScoreParVideo().remove(sv.getIdVideo());
                            } else if (entrant.getCaches().get(numCacheAssociee).getEnCommun() >= 1 
                                    //&& !cacheTraitee.contains(cache.getNumCache())
                                    ) {
                                  System.out.println("Suppression du cache associée" + cache.getNumCache() + " du cache " + numCacheAssociee);
                                entrant.getCaches().get(numCacheAssociee).setEnCommun(entrant.getCaches().get(numCacheAssociee).getEnCommun() -1);
                                cachesEnCommun.get(numCacheAssociee).remove(cache.getNumCache());
                            }
                            
                        }
                    }
    			}
    			//cacheTraitee.add(cache.getNumCache());
			}
			tailleOccupee = entrant.getParams().getTailleCache();
		}
		
		FileWriter fw = new FileWriter("D:/reponse.out");
		fw.write(Integer.toString(cacheSet.size()));
		fw.append("\n");
		Double score = new Double(0);
		for (Cache cache : cacheSet) {
		    StringBuilder sb = new StringBuilder();
            List<ScoreVideo> listScoreVideo = new ArrayList(cache.getScoreParVideo().values());
//            System.out.println("For cache "+ cache.getNumCache() +"======> " + listScoreVideo);
            score = score + listScoreVideo.stream().mapToDouble(o->o.getScore()).sum();
            if (listScoreVideo != null && !listScoreVideo.isEmpty()) {
                sb.append(cache.getNumCache());
                sb.append(" ");
                for (ScoreVideo sv : listScoreVideo) {
                    sb.append(sv.getIdVideo());
                    sb.append(" ");
                }
                fw.append(sb.toString().trim());
                fw.append("\n");
            }
		}
		fw.flush();
		fw.close();
//		System.out.println("======> " + entrant.getCaches());
		Double result = score * 1000 / totalRequest;
		System.out.println("Final score = " + result.toString());
		
	}
	
}
