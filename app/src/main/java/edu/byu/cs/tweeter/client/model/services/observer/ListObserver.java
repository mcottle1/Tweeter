package edu.byu.cs.tweeter.client.model.services.observer;

import java.util.List;

public interface ListObserver<T> extends ServiceObserver {
    void getListSucceeded(List<T> list, boolean hasMorePages);
}
