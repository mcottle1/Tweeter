package edu.byu.cs.tweeter.client.model.services.handler;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.BackgroundTaskHandler;
import edu.byu.cs.tweeter.client.model.services.observer.LoginObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class LoginHandler extends BackgroundTaskHandler<LoginObserver> {
    public static final String USER_KEY = "user";
    public static final String AUTH_TOKEN_KEY = "auth-token";

    public LoginHandler(LoginObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(LoginObserver observer, Bundle data) {
        observer.loginSucceeded((AuthToken) data.getSerializable(AUTH_TOKEN_KEY), (User) data.getSerializable(USER_KEY));
        Cache.getInstance().setCurrUser((User) data.getSerializable(USER_KEY));
        Cache.getInstance().setCurrUserAuthToken((AuthToken) data.getSerializable(AUTH_TOKEN_KEY));
    }
}

