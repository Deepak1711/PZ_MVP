package com.example.deepak.partyzing;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created on 25/7/16.
 */
public class ThemeActivity extends AppCompatActivity implements VisibilityClickabilityInterface {
    private final String title[] = {"Theme 1", "Theme 2", "Theme 3", "Theme 4", "Theme 5", "Theme 6", "Theme 7", "Not decided"};
    private ViewPager viewPager;
    private ItemAdapter adapter;
    private TextView textViewNext;
    private Animation animFadein, animFadeout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occasion_theme);
        TextView headText = (TextView) findViewById(R.id.text);
        headText.setText(R.string.partyTheme);
        initViews();

        animFadein = AnimationUtils.loadAnimation(this, R.anim.fade_in);       //Initializing animations
        animFadeout = AnimationUtils.loadAnimation(this, R.anim.fade_out);
    }

    //initializing Views,Listeners and adapters
    public void initViews() {
        viewPager = (ViewPager) findViewById(R.id.view_Pager);
        adapter = new ItemAdapter(this, this, title);
        viewPager.setAdapter(adapter);
        textViewNext = (TextView) findViewById(R.id.next);
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
}

