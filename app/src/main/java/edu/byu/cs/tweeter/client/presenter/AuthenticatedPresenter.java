package edu.byu.cs.tweeter.client.presenter;


import edu.byu.cs.tweeter.client.model.services.observer.LoginObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public abstract class AuthenticatedPresenter implements LoginObserver {
    private final View view;

    protected AuthenticatedPresenter(View view) {
        this.view = view;
    }

    public interface View extends edu.byu.cs.tweeter.client.presenter.View {
        void openMainView(User user);
    }

    @Override
    public void loginSucceeded(AuthToken authToken, User user) {
        view.showInfoMessage("Hello, " + user.getName());
        view.openMainView(user);
    }

    @Override
    public void handleFailure(String message) {
        view.showErrorMessage(message);
    }

    @Override
    public void handleException(Exception exception) {

    }
}
