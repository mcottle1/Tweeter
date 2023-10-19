package edu.byu.cs.tweeter.client.model.services;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.FollowTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetFollowersCountTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetFollowersTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetFollowingCountTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetFollowingTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.IsFollowerTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.UnfollowTask;
import edu.byu.cs.tweeter.client.model.services.handler.BasicHandler;
import edu.byu.cs.tweeter.client.model.services.handler.CountHandler;
import edu.byu.cs.tweeter.client.model.services.handler.ListHandler;
import edu.byu.cs.tweeter.client.model.services.handler.IsFollowerHandler;
import edu.byu.cs.tweeter.client.model.services.observer.BasicObserver;
import edu.byu.cs.tweeter.client.presenter.MainPresenter;
import edu.byu.cs.tweeter.client.presenter.PagedPresenter;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class FollowService {

    public void getFollowing(AuthToken authToken, User user, int pageSize,
                             User lastFollowee, PagedPresenter.ListObserver observer){
        GetFollowingTask getFollowingTask = new GetFollowingTask(authToken,
                user, pageSize, lastFollowee, new ListHandler<User>(observer));
        getFollowingTask.run();
    }

    public void getFollowers(AuthToken authToken, User user, int pageSize,
                             User lastFollowee, PagedPresenter.ListObserver observer){
        GetFollowersTask getFollowingTask = new GetFollowersTask(authToken,
                user, pageSize, lastFollowee, new ListHandler<User>(observer));
        getFollowingTask.run();
    }

    public void getFollowingCount(AuthToken authToken, User selectedUser, MainPresenter.CountObserver observer){
        GetFollowingCountTask followingCountTask = new GetFollowingCountTask(authToken, selectedUser,
                new CountHandler(observer));
        followingCountTask.run();
    }

    public void getFollowersCount(AuthToken authToken, User selectedUser, MainPresenter.CountObserver observer){
        GetFollowersCountTask followersCountTask = new GetFollowersCountTask(authToken,
                selectedUser, new CountHandler(observer));
        followersCountTask.run();
    }

    public void isFollower(AuthToken authToken, User user, User selectedUser, MainPresenter.IsFollowerObserver observer){
        IsFollowerTask isFollowerTask = new IsFollowerTask(authToken, user, selectedUser,
                new IsFollowerHandler(observer));
        isFollowerTask.run();
    }

    public void follow(AuthToken authToken, User selectedUser, BasicObserver observer){
        FollowTask followTask = new FollowTask(authToken, selectedUser, new BasicHandler(observer));
        followTask.run();
    }

    public void unfollow(AuthToken authToken, User selectedUser, BasicObserver observer){
        UnfollowTask unfollowTask = new UnfollowTask(authToken, selectedUser, new BasicHandler(observer));
        unfollowTask.run();
    }

}
