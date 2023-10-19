package edu.byu.cs.tweeter.client.model.services.handler;

import android.os.Bundle;

import java.util.List;

import edu.byu.cs.tweeter.client.model.services.backgroundTask.handler.BackgroundTaskHandler;
import edu.byu.cs.tweeter.client.presenter.PagedPresenter;

public class ListHandler<T> extends BackgroundTaskHandler<PagedPresenter.ListObserver> {
    public static final String ITEMS_KEY = "items";
    public static final String MORE_PAGES_KEY = "more-pages";

    public ListHandler(PagedPresenter.ListObserver observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(PagedPresenter.ListObserver observer, Bundle data) {
        observer.getListSucceeded((List<T>)data.getSerializable(ITEMS_KEY), data.getBoolean(MORE_PAGES_KEY));
    }
}
