package edu.byu.cs.tweeter.client.presenter;


import java.util.List;

import edu.byu.cs.tweeter.client.model.services.UserService;
import edu.byu.cs.tweeter.client.model.services.observer.GetUserObserver;
import edu.byu.cs.tweeter.client.model.services.observer.ListObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public abstract class PagedPresenter <T> implements GetUserObserver, ListObserver<T> {
    public interface View<U> extends edu.byu.cs.tweeter.client.presenter.View {
        void setViewLoading(boolean isLoading);
        void addItems(List<U> items);
        void openMainView(User user);
    }

    private final View view;
    private static final int PAGE_SIZE = 10;
    private final User targetUser;
    private final AuthToken authToken;
    private T lastItem;
    private boolean hasMorePages = true;
    private boolean isLoading = false;

    protected PagedPresenter(View view, User targetUser, AuthToken authToken) {
        this.view = view;
        this.targetUser = targetUser;
        this.authToken = authToken;
    }

    public void getUser(AuthToken authToken, String alias){
        var userService = new UserService();
        userService.getUser(authToken, alias, this);
        view.showInfoMessage("Getting user's profile...");
    }

    public void loadMoreItems() {
        if (!isLoading && hasMorePages) {// This guard is important for avoiding a race condition in the scrolling code.
            setLoading(true);
            view.setViewLoading(true);
            getItems(authToken, targetUser, PAGE_SIZE, lastItem);
        }
    }

    protected abstract void getItems(AuthToken authToken, User targetUser, int pageSize, T lastItem);


    public void setLastItem(T lastItem) {
        this.lastItem = lastItem;
    }

    public boolean isHasMorePages() {
        return hasMorePages;
    }

    public void setHasMorePages(boolean hasMorePages) {
        this.hasMorePages = hasMorePages;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    @Override
    public void getUserSucceeded(User user) {
        view.openMainView(user);
    }


    @Override
    public void getListSucceeded(List list, boolean hasMorePages) {
        setLastItem((list.size() > 0) ? (T) list.get(list.size() - 1) : null);
        setHasMorePages(hasMorePages);
        setLoading(false);
        view.setViewLoading(false);
        view.addItems(list);
    }

    @Override
    public void handleFailure(String message) {
        setLoading(false);
        view.setViewLoading(false);
        view.showErrorMessage(message);
    }

    @Override
    public void handleException(Exception exception) {

    }

}
