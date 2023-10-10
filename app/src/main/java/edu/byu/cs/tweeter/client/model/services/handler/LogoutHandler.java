package edu.byu.cs.tweeter.client.model.services.handler;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.BackgroundTaskHandler;
import edu.byu.cs.tweeter.client.model.services.observer.LogoutObserver;

public class LogoutHandler extends BackgroundTaskHandler<LogoutObserver> {
    public LogoutHandler(LogoutObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(LogoutObserver observer, Bundle data) {
        observer.LogoutSucceeded();
    }
}
