package edu.byu.cs.tweeter.client.presenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import edu.byu.cs.tweeter.client.model.services.FeedService;
import edu.byu.cs.tweeter.client.model.services.observer.MessageObserver;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class MainPresenterUnitTests {

    private MainPresenter.View mockMainView;
    private FeedService mockFeedService;
    private MainPresenter mainPresenterSpy;

    private AuthToken authToken = new AuthToken("123456789");
    private User currUser = new User("John", "Doe", "URL");
    private String post = "Pizza";
    private Status status = new Status(post, currUser, System.currentTimeMillis(), new ArrayList<>(), new ArrayList<>());
    private Status createdStatus;

    @BeforeEach
    public void setup(){
        mockMainView = Mockito.mock(MainPresenter.View.class);
        mockFeedService = Mockito.mock(FeedService.class);
        mainPresenterSpy = Mockito.spy( new MainPresenter(mockMainView));
        Mockito.when(mainPresenterSpy.feedFactory()).thenReturn(mockFeedService);
    }

    @Test
    public void testPost_postSucceeds(){
        Answer<Void> answer = new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                MessageObserver observer = testParametersPassedByPresenter(invocation);
                observer.messageSucceeded("Post Succeeded!");
                return null;
            }
        };
        setUpAndCallAndVerify(answer);
        Mockito.verify(mockMainView).showInfoMessage("Post Succeeded!");
    }

    @Test
    public void testPost_postFailsWithErrorMessage(){
        Answer<Void> answer = new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                MessageObserver observer = testParametersPassedByPresenter(invocation);
                observer.handleFailure("the error message");
                return null;
            }
        };
        setUpAndCallAndVerify(answer);
        Mockito.verify(mockMainView).showErrorMessage("Failed with message:the error message");
    }

    @Test public void testPost_postFailsWithException(){
        Answer<Void> answer = new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                MessageObserver observer = testParametersPassedByPresenter(invocation);
                observer.handleException(new Exception("the exception message"));
                return null;
            }
        };
        setUpAndCallAndVerify(answer);
        Mockito.verify(mockMainView).showErrorMessage("Failed because of exception" + "the exception message");
    }

    public void setUpAndCallAndVerify(Answer<Void> answer){
        Mockito.doAnswer(answer).when(mockFeedService).post(Mockito.any(), Mockito.any(), Mockito.any());
        mainPresenterSpy.post(authToken, currUser, post);
        Mockito.verify(mainPresenterSpy).statusFactory(post, currUser);
        Mockito.verify(mockMainView).showInfoMessage("Posting Status...");
        Mockito.verify(mainPresenterSpy).feedFactory();
    }

    public MessageObserver testParametersPassedByPresenter(InvocationOnMock invocation){
        AuthToken token = invocation.getArgument(0);
        assertTrue(token instanceof AuthToken);
        assertEquals(authToken, token);
        Status pulledStatus = invocation.getArgument(1);
        assertTrue(pulledStatus instanceof Status);
        assertEquals(status.post, pulledStatus.post);
        assertEquals(status.user, pulledStatus.user);
        assertEquals(status.urls, pulledStatus.urls);
        assertEquals(status.mentions, pulledStatus.mentions);
        MessageObserver observer = invocation.getArgument(2);
        assertTrue(observer instanceof MessageObserver);
        return observer;
    }
}
