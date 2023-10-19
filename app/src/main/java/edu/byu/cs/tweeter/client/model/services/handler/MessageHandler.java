package edu.byu.cs.tweeter.client.model.services.handler;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.BackgroundTaskHandler;
import edu.byu.cs.tweeter.client.presenter.MainPresenter;

public class MessageHandler extends BackgroundTaskHandler<MainPresenter.MessageObserver> {
    public static final String MESSAGE_KEY = "message";

    public MessageHandler(MainPresenter.MessageObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(MainPresenter.MessageObserver observer, Bundle data) {
        observer.messageSucceeded(data.getString(MESSAGE_KEY));
    }
}
