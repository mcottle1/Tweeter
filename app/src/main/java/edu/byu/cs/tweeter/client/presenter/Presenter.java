package edu.byu.cs.tweeter.client.presenter;

public class Presenter {
    protected View view;

    public Presenter(View view) {
        this.view = view;
    }

    public interface View {
        void showErrorMessage(String message);
        void showInfoMessage(String message);
    }

    public View getView() {
        return view;
    }
}
