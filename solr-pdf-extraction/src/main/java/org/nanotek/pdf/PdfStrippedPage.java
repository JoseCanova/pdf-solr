package org.nanotek.pdf;

public class PdfStrippedPage {
	
	private Long pageNumber;
	
	private String textPage;

	public PdfStrippedPage() {}
	
	public PdfStrippedPage (Long pageNumber , String textPage) { 
		this.pageNumber = pageNumber; 
		this.textPage = textPage;
	}

	public Long getPageNumber() {
		return pageNumber;
	}

	public String getTextPage() {
		return textPage;
	}
	
}
