package com.example.deepak.partyzing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created on 13/7/16.
 */
public class ItemAdapter extends android.support.v4.view.PagerAdapter {
    private Context ctx;
    private VisibilityClickabilityInterface visibilityClickabilityInterface;
    private boolean select = false;
    private View tempFrame;
    private String title[];


    public ItemAdapter(Context ctx, VisibilityClickabilityInterface visibilityClickabilityInterface, String title[]) {
        this.ctx = ctx;
        this.visibilityClickabilityInterface = visibilityClickabilityInterface;
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
    public Object instantiateItem(ViewGroup container, final int position) {
        int tmp = 0;
        View view;
        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.adapter_item, container, false);
        FrameLayout frameLayout1 = (FrameLayout) view.findViewById(R.id.flContainer);
        FrameLayout frameLayout2 = (FrameLayout) view.findViewById(R.id.flContainer1);
        TextView text1 = (TextView) view.findViewById(R.id.text1);
        TextView text2 = (TextView) view.findViewById(R.id.text2);
        tmp = position * 2;
        frameLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v, position * 2);
            }
        });
        frameLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v, (position * 2) + 1);
            }
        });

        frameLayout1.setBackgroundResource(R.drawable.icon_selector);
        frameLayout2.setBackgroundResource(R.drawable.icon_selector);
        text1.setText(title[tmp]);
        text2.setText(title[tmp + 1]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

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
