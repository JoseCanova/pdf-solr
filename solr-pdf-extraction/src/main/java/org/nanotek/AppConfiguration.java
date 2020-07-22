package org.nanotek;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;

@Configuration
@ConfigurationProperties
public class AppConfiguration {

	@Bean
	@Qualifier (value="pdfPath")
	public Path pdfPath(@Value("${pdfdirectory}") String pdfDirectory) { 
		return Path.of(pdfDirectory);
	}

	@Bean 
	@Qualifier(value="pdfCollection")
	public List<File> pdfFileCollection
	(@Autowired @Qualifier("pdfPath") Path pdfPath) 
			throws IOException { 
		var pdfFiles = new ArrayList<File>();
		DirectoryStream<Path> stream = Files.newDirectoryStream(pdfPath);
		for (Path filePath: stream) {
			pdfFiles.add(filePath.toFile());
		}
		return pdfFiles;
	}

	private String baseSolrUrl = "http://localhost:8983/solr/";

	@Bean
	public SolrClient solrClient() {
		HttpSolrClient.Builder builder =   new HttpSolrClient.Builder();
		return builder.withBaseSolrUrl(baseSolrUrl).build();
	}

	@Bean
	public SolrTemplate solrTemplate(@Autowired SolrClient solrClient) {
		return new SolrTemplate(solrClient);
	}

	@Bean
	public SolrOperations solrTemplate() {
		return new SolrTemplate(solrClient());
	}

}
