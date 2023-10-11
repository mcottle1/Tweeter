package edu.byu.cs.tweeter.client.presenter;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.client.model.services.FeedService;
import edu.byu.cs.tweeter.client.model.services.FollowService;
import edu.byu.cs.tweeter.client.model.services.UserService;
import edu.byu.cs.tweeter.client.model.services.observer.CountObserver;
import edu.byu.cs.tweeter.client.model.services.observer.IssueMessageObserver;
import edu.byu.cs.tweeter.client.model.services.observer.BasicObserver;
import edu.byu.cs.tweeter.client.model.services.observer.IsFollowerObserver;
import edu.byu.cs.tweeter.client.model.services.observer.MessageObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class MainPresenter extends IssueMessageObserver implements IsFollowerObserver, CountObserver, MessageObserver {

    private final View view;

    private class LogoutObserver extends IssueMessageObserver implements BasicObserver {

        public LogoutObserver(View view) {
            super(view, "Failed to logout:");
        }

        @Override
        public void serviceSucceeded() {
            view.logoutUser();
        }

    }

    private class FollowObserver extends IssueMessageObserver implements BasicObserver {

        public FollowObserver(View view) {
            super(view, "Failed to follow:");
        }

        @Override
        public void serviceSucceeded() {
            view.updateSelectedUserFollowingAndFollowers();
            view.updateFollowButtonFollowing();
        }

    }

     private class UnfollowObserver extends IssueMessageObserver implements BasicObserver {

         public UnfollowObserver(View view) {
             super(view, "Failed to unfollow:");
         }

         @Override
         public void serviceSucceeded() {
             view.updateSelectedUserFollowingAndFollowers();
             view.updateFollowButtonFollow();
         }

     }

    public interface View extends edu.byu.cs.tweeter.client.presenter.View  {
        void hideFollowButton();
        void showFollowButton();
        void logoutUser();
        void setFollowersText(String message);
        void setFollowingText(String message);
        void setFollowerButton();
        void setFollowButton();
        void updateSelectedUserFollowingAndFollowers();
        void updateFollowButtonFollow();
        void updateFollowButtonFollowing();
        void enableButton();
    }

    public MainPresenter(View view){
        super(view, "Main presented action failed");
        this.view = view;
    }

    public void logout(AuthToken authToken){
        view.showInfoMessage("Logging Out...");
        var userService = new UserService();
        userService.Logout(authToken, new LogoutObserver(this.view));
        //userService.Logout(authToken, this);
    }

    public void getFollowerCount(AuthToken authToken, User selectedUser){
        var followService = new FollowService();
        followService.getFollowersCount(authToken, selectedUser, this);
    }

    public void getFollowingCount(AuthToken authToken, User selectedUser){
        var followService = new FollowService();
        followService.getFollowingCount(authToken, selectedUser, this);
    }

    public void isFollower(AuthToken authToken, User selectedUser, User currUser){
        if(selectedUser.compareTo(currUser) == 0){
            view.hideFollowButton();
        }else{
            view.showFollowButton();
            var followService = new FollowService();
            followService.isFollower(authToken, currUser, selectedUser, this);
        }
    }

    public void followOrUnfollow(String buttonText, AuthToken authToken, User selectedUser){
        view.enableButton();
        if (buttonText.equals("Following")) {
            unfollow(authToken, selectedUser);
        } else {
            follow(authToken, selectedUser);
        }
    }

    private void follow(AuthToken authToken, User selectedUser){
        var followService = new FollowService();
        followService.follow(authToken, selectedUser, new FollowObserver(this.view));
        view.showInfoMessage("Adding " + selectedUser.getName() + "...");
    }

    private void unfollow(AuthToken authToken, User selectedUser){
        var followService = new FollowService();
        followService.unfollow(authToken, selectedUser, new UnfollowObserver(this.view));
        view.showInfoMessage("Removing " + selectedUser.getName() + "...");
    }

    public void post(AuthToken authToken, User currUser, String post){
        Status newStatus = new Status(post, currUser, System.currentTimeMillis(), parseURLs(post), parseMentions(post));
        view.showInfoMessage("Posting Status...");
        var feedService = new FeedService();
        feedService.post(authToken, newStatus, this);
    }

    public void checkUser(User selectedUser){
        if (selectedUser == null) {
            view.showErrorMessage("User not passed to activity");
        }
    }

    public String getFormattedDateTime() throws ParseException {
        SimpleDateFormat userFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat statusFormat = new SimpleDateFormat("MMM d yyyy h:mm aaa");

        return statusFormat.format(userFormat.parse(LocalDate.now().toString() + " " + LocalTime.now().toString().substring(0, 8)));
    }

    public List<String> parseURLs(String post) {
        List<String> containedUrls = new ArrayList<>();
        for (String word : post.split("\\s")) {
            if (word.startsWith("http://") || word.startsWith("https://")) {

                int index = findUrlEndIndex(word);

                word = word.substring(0, index);

                containedUrls.add(word);
            }
        }

        return containedUrls;
    }

    public List<String> parseMentions(String post) {
        List<String> containedMentions = new ArrayList<>();

        for (String word : post.split("\\s")) {
            if (word.startsWith("@")) {
                word = word.replaceAll("[^a-zA-Z0-9]", "");
                word = "@".concat(word);

                containedMentions.add(word);
            }
        }

        return containedMentions;
    }

    public int findUrlEndIndex(String word) {
        if (word.contains(".com")) {
            int index = word.indexOf(".com");
            index += 4;
            return index;
        } else if (word.contains(".org")) {
            int index = word.indexOf(".org");
            index += 4;
            return index;
        } else if (word.contains(".edu")) {
            int index = word.indexOf(".edu");
            index += 4;
            return index;
        } else if (word.contains(".net")) {
            int index = word.indexOf(".net");
            index += 4;
            return index;
        } else if (word.contains(".mil")) {
            int index = word.indexOf(".mil");
            index += 4;
            return index;
        } else {
            return word.length();
        }
    }

    @Override
    public void countSucceeded(int count) {
        view.setFollowersText("Followers: " + count);
        view.setFollowingText("Following: " + count);
    }


    @Override
    public void IsFollowerSucceeded(boolean isFollower) {
        if (isFollower) {
            view.setFollowerButton();
        } else {
            view.setFollowButton();
        }
    }

    @Override
    public void messageSucceeded(String message) {
        view.showInfoMessage(message);
    }

}
