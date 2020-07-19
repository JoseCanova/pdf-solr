package org.nanotek.pdf;

public class PdfStrippedPage {
	
	private Integer pageNumber;
	
	private String textPage;

	public PdfStrippedPage() {}
	
	public PdfStrippedPage (Integer pageNumber , String textPage) { 
		this.pageNumber = pageNumber; 
		this.textPage = textPage;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public String getTextPage() {
		return textPage;
	}
	
}
