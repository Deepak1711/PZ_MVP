package com.example.deepak.partyzing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created on 13/7/16.
 */
public class ItemAdapter extends android.support.v4.view.PagerAdapter {
    private final Context ctx;
    private final VisibilityClickabilityInterface visibilityClickabilityInterface;
    private final String title[];
    private final float PAGE_WIDTH = 0.45f;
    private boolean select = false;
    private View tempFrame;


    public ItemAdapter(Context ctx, VisibilityClickabilityInterface visibilityClickabilityInterface, String title[]) {
        this.ctx = ctx;
        this.visibilityClickabilityInterface = visibilityClickabilityInterface;
        this.title = title;
    }

    /*As we are displaying two items per page,therefore length is divided by 2
    and returned value is rounded to upper bound for the cases of odd length*/
    @Override
    public int getCount() {
        return (int) Math.ceil(((double) title.length) / 2);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public float getPageWidth(int position) {
        return PAGE_WIDTH;
    }

    //Here we are displaying two items per page,hence a temporary variable is used to handle the position
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        int tmp = 0;
        View view;
        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.adapter_item, container, false);
        tmp = position * 2;

        if (tmp<title.length) {
            FrameLayout frameLayout1 = (FrameLayout) view.findViewById(R.id.flContainer);
            frameLayout1.setBackgroundResource(R.drawable.icon_selector);
            TextView text1 = (TextView) view.findViewById(R.id.text1);
            text1.setText(title[tmp]);
            frameLayout1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(v, position * 2);
                }
            });
            if (tmp + 1 <title.length) {
                FrameLayout frameLayout2 = (FrameLayout) view.findViewById(R.id.flContainer1);
                TextView text2 = (TextView) view.findViewById(R.id.text2);
                frameLayout2.setBackgroundResource(R.drawable.icon_selector);
                text2.setText(title[tmp + 1]);
                frameLayout2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClick(v, (position * 2) + 1);
                    }
                });
            }
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    //Changing the presentation of the item when the item is selected or unselected
    public void onItemClick(View v, int position) {
        v.setSelected(!v.isSelected());
        if (v.isSelected() && select == true) {
            tempFrame.setSelected(false);
        } else if (!v.isSelected()) {
            select = false;
            visibilityClickabilityInterface.hideNext();
        } else {
            select = true;
            visibilityClickabilityInterface.showNext();
        }
        tempFrame = v;
        visibilityClickabilityInterface.onItemClicked(title[position]);
    }
}
