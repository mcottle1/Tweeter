package edu.byu.cs.tweeter.client.model.services.observer;

import edu.byu.cs.tweeter.client.presenter.Presenter;


public class IssueMessageObserver implements ServiceObserver{

    private Presenter.View view;
    private String uniqueMessage;

    public IssueMessageObserver(Presenter.View view, String uniqueMessage) {
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
