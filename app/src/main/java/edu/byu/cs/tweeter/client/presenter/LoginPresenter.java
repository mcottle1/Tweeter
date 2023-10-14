package edu.byu.cs.tweeter.client.presenter;


import edu.byu.cs.tweeter.client.model.services.UserService;

public class LoginPresenter extends AuthenticatedPresenter{


    private final View view;

    public LoginPresenter(View view){
        super(view);
        this.view = view;
    }

    public void login(String alias, String password){
        if(validateLogin(alias, password)){
            view.showInfoMessage("Logging In...");
            var userService = new UserService();
            userService.login(alias, password, new AuthenticateObserver(view));
        }
    }

    public boolean validateLogin(String alias, String password) {
        if (alias.length() > 0 && alias.charAt(0) != '@') {
            view.showErrorMessage("Alias must begin with @.");
            return  false;
        }
        if (alias.length() < 2) {
            view.showErrorMessage("Alias must contain 1 or more characters after the @.");
            return false;
        }
        if (password.length() == 0) {
            view.showErrorMessage("Password cannot be empty.");
            return false;
        }
        return true;
    }

}
