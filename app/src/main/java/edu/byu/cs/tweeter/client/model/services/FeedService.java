package edu.byu.cs.tweeter.client.model.services;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetFeedTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetStoryTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.PostStatusTask;
import edu.byu.cs.tweeter.client.model.services.handler.ListHandler;
import edu.byu.cs.tweeter.client.model.services.handler.MessageHandler;
import edu.byu.cs.tweeter.client.model.services.observer.ListObserver;
import edu.byu.cs.tweeter.client.model.services.observer.MessageObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class FeedService{

    public void getFeed(AuthToken authToken, User user, int pageSize,
                        Status lastStatus, ListObserver observer){
        GetFeedTask getFeedTask = new GetFeedTask(authToken,
                user, pageSize, lastStatus, new ListHandler<Status>(observer));
        getFeedTask.run();
    }

    public void getStory(AuthToken authToken, User user, int pageSize,
                         Status lastStatus, ListObserver observer){
        GetStoryTask getStoryTask = new GetStoryTask(authToken,
                user, pageSize, lastStatus, new ListHandler<Status>(observer));
        getStoryTask.run();
    }

    public void post(AuthToken authToken, Status newStatus, MessageObserver observer){
        PostStatusTask statusTask = new PostStatusTask(authToken,
                newStatus, new MessageHandler(observer));
        statusTask.run();
    }


}
