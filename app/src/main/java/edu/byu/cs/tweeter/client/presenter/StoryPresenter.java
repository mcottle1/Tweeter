package edu.byu.cs.tweeter.client.presenter;



import edu.byu.cs.tweeter.client.model.services.FeedService;
import edu.byu.cs.tweeter.client.model.services.UserService;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
public class StoryPresenter extends PagedPresenter<Status>{

    public StoryPresenter(View<Status> view, User user, AuthToken authToken){
        super(view,user, authToken);
    }

    @Override
    protected void getItems(AuthToken authToken, User targetUser, int pageSize, Status lastItem) {
        var feedService = new FeedService();
        feedService.getStory(authToken, targetUser, pageSize, lastItem, this);
    }


    @Override
    protected void loadUser(AuthToken authToken, String alias) {
        var userService = new UserService();
        userService.getUser(authToken, alias, this);
    }

}
