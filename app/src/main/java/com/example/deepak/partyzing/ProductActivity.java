package com.example.deepak.partyzing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created on 13/7/16.
 */
public class ProductActivity extends AppCompatActivity implements ShowHideInterface, OnClickInterface, View.OnClickListener {
    private ViewPager viewPager;
    private ProductAdapter adapter;
    private TextView textViewNext;
    private TextView textViewPrice;
    private Animation animFadein, animFadeout;
    private final String title[] = {"Product 1", "Product 2", "Product 3", "Product 4", "Product 5", "Product 6", "Product 7", "Product 8"};
    private final int price[] = {1200, 200, 400, 600, 350, 1500, 1300, 900};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        initViews();
        initAnimations();
    }

    //initializing Views,Listeners and adapters
    public void initViews() {
        viewPager = (ViewPager) findViewById(R.id.view_Pager);
        adapter = new ProductAdapter(this, this, this, title);
        viewPager.setAdapter(adapter);
        textViewNext = (TextView) findViewById(R.id.next);
        textViewPrice = (TextView) findViewById(R.id.price);
        textViewNext.setOnClickListener(this);
    }

    //Initializing animations
    public void initAnimations() {
        animFadein = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animFadeout = AnimationUtils.loadAnimation(this, R.anim.fade_out);
    }

    //Show next button when an item is selected
    @Override
    public void showNext() {
        textViewNext.startAnimation(animFadein);
        textViewPrice.startAnimation(animFadein);
    }

    //Hide next button when no item is selected
    @Override
    public void hideNext() {
        textViewNext.startAnimation(animFadeout);
        textViewPrice.startAnimation(animFadeout);
    }

    //updates the total amount for the selected products
    @Override
    public void onItemClick() {
        ArrayList<Integer> selectedPositions = adapter.getSelectedPositions();
        int totalAmount = 0;
        for (int position : selectedPositions) {
            totalAmount = totalAmount + price[position];
        }
        textViewPrice.setText(totalAmount + "/-");
    }

    @Override
    public void onClick(View v) {
        //start the next activity
    }
}
