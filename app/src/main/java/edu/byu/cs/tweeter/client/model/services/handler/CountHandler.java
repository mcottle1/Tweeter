package edu.byu.cs.tweeter.client.model.services.handler;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.BackgroundTaskHandler;
import edu.byu.cs.tweeter.client.presenter.MainPresenter;

public class CountHandler extends BackgroundTaskHandler<MainPresenter.CountObserver> {
    public static final String COUNT_KEY = "count";

    public CountHandler(MainPresenter.CountObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(MainPresenter.CountObserver observer, Bundle data) {
        observer.countSucceeded(data.getInt(COUNT_KEY));
    }
}
