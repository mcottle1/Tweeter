package edu.byu.cs.tweeter.client.model.services.handler;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.BackgroundTaskHandler;
import edu.byu.cs.tweeter.client.model.services.observer.CountObserver;
import edu.byu.cs.tweeter.client.model.services.observer.IsFollowerObserver;

public class IsFollowerHandler extends BackgroundTaskHandler<IsFollowerObserver> {
    public static final String IS_FOLLOWER_KEY = "is-follower";

    public IsFollowerHandler(IsFollowerObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(IsFollowerObserver observer, Bundle data) {
        observer.IsFollowerSucceeded(data.getBoolean(IS_FOLLOWER_KEY));
    }
}
