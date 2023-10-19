package edu.byu.cs.tweeter.client.model.services.handler;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.BackgroundTaskHandler;
import edu.byu.cs.tweeter.client.presenter.MainPresenter;

public class IsFollowerHandler extends BackgroundTaskHandler<MainPresenter.IsFollowerObserver> {
    public static final String IS_FOLLOWER_KEY = "is-follower";

    public IsFollowerHandler(MainPresenter.IsFollowerObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(MainPresenter.IsFollowerObserver observer, Bundle data) {
        observer.IsFollowerSucceeded(data.getBoolean(IS_FOLLOWER_KEY));
    }
}
