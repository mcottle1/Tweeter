package edu.byu.cs.tweeter.client.model.services;


import edu.byu.cs.tweeter.client.model.services.backgroundTask.GetUserTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.LoginTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.LogoutTask;
import edu.byu.cs.tweeter.client.model.services.backgroundTask.RegisterTask;
import edu.byu.cs.tweeter.client.model.services.handler.BasicHandler;
import edu.byu.cs.tweeter.client.model.services.handler.GetUserHandler;
import edu.byu.cs.tweeter.client.model.services.handler.LoginHandler;
import edu.byu.cs.tweeter.client.model.services.observer.BasicObserver;
import edu.byu.cs.tweeter.client.presenter.AuthenticatedPresenter;
import edu.byu.cs.tweeter.client.presenter.PagedPresenter;
import edu.byu.cs.tweeter.model.domain.AuthToken;

public class UserService {

    public void login(String alias, String password, AuthenticatedPresenter.AuthenticateObserver observer){
        LoginTask loginTask = new LoginTask(alias, password, new LoginHandler(observer));
        loginTask.run();
    }

    public void getUser(AuthToken authToken, String alias, PagedPresenter.GetUserObserver observer){
        GetUserTask getUserTask = new GetUserTask(authToken, alias, new GetUserHandler(observer));
        getUserTask.run();
    }

    public void Register(String firstName, String lastName, String alias, String password, String imageBytesBase64, AuthenticatedPresenter.AuthenticateObserver observer){
        RegisterTask registerTask = new RegisterTask(firstName, lastName,
                alias, password, imageBytesBase64, new LoginHandler(observer));
        registerTask.run();
    }

    public void Logout(AuthToken authToken, BasicObserver observer){
        LogoutTask logoutTask = new LogoutTask(authToken, new BasicHandler(observer));
        logoutTask.run();
    }

}
