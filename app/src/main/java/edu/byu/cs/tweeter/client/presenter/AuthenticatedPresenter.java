package edu.byu.cs.tweeter.client.presenter;


import edu.byu.cs.tweeter.client.model.services.observer.IssueMessageObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public abstract class AuthenticatedPresenter{
    private final View view;

    protected AuthenticatedPresenter(View view) {
        this.view = view;
    }

    public class LoginObserver extends IssueMessageObserver implements edu.byu.cs.tweeter.client.model.services.observer.LoginObserver {

        public LoginObserver(View view) {
            super(view, "Login failed: ");
        }

        @Override
        public void loginSucceeded(AuthToken authToken, User user) {
            view.showInfoMessage("Hello, " + user.getName());
            view.openMainView(user);
        }
    }

    public interface View extends edu.byu.cs.tweeter.client.presenter.View {
        void openMainView(User user);
    }

}
