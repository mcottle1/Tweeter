package edu.byu.cs.tweeter.client.model.services.observer;

public interface FollowObserver extends ServiceObserver{
    void FollowSucceeded();
    void EnableButton();
}
