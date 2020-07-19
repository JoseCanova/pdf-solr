package org.nanotek.mq;

import javax.jms.Queue;

import org.nanotek.Sender;
import org.springframework.jms.core.JmsMessagingTemplate;

public class JmsMessageSender<K> implements Sender<K,K> {

	protected JmsMessagingTemplate jmsMessagingTemplate;
	
	protected Queue queue;
	
	public JmsMessageSender() {
	}
	
	public JmsMessageSender(JmsMessagingTemplate theMessagingTemplate ,
							Queue theQueue) {
		this.jmsMessagingTemplate = theMessagingTemplate; 
		this.queue = theQueue;
	}

	@Override
	public K send(K t) {
		jmsMessagingTemplate.convertAndSend(queue, t);
		return t;
	}

}
