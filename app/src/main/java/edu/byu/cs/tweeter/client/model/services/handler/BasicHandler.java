package edu.byu.cs.tweeter.client.model.services.handler;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.BackgroundTaskHandler;
import edu.byu.cs.tweeter.client.model.services.observer.BasicObserver;

public class BasicHandler extends BackgroundTaskHandler<BasicObserver> {
    public BasicHandler(BasicObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(BasicObserver observer, Bundle data) {
        observer.serviceSucceeded();
    }
}