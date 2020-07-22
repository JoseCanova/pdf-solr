package org.nanotek.solr;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.Query;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class SolrClientService<K extends SolrDocumentBase<K>> {
	
	private String collectionName;
	
	@Autowired
	Gson gson;
	
	@Autowired
	private SolrOperations solrOperations;

	public SolrClientService() {
		this.collectionName  = "brainz_core";
	}

	public SolrClientService(String collection) {
		this.collectionName = collection;
	}
	
	public void saveEntity (K entity) {
		solrOperations.saveBean(collectionName, entity);
		solrOperations.commit(collectionName);
	}
	
	public Optional<?> findEntity(K entity) { 
		return solrOperations.getById(collectionName, entity.getPageId(), entity.getClass());
	}
	
	public Page<K> findBy(Query query , Class<K> clazz) {
		return solrOperations.query(collectionName, query, clazz);
	}
}
