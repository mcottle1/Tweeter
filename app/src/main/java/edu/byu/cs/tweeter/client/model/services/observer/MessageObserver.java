package edu.byu.cs.tweeter.client.model.services.observer;

public interface MessageObserver extends ServiceObserver{
    void messageSucceeded(String message);
}
