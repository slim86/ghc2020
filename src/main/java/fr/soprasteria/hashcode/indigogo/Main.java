package fr.soprasteria.hashcode.indigogo;

import java.util.ArrayList;
import java.util.List;

import fr.soprasteria.hashcode.indigogo.model.Context;
import fr.soprasteria.hashcode.indigogo.model.Librairie;
import fr.soprasteria.hashcode.indigogo.reader.Reader;
import fr.soprasteria.hashcode.indigogo.writer.Writer;

public class Main {

	static String BASENAME = "C:/projects/prj-hc/workspace_hc/ghc2020/src/main/resources/2020/";
	static String BASENAME_OUT = "C:/projects/prj-hc/workspace_hc/ghc2020/out/2020/";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test");
		
		Reader.read(BASENAME + args[0]);
		
		Context ctx = new Context();
		List<Librairie> listeLibrairies = new ArrayList<Librairie>();
		Librairie librairie1 = new Librairie();
		librairie1.setIdx("0");
		List<String> listeLivres1 = new ArrayList<String>();
		listeLivres1.add("1");
		listeLivres1.add("2");
		librairie1.setListeIndexLivres(listeLivres1);
		
		Librairie librairie2 = new Librairie();
		librairie2.setIdx("1");
		
		List<String> listeLivres2 = new ArrayList<String>();
		listeLivres2.add("3");
		listeLivres2.add("4");
		listeLivres2.add("5");
		librairie2.setListeIndexLivres(listeLivres2);
		
		listeLibrairies.add(librairie1);
		listeLibrairies.add(librairie2);
		
		ctx.setLibrairies(listeLibrairies);
		
		Writer.write(BASENAME_OUT + args[0], ctx);
	} 

}
