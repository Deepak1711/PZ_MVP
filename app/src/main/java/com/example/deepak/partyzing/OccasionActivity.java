package com.example.deepak.partyzing;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created on 13/7/16.
 */
public class OccasionActivity extends AppCompatActivity implements VisibilityClickabilityInterface {
    private ViewPager viewPager;
    private ItemAdapter adapter;
    private TextView textView;
    private Animation animFadein, animFadeout;
    private String item;
    private String title[] = {"Birthday", "Anniversary", "Graduate", "Holiday Party", "Baby Shower", "Graduate", "Birthday Surprise", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occasion);
        initViews();
        animFadein = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animFadeout = AnimationUtils.loadAnimation(this, R.anim.fade_out);
    }

    public void initViews() {
        viewPager = (ViewPager) findViewById(R.id.view_Pager);
        adapter = new ItemAdapter(this, this, title);
        viewPager.setAdapter(adapter);
        textView = (TextView) findViewById(R.id.next);
    }

    @Override
    public void showNext() {
        textView.startAnimation(animFadein);
    }

    @Override
    public void hideNext() {
        textView.startAnimation(animFadeout);
    }

    @Override
    public void onItemClicked(String item) {
        this.item = item;
    }
}
