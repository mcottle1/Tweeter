package edu.byu.cs.tweeter.client.model.services.backgroundTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import edu.byu.cs.tweeter.util.FakeData;

public abstract class BackgroundTask implements Runnable {
    private static final String LOG_TAG = "BackgroundTask";

    public static final String SUCCESS_KEY = "success";
    public static final String MESSAGE_KEY = "message";
    public static final String EXCEPTION_KEY = "exception";

    private final Handler messageHandler;

    protected BackgroundTask(Handler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void run() {
        try {
            runTask();
        } catch (Exception ex) {
            Log.e(LOG_TAG, ex.getMessage(), ex);
            sendExceptionMessage(ex);
        }
    }

    protected abstract void runTask() throws IOException;

    protected FakeData getFakeData() {
        return FakeData.getInstance();
    }

    public void sendSuccessMessage() {
        Bundle msgBundle = new Bundle();
        msgBundle.putBoolean(SUCCESS_KEY, true);
        loadSuccessBundle(msgBundle);
        sendMessage(msgBundle);
    }

    public void sendFailedMessage(String errorMessage) {
        Bundle msgBundle = new Bundle();
        msgBundle.putBoolean(SUCCESS_KEY, false);
        msgBundle.putString(MESSAGE_KEY, errorMessage);
        sendMessage(msgBundle);
    }

    public void sendExceptionMessage(Exception exception) {
        Bundle msgBundle = new Bundle();
        msgBundle.putBoolean(SUCCESS_KEY, false);
        msgBundle.putSerializable(EXCEPTION_KEY, exception);
        sendMessage(msgBundle);
    }

    protected void loadSuccessBundle(Bundle msgBundle) {

    }

    private void sendMessage(Bundle msgBundle) {
        Message msg = Message.obtain();
        msg.setData(msgBundle);
        messageHandler.sendMessage(msg);
    }
}
