package edu.byu.cs.tweeter.client.model.services.observer;


import edu.byu.cs.tweeter.client.presenter.View;

public class IssueMessageObserver implements ServiceObserver{

    private View view;
    private String uniqueMessage;

    public IssueMessageObserver(View view, String uniqueMessage) {
        this.view = view;
        this.uniqueMessage = uniqueMessage;
    }

    @Override
    public void handleFailure(String message) {
        view.showErrorMessage(uniqueMessage + message);
    }


    @Override
    public void handleException(Exception exception) {
        view.showErrorMessage("Failed because of exception" + exception.getMessage());
    }
}
