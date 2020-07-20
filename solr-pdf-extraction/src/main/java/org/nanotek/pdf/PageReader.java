package org.nanotek.pdf;

import java.io.BufferedReader;
import java.io.StringReader;

public class PageReader {

	private BufferedReader bufferedReader; 
	
	public PageReader(String stream) { 
		bufferedReader = new BufferedReader(new StringReader(stream));
	}
	
	
	
}
