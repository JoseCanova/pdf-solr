package org.nanotek.mq;

import java.io.StringReader;

import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.Message;
import org.apache.activemq.util.ByteSequence;
import org.nanotek.MapBase;
import org.nanotek.solr.SolrClientService;
import org.nanotek.solr.SolrDocumentBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
@Qualifier(value = "JmsListener")
public class JmsListener implements SessionAwareMessageListener<ActiveMQBytesMessage>{

	private Logger logger = LoggerFactory.getLogger(JmsListener.class);
	
	@Autowired
	private Gson gson;
	
	@Autowired
	private SolrClientService solrService;
	
	@Override
	public void onMessage(ActiveMQBytesMessage message, Session session) throws JMSException {
		logger.debug("Received JMSTYPE: "+ message.getJMSType());		
		logger.debug("Received JMSTYPE: "+ message.getJMSXMimeType());
		Message innerMessage = message.getMessage();
		ByteSequence sequence = innerMessage.getContent();
		String payLoad = new String (sequence.data);
		StringBuffer bf =  new StringBuffer().append(payLoad);
		processBuffer(bf);
	}

	private void processBuffer(StringBuffer bf) {
		String result = bf.toString();
		MapBase valueMap = gson.fromJson(new StringReader(result), MapBase.class);
		Object nameValue = valueMap.get("pageNumber");
		Object textPage = valueMap.get("textPage");
		double db = Double.parseDouble(nameValue.toString());
		Long lon  = System.currentTimeMillis();//Double.valueOf(nameValue.toString()).longValue();
		String page = textPage.toString();
		SolrDocumentBase doc = new SolrDocumentBase(lon,page);
		solrService.saveEntity(doc);
	}

}
