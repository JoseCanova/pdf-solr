package org.nanotek;

import org.nanotek.pdf.PdfReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App 
implements ApplicationRunner{

	@Autowired
	PdfReader readExample;
	
	public App() {
	}

	public static void main(String[] args) { 
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		readExample.readPdf();
	}
}
