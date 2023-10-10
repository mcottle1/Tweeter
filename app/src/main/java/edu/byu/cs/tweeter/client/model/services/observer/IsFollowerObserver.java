package edu.byu.cs.tweeter.client.model.services.observer;

public interface IsFollowerObserver extends ServiceObserver{
    void IsFollowerSucceeded(boolean isFollower);
}
