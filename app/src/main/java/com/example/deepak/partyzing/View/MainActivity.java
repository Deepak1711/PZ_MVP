package com.example.deepak.partyzing.View;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.deepak.partyzing.PagerAdapter;
import com.example.deepak.partyzing.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    PagerAdapter adapter;
    RadioButton radioButtonOne;
    RadioButton radioButtonTwo;
    RadioButton radioButtonThree;
    TextView fbConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    //initializing Views,Listeners and adapters
    public void initViews() {
        fbConnect = (TextView) findViewById(R.id.fb_connect);
        viewPager = (ViewPager) findViewById(R.id.view_Pager);
        radioButtonOne = (RadioButton) findViewById(R.id.radio_one);
        radioButtonTwo = (RadioButton) findViewById(R.id.radio_two);
        radioButtonThree = (RadioButton) findViewById(R.id.radio_three);

        fbConnect.setOnClickListener(this);
        radioButtonOne.setOnClickListener(this);
        radioButtonTwo.setOnClickListener(this);
        radioButtonThree.setOnClickListener(this);

        viewPager.addOnPageChangeListener(this);
        adapter = new PagerAdapter(this);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //Changing selected radio button on slide change
    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                radioButtonOne.toggle();
                break;
            case 1:
                radioButtonTwo.toggle();
                break;
            case 2:
                radioButtonThree.toggle();
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //Initializing call to facebook API for login authentication
    public void callFacebook() {
        FacebookFragment frag = new FacebookFragment();
        FragmentManager fragment = getSupportFragmentManager();
        FragmentTransaction transaction = fragment.beginTransaction();
        transaction.add(R.id.activity_main, frag);
        transaction.commit();
    }

    /**
     * Changing the slides on radio button click
     *
     * @param v contains the View which is clicked
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fb_connect:
                callFacebook();
                break;
            case R.id.radio_one:
                viewPager.setCurrentItem(0);
                break;
            case R.id.radio_two:
                viewPager.setCurrentItem(1);
                break;
            case R.id.radio_three:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
