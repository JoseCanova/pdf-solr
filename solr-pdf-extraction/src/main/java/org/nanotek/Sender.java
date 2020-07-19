package org.nanotek;

@FunctionalInterface
public interface Sender<K,T> {

	public T send(K t);
	
}
