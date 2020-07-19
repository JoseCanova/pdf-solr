package org.nanotek.pdf;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * https://mkyong.com/java/pdfbox-how-to-read-pdf-file-in-java/
 * @author mkyong.com
 */
@Service
public class PdfReader {
	
	@Autowired
	private PageSender<PdfStrippedPage> pageSender;

	public PdfReader() {
	}

	
	public void  readPdf() throws IOException {
		int ini = 0 , fim = 0; 
		
		
	        try (PDDocument document = PDDocument.load(new File("/home/jose/teste.pdf"))) {

	            int numPages = document.getNumberOfPages();
	            PDFTextStripper tStripper = new PDFTextStripper();
	            while (ini < numPages) { 
	            	++ini;
	            	fim = ini;
	            	tStripper.setStartPage(ini);
		            tStripper.setEndPage(fim);
		            String pdfFileInText = tStripper.getText(document);
		            pageSender.send(new PdfStrippedPage(ini, pdfFileInText));
	            }
	            
	            /**if (!document.isEncrypted()) {

	                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
	                stripper.setSortByPosition(true);

	                PDFTextStripper tStripper = new PDFTextStripper();

	                String pdfFileInText = tStripper.getText(document);
	                //System.out.println("Text:" + st);
	                System.out.println(pdfFileInText);
					// split by whitespace
//	                String lines[] = pdfFileInText.split("\\r?\\n");
//	                for (String line : lines) {
//	                    System.out.println(line);
//	                }

	            }**/

	        }

	    }
	
}
