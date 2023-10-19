package edu.byu.cs.tweeter.client.presenter;


import edu.byu.cs.tweeter.client.model.services.observer.IssueMessageObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public abstract class AuthenticatedPresenter extends Presenter{

    protected AuthenticatedPresenter(AuthenticatedView view) {
        super(view);
    }


    public interface AuthenticatedView extends Presenter.View {
        void openMainView(User user);
    }

    public class AuthenticateObserver extends IssueMessageObserver implements edu.byu.cs.tweeter.client.model.services.observer.AuthenticateObserver {

        public AuthenticateObserver(AuthenticatedView view) {
            super(view, "Login failed: ");
        }

        @Override
        public void loginSucceeded(AuthToken authToken, User user) {
            view.showInfoMessage("Hello, " + user.getName());
            ((AuthenticatedView)view).openMainView(user);
        }
    }

}
