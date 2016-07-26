package com.example.deepak.partyzing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created on 13/7/16.
 */
public class OccasionActivity extends AppCompatActivity implements VisibilityClickabilityInterface, View.OnClickListener {
    private final String title[] = {"Birthday", "Anniversary", "Graduate", "Holiday Party", "Baby Shower", "Graduate", "Birthday Surprise", "Other"};
    private ViewPager viewPager;
    private ItemAdapter adapter;
    private TextView textViewNext;
    private Animation animFadein, animFadeout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occasion_theme);

        initViews();
    }

    //initializing Views,Listeners and adapters
    public void initViews() {
        viewPager = (ViewPager) findViewById(R.id.view_Pager);
        adapter = new ItemAdapter(this, this, title);
        viewPager.setAdapter(adapter);
        textViewNext = (TextView) findViewById(R.id.next);
        textViewNext.setOnClickListener(this);
    }

    //Initializing animations
    public void initAnims() {
        animFadein = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animFadeout = AnimationUtils.loadAnimation(this, R.anim.fade_out);
    }

    //Show next button when an item is selected
    @Override
    public void showNext() {
        textViewNext.startAnimation(animFadein);
    }

    //Hide next button when no item is selected
    @Override
    public void hideNext() {
        textViewNext.startAnimation(animFadeout);
    }

    @Override
    public void onItemClicked(String item) {
    }//item is unused for now,as it needs to be stored in DB and DB is not integrated yet.

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ThemeActivity.class);
        startActivity(intent);
    }
}
