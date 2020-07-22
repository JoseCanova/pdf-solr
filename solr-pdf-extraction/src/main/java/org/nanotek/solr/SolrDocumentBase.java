package org.nanotek.solr;

import org.nanotek.SolrBase;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;

public class SolrDocumentBase<K extends SolrBase<K>>
implements SolrBase<K>{

	private static final long serialVersionUID = 1798347613053387449L;

	@Id
	@Indexed(name = "pageId")
	public Long pageId;

	@Indexed(name = "pageContent")
	public String page;
	
	public SolrDocumentBase() {
		super();
	}
			
	public SolrDocumentBase(Long pageId, String page) {
		super();
		this.pageId = pageId;
		this.page = page;
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
	
	
}
