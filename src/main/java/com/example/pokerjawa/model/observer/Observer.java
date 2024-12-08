package com.example.pokerjawa.model.observer;

public interface Observer<E> {
	public void subscribe(E subcriber);
	public void unsubscribe(E subcriber);
	public void notifySubcribers();
}
