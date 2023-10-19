package edu.byu.cs.tweeter.client.presenter;


import java.util.List;

import edu.byu.cs.tweeter.client.model.services.UserService;
import edu.byu.cs.tweeter.client.model.services.observer.IssueMessageObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public abstract class PagedPresenter <T> extends Presenter{

    private static final int PAGE_SIZE = 10;
    private final User targetUser;
    private final AuthToken authToken;
    private T lastItem;
    private boolean hasMorePages = true;
    private boolean isLoading = false;

    public interface PagedView<U> extends Presenter.View {
        void setViewLoading(boolean isLoading);
        void addItems(List<U> items);
        void openMainView(User user);
    }

    protected PagedPresenter(PagedView view, User targetUser, AuthToken authToken) {
        super(view);
        this.targetUser = targetUser;
        this.authToken = authToken;
    }

    public class GetUserObserver extends IssueMessageObserver implements edu.byu.cs.tweeter.client.model.services.observer.GetUserObserver {
        private View view;
        public GetUserObserver(View view) {
            super(view, "Get user failed: ");
            this.view = view;
        }

        @Override
        public void getUserSucceeded(User user) {
            ((PagedView) view).openMainView(user);
        }
    }

    public class ListObserver extends IssueMessageObserver implements edu.byu.cs.tweeter.client.model.services.observer.ListObserver<T>{
        private View view;

        public ListObserver(PagedView view) {
            super(view, "Get List failed: ");
            this.view = view;
        }

        @Override
        public void getListSucceeded(List<T> list, boolean hasMorePages) {
            setLastItem((list.size() > 0) ? (T)list.get(list.size() - 1) : null);
            setHasMorePages(hasMorePages);
            setLoading(false);
            ((PagedView) view).setViewLoading(false);
            ((PagedView) view).addItems(list);
        }
    }


    public void getUser(AuthToken authToken, String alias){
        var userService = new UserService();
        userService.getUser(authToken, alias, new GetUserObserver(((PagedView) view)));
        view.showInfoMessage("Getting user's profile...");
    }

    public void loadMoreItems() {
        if (!isLoading && hasMorePages) {// This guard is important for avoiding a race condition in the scrolling code.
            setLoading(true);
            ((PagedView) view).setViewLoading(true);
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

    public PagedView getView() {
        return ((PagedView) view);
    }

}
