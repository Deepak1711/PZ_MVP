package com.example.deepak.partyzing.Presenter;

import com.example.deepak.partyzing.View.NextInterface;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static java.lang.Thread.sleep;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created on 28/7/16.
 */
public class SplashFBPresenterTest {
    private final int WAIT_TIME = 2000;
    SplashPresenter splashPresenter;

    @Mock
    NextInterface nextInterface;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        splashPresenter = new SplashPresenter(nextInterface);

    }

    @Test
    public void holdScreenForSomeTimeAndCallActivity() {
        splashPresenter.holdScreen(nextInterface);
        try {
            sleep(WAIT_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        verify(nextInterface).callActivity();
    }

    @Test
    public void NoHoldScreenThenCallActivityFail() {
        splashPresenter.holdScreen(nextInterface);
        verify(nextInterface,never()).callActivity();
    }
}
