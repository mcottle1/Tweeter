package edu.byu.cs.tweeter.client.model.services.observer;

public interface UnfollowObserver extends ServiceObserver{
    void UnfollowSucceeded();
    void EnableButton();
}
