package edu.byu.cs.tweeter.client.model.services.observer;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public interface AuthenticateObserver extends ServiceObserver{
    void loginSucceeded(AuthToken authToken, User user);
}