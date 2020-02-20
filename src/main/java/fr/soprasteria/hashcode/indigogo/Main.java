package fr.soprasteria.hashcode.indigogo;

import fr.soprasteria.hashcode.indigogo.reader.Reader;

public class Main {

	static String BASENAME = "C:/projects/prj-hc/workspace_hc/ghc2020/src/main/resources/2020/";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test");
		
		Reader.read(BASENAME + args[0]);
	}

}
