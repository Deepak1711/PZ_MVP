package com.example.deepak.partyzing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    PagerAdapter adapter;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        textView.setOnClickListener(this);
        radioButton1.setOnClickListener(this);
        radioButton2.setOnClickListener(this);
        radioButton3.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);

        adapter = new PagerAdapter(this);
        viewPager.setAdapter(adapter);
        int position = viewPager.getCurrentItem();
    }

    public void initViews() {
        textView = (TextView) findViewById(R.id.text);
        viewPager = (ViewPager) findViewById(R.id.view_Pager);
        radioButton1 = (RadioButton) findViewById(R.id.radio1);
        radioButton2 = (RadioButton) findViewById(R.id.radio2);
        radioButton3 = (RadioButton) findViewById(R.id.radio3);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            radioButton1.toggle();
        } else if (position == 1) {
            radioButton2.toggle();
        } else if (position == 2) {
            radioButton3.toggle();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text:
                FacebookFragment frag = new FacebookFragment();
                FragmentManager fragment = getSupportFragmentManager();
                FragmentTransaction transaction = fragment.beginTransaction();
                transaction.add(R.id.activity_main, frag);
                transaction.commit();
                break;
            case R.id.radio1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.radio2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.radio3:
                viewPager.setCurrentItem(2);
                break;
        }
    }

}
