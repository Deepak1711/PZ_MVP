package com.example.deepak.partyzing.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.deepak.partyzing.Presenter.SplashPresenter;
import com.example.deepak.partyzing.R;

/**
 * Created on 29/6/16.
 */
public class SplashActivity extends Activity implements NextInterface {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new SplashPresenter(this).holdScreen(this);
    }

    @Override
    public void callActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
