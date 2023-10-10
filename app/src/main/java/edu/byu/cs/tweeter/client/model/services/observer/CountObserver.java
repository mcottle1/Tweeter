package edu.byu.cs.tweeter.client.model.services.observer;

public interface CountObserver extends ServiceObserver{
    void countSucceeded(int count);
}

