package com.example.deepak.partyzing.Presenter;

import com.example.deepak.partyzing.View.NextInterface;


/**
 * Created on 28/7/16.
 */
public class SplashPresenter {
    final int WAIT_TIME = 2000;
    private NextInterface nextInterface;

    public SplashPresenter(NextInterface nextInterface) {

        this.nextInterface = nextInterface;
    }

    public void holdScreen(final NextInterface nextInterface) {
        //To hold the splash screen for two seconds
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(WAIT_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    nextInterface.callActivity();
                }
            }
        };
        timerThread.start();
    }
}
