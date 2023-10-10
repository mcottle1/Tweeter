package edu.byu.cs.tweeter.client.model.services.handler;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.BackgroundTaskHandler;
import edu.byu.cs.tweeter.client.model.services.observer.MessageObserver;

public class MessageHandler extends BackgroundTaskHandler<MessageObserver> {
    public static final String MESSAGE_KEY = "message";

    public MessageHandler(MessageObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(MessageObserver observer, Bundle data) {
        observer.messageSucceeded(data.getString(MESSAGE_KEY));
    }
}
