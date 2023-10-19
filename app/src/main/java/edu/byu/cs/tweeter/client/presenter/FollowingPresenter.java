package edu.byu.cs.tweeter.client.presenter;


import edu.byu.cs.tweeter.client.model.services.FollowService;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class FollowingPresenter extends PagedPresenter<User>{

    public FollowingPresenter(PagedView view, User user, AuthToken authToken) {
        super(view, user, authToken);
    }

    @Override
    protected void getItems(AuthToken authToken, User targetUser, int pageSize, User lastItem) {
        var followService = new FollowService();
        followService.getFollowing(authToken, targetUser, pageSize, lastItem, new ListObserver(super.getView()));
    }
}
