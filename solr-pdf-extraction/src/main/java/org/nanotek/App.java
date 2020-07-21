package org.nanotek;

import java.io.File;
import java.util.List;

import org.nanotek.pdf.PdfReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App 
implements ApplicationRunner{

	@Autowired
	private PdfReader pdfReader;
	
	@Autowired
	@Qualifier("pdfCollection")
	private List<File> pdfFiles;
	
	
	public App() {
	}

	public static void main(String[] args) { 
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		pdfFiles.forEach(f -> {
				pdfReader.readPdf(f);
			});
	}
}
