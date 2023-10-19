package edu.byu.cs.tweeter.client.presenter;



import edu.byu.cs.tweeter.client.model.services.FeedService;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class StoryPresenter extends PagedPresenter<Status>{

    public StoryPresenter(PagedView<Status> view, User user, AuthToken authToken){
        super(view,user, authToken);
    }

    @Override
    protected void getItems(AuthToken authToken, User targetUser, int pageSize, Status lastItem) {
        var feedService = new FeedService();
        feedService.getStory(authToken, targetUser, pageSize, lastItem, new ListObserver(super.getView()));
    }

}
