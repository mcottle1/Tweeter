package edu.byu.cs.tweeter.client.model.services.handler;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.BackgroundTaskHandler;
import edu.byu.cs.tweeter.client.model.services.observer.FollowObserver;
import edu.byu.cs.tweeter.client.model.services.observer.UnfollowObserver;

public class FollowHandler extends BackgroundTaskHandler<FollowObserver> {
    public FollowHandler(FollowObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(FollowObserver observer, Bundle data) {
        observer.FollowSucceeded();
        observer.EnableButton();
    }
}
