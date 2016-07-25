package com.example.deepak.partyzing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created on 29/6/16.
 */
public class SplashActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        final int WAIT_TIME = 2000;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //To hold the splash screen for two seconds
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(WAIT_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
