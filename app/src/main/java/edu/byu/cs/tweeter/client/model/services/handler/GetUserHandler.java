package edu.byu.cs.tweeter.client.model.services.handler;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.BackgroundTaskHandler;
import edu.byu.cs.tweeter.client.presenter.PagedPresenter;
import edu.byu.cs.tweeter.model.domain.User;

public class GetUserHandler extends BackgroundTaskHandler<PagedPresenter.GetUserObserver> {
    public static final String USER_KEY = "user";

    public GetUserHandler(PagedPresenter.GetUserObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(PagedPresenter.GetUserObserver observer, Bundle data) {
        observer.getUserSucceeded((User) data.getSerializable(USER_KEY));
    }
}
