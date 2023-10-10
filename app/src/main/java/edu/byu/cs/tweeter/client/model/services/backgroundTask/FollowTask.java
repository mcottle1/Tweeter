package edu.byu.cs.tweeter.client.model.services.backgroundTask;

import android.os.Handler;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;


public class FollowTask extends AuthenticatedTask {
    private final User followee;

    public FollowTask(AuthToken authToken, User followee, Handler messageHandler) {
        super(authToken, messageHandler);
        this.followee = followee;
    }

    @Override
    protected void runTask() {
        //TODO:
        // We could do this from the presenter, without a task and handler, but we will
        // eventually access the database from here when we aren't using dummy data.

        // Call sendSuccessMessage if successful
        sendSuccessMessage();
        // or call sendFailedMessage if not successful
        // sendFailedMessage()
    }
}
