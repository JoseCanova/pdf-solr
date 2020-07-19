package org.nanotek.pdf;

import org.nanotek.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageSender<K extends PdfStrippedPage> 
						implements Sender<K , K>{

	@Autowired
	private Sender<K , K> delegateSender;
	
	public PageSender() {
	}

	@Override
	public K send(K t) {
		return delegateSender.send(t);
	}

}
