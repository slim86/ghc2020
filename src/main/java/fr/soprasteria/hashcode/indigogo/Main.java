package fr.soprasteria.hashcode.indigogo;

import fr.soprasteria.hashcode.indigogo.reader.Reader;
import fr.soprasteria.hashcode.indigogo.writer.Writer;

public class Main {

	static String BASENAME = "C:/projects/prj-hc/workspace_hc/ghc2020/src/main/resources/2020/";
	static String BASENAME_OUT = "C:/projects/prj-hc/workspace_hc/ghc2020/out/2020/";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test");
		
		Reader.read(BASENAME + args[0]);
		
		Writer.write(BASENAME_OUT + args[0]);
	}

}
