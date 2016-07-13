package com.example.deepak.partyzing;

import android.content.Context;
import android.support.v4.view.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by deepak on 13/7/16.
 */
public class ItemAdapter extends android.support.v4.view.PagerAdapter implements ViewPager.OnClickListener {
    private Context ctx;
    private LayoutInflater layoutInflater;
    private NextInterface nextInterface;
    int select = 0;
    FrameLayout frameLayout1;
    FrameLayout frameLayout2;
    FrameLayout frameLayout;
    FrameLayout tempFrame;
    TextView text1;
    TextView text2;
    String occasion;
    String title[];
    CheckBox checkBox;
    View view;

    public ItemAdapter(Context ctx, NextInterface nextInterface, String title[]) {
        this.ctx = ctx;
        this.nextInterface = nextInterface;
        this.title = title;
    }

    @Override
    public int getCount() {
        return title.length / 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public float getPageWidth(int position) {
        return 0.45f;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int tmp = 0;
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.adapter_item, container, false);
        initViews();
        tmp = position * 2;
        frameLayout1.setBackgroundResource(R.drawable.icon_selector);
        frameLayout2.setBackgroundResource(R.drawable.icon_selector);
        text1.setText(title[tmp]);
        text2.setText(title[tmp + 1]);
        container.addView(view);
        return view;
    }

    public void initViews() {
        frameLayout1 = (FrameLayout) view.findViewById(R.id.flContainer);
        frameLayout2 = (FrameLayout) view.findViewById(R.id.flContainer1);
        text1 = (TextView) view.findViewById(R.id.text1);
        text2 = (TextView) view.findViewById(R.id.text2);
        frameLayout1.setOnClickListener(this);
        frameLayout2.setOnClickListener(this);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }


    @Override
    public void onClick(View v) {
        if (frameLayout != null) {
            tempFrame = frameLayout;
        }
        frameLayout = (FrameLayout) v.findViewById(v.getId());
        frameLayout.setSelected(!frameLayout.isSelected());
        switch (v.getId()) {
            case R.id.flContainer:
                checkBox = (CheckBox) v.findViewById(R.id.checkbox1);
                text1 = (TextView) v.findViewById(R.id.text1);
                break;
            case R.id.flContainer1:
                checkBox = (CheckBox) v.findViewById(R.id.checkbox2);
                text1 = (TextView) v.findViewById(R.id.text2);
                break;
        }
        checkBox.setChecked(v.isSelected());
        if (frameLayout.isSelected() && select == 0) {
            text1.setTextColor(ctx.getResources().getColor(R.color.white));
            occasion = text1.getText().toString();
            select = 1;
            nextInterface.showNext();
        } else if (frameLayout.isSelected() && select == 1) {
            tempFrame.setSelected(false);
            CheckBox tempCheck = null;
            TextView tempText = null;
            switch (tempFrame.getId()) {
                case R.id.flContainer:
                    tempCheck = (CheckBox) tempFrame.findViewById(R.id.checkbox1);
                    tempText = (TextView) tempFrame.findViewById(R.id.text1);
                    break;
                case R.id.flContainer1:
                    tempText = (TextView) tempFrame.findViewById(R.id.text2);
                    tempCheck = (CheckBox) tempFrame.findViewById(R.id.checkbox2);
                    break;
            }
            tempCheck.setChecked(false);
            tempText.setTextColor(ctx.getResources().getColor(R.color.colorPrimary));
            text1.setTextColor(ctx.getResources().getColor(R.color.white));
            occasion = text1.getText().toString();
        } else {
            text1.setTextColor(ctx.getResources().getColor(R.color.colorPrimary));
            select = 0;
            occasion = null;
            nextInterface.hideNext();
        }
    }
}
