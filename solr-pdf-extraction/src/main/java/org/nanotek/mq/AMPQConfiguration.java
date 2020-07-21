package org.nanotek.mq;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
public class AMPQConfiguration {

	public AMPQConfiguration() {
	}
	
	@Bean
	@Qualifier(value="page_queue")
	public Queue requestQueue() {
		return new ActiveMQQueue("activemq.page_queue");
	}

	@Bean
	@Qualifier(value="reply_queue")
	public Queue responseQueue() {
		return new ActiveMQQueue("activemq.reply_queue");
	}
	
	@Bean
	public MappingJackson2MessageConverter jsonConverter() {
		MappingJackson2MessageConverter mc = new MappingJackson2MessageConverter();
		return mc;
	}

	@Bean
	public JmsMessagingTemplate jmsMessagingTemplate(
					@Autowired ConnectionFactory connectionFactory,
					@Autowired MappingJackson2MessageConverter jsonConverter) {
		JmsMessagingTemplate jmsMessagingTemplate = new JmsMessagingTemplate(connectionFactory);
		jmsMessagingTemplate.setMessageConverter(jsonConverter);
		return jmsMessagingTemplate;
	}
	
	@Bean
	public JmsMessageSender<?> messageSender(
			@Autowired JmsMessagingTemplate jmsMessagingTemplate, 
			@Autowired @Qualifier("page_queue") Queue queue) {
		return new JmsMessageSender<>(jmsMessagingTemplate , queue);
	}
	
	@Bean
	public DefaultMessageListenerContainer 
				listenerContainer(@Autowired ConnectionFactory connectionFactory , 
								  @Autowired @Qualifier("JmsListener") JmsListener jmsListener ) {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setMaxConcurrentConsumers(1);
		container.setDestinationName("activemq.page_queue");
		container.setMessageListener(jmsListener);
		return container;
	}

}
