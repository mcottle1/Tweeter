package edu.byu.cs.tweeter.client.model.services.handler;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.BackgroundTaskHandler;
import edu.byu.cs.tweeter.client.model.services.observer.UnfollowObserver;

public class UnfollowHandler extends BackgroundTaskHandler<UnfollowObserver> {
    public UnfollowHandler(UnfollowObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(UnfollowObserver observer, Bundle data) {
        observer.UnfollowSucceeded();
        observer.EnableButton();
    }
}
